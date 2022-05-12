package com.onion.test.common.base300;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author lijicong
 * @since 2022-03-09
 */
public class BaseTest {

    @Test
    public void test() {
        // System.out.println("please in put");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] words = input.split(" ");
        String last = words[words.length - 1].trim();
        int length = last.length();
        //System.out.println("输入：" + input);
        //System.out.println("输出：" + length);
        System.out.println(length);
        // System.out.println("最后一个单词为" + last + "，长度为" + length);
    }


}
