package com.blog.玩玩.导出excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ToExcel {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void getExcel(List<CheckExcel> object) {
        //创建一个工作簿，也就是Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一个工作表
        HSSFSheet sheet = wb.createSheet();
        //第一行，标题
//        HSSFRow row0 = sheet.createRow(0);
//        HSSFCell cell0 = row0.createCell(0);
//
//        cell0.setCellValue(object.get(0).getCheckName() + "签到统计表");
        // 创建一个样式对象
//        CellStyle style = wb.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //第二行，表头
        HSSFRow row1 = sheet.createRow(0);
//        row1.setRowStyle(style);
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue("学号");

        row1.createCell(3).setCellValue("签到时间");
        //表中数据
        int rowInt = 1;
        for (CheckExcel obj : object) {
            HSSFRow row = sheet.createRow(rowInt++);
            int cellInt = 0;
            row.createCell(cellInt++).setCellValue(obj.getName());
            row.createCell(cellInt++).setCellValue(obj.getNumber());
            row.createCell(cellInt).setCellValue(sdf.format(obj.getCheckTime()));//将日期转换成字符串，要不然存的是一个时间戳
        }
        try {
            //输出文件
            //创建字节输出流
            File file = new File("D://excel.xls");
            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ToExcel.getExcel");
            System.out.println("IO出异常了");
        }
    }
    public static void main(String[] args) {
        List<CheckExcel> stu = new ArrayList<>();
        stu.add(new CheckExcel("学生1", 1001,"男", "高等数学", new Date()));
        stu.add(new CheckExcel("学生2", 1002,"男", "高等数学", new Date()));
        stu.add(new CheckExcel("学生3", 1003,"", "高等数学", new Date()));
        getExcel(stu);
    }
//public static void main(String[] args) {
//    // 定义时间格式
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    // 给定的两个时间点
//    String startTimeStr = "2024-07-07 23:30:00";
//    String endTimeStr = "2024-07-07 23:00:00";
//
//    // 将字符串转换为LocalDateTime对象
//    LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
//    LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
//
//    // 确保结束时间晚于开始时间
//    if (endTime.isBefore(startTime)) {
//        LocalDateTime temp = startTime;
//        startTime = endTime;
//        endTime = temp;
//    }
//
//    // 创建JSONArray来存储时间段
//    JSONArray timeSlots = new JSONArray();
//
//    // 按10分钟切割时间
//    for (LocalDateTime current = endTime; current.isBefore(startTime) || current.isEqual(startTime); current = current.plusMinutes(10)) {
//        // 创建时间段的JSONObject
//        JSONObject timeSlot = new JSONObject();
//        timeSlot.put("startTime", current.format(formatter));
//        timeSlot.put("endTime", current.plusMinutes(10).minusSeconds(1).format(formatter)); // 减去1秒确保不包含下个10分钟的开始时间
//
//        // 将时间段添加到数组中
//        timeSlots.add(timeSlot);
//    }
//
//    // 打印结果
//    System.out.println(timeSlots.toJSONString()); // 使用toJSONString方法输出格式化的JSON字符串
//    String BB="2024-07-03 14:40:00|2024-07-03 14:50:00";
//    System.out.println(BB.split("\\|")[0]);
//    JSONObject jsonObject = JSONObject.parseObject("{\"list\":[]}");
//    JSONArray list = jsonObject.getJSONArray("list");
//    if (list.size() > 0){
//        System.out.println(2123);
//    }
//}
}