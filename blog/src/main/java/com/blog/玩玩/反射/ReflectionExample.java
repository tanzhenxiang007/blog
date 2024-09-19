package com.blog.玩玩.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class ReflectionExample {
    /**
     * 使用 Class.forName 获取 Person 类的 Class 对象。
     * 通过 getConstructor 方法获取带有参数的构造函数，并使用 newInstance 方法创建 Person 类的一个实例。
     * 使用 getMethod 获取 introduce 方法，并通过 invoke 调用该方法。
     * 使用 getDeclaredField 获取私有字段 name，并通过 setAccessible(true) 允许访问私有字段，接着修改它的值。
     * 再次调用 introduce 方法，输出修改后的结果。
     * @param args
     */
    public static void main(String[] args) {
        try {
            // 获取Person类的Class对象
            Class<?> personClass = Class.forName("com.blog.玩玩.反射.FanShePerson");

            // 获取构造函数并创建实例
            Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
            Object personInstance = constructor.newInstance("Alice", 30);

            // 获取并调用introduce方法
            Method introduceMethod = personClass.getMethod("introduce");
            introduceMethod.invoke(personInstance);

            // 获取并修改私有字段name
            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true); // 允许访问私有字段
            nameField.set(personInstance, "Bob"); // 修改字段值

            // 再次调用introduce方法
            introduceMethod.invoke(personInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
