package com.blog.玩玩.测试;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileAnalyzer1 {

    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
            "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})" + // 时间戳
                    "\\t(\\w+)" +                                    // 日志类型
                    "\\t\\[([^\\]]+)\\]" +                            // 模块
                    "\\t(.*)"                                         // 信息
    );
    private static String tenTime;
    private static String lastTime;

    public static void main(String[] args) {
        String logDirectoryPath = "D:\\testlog"; // 日志文件所在的目录路径
        String filePrefix = "alarm"; // 日志文件的前缀
        int numberOfThreads = 5; // 线程池中的线程数量
        System.out.println("开始分析日志文件..."+ getLocalTime());
        tenTime = getLocalDateTime();
        lastTime="2024-07-17 19:30:00";
        File directory = new File(logDirectoryPath);
        FilenameFilter filter = (dir, name) -> {
            if (filePrefix.equals("*")) {
                return true; // 如果FILE_PREFIX为*，则不过滤
            }
            String[] prefixes = filePrefix.split(";");
            // 检查文件名是否符合任一 前缀
            return Arrays.stream(prefixes).anyMatch(prefix -> name.startsWith(prefix));
        };
        File[] files = directory.listFiles(filter);
        System.out.println("找到 " + files.length + " 个日志文件");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        JSONArray jsonArray = new JSONArray(); // 用于存储所有线程的结果

        for (File file : files) {
            try {
                Path path = Paths.get(file.getAbsolutePath());
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                java.nio.file.attribute.FileTime lastModifiedTime = attr.lastModifiedTime();
                Instant instant = lastModifiedTime.toInstant(); // 转换为Instant
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // 转换为LocalDateTime
                String formattedDateTime = dateTime.format(formatter); // 格式化日期时间
                System.out.println("文件 " + file.getName() + " 的最后修改时间是: " + formattedDateTime);
                if (formattedDateTime.compareTo(lastTime) < 0){
                    System.out.println("文件 " + file.getName() + " 的最后修改时间小于"+lastTime+",跳过");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("无法获取文件 " + file.getName() + " 的最后修改时间");
            }

            executorService.submit(() -> {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    String currentTime = null;
                    String currentModule = null;
                    String fileName = file.getName();
                    StringBuilder currentInfo = new StringBuilder(); // 用于累积日志信息
                    boolean isCollectingErrorInfo = false;

                    while ((line = reader.readLine()) != null) {
                        if (line.isEmpty() || line.startsWith("\t")) {
                            // 如果是空行或堆栈跟踪行，追加到当前累积的信息
                            if (isCollectingErrorInfo) {
                                currentInfo.append(line);
                            }
                        } else {
                            Matcher matcher = LOG_ENTRY_PATTERN.matcher(line);
                            if (matcher.matches()) {
                                String time = matcher.group(1);
                                String logType = matcher.group(2);
                                String module = matcher.group(3);
                                String info = matcher.group(4).trim();
//                                if (StringUtils.isNotEmpty(dateTime) &&!isTime1Later(time,dateTime)) continue;
                                if (currentTime != null ) {
                                    if (StringUtils.isNotEmpty(tenTime) &&!isTimeInRange(time,lastTime,tenTime)){                                        continue;
                                    }
                                    // 遇到新的时间戳，处理之前累积的错误日志
                                    if (isCollectingErrorInfo) {
                                        processLogEntry(fileName, currentTime, "ERROR", currentModule, currentInfo.toString(), jsonArray);
                                        isCollectingErrorInfo = false;
                                    }
                                }

                                if ("ERROR".equals(logType)) {
                                    if (StringUtils.isNotEmpty(tenTime) &&!isTimeInRange(time,lastTime,tenTime)){                                        continue;
                                    }
                                    // 开始累积错误日志的详细信息
                                    isCollectingErrorInfo = true;
                                    currentModule = module;
                                    currentInfo = new StringBuilder().append(info);
                                } else {
                                    if (StringUtils.isNotEmpty(tenTime) &&!isTimeInRange(time,lastTime,tenTime)){                                        continue;
                                    }
                                    // 对于非错误日志，正常处理
                                    processLogEntry(fileName, time, logType, module, info, jsonArray);
                                }

                                currentTime = time; // 更新当前时间戳
                            }
                        }
                    }

                    // 处理最后一个日志条目
                    if (isCollectingErrorInfo) {
                        if (StringUtils.isNotEmpty(tenTime) &&!isTimeInRange(currentTime,lastTime,tenTime)){                            return;
                        }
                        processLogEntry(fileName, currentTime, "ERROR", currentModule, currentInfo.toString(), jsonArray);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }

        // 等待所有任务完成
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1800, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (Exception e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("任务结束时间"+getLocalTime());
    }

    private static void processLogEntry(String fileName, String time, String logType, String module, String info, JSONArray jsonArray) {
        String id = UUID.randomUUID().toString(); // 生成UUID作为id
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("fileName", fileName);
        jsonObject.put("time", time);
        jsonObject.put("recordTime", time);
        jsonObject.put("logType", logType);
        jsonObject.put("moduleName", module.split(":")[0]);
        jsonObject.put("info", info.replace("\t", ","));
        jsonArray.add(jsonObject);
//        System.out.println("1");
    }


    public static String getLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenSecondsAgo = now.minusSeconds(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = tenSecondsAgo.format(formatter);
        System.out.println(formattedTime);
        return formattedTime;
    }

    public static String getLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);
        return formattedTime;
    }

    public static boolean isTime1Later(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);
            return dateTime1.isAfter(dateTime2);
        } catch (DateTimeParseException e) {
            // 如果时间格式不正确，打印错误并返回 false
            System.err.println("One of the provided times is not in the expected format 'yyyy-MM-dd HH:mm:ss'");
            return false;
        }
    }


    public static boolean isTimeInRange(String time, String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime timeDateTime = LocalDateTime.parse(time, formatter);
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);

            return !timeDateTime.isBefore(startDateTime) && timeDateTime.isEqual(endDateTime) || timeDateTime.isAfter(startDateTime);
        } catch (DateTimeParseException e) {
            System.err.println("One of the provided times is not in the expected format 'yyyy-MM-dd HH:mm:ss'");
            return false;
        }
    }
}