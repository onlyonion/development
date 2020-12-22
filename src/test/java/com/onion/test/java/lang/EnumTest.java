package com.onion.test.java.lang;

public enum EnumTest {
    A {
        @Override
        String hello() {
            return getClass().getName();
        }

        @Override
        public String hehe(String other) {
            return this.ordinal() + "-" + this.name() + "-" + other;
        }
    };

    abstract String hello();

    public String hehe(String other) {
        return other;
    }

    public static void main(String[] args) {
        System.out.println(EnumTest.A.hello());
        System.out.println(EnumTest.A.hehe("hehe"));
    }
}
