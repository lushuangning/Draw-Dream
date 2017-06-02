package com.cuit;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class greenGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.cuit.drawdream.bean");
        //添加AppInfo实体类
        addAppInfoEntity(schema);
        addUserInfoEntity(schema);
        addAccountEntity(schema);
        addNewsDetailEntity(schema);
        addReplayEntity(schema);
        //在tabframework/src/main/java-gen文件夹下生成java对象、javaDAO、DAOSession和DAOMaster类
        new DaoGenerator().generateAll(schema, "E:\\Github\\drawDream\\Draw-Dream\\Mobile Software Design\\DrawDream\\app\\src\\main\\java-gen");
    }

    private static void addReplayEntity(Schema schema) {
        Entity replayEntity = schema.addEntity("ReplayEntity");
        replayEntity.addStringProperty("core_nede_id").notNull();
        replayEntity.addStringProperty("core_acco_id");
        replayEntity.addStringProperty("core_content");
        replayEntity.addStringProperty("core_date");
    }

    private static void addNewsDetailEntity(Schema schema) {
        Entity newsDetail = schema.addEntity("NewsDetail");
        newsDetail.addStringProperty("nede_id").notNull();
        newsDetail.addStringProperty("nede_title").notNull();
        newsDetail.addStringProperty("nede_author").notNull();
        newsDetail.addStringProperty("nede_time").notNull();
        newsDetail.addStringProperty("nede_content").notNull();
        newsDetail.addStringProperty("nede_img").notNull();
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
    private static void addUserInfoEntity(Schema schema) {
        Entity userInfo = schema.addEntity("UserInfoEntity");
        userInfo.addStringProperty("user_name").notNull();
        userInfo.addStringProperty("user_id").notNull();
        userInfo.addStringProperty("user_gander").notNull();
        userInfo.addStringProperty("user_phone").notNull();
        userInfo.addStringProperty("user_email").notNull();
        userInfo.addStringProperty("user_sign");
    }
    private static void addAccountEntity(Schema schema) {
        Entity account = schema.addEntity("AccountEntity");
        account.addStringProperty("account").notNull();
        account.addStringProperty("pwd").notNull();
        account.addStringProperty("user_id").notNull();
    }

}