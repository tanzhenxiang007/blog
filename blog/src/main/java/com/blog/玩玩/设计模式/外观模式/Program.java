package com.blog.玩玩.设计模式.外观模式;

public class Program {
    public static void main(String[] args) {
        EncryptFacade ef = new EncryptFacade();
        ef.FileEncrypt("C:\\Users\\tzx\\Desktop\\jar\\test2.txt", "C:\\Users\\tzx\\Desktop\\jar\\des.txt");


    }
}
