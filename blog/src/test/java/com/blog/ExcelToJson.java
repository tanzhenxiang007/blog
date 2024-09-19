package com.blog;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java 读取excel文件转化为json数据
 */
public class ExcelToJson {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\tzx\\Desktop\\金融外呼导入模板.xlsx");

            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int rows_total = sheet.getLastRowNum();
            int columns_total = sheet.getRow(0).getLastCellNum();
            Map<Integer, String> maps = new HashMap<Integer, String>();
            Row rows = sheet.getRow(0);
            for (int i = 0; i < columns_total; i++) {
                maps.put(i, rows.getCell(i).getStringCellValue());
            }
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (int i = 1; i <= rows_total; i++) {
                Row row = sheet.getRow(i);                Map<String, String> map = new HashMap<String, String>();
                for (int j = 0; j < columns_total; j++) {
                    Cell cell = row.getCell(j);
                    System.out.println(cell.getCellTypeEnum());
                    DataFormatter formatter = new DataFormatter();
                    String value = formatter.formatCellValue(cell);
                    map.put(maps.get(j), value);
                }
                list.add(map);
            }
            Gson gson = new Gson();
            String json = gson.toJson(list);
            System.out.println(json);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();        }
    }
}