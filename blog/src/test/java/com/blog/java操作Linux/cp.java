package com.blog.java操作Linux;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class cp {
    public  static void copyFolder(File fileold , File filenew) {
        if(fileold.isDirectory()){
//            File temp=new File(filenew,fileold.getName());
//            temp.mkdirs();
//            for (File file : fileold.listFiles()) {
//                copyFolder(file,temp);
//            }
        }else{
            copyFile(fileold,new File(filenew,fileold.getName()));
        }
    }
    public static void copyFile(File strOldpath, File strNewPath) {
        InputStream inputStream =null;
        FileOutputStream fileOutputStream =null;
        try {
            if (strOldpath.exists()) {
                int bytesum = 0;
                int byteread = 0;
                inputStream = new FileInputStream(strOldpath);
                fileOutputStream = new FileOutputStream(strNewPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inputStream.read(buffer)) != -1) {
                    bytesum += byteread; //这一行是记录文件大小的，可以删去
                    fileOutputStream.write(buffer, 0, byteread);
                    //三个参数，第一个参数是写的内容，
                    //第二个参数是从什么地方开始写，第三个参数是需要写的大小
                }
                // inputStream.close();
                // fileOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("复制单个文件出错");
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
            {
                try{
                    inputStream.close();
                }catch (IOException e2)
                {
                    e2.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
    public static void main(String args[]) throws IOException {
//        Scanner input=new Scanner(System.in);
//        System.out.println("请输入旧的路径:");
//        String oldpath = input.next();
//        File  f1 = new File(oldpath);
//        System.out.println("请输入新的路径");
//        String newpath = input.next();
//        File f2 = new File(newpath);
//        copyFolder(f1,f2);


        File  f1 = new File("D:\\ce");
        File  f2 = new File("D:\\ce1");
        copyFileUsingApacheCommonsIO(f1,f2);
    }

    /**
     * 得到文件名称
     *
     * @param path 路径
     * @return {@link List}<{@link String}>
     */
    public List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(file, fileNames);
    }

    /**
     * 得到文件名称
     *
     * @param file      文件
     * @param fileNames 文件名
     * @return {@link List}<{@link String}>
     */
    public List<String> getFileNames(File file, List<String> fileNames) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileNames(f, fileNames);
            } else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }

    private static void copyFileUsingApacheCommonsIO(File source, File dest)

            throws IOException {

        FileUtil.copyFile(source, dest);

    }


}