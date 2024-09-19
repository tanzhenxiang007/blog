package com.blog.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ceshi {
    public static void main(String[] args) {
        // 指定文件名
        String fileName = "a.sql";

        // 获取当前工作目录的文件路径
        File file = new File("src/main/java/com/blog/script/" + fileName);

        // 检查文件是否存在
        if (file.exists() && !file.isDirectory()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 打印文件的每一行
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }
}
