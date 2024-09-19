package com.blog.玩玩.io.文件压缩;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BatchCompressFiles {

    public static void main(String[] args) {
        // 使用List<String>来存储文件的绝对路径
        List<String> filesToCompress = new ArrayList<>();
        filesToCompress.add("D:\\ce\\123\\c-副本.txt");
        filesToCompress.add("D:\\ce\\123\\c.txt");

        // 指定输出ZIP文件的前缀
        String outputZipPrefix = "D:\\ce\\压缩\\abc";

        try {
            // 调用compressFiles方法进行分批次压缩   此处100条为一批次
            compressFiles(filesToCompress, outputZipPrefix, 100);
            System.out.println("所有批次的压缩完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compressFiles(List<String> filesToCompress, String outputZipPrefix, int batchSize) throws IOException {
        // 将List分组为每batchSize个元素的小列表
        List<List<String>> batches = new ArrayList<>();
        for (int i = 0; i < filesToCompress.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, filesToCompress.size());
            List<String> batch = filesToCompress.subList(i, endIndex);
            batches.add(batch);
        }

        // 遍历批次并压缩
        batches.forEach(batch -> {
            try {
                // 构造当前批次的ZIP文件名
                String zipFileName = outputZipPrefix + "_" + UUID.randomUUID() + ".zip";
                Path path = Paths.get(zipFileName);
                Path dirPath = path.getParent();
                if (Files.notExists(dirPath)) {
                    Files.createDirectories(dirPath); // 创建所有必需的父目录
                }
                // 压缩当前批次的文件
                compressZipFile(batch, zipFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void compressZipFile(List<String> files, String zipFileName) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            files.forEach(filePath -> {
                try {
                    compressFile(filePath, zipOut);
                } catch (IOException e) {
                    System.err.println("压缩文件时出错: " + filePath);
                }
            });
        }
    }

    private static void compressFile(String filePath, ZipOutputStream zipOut) throws IOException {
        File fileToZip = new File(filePath);
        if (!fileToZip.exists()) {
            System.err.println("文件不存在，跳过压缩: " + filePath);
            return;
        }
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileToZip))) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer)) != -1) {
                zipOut.write(buffer, 0, read);
            }
        }
        zipOut.closeEntry();
    }
}