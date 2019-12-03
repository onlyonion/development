package com.onion.test.distributed.dubbo.samples;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijicong
 * @since 2019-12-02
 */
public class AnnotationProvider {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationProvider.class);
        context.start();
        System.in.read();
    }


    @Configuration
    static class ProviderConfiguration {

    }

}
