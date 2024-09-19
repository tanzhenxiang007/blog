package com.blog.玩玩.设计模式.外观模式;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileWriter {
    public void Write(String encryptStr,String fileNameDes)
    {
        System.out.println("保存密文，写入文件。");
        FileOutputStream fs = null;
        try
        {
            fs = new FileOutputStream(fileNameDes);
            //字符串转换为字节数组
            byte[] str = encryptStr.getBytes(StandardCharsets.UTF_8);
            fs.write(str,0,str.length);
            fs.flush();
            fs.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("文件不存在！");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println("文件操作错误！");
        }
    }

}
