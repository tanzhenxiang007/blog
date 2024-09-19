package com.blog.玩玩.导出excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckExcel {
    private String name;//姓名

    private Integer number;//学号
    private String sex;//学号
    private String checkName;//姓名
    private Date checkTime;//签到时间
}
