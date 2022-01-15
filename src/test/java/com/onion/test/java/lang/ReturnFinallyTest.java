package com.onion.test.java.lang;

/**
 * @author lijicong
 * @since 2021-12-06
 */
public class ReturnFinallyTest {

    public static void main(String[] args) {
        int i = main2();
        System.out.println("main2 " + i);
    }

    public static int main2() {
        int x = 0;
        try {
            x = 1;
            return main3(x);
        } catch (Exception e) {
            return x;
        } finally {
            x = 2;
            System.out.println("finally " + x);
        }
    }

    public static int main3(int x) {
        System.out.println("return " + x);
        return x;
    }
}
