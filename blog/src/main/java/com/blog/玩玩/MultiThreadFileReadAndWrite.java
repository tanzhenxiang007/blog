
package com.blog.玩玩;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程读写文件示例类。
 * 该类演示了如何使用读写锁（ReadWriteLock）来控制对文件的并发读写操作。
 */
public class MultiThreadFileReadAndWrite {
    /**
     * 使用读写锁来控制文件的并发访问。
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 并发读取指定文件夹中的文件，并将每个文件的内容写入新文件。
     *
     * @param folderPath 文件夹路径，用于读取文件。
     * @param newFilePath 新文件的路径前缀，每个文件将在这个路径前缀后添加原文件名。
     * @throws IOException 如果读写文件发生错误。
     */
    public void readAndWrite(String folderPath, String newFilePath) throws IOException {
        // 获取读锁，允许多个线程同时读取文件
        lock.readLock().lock();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            // 列出文件夹中的所有文件，并为每个文件提交一个任务
            Path folder = Paths.get(folderPath);
            Files.list(folder).forEach(file -> {
                String fileName = file.getFileName().toString();
                String filePath = folder.resolve(fileName).toString();
                String newFile = newFilePath + fileName;

                // 使用写锁，确保同一时间只有一个线程写入文件
                executorService.submit(() -> {
                    try {
                        lock.writeLock().lock();
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        FileWriter writer = new FileWriter(newFile);
                        String line;
                        while ((line = reader.readLine()) != null) {
                            writer.write(line + "|");
                        }
                        reader.close();
                        writer.close();
                        System.out.println("线程 " + Thread.currentThread().getName() + " 读取文件 " + filePath + " 并写入文件 " + newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        lock.writeLock().unlock();
                    }
                });
            });
        } finally {
            // 释放读锁
            lock.readLock().unlock();
            // 关闭执行服务
            executorService.shutdown();
        }
    }

    /**
     * 程序入口点。
     *
     * @param args 命令行参数。
     */
    public static void main(String[] args) {
        try {
            MultiThreadFileReadAndWrite M = new MultiThreadFileReadAndWrite();
            M.readAndWrite("D:\\ce\\456","D:\\ce1\\L");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}