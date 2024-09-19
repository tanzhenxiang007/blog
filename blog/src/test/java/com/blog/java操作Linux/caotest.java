package com.blog.java操作Linux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class caotest {
//    public static void main(String[] args) {
//        String[] arr = {"内容1", "内容2", "内容3","内容4","内容5","内容6","内容7","内容8","内容9","内容10","内容11","内容12","内容13"};
//        int n = 3; // 拼接的内容数量
//
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//        for (String s : arr) {
//            sb.append(s);
//            count++;
//            if (count % n == 0) {
//                if (arr.length - count < n) {
//                    // 如果剩余不足n个，则拼接剩余的元素
//                    for (int i = arr.length - count; i < arr.length - count; i++) {
//                        sb.append(s);
//                    }
//                } else {
//                    sb.append("    ");
//                }
//            }
//        }
//        System.out.println(sb.toString());
//    }

    public static String[] splitArray(List<String> originalArray, int count) {
        int originalLength = originalArray.size();
        int newArrayLength = (int) Math.ceil((double) originalLength / count); // 计算新数组的长度，向上取整

        String[] newArray = new String[newArrayLength];

        int newIndex = 0; // 新数组的索引
        int startIndex = 0; // 原数组的起始索引

        while (startIndex < originalLength) {
            int endIndex = Math.min(startIndex + count, originalLength); // 计算每个分段的结束索引
            StringBuilder subArrayBuilder = new StringBuilder();

            for (int i = startIndex; i < endIndex; i++) {
                subArrayBuilder.append(originalArray.get(i));
                if (i < endIndex - 1) {
                    subArrayBuilder.append(" "); // 添加分隔符，可以根据需要更改
                }
            }

            newArray[newIndex] = subArrayBuilder.toString(); // 将子数组的字符串添加到新数组中
            newIndex++;
            startIndex = endIndex; // 更新起始索引
        }

        return newArray;
    }

//    public static void main(String[] args) {
//        List<String> originalArray = new ArrayList<>();
//        originalArray.add("a1.txt");
//        originalArray.add("a2.txt");
//        originalArray.add("a3.txt");
//        originalArray.add("a4.txt");originalArray.add("a5.txt");originalArray.add("a6.txt");
//        originalArray.add("a7.txt");
//        originalArray.add("a8.txt");
//        originalArray.add("a9.txt");
//
//
//        int count = 3; // 指定的个数
//
//        String[] resultArray = splitArray(originalArray, count);
//
//        // 打印新数组
//        for (String str : resultArray) {
//            System.out.println(str);
//        }
//    }
public static void main(String[] args) {
    Map<String,Object> map =new HashMap<>();
    String o = (String)map.get("123");
    System.out.println(o);
}
}


//			for (int i = 0; i < fileNames.size() - 1; i=i+2) {
//				String uuid = UUID.randomUUID().toString();
//				logger.info("cat "+fileNames.get(i)+" >> "+fileNames.get(i+1)+" > "+uuid+"_SBCcdr.txt1");
//				executeLinux("cat "+add2+"/"+fileNames.get(i)+" >> "+add2+"/"+fileNames.get(i+1)+" && mv"+uuid+"_SBCcdr.txt1");
//			}
//			// 如果列表长度是奇数，直接改名
//			if (fileNames.size() > 1) {
//				if ((fileNames.size() - 1) % 2 == 0) {
//					logger.info("最后一个文件不需要合并，直接改名");
//					executeLinux("mv /sftp/sftp/temporary/"+fileNames.get(fileNames.size() - 1)+" "+UUID.randomUUID().toString()+"_SBCcdr.txt1");
//				}
//			}