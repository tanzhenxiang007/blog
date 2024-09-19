package com.blog.玩玩.设计模式.外观模式;
//FileReader.cs

import java.io.*;


class FileReader
{
    public String Read(String fileNameSrc)
    {
        System.out.println("读取文件，获取明文：");
        FileInputStream fs = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            fs = new FileInputStream(fileNameSrc);
            int data;
            while((data = fs.read())!= -1)
            {
                sb = sb.append((char)data);
            }
            fs.close();
            System.out.println(sb.toString());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("文件不存在！");
        }
        catch(IOException e)
        {
            System.out.println("文件操作错误！");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String read = new FileReader().Read("C:\\Users\\tzx\\Desktop\\jar\\test2.txt");
        System.out.println(read);

    }
}
