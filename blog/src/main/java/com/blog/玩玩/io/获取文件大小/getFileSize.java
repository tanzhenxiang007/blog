package com.blog.玩玩.io.获取文件大小;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class getFileSize {
    public static void main(String[] args) {
        getFileSize3(new File("D:\\app.log"));
    }


    /**
     * 根据java.nio.*的流获取文件大小
     * @param file
     */
    public static void getFileSize3(File file){
        FileChannel fc = null;
        try {
            if(file.exists() && file.isFile()){
                String fileName = file.getName();
                FileInputStream fis = new FileInputStream(file);
                fc = fis.getChannel();
                long size = fc.size()/1024;
                System.out.println("文件"+fileName+"的大小是："+size+"mb");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null!=fc){
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
