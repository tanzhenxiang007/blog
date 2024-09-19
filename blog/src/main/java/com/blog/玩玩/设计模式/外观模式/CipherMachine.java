package com.blog.玩玩.设计模式.外观模式;

public class CipherMachine {

        public String Encrypt(String plainText)
        {
            System.out.println("数据加密，将明文转换为密文：");
            String es = "";
            char[] chars = plainText.toCharArray();
            for (char ch : chars)
            {
                String c = (ch % 7)+"";
                es += c;
            }
            System.out.println(es);
            return es;
        }


}
