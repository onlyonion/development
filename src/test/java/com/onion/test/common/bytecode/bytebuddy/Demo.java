package com.onion.test.common.bytecode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.FixedValue;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Demo {

    @Test
    public void testToString() throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
        System.out.println(helloWorld);  // Hello World!
    }

    @Test
    public void testName() throws IOException {
        Map<TypeDescription, File> toString = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .name("com.test.ByteBuddyHelloWorld")
                .make()
                .saveIn(new File("/tmp/classes"));
    }

    @Test
    public void testMain() throws IOException {
        Map<TypeDescription, File> typeDescriptionFileMap = new ByteBuddy()
                .subclass(Object.class)
                .name("com.test.HelloWorld")
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class, "args")
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .saveIn(new File("/tmp/classes"));
    }

}
