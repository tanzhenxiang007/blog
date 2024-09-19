package com.blog.玩玩.NFS;


import lombok.var;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BinaryUtil {

    public static ByteArrayOutputStream getMemoryOutputStream() {
        return new ByteArrayOutputStream();
    }

    public static ByteArrayOutputStream getMemoryOutputStream(InputStream inputStream) throws IOException {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        int _byte;
        while ((_byte = inputStream.read()) != -1)
            byteArrayOutputStream.write(_byte);
        inputStream.close();
        return byteArrayOutputStream;
    }

    public static ByteArrayInputStream getMemoryInputStream(ByteArrayOutputStream output) {
        return new ByteArrayInputStream(output.toByteArray());
    }

    public static ByteArrayInputStream getMemoryInputStream(InputStream inputStream) throws IOException {
        return getMemoryInputStream(getMemoryOutputStream(inputStream));
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        var stream = getMemoryOutputStream(inputStream);
        var data = stream.toByteArray();
        stream.close();
        return data;
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        return readBytes(inputStream);
    }

    public static byte[] getBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

    public static InputStream getStream(byte[] data) {
        return new ByteArrayInputStream(data);
    }

    public static InputStream getStream(String text) {
        return getStream(getBytes(text));
    }

    public static String readText(InputStream inputStream, String charset) throws IOException {
        var stream = getMemoryOutputStream(inputStream);
        var text = stream.toString(charset);
        stream.close();
        return text;
    }

    public static String readText(InputStream inputStream) throws IOException {
        return readText(inputStream, StandardCharsets.UTF_8.name());
    }


    public static void write(OutputStream outputStream, byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.close();
    }

    public static void write(OutputStream outputStream, InputStream data) throws IOException {
        write(outputStream, getBytes(data));
    }

    public static void write(OutputStream outputStream, String text) throws IOException {
        write(outputStream, getBytes(text));
    }

//    public static long getHashCode(InputStream inputStream) throws IOException {
//        return HashUtil.cityHash32(getBytes(inputStream));
//    }

}
