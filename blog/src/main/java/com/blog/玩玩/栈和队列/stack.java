package com.blog.玩玩.栈和队列;

import java.util.Stack;

public class stack {
    public static void main(String[] args) {
        //栈是后进先出的数据结构‌
        //逆波兰表达式遵循从左到右的运算,所以采用栈来计算
        //如果遇到数字,将数字入栈
        //如果遇到运算符,将栈内出栈2个元素,通过运算符计算后,将计算结果放入栈中.
        String[] str = new String[]{"3", "1", "+", "4", "*"};
        String[] str1 = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        int i = evalRPN(str);
        int i1 = evalRPN(str1);
        System.out.println(i);
        System.out.println(i1);
    }

    public static int evalRPN(String[] tokens) {
        // 创建一个栈
        Stack<Integer> stack = new Stack<>();
        // 遍历每一个字符串
        for (String token : tokens) {
            //判断是否为数字,如果是,加入栈中,如果不是,取出2个元素计算后重新加入栈中
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(pop2 + pop1);
                        break;
                    case "-":
                        stack.push(pop2 - pop1);
                        break;
                    case "*":
                        stack.push(pop2 * pop1);
                        break;
                    case "/":
                        stack.push(pop2 / pop1);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        //计算完毕后,剩下一个元素,即为结果
        return stack.pop();
    }

}
