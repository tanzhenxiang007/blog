package com.blog.玩玩.测试;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileIndexer {

    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
            "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})" +     // 时间戳
                    "( ERROR| WARN|INFO)" +                              // 日志级别
                    "(\\[easitline-jdbc:easitline-jdbc-error\\])" +    // 模块信息
                    "(.*)" +                                            // 错误信息和堆栈跟踪
                    "(?=(\\r?\\n\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}|$))" // 确保是独立的日志条目
    );
    private static final String ELASTICSEARCH_HOST = "http://172.16.91.43";
    private static final int ELASTICSEARCH_PORT = 9200;
    private static final String INDEX_NAME = "cx_mix_tyy_log";
    private static final int THREAD_POOL_SIZE = 10; // 可以根据你的硬件配置调整线程池大小

    public static void main(String[] args) {
        String absolutePath = "D:\\testlog"; // 日志文件的绝对路径
        String filePrefix = "easitline";            // 日志文件的前缀
        try {
            RestHighLevelClient client = createElasticsearchClient();
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<File> logFiles = findLogFiles(absolutePath, filePrefix);

            for (File logFile : logFiles) {
                executorService.submit(() -> {
                    try {
                        indexLogEntries(client, logFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            executorService.shutdown();
            // 等待所有任务完成
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static RestHighLevelClient createElasticsearchClient() throws IOException, URISyntaxException {
        // 创建一个HttpHost
        HttpHost httpHost = new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT);

        // 使用RestClient.builder()方法，传入一个HttpHost对象列表
        return new RestHighLevelClient(
                RestClient.builder(httpHost)
        );
    }


    private static List<File> findLogFiles(String directoryPath, String prefix) throws IOException {
        List<File> logFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.startsWith(prefix));

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    logFiles.add(file);
                }
            }
        }
        return logFiles;
    }

    private static void indexLogEntries(RestHighLevelClient client, File logFile) throws IOException {
        String logContent = new String(Files.readAllBytes(logFile.toPath()));
        List<String> logEntries = parseLogEntries(logContent);

        for (String logEntry : logEntries) {
            LogEntry logEntryData = parseLogEntry(logEntry);
            IndexRequest indexRequest = new IndexRequest(INDEX_NAME, logEntryData.getId())
                    .source(logEntryData.getSource(), XContentType.JSON);
            try {
                IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
                System.out.println("Indexed with id: " + response.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // parseLogEntries 和 parseLogEntry 方法与之前的示例相同，不再重复
    public static List<String> parseLogEntries(String logContent) {
        Matcher matcher = LOG_ENTRY_PATTERN.matcher(logContent);
        List<String> logEntries = new ArrayList<>();

        while (matcher.find()) {
            // 将匹配到的每个日志条目按照指定格式输出
            String logEntry = String.format(
                    "time: %s, logType: %s, module: %s, info: %s",
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(3).replace("]", "").replace("[", ""),
                    matcher.group(4).trim()
            );
            logEntries.add(logEntry);
        }
        return logEntries;
    }
    private static LogEntry parseLogEntry(String logEntry) {
        // 解析日志条目的具体逻辑，这里需要根据你的日志格式来编写
        // 例如，提取时间、日志类型、模块和错误信息等
        // 这里以时间戳作为文档ID，实际使用中可以根据需要生成或使用其他唯一标识符
        String timestamp = extractTimestamp(logEntry);
        // 构造要索引的数据源
        String source = "{"
                + "\"time\":\"" + timestamp + "\","
                + "\"logType\":\"ERROR\","
                + "\"module\":\"easitline-jdbc\","
                + "\"info\":\"" + extractErrorMessage(logEntry) + "\""
                + "}";
        return new LogEntry(timestamp, source);
    }
    private static String extractTimestamp(String logEntry) {
        // 从日志条目中提取时间戳
        // 假设时间戳格式为 yyyy-MM-dd HH:mm:ss
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String extractErrorMessage(String logEntry) {
        // 从日志条目中提取错误信息
        // 这里需要根据你的日志格式来编写提取逻辑
        // 示例：提取从 'ERROR\t' 到行尾的部分
        int startIndex = logEntry.indexOf("ERROR\t") + "ERROR\t".length();
        return logEntry.substring(startIndex).trim();
    }


}    // 用于存储日志数据的简单类
class LogEntry {
    private final String id;
    private final String source;

    LogEntry(String id, String source) {
        this.id = id;
        this.source = source;
    }

    String getId() {
        return id;
    }

    String getSource() {
        return source;
    }
}