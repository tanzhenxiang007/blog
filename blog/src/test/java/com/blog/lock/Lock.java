package com.blog.lock;

public class Lock implements Runnable {
    public final Object LOCK = new Object();
    public final static Object STATIC_LOCK = new Object();
    private String type;
    private static int count = 0;

    public Lock() {
    }

    public Lock(String type) {
        this.type = type;
    }


    private void objectLock() {
        synchronized (LOCK) {
            while (++count < 10) {
                System.out.println(count);
            }
        }
    }

    private void staticObjectLock() {
        synchronized (STATIC_LOCK) {
            while (++count < 10) {
                System.out.println(count);
            }

        }
    }

    private void staticLock() {
        synchronized (Lock.class) {
            while (++count < 10) {
                System.out.println(count);
            }
        }
    }

    public void run() {
        if ("object".equals(type)) {
            objectLock();
        } else if ("static".equals(type)) {
            staticLock();
        } else if ("staticObject".equals(type)) {
            staticObjectLock();
        } else {
            throw new IllegalArgumentException("参数异常...");
        }
    }

}
