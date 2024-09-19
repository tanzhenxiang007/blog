package com.blog.java操作Linux;

import java.io.File;

class fileMove {
    public static int fileMove1(String from, String to) {
        try {
            File dir = new File(from);

            File[] files = dir.listFiles();

            if (files == null) {
                return -1;

            }

            File moveDir = new File(to);

//            if (!moveDir.exists()) {
//                moveDir.mkdirs();
//
//                System.out.println("已新建一个目标移动文件夹");
//
//            }

            for (int i = 0; i < files.length; i++) {
                System.out.println("files[i].isDirectory()：" + files[i].isDirectory());

//                if (files[i].isDirectory()) {
//                    fileMove1(files[i].getPath(), to + dir.separator + files[i].getName());
//
//                    files[i].delete();
//
//                }

                File moveFile = new File(moveDir.getPath() + dir.separator + files[i].getName());

                if (moveFile.exists()) {
                    moveFile.delete();

                }

                files[i].renameTo(moveFile);

            }

            System.out.println("文件移动成功！");

        } catch (Exception e) {
            System.out.println("移动文件出现异常，异常信息为[" + e.getMessage() + "]");

            return -1;

        }

        return 0;

    }

    public static void main(String[] args) {
        fileMove1("D:\\ce1", "D:\\ce");

    }
}