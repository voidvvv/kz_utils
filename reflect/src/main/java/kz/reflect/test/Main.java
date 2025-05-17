package kz.reflect.test;

import anno.KzTransaction;
import impl.MyEater;
import interfaces.Eatable;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//
//        byte[] bytes = ProxyGenerator.generateProxyClass(
//                "aaa",
//                new Class[]{Eatable.class}, Modifier.PUBLIC | Modifier.FINAL);
//        FileOutputStream fos = new FileOutputStream("aaa.java");
//        try {
//            fos.write(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            fos.close();
//        }
        Eatable origin = new MyEater();
        Eatable proxyObj = (Eatable) Proxy.newProxyInstance(
                Eatable.class.getClassLoader(),
                new Class[]{Eatable.class},
                (proxy, method, args1) -> {
                    origin.getClass().getDeclaredMethod(method.getName(), null);
                    KzTransaction trans = method.getAnnotation(KzTransaction.class);
                    if (trans != null) {
                        System.out.println("Transaction started");
                    }
                    Object invoke = method.invoke(origin, args1);
                    if (trans != null) {
                        System.out.println("Transaction end");
                    }
                    return invoke;
                }
        );

        proxyObj.eat();
        proxyObj.drink();
    }
}
