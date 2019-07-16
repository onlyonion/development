package com.onion.test.common.javasist;

import java.lang.reflect.Field;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.bytecode.AccessFlag;

public class JavasistTest {

    @Test
    public void DynGenerateClass() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.makeClass("top.ss007.GenerateClass");//创建类
        ct.setInterfaces(new CtClass[] { pool.makeInterface("java.lang.Cloneable") });//让类实现Cloneable接口
        try {
            CtField f = new CtField(CtClass.intType, "id", ct);//获得一个类型为int，名称为id的字段
            f.setModifiers(AccessFlag.PUBLIC);//将字段设置为public
            ct.addField(f);//将字段设置到类上
            //添加构造函数
            CtConstructor constructor = CtNewConstructor.make("public GeneratedClass(int pId){this.id=pId;}", ct);
            ct.addConstructor(constructor);
            //添加方法
            CtMethod helloM = CtNewMethod.make("public void hello(String des){ System.out.println(des);}", ct);
            ct.addMethod(helloM);

            ct.writeFile("D:/temp");//将生成的.class文件保存到磁盘

            //下面的代码为验证代码
            Field[] fields = ct.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
