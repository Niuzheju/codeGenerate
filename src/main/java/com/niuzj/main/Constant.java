package com.niuzj.main;

public interface Constant {

    //实体

    /**
     * 实体类简单类名
     */
    String MODEL = "${model}";

    /**
     * 实体类全类名
     */
    String FULL_MODEL_CLASS = "${fullModelClass}";

    /**
     * 实体类第一个字母小写,如user
     */
    String S_MODEL = "${sModel}";

    //query

    /**
     * query类全包名
     */
    String QUERY_PACKAGE = "${queryPackage}";

    /**
     * 查询类全类名, 如com.tthouse.admin.user.query.UserQuery
     */
    String FULL_QUERY_CLASS = "${fullQueryClass}";


    /**
     * 查询类简单类名=实体类简单类名+Query, 如UserQuery
     */
    String QUERY_CLASS = "${queryClass}";

    /**
     * 查询类变量名,查询类简单类名第一个字母小写, 如userQuery
     */
    String QUERY_VAR = "${queryVar}";

    //vo

    /**
     * vo全包名
     */
    String VO_PACKAGE = "${voPackage}";

    /**
     * vo类全类名
     */
    String FULL_VO_CLASS = "${fullVoClass}";

    /**
     * vo类简单类名
     */
    String VO_CLASS = "${voClass}";

    /**
     * controller访问路径,以/开头
     */
    String CONTROLLER_PATH = "${controllerPath}";

    /**
     * jsp路径,以/开头
     */
    String JSP_PATH = "${jspPath}";



    //dao

    /**
     * dao类全包名
     */
    String DAO_PACKAGE = "${daoPackage}";

    /**
     * dao类全类名
     */
    String FULL_DAO_CLASS = "${fullDaoClass}";

    /**
     * dao类简单类名
     */
    String DAO_CLASS = "${daoClass}";

    /**
     * dao类变量名
     */
    String DAO_VAR = "${daoVar}";

    // service

    /**
     * service全包名
     */
    String SERVICE_PACKAGE = "${servicePackage}";

    /**
     * service全类名
     */
    String FULL_SERVICE_CLASS = "${fullServiceClass}";

    /**
     * service简单类名
     */
    String SERVICE_CLASS = "${serviceClass}";

    /**
     * service变量名
     */
    String SERVICE_VAR = "${serviceVar}";

    // controller

    /**
     * controller全包名
     */
    String CONTROLLER_PACKAGE = "${controllerPackage}";

    /**
     * controller简单类名
     */
    String CONTROLLER_CLASS = "${controllerClass}";

    /**
     * 表名
     */
    String TABLE = "${table}";

}
