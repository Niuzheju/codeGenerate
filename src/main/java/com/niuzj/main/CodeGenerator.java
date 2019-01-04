package com.niuzj.main;

import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    private String model;

    private String rootPath;

    private String rootPackagePath;

    private String controllerPath;

    private String jspPath;

    private Map<String, String> data = new HashMap<>();

    public CodeGenerator() {
    }

    public CodeGenerator(String model, String rootPath, String rootPackagePath, String controllerPath, String jspPath) {
        this.model = model;
        this.rootPath = rootPath;
        this.rootPackagePath = rootPackagePath;
        this.controllerPath = controllerPath;
        this.jspPath = jspPath;
    }

    public void init(){

        data.put(Constant.CONTROLLER_PATH, controllerPath);
        data.put(Constant.JSP_PATH, jspPath);

        //model
        data.put(Constant.FULL_MODEL_CLASS, model);
        String model = this.model.substring(this.model.lastIndexOf(".") + 1);
        data.put(Constant.MODEL, model);
        data.put(Constant.S_MODEL, firstToLower(model));

        //query
        String queryPackage = rootPackagePath + ".query";
        data.put(Constant.QUERY_PACKAGE, queryPackage);
        String queryClass = model + "Query";
        data.put(Constant.QUERY_CLASS, queryClass);
        data.put(Constant.FULL_QUERY_CLASS, queryPackage + "." +  queryClass);
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
        String servicePackage =  rootPackagePath + ".service";
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





    }

    private String firstToLower(String str){
        return ((char)(str.charAt(0) + 32)) + str.substring(1);
    }





}
