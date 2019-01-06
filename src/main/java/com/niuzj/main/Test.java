package com.niuzj.main;

import java.io.IOException;
import java.util.Scanner;

public class Test {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String model = "com.niuzj.admin.model.User";
        String rootPath = "F:\\技术学习\\testpro\\src\\main";
        String rootPackagePath = "com.niuzj.admin";
        String controllerPath = "/user/user_manage.htm";
        String jspPath = "/user/user_manage.jsp";
        String table = "user";

//        try {
//            System.out.println("输入实体类全类名:");
//            model = scanner.nextLine();
//            System.out.println("输入main所在目录:");
//            rootPath = scanner.nextLine();
//            System.out.println("输入基础包:");
//            rootPackagePath = scanner.nextLine();
//            System.out.println("输入表名");
//            table = scanner.nextLine();
//            System.out.println("输入controller访问路径:");
//            controllerPath = scanner.nextLine();
//            System.out.println("输入jsp所在路径:");
//            jspPath = scanner.nextLine();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        CodeGenerator generator = new CodeGenerator(model, rootPath, rootPackagePath, controllerPath, jspPath, table);
        generator.init();
        generator.generate();

    }

}
