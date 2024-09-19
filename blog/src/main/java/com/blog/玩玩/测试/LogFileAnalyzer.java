package com.blog.玩玩.测试;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileAnalyzer {
    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile(
            "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})" + // 时间戳
                    "\\t(\\w+)" + // 日志类型
                    "\\t\\[([^\\]]+)\\]" + // 模块
                    "\\t(.*)" // 信息
    );

    public static void main(String[] args) {
        String logFilePath = "D:\\testlog\\cos-gw-telx.log"; // 替换为日志文件的绝对路径
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(logFilePath));
            String line;
            String currentTime = null;
            String currentModule = null;
            StringBuilder currentInfo = new StringBuilder(); // 用于累积日志信息
            JSONArray jsonArray = new JSONArray();
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

                        if (currentTime != null && !time.equals(currentTime)) {
                            // 遇到新的时间戳，处理之前累积的错误日志
                            if (isCollectingErrorInfo) {
                                processLogEntry(currentTime, "ERROR", currentModule, currentInfo.toString(),jsonArray);
                                isCollectingErrorInfo = false;
                            }
                        }

                        if ("ERROR".equals(logType)) {
                            // 开始累积错误日志的详细信息
                            isCollectingErrorInfo = true;
                            currentModule = module;
                            currentInfo = new StringBuilder().append(info);
                        } else {
                            // 对于非错误日志，正常处理
                            processLogEntry(time, logType, module, info,jsonArray);
                        }

                        currentTime = time; // 更新当前时间戳
                    }
                }
            }

            // 处理文件中最后一个日志条目，如果是错误类型
            if (isCollectingErrorInfo) {
                processLogEntry(currentTime, "ERROR", currentModule, currentInfo.toString(), jsonArray);
            }
            System.out.println(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processLogEntry(String time, String logType, String module, String info, JSONArray jsonArray) {
        String id = UUID.randomUUID().toString(); // 生成UUID作为id
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("time", time);
        jsonObject.put("logType", logType);
        jsonObject.put("module", module);
        jsonObject.put("info", info.replace("\t",","));
        jsonArray.add(jsonObject);
    }
}