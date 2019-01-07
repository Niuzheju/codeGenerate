package com.niuzj.main;

import java.io.IOException;
import java.util.Scanner;

public class Test {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String model = "com.fx.tthouse.model.base.ExpendConfig";
        String rootPath = "E:\\公司代码\\tt_house\\tth_parent\\admin\\src\\main\\java\\com\\tthouse\\admin\\biz";
        String controllerPath = "/operation/expend/expend_config.htm";
        String jspPath = "/expend/expend_config.jsp";
        String table = "expend_config";
        String mapperPath = "E:\\公司代码\\tt_house\\tth_parent\\admin\\src\\main\\resources\\mybatis\\mapper\\";

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
        CodeGenerator generator = new CodeGenerator(model, rootPath, mapperPath, controllerPath, jspPath, table);
        generator.init();
        generator.generate();

    }

}
