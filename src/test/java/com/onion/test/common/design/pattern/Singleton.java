package com.onion.test.common.design.pattern;

/**
 * @author lijicong
 * @since 2022-03-29
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    private static Singleton newInstance() {
        return instance;
    }
}

class Singleton2 {
    private static volatile Singleton2 instance = null;

    private Singleton2() {}

    private static Singleton2 newInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}

class Singleton3 {
    private static class Singleton3Holder {
        public static Singleton3 instance = new Singleton3();
    }

    private Singleton3() {}

    private static Singleton3 newInstance() {
        return Singleton3Holder.instance;
    }
}