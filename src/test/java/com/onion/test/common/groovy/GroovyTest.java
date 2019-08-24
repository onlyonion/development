package com.onion.test.common.groovy;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.lang.Binding;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GroovyTest {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {

        ClassLoader parent = ClassLoader.getSystemClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        Class groovyClass = loader.parseClass(new File("./src/test/java/com/onion/test/common/groovy/GroovyDemo.groovy"));
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        Object[] param = {123, 321};
        int res = (int) groovyObject.invokeMethod("add", param);
        System.out.println("res=" + res);

    }

    @Test
    public void test() throws IOException, ResourceException, ScriptException {
        String path = "./src/test/java/com/onion/test/common/groovy/";

        GroovyScriptEngine gse = new GroovyScriptEngine(path);
        Binding binding = new Binding();
        binding.setVariable("input", "Groovy");
        gse.run("Test.groovy", binding);
        System.out.println(binding.getVariable("output"));
    }

}
