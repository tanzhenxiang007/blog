package com.blog.玩玩.异步.异步写入;

import com.blog.异步线程.ThreadPoolManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

// 导入Excel文件的类
public class ExcelImporter {
    // 使用SLF4J记录日志，日志级别为ExcelImporter类
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImporter.class);

    // 异步导入Excel文件的方法，返回一个CompletableFuture对象
    public static CompletableFuture<Void> importAsync(String filePath) {
        // 使用CompletableFuture.runAsync在新的线程中执行导入操作
        return CompletableFuture.runAsync(() -> {
            try (XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)))) {
                System.out.println("当前线程名："+Thread.currentThread().getName());
                // 获取第一个工作表
                Sheet sheet = workbook.getSheetAt(0);
                // 遍历工作表中的每一行
                for (Row row : sheet) {
                    // 遍历每一行中的每一个单元格
                    for (Cell cell : row) {
                        // 将单元格数据转换为字符串并记录日志
                        String value = cellToString(cell);
                        LOGGER.info("Value: {}", value);
                    }
                }
            } catch (IOException e) {
                // 如果出现IO异常，记录错误日志
                LOGGER.error("Error importing Excel file", e);
            }
        },ThreadPoolManager.getInstance().getPool()); // 使用线程池进行异步处理
    }

    // 将单元格数据转换为字符串的方法
    private static String cellToString(Cell cell) {
        if (cell == null) {
            // 如果单元格为空，返回null
            return null;
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            // 如果单元格类型为字符串，返回其值
            return cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            // 如果单元格类型为数字，将其转换为字符串并返回
            return String.valueOf(cell.getNumericCellValue());
        } else { // 处理其他类型的数据...
            // 其他类型的数据统一返回"Other type"字符串
            return "Other type";
        }
    }

    // 主方法，程序的入口点
    public static void main(String[] args) {
        // 调用getResult方法获取导入结果并打印输出
        String result = getResult();
        System.out.println(result);
    }

    // 获取导入结果的方法，实际导入操作在后台异步执行，此方法返回一个"正在导入"的字符串作为占位符。
    public static String getResult() {
        String filePath = "C:\\Users\\tzx\\Desktop\\金融外呼导入模板.xlsx"; // Excel文件的路径
        CompletableFuture<Void> importFuture = importAsync(filePath); // 调用importAsync方法开始导入操作，返回一个CompletableFuture对象
        importFuture.thenRun(() -> LOGGER.info("导入完成")); // 当导入操作完成后，记录一条日志信息

        //另类写法 跟上面不一样 启动一个线程异步！
        CompletableFuture.runAsync(() -> excueteAudit(Thread.currentThread().getName()), ThreadPoolManager.getInstance().getPool());
        // 返回一个"正在导入"的字符串作为占位符，表示导入操作正在进行中。
        return "正在导入";
    }

    public static void excueteAudit(String thread) {
        System.out.println("这里是异步方法噢"+thread);
    }


}

