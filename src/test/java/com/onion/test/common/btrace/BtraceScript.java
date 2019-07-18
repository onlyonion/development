package com.onion.test.common.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class BtraceScript {
    @OnMethod(clazz = "com.onion.test.common.btrace.BtraceTest", method = "add", location = @Location(Kind.RETURN))
    public static void func(@Self com.onion.test.common.btrace.BtraceTest instance, int a, int b, @Return int result) {
        println("invoke stack: ");
        jstack();
        println(strcat("parameter a=", str(a)));
        println(strcat("parameter b=", str(b)));
        println(strcat("parameter result=", str(result)));
    }

    // btrace -v pid BtraceScript.java
}
