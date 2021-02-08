package com.example.qzm.study.function.jvm.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ClassLoadTest
 * @Description 不同的类加载器对instanceof关键字运算的结果的影响
 * @Version 1.0
 **/
public class ClassLoadTest {
    public static void main(String[] args) throws Exception{
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                InputStream inputStream = null;
                byte[] bytes = null;
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, bytes, 0, bytes.length);
            }
        };

        Object object = myClassLoader.loadClass("com.example.qzm.study.function.jvm.classload.ClassLoadTest").newInstance();
        System.out.println(object.getClass());
        System.out.println(object instanceof com.example.qzm.study.function.jvm.classload.ClassLoadTest);
    }
}
