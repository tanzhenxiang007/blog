package com.blog.玩玩.排序.冒泡排序的优化;

import java.util.Arrays;
//冒泡排序

//该代码实现的是冒泡排序算法，通过相邻元素的比较和交换，一步步将最大的元素移动到数组的末尾。
//使用了一个 flag 标志位来检测是否可以提前结束排序，以提高效率。
//通过一个计数器 count 来统计比较次数，方便了解算法的性能。
public class BubbleSort {
    public static void main(String[] args) {
        int a[]={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        int count=0;
        for (int i = 0; i < a.length-1; i++) {
            boolean flag=true;
            for (int j = 0; j < a.length-1-i; j++) {
                if (a[j]>a[j+1]) {
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=false;
                }
                count++;
            }
            //如果在某一轮排序中没有进行任何交换（flag 仍为 true），则说明数组已经有序，跳出循环，提前结束排序。
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(a));// [2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50]
        System.out.println("一共比较了："+count+"次");//一共比较了：95次
    }
}


