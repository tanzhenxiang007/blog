package com.blog.玩玩.java写的爬虫小程序爬取网站照片;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class meizi {
    /**
     * 下载图片到指定目录
     *
     * @param filePath 文件路径
     * @param imgUrl   图片URL
     */

    public static void downImages(String filePath, String imgUrl) {
        // 若指定文件夹没有，则先创建
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 截取图片文件名
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1, imgUrl.length());

        try {
            // 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
            String urlTail = URLEncoder.encode(fileName, "UTF-8");
            // 因此要将加号转化为UTF-8格式的%20
            imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // 写出的路径
        File file = new File(filePath + File.separator + fileName);

        try {
            // 获取图片URL
            URL url = new URL(imgUrl);
            // 获得连接
            URLConnection connection = url.openConnection();
            // 设置10秒的相应时间
            connection.setConnectTimeout(10 * 1000);
            //connection.setReadTimeout(20*1000);
            // 获得输入流
            InputStream in = connection.getInputStream();
            // 获得输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            // 构建缓冲区
            byte[] buf = new byte[1024];
            int size;
            // 写入到文件
            while (-1 != (size = in.read(buf))) {
                out.write(buf, 0, size);
            }
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void findhref(String hrefURL) {
        // 利用Jsoup获得连接
        Connection connect = Jsoup.connect(hrefURL);
        try {
            // 得到Document对象
            Document document = connect.get();
            // 查找所有img标签
            Elements imgs = document.getElementsByTag("img");
            System.out.println("共检测到下列imgURL："+imgs);
            // 遍历img标签并获得src的属性
            for (Element element : imgs) {
                //获取每个a标签URL "abs:"表示绝对路径
                String imgUrl = element.attr("abs:src");
                // 打印URL
                if(imgUrl.charAt(4)=='s') {
                    if(imgUrl.charAt(6)=='/' && imgUrl.charAt(7)!='/') {
                        imgUrl = imgUrl.substring(0,6) + '/' + imgUrl.substring(6,imgUrl.length());
                    }
                }
                System.out.println(imgUrl);
                //下载图片到本地
                meizi.downImages("e:/img", imgUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // 利用Jsoup获得连接
        Connection connect = Jsoup.connect("https://www.ivsky.com/tupian/ziranfengguang/");
        try {
            // 得到Document对象
            Document document = connect.get();
            // 查找所有a标签
            Elements hrefs = document.getElementsByTag("a");
            System.out.println("开始下载");
            // 遍历a标签并获得href的属性
            for (Element element : hrefs) {
                //获取每个a标签URL "abs:"表示绝对路径
                String hrefURL = element.attr("abs:href");
                //下载图片到本地
                if(hrefURL.equals("")) {
                    //System.out.println("1111111111111111:");
                    continue;
                }

                meizi.findhref(hrefURL);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

//class LogicalOperatorsExample {
//    public static void main(String[] args) {
//        boolean a = false;
//        boolean b = true;
//        //在这个例子中，当使用 && 时，由于 a 是 true，someMethodThatReturnsFalse() 实际上不会被调用，
//        // 因为 && 表现出短路行为。但是，当使用 & 时，无论 a 的值是什么，someMethodThatReturnsFalse() 都会被调用，因为 & 不具有短路行为。
//        // 使用 &&（逻辑与）
//        if (a && someMethodThatReturnsFalse()) {
//            System.out.println("This will not be printed because someMethodThatReturnsFalse() is not called.");
//        }
//
//        // 使用 &（按位与）
//        if (a & someMethodThatReturnsFalse()) {
//            System.out.println("This might be printed because someMethodThatReturnsFalse() is called even if a is true.");
//        }
//    }
//
//    private static boolean someMethodThatReturnsFalse() {
//        System.out.println("Method is called.");
//        return true;
//    }
//}



