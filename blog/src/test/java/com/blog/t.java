package com.blog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class t {
    public static  int abc=123;
    static {
        System.out.println("t is 456");

    }
}
class s extends t{
static {
    System.out.println("s is 789");

}}

class b{
    public static void main(String[] args) {
        System.out.println(s.abc);
    }
}

 class PhoneNumberMatcher {

     // 插入排序
     private static int count;
     public static void main(String[] args) throws InterruptedException {
//         Object lock = new Object();//创建一个Object类型的对象，命名为lock，将count++放入{}中
//         Thread t1 = new Thread(()->{
//             for (int i = 0; i < 100; i++) {
//                 synchronized(lock) {//1
//                     count++;
//                     System.out.println("增加"+count);
//                     synchronized(lock) {//2
//                         //此处执行一些工作……
//                         count--;
//                         System.out.println("减少"+count);
//                     }//3
//                 }//4
//
//             }
//         });
//         Thread t2 = new Thread(()->{
//             for (int i = 0; i < 100; i++) {
//                 synchronized(lock) {
//                     count++;
//                     System.out.println(Thread.currentThread().getName()+":"+count);
//                 }
//             }
//         });
//
//         t1.start();
//         t2.start();
//
//         t1.join();
//         t2.join();
//         System.out.println(count);

         Map<String, String> concMap = new ConcurrentHashMap<>();
         concMap.put("123","a");
         String phone="456";
         boolean b = concMap.containsKey(phone);
         if (b){

             System.out.println(
                     concMap.get(phone)
             );
         }
     }


//     //冒泡排序升级版
//     public static void main(String[] args) {
//         int[] arr = {1,3,2,4,9,2,13};
//         fun4(arr, arr.length - 1);
//         System.out.println(Arrays.toString(arr));
//     }
//     public static void fun4 (int[] arr, int n) {
//         if (n == 0) {
//             return;
//         }
//         int j = 0;
//         for (int i = 0; i < n; i++) {
//             if (arr[i] > arr[i + 1]) {
//                 int temp = arr[i];
//                 arr[i] = arr[i+1];
//                 arr[i+1] = temp;
//                 j = i;
//             }
//         }
//         fun4(arr,j);
//     }
}