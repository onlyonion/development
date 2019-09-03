package com.onion.test.java.lang;

public class ThreadWaitNotifyAB {

    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("a");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {

            while (true) {
                synchronized (lock) {
                    System.out.println("b");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

    }

}