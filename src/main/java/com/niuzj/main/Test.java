package com.niuzj.main;

import java.util.Scanner;

public class Test {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String model = null;
        String rootPath = null;
        String rootPackagePath = null;
        String controllerPath = null;
        String jspPath = null;

        try {
            System.out.println("输入实体类全类名:");
            model = scanner.nextLine();
            System.out.println("输入模块所在目录:");
            rootPath = scanner.nextLine();
            System.out.println("输入模块所在包:");
            rootPackagePath = scanner.nextLine();
            System.out.println("输入controller访问路径:");
            controllerPath = scanner.nextLine();
            System.out.println("输入jsp所在路径:");
            jspPath = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CodeGenerator generator = new CodeGenerator(model, rootPath, rootPackagePath, controllerPath, jspPath);
        generator.init();
//        System.out.println(model);
//        System.out.println(rootPath);
//        System.out.println(rootPackagePath);
//        System.out.println(controllerPath);
//        System.out.println(jspPath);

    }

}
