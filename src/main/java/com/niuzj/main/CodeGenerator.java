package com.niuzj.main;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    //实体类全类名
    private String model;

    //项目源码所在目录，main/java
    private String rootPath;

    //基础包, com.
    private String rootPackagePath;

    //controller访问路径
    private String controllerPath;

    //jsp访问路径
    private String jspPath;

    private String table;

    //mapper所在路径
    private String mapperPath;

    private Map<String, String> data = new HashMap<>();

    private Map<String, String[]> moduleKeyMap = new HashMap<>();

    public CodeGenerator() {

    }

    public CodeGenerator(String model, String rootPath, String mapperPath, String controllerPath, String jspPath, String table) {
        this.model = model;
        //获取顶层包结构
        this.rootPackagePath = rootPath.split("java\\\\")[1].replace("\\", ".");
        //获取java所在目录
        this.rootPath = rootPath.split("java\\\\")[0] + "java";
        this.controllerPath = controllerPath;
        this.jspPath = jspPath;
        this.table = table;
        this.mapperPath = mapperPath;
    }

    public void init() {

        data.put(Constant.CONTROLLER_PATH, controllerPath);
        data.put(Constant.JSP_PATH, jspPath);

        //model
        data.put(Constant.FULL_MODEL_CLASS, model);
        String model = this.model.substring(this.model.lastIndexOf(".") + 1);
        data.put(Constant.MODEL, model);
        data.put(Constant.S_MODEL, firstToLower(model));
//        rootPackagePath += "." + model.toLowerCase();

        //query
        String queryPackage = rootPackagePath + ".query";
        data.put(Constant.QUERY_PACKAGE, queryPackage);
        String queryClass = model + "Query";
        data.put(Constant.QUERY_CLASS, queryClass);
        data.put(Constant.FULL_QUERY_CLASS, queryPackage + "." + queryClass);
        data.put(Constant.QUERY_VAR, firstToLower(queryClass));

        //vo
        String voPackage = rootPackagePath + ".vo";
        data.put(Constant.VO_PACKAGE, voPackage);
        String voClass = model + "Vo";
        data.put(Constant.VO_CLASS, voClass);
        data.put(Constant.FULL_VO_CLASS, voPackage + "." + voClass);

        //dao
        String daoPackage = rootPackagePath + ".dao";
        data.put(Constant.DAO_PACKAGE, daoPackage);
        String daoClass = model + "Dao";
        data.put(Constant.DAO_CLASS, daoClass);
        data.put(Constant.FULL_DAO_CLASS, daoPackage + "." + daoClass);
        data.put(Constant.DAO_VAR, firstToLower(daoClass));

        //service
        String servicePackage = rootPackagePath + ".service";
        data.put(Constant.SERVICE_PACKAGE, servicePackage);
        String serviceClass = model + "Service";
        data.put(Constant.SERVICE_CLASS, serviceClass);
        data.put(Constant.FULL_SERVICE_CLASS, servicePackage + "." + serviceClass);
        data.put(Constant.SERVICE_VAR, firstToLower(serviceClass));

        //controller
        String controllerPackage = rootPackagePath + ".controller";
        data.put(Constant.CONTROLLER_PACKAGE, controllerPackage);
        data.put(Constant.CONTROLLER_CLASS, model + "Controller");
        System.out.println(data);

        //table
        data.put(Constant.TABLE, table);

        initModuleKeyMap();

    }

    //生成代码
    public void generate() throws IOException {
        URL url = CodeGenerator.class.getClassLoader().getResource("template");
        if (url == null || url.getFile() == null) {
            System.out.println("模板文件获取失败");
            return;
        }
        File file = new File(URLDecoder.decode(url.getFile(), "utf-8"));
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File file1 : files) {
                String s = readFileToString(file1);
                s = renderData(s);
                //生成文件
                File newFile = generateFile(file1);
                //写入内容
                writeToFile(newFile, s);
            }
        }

    }

    private void writeToFile(File newFile, String s) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(newFile);
            fileWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String firstToLower(String str) {
        return ((char) (str.charAt(0) + 32)) + str.substring(1);
    }

    private String readFileToString(File file) {
        FileReader fileReader = null;
        try {
            char[] chars = new char[1024];
            int len = -1;
            fileReader = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            while ((len = fileReader.read(chars)) != -1) {
                sb.append(new String(chars, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    private String renderData(String str) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            str = str.replace(entry.getKey(), entry.getValue());
        }
        return str;
    }

    private File generateFile(File file) throws IOException {
        String fileName = file.getName().split("\\.")[0];
        String fileSuffix = "." + file.getName().split("\\.")[1];
        String module = fileName.split("-")[0];
        String rootPath = "";
        if ("mapper".equals(module)) {
            File mapperRootPath = new File(this.mapperPath);
            if (!mapperRootPath.exists()) {
                if (!mapperRootPath.mkdirs()) {
                    System.out.println("创建mapper目录失败" + rootPath);
                    return null;
                }
            }
            rootPath = this.mapperPath + data.get(Constant.S_MODEL) + "-mapper" + fileSuffix;
            File mapperFile = new File(rootPath);
            if (!mapperFile.exists()) {
                if (!mapperFile.createNewFile()) {
                    System.out.println("mapper文件创建失败" + rootPath);
                }
            }
            return mapperFile;

        } else {
            rootPath = this.rootPath;
            String[] datas = moduleKeyMap.get(module);
            //包名
            String pac = datas[0];
            //类名
            String cls = datas[1];
            rootPath += "\\" + pac.replace(".", "\\");
            File pacDir = new File(rootPath);
            if (!pacDir.exists()) {
                boolean b = pacDir.mkdirs();
                if (!b) {
                    System.out.println("创建目录失败" + pacDir.getAbsolutePath());
                    return null;
                }
            }
            rootPath += "\\" + cls + fileSuffix;
            File sourceFile = new File(rootPath);
            if (!sourceFile.exists()) {
                if (!sourceFile.createNewFile()) {
                    System.out.println("创建文件失败" + rootPath);
                }
            }
            return sourceFile;
        }
    }

    private void initModuleKeyMap(){
        moduleKeyMap.put("controller", new String[]{data.get(Constant.CONTROLLER_PACKAGE), data.get(Constant.CONTROLLER_CLASS)});
        moduleKeyMap.put("service", new String[]{data.get(Constant.SERVICE_PACKAGE), data.get(Constant.SERVICE_CLASS)});
        moduleKeyMap.put("dao", new String[]{data.get(Constant.DAO_PACKAGE), data.get(Constant.DAO_CLASS)});
        moduleKeyMap.put("query", new String[]{data.get(Constant.QUERY_PACKAGE), data.get(Constant.QUERY_CLASS)});
        moduleKeyMap.put("vo", new String[]{data.get(Constant.VO_PACKAGE), data.get(Constant.VO_CLASS)});
    }

    public static void main(String[] args) {
//        generate();
    }


}
