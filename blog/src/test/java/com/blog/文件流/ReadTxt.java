package com.blog.文件流;

import org.junit.platform.commons.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadTxt {
    /**
     * 传入txt路径读取txt文件
     *
     * @param txtPath
     * @return 返回读取到的内容
     */
    public static String readTxt(String txtPath) {
        File file = new File(txtPath);
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 使用FileOutputStream来写入txt文件
     *
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath, String content) {
//        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if (file.exists()) {
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }

            if (StringUtils.isNotBlank(content)) {
                //这种创建方式，当写入内容时，会覆盖原先的内容
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
                //这种创建方式，当写入内容时，是追加到文件内容的后面，不会覆盖原先的内容
                FileOutputStream fileOutputStream = new FileOutputStream(file,true);
               //键盘输入
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                //内容写入文件
                fileOutputStream.write((content+s).getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //验证方法：先写入文件后读取打印如下：
    public static void main(String[] args) {
        writeTxt("D:\\bishe\\blog-master\\blog\\src\\test\\java\\com\\blog\\文件流\\readtxt.txt", "努力学习");
        String str = readTxt("D:\\bishe\\blog-master\\blog\\src\\test\\java\\com\\blog\\文件流\\readtxt.txt");
        System.out.println(str);
    }

}
