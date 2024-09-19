package com.blog.玩玩.Java生成Echarts表图;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

/**
 * 类描述: 重写BarRenderer类
 *
 * @author Jack_Liberty
 * @version 1.0
 * @date 2021-04-01 11:30
 */
@Data
public class CustomRenderer extends org.jfree.chart.renderer.category.BarRenderer {

    /**
     * 分值
     */
    private static final Double EIGHTY_NINE = 89D;

    /**
     * 分值
     */
    private static final Double SEVENTY_NINE = 79D;

    /**
     * 柱子颜色
     */
    private Paint[] colors;

    /**
     * 柱子分数
     */
    private ArrayList<Double> scores;

    public CustomRenderer() {
        /**
         * 初始化柱子颜色
         */
        String[] colorValues = {"#9BBE62", "#F59A23", "#E84614"};
        colors = new Paint[colorValues.length];
        for (int i = 0; i < colorValues.length; i++) {
            colors[i] = Color.decode(colorValues[i]);
        }
    }

    @Override
    public Paint getItemPaint(int i, int j) {
        Double score = this.scores.get(j);
        if (score > EIGHTY_NINE) {
            return colors[0];
        } else if (score > SEVENTY_NINE) {
            return colors[1];
        } else {
            return colors[2];
        }
    }
}
