package com.cuit;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class greenGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.electornics_meeting.bean");
        //添加AppInfo实体类
        addAppInfoEntity(schema);
        //在tabframework/src/main/java-gen文件夹下生成java对象、javaDAO、DAOSession和DAOMaster类
        new DaoGenerator().generateAll(schema, "D:\\Electronics_meeting\\app\\src\\main\\java-gen");
    }

    /**
     * @param schema 1.通过schema.addEntity("UserEntitty")添加一个实体对象,这相当于在数据库中创建了一个表,会以"UserEntitty"为表名,但是会将其转换成
     *               数据库表的命名规范,最后的表名为"USER_ENTITY"
     *               2.之后就是向表中添加字段,比如user.addStringProperty("name").notNull();表示向表中添加了一个"name"字段,类型为String,且不为空
     */
    private static void addAppInfoEntity(Schema schema) {
        Entity appInfo = schema.addEntity("AppInfoEntity");
        appInfo.addIdProperty().autoincrement();
        appInfo.addStringProperty("app_id").notNull();
        appInfo.addStringProperty("version").notNull();
        appInfo.addStringProperty("type").notNull();
        appInfo.addStringProperty("page_url");
        appInfo.addStringProperty("ui_type").notNull();
        appInfo.addStringProperty("logo");
    }
}