package com.blog.玩玩.NFS;

import lombok.var;

import java.util.*;

public class StringUtils {

    public static boolean equals(String value, String... keywords) {
        for (var keyword : keywords)
            if (keyword.equals(value))
                return true;
        return false;
    }

    public static boolean equals(Integer value, Integer... keywords) {
        for (var keyword : keywords)
            if (keyword == value)
                return true;
        return false;
    }

    public static boolean like(String value, String... keywords) {
        if (value == null)
            return false;
        for (var keyword : keywords)
            if (keyword.contains(value))
                return true;
        return false;
    }

    public static <T> String join(List<T> list) {
        var builder = new StringBuilder();
        for (var index = 0; index < list.size(); index++) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append(list.get(index));
        }
        return builder.toString();
    }

    public static String addSingleQuotationMarks(String value) {
        if (value.startsWith("'"))
            return value;
        return "'" + value + "'";
    }

    public static String addDoubleQuotationMarks(String value) {
        if (value.startsWith("\""))
            return value;
        return "\"" + value + "\"";
    }

//    public static String[] removeEmpty(String[] array) {
//        var list = LinqUtil.filter(array, m -> isNotEmpty(m));
//        return list.toArray(new String[0]);
//    }

    public static boolean contains(String[] values, String keyword) {
        for (var value : values) {
            if (value.equals(keyword))
                return true;
        }
        return false;
    }

    public static boolean in(String value, String... values) {
        for (var item : values)
            if (item.equals(value))
                return true;
        return false;
    }

    public static List<String> list(List<?> values) {
        var list = new ArrayList<String>();
        for (var value : values)
            list.add(value.toString());
        return list;
    }

    public static String digit2Hanzi(String value) {
        return value.replace('0', '零')
                .replace('1', '一')
                .replace('2', '二')
                .replace('3', '三')
                .replace('4', '四')
                .replace('5', '五')
                .replace('6', '六')
                .replace('7', '七')
                .replace('8', '八')
                .replace('9', '九');
    }

    public static boolean contains(String text, String keyword) {
        if (keyword == null || text == null)
            return false;
        return text.contains(keyword);
    }

    public static String getSex(Integer sex) {
        if (sex == 0)
            return "男";
        if (sex == 1)
            return "女";
        return "";
    }

//    public static String[] combination(String[] array1, String[] array2, String spo) {
//        var list = new ArrayList<String>();
//        for (var item1 : array1)
//            for (var item2 : array2)
//                list.add(item1 + spo + item2);
//        return ArrayUtil.toArray(list, array1);
//    }

    public static String[] getDistinct(String[] array) {
        List list = Arrays.asList(array);
        Set set = new HashSet(list);
        return (String[]) set.toArray(new String[0]);
    }

    public static boolean contains(List<String> keywords, String keyword) {
        for (var value : keywords) {
            if (value.equals(keyword))
                return true;
        }
        return false;
    }

//    public static List<String> split(String text) {
//        if (text == null)
//            return new ArrayList<>();
//        var array = text.split(",");
//        return ArrayUtil.arrayToList(array);
//    }

//    public static String listToStringSplit(List list, String split) {
//        if (list == null || list.size() == 0)
//            return null;
//
//        return StringUtils.trimAllWhitespace(list.toString()
//                .replaceAll("\\[", "")
//                .replaceAll("]", ""))
//                .replaceAll(",", split);
//    }

//    public static String listToStringSplit(List list, String split, boolean isTrimSpace) {
//        if (list == null || list.size() == 0)
//            return null;
//        if (isTrimSpace) {
//            return listToStringSplit(list, split);
//        }
//        return list.toString()
//                .replaceAll("\\[", "")
//                .replaceAll("]", "")
//                .replaceAll(",", split);
//    }

    public static List joinList(List list, String startJoin, String endJoin) {
        if (list == null || list.size() == 0)
            return null;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, joinString(list.get(i), startJoin, endJoin));
        }
        return list;
    }

    public static String joinString(Object obj, String startJoin, String endJoin) {
        String value = obj.toString();
        if (isEmpty(value))
            return null;
        value = startJoin + value + endJoin;
        return value;
    }

    public static List<String> getLetters(String text) {
        var list = new ArrayList<String>();
        for (var ch : text.toCharArray())
            list.add(ch + "");
        return list;
    }

    public static String newId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String newId(int length) {
        return newId().substring(0, length);
    }

    public static String guid(String tail) {
        return newId() + tail;
    }

    public static boolean isEmpty(Object text) {
        return StringUtils.isEmpty(text);
    }

    public static boolean isNotEmpty(Object... values) {
        for (var value : values)
            if (StringUtils.isEmpty(value))
                return false;
        return true;
    }

    public static String format(Object value, Object... args) {
        if (value == null)
            return "";
        if (args.length > 0)
            return String.format(value.toString().replace("{}", "%s"), args);
        return value.toString();
    }

    public static String number(long value, int length) {
        return String.format("%0" + length + "d", value);
    }

    public static String number(int value, int length) {
        return String.format("%0" + length + "d", value);
    }

    public static String upperFirst(String value) {
        char[] cs = value.toCharArray();
        if (cs[0] > 96 && cs[0] < 123)
            cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String[] upperFirst(String[] values) {
        for (var index = 0; index < values.length; index++)
            values[index] = upperFirst(values[index]);
        return values;
    }

    public static String join(String[] values, String spor) {
        var sb = new StringBuilder(values[0]);
        for (int index = 1; index < values.length; index++) {
            sb.append(spor);
            sb.append(values[index]);
        }
        return sb.toString();
    }

//    public static String join(List<String> values, String spor) {
//        return join(ArrayUtil.toArray(values, new String[0]), spor);
//    }

}
