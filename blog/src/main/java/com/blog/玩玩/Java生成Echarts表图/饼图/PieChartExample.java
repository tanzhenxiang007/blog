package com.blog.玩玩.Java生成Echarts表图.饼图;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;

public class PieChartExample {
    public static void main(String[] args) {
        // 第一步：创建数据集
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("张三", 99);
        dataset.setValue("李四", 87);
        dataset.setValue("王五", 89);
        dataset.setValue("马六", 45);
        dataset.setValue("陈七", 78);
        dataset.setValue("赵八", 92);
        dataset.setValue("测试1", 98);
        dataset.setValue("测试二", 80);

        // 第二步：创建饼图
        JFreeChart pieChart = ChartFactory.createPieChart(
                "学生成绩统计",  // 图表标题
                dataset,          // 数据集
                true, true, false // 显示图例、工具提示、URL生成器
        );

        // 第三步：自定义渲染器（可选）
        PiePlot plot = (PiePlot) pieChart.getPlot();
        // 这里可以设置渲染器的属性，例如颜色等

        // 第四步：设置图表属性（可选）
        pieChart.setTitle("学生成绩饼图");
        pieChart.setAntiAlias(true);

        // 第五步：输出图表
        // 保存为文件
        try {
            ChartUtilities.saveChartAsPNG(new File("D:\\piechart.png"), pieChart, 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}