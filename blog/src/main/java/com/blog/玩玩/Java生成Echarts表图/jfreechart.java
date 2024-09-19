package com.blog.玩玩.Java生成Echarts表图;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;


import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class jfreechart {


    /**
     * 功能描述: 创建JFreeChart对象并设置样式
     *
     * @param categoryDataset 类别数据集
     * @return org.jfree.chart.JFreeChart
     * @author Jack_Liberty
     * @date 2021-04-01 11:16
     */
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        JFreeChart jfreechart = ChartFactory.createBarChart("Test", "", "", categoryDataset,
                PlotOrientation.VERTICAL, false, false, false);

        Font labelFont = new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 15);
//        Font labelFont1 = new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 24);

//         jfreechart.setTextAntiAlias(false);
        jfreechart.setBackgroundPaint(Color.white);
        // 获得图表区域对象
        CategoryPlot plot = jfreechart.getCategoryPlot();

        // 设置横虚线可见
        plot.setRangeGridlinesVisible(true);
        // 虚线色彩
        plot.setRangeGridlinePaint(Color.gray);
        // 数据轴精度
        NumberAxis vn = (NumberAxis) plot.getRangeAxis();
        DecimalFormat df = new DecimalFormat("#0.0");
        // 数据轴数据标签的显示格式
        vn.setNumberFormatOverride(df);

        // x轴设置
        CategoryAxis domainAxis = plot.getDomainAxis();
        // 轴标题
        domainAxis.setLabelFont(labelFont);
        // 轴数值
        domainAxis.setTickLabelFont(labelFont);
        // X轴标题过长可设置倾斜度
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 3.0));
        // 横轴上的 Label
        domainAxis.setMaximumCategoryLabelWidthRatio(6.00f);
        // 是否完整显示

        // 设置距离图片左端距离
        domainAxis.setLowerMargin(0.0);
        // 设置距离图片右端距离
        domainAxis.setUpperMargin(0.0);
        // 设置 columnKey 是否间隔显示
        plot.setDomainAxis(domainAxis);
        // 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
        plot.setBackgroundPaint(new Color(255, 255, 255, 255));

        // y轴设置
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(labelFont);
        rangeAxis.setTickLabelFont(labelFont);
        // 设置最高的一个 Item 与图片顶端的距离
        rangeAxis.setUpperMargin(0.15);
        // 设置最低的一个 Item 与图片底端的距离
        rangeAxis.setLowerMargin(0.15);
        plot.setRangeAxis(rangeAxis);

        // 解决中文乱码问题(关键)
        TextTitle textTitle = jfreechart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 15));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));
        vn.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 15));
        vn.setLabelFont(new Font("黑体", Font.PLAIN, 15));


        // 使用自定义的渲染器
        CustomRenderer renderer = new CustomRenderer();
        ArrayList<Double> data = new ArrayList<Double>();
        data.add(99D);
        data.add(87D);
        data.add(89D);
        data.add(45D);
        data.add(78D);
        data.add(92D);
        data.add(98D);
        data.add(80D);
        renderer.setScores(data);
        // 设置柱子宽度
        renderer.setMaximumBarWidth(0.1);
        // 设置柱子高度
        renderer.setMinimumBarLength(0.1);
        // 设置柱子边框颜色
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 设置柱子边框可见
        renderer.setDrawBarOutline(true);
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(1);
        jfreechart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        // 显示每个柱的数值，并修改该数值的字体属性
//          renderer.setIncludeBaseInRange(true);

        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer);
        // 设置柱的透明度
        plot.setForegroundAlpha(1.0f);
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        return jfreechart;
    }



    /**
     * 功能描述: 创建CategoryDataset对象
     *
     * @return org.jfree.data.category.CategoryDataset
     * @author Jack_Liberty
     * @date 2021-04-01 16:20
     */
    public static CategoryDataset createDataset() {
        double[][] data = new double[][]{{1,9,0,15,9,3,4}};
        String[] rowKeys = {"1"};
        String[] columnKeys = {"Test1","Test2","Test3","Test4","Test5","Test6","Test7"};
        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
    }



    public static void main(String[] args) throws Exception {
        // 创建CategoryDataset对象
        CategoryDataset dataset = createDataset();
        // 根据Dataset 生成JFreeChart对象,并设置相应的样式
        JFreeChart freeChart = createChart(dataset);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\chart110605.png");
            ChartUtilities.writeChartAsPNG(out, freeChart,
                    800, 450);// 输出图表
            System.out.println("图片运行成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
