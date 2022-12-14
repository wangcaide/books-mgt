DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT(
    `ACCOUNT_ID` INT NOT NULL AUTO_INCREMENT  COMMENT '账户ID' ,
    `USERNAME` VARCHAR(255) NOT NULL   COMMENT '用户名(登陆名)' ,
    `PASSWORD` VARCHAR(255) NOT NULL   COMMENT '密码' ,
    `STATUS` VARCHAR(32) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ACCOUNT_ID)
)  COMMENT = '账户';


CREATE UNIQUE INDEX USERNAME_STATUS_UNIQUE ON ACCOUNT(USERNAME,STATUS);

DROP TABLE IF EXISTS PERSON;
CREATE TABLE PERSON(
    `PERSON_ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `FIRST_NAME` VARCHAR(255) NOT NULL   COMMENT '名字' ,
    `LAST_NAME` VARCHAR(255) NOT NULL   COMMENT '姓' ,
    `ID_TYPE` VARCHAR(32)    COMMENT '证件类型' ,
    `ID_VALUE` VARCHAR(255)    COMMENT '证件号' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(255)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (PERSON_ID)
)  COMMENT = '个人信息';

DROP TABLE IF EXISTS PERSON_ACCOUNT_RELATION;
CREATE TABLE PERSON_ACCOUNT_RELATION(
    `ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `PERSON_ID` INT NOT NULL   COMMENT '个人信息表ID' ,
    `ACCOUNT_ID` INT NOT NULL   COMMENT '账户id' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ID)
)  COMMENT = '个人信息账户关系表';

DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT(
    `CONTACT_ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    `CONTACT_TYPE` VARCHAR(32) NOT NULL   COMMENT '联系方式(手机/邮箱/地址)' ,
    `CONTACT_INFO` VARCHAR(255) NOT NULL   COMMENT '联系信息' ,
    `PERSON_ID` INT NOT NULL   COMMENT '个人信息ID' ,
    `IS_PRIMARY` VARCHAR(1) NOT NULL   COMMENT '首选联系方式，Y/N' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (CONTACT_ID)
)  COMMENT = '联系方式';


CREATE UNIQUE INDEX CONTACT_UNIQUE ON CONTACT(CONTACT_TYPE,CONTACT_INFO,STATUS);

DROP TABLE IF EXISTS ROLE;
CREATE TABLE ROLE(
    `ROLE_ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `ROLE_CODE` VARCHAR(255) NOT NULL   COMMENT '角色码' ,
    `ROLE_NAME` VARCHAR(255) NOT NULL   COMMENT '角色名称' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ROLE_ID)
)  COMMENT = '账户角色信息';


CREATE UNIQUE INDEX ROLE_CODE_UNIQUE ON ROLE(ROLE_CODE);

DROP TABLE IF EXISTS ACCOUNT_ROLE_RELATION;
CREATE TABLE ACCOUNT_ROLE_RELATION(
    `ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `ACCOUNT_ID` INT NOT NULL   COMMENT '账户ID' ,
    `ROLE_ID` INT NOT NULL   COMMENT '角色ID' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ID)
)  COMMENT = '账户角色关系';

DROP TABLE IF EXISTS RESOURCE;
CREATE TABLE RESOURCE(
    `RESOURCE_ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `RESOURCE_TYPE` VARCHAR(255) NOT NULL   COMMENT '资源类型，系统/菜单/按键/链接' ,
    `RESOURCE_URL_METHOD` VARCHAR(255)    COMMENT '资源链接对应的http方法，GET/POST...' ,
    `RESOURCE_URL` VARCHAR(255) NOT NULL   COMMENT '资源链接' ,
    `RESOURCE_NAME` VARCHAR(255) NOT NULL   COMMENT '资源名称' ,
    `RESOURCE_DESCRIPTION` VARCHAR(255)    COMMENT '资源描述' ,
    `STATUS` VARCHAR(255) NOT NULL   COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (RESOURCE_ID)
)  COMMENT = '资源(系统/菜单/按键/链接)';

DROP TABLE IF EXISTS ROLE_RESOURCE_RELATION;
CREATE TABLE ROLE_RESOURCE_RELATION(
    `ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    `ROLE_ID` INT NOT NULL   COMMENT '角色id' ,
    `RESOURCE_ID` INT NOT NULL   COMMENT '资源id' ,
    `STATUS` VARCHAR(255)    COMMENT '状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ID)
)  COMMENT = '角色资源关系';

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(
    `BOOK_ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `BOOK_ISBN` VARCHAR(255) NOT NULL   COMMENT '图书ISBN码' ,
    `BOOK_NAME` VARCHAR(255) NOT NULL   COMMENT '图书名称' ,
    `PUBLISHER` VARCHAR(255)    COMMENT '出版社' ,
    `BOOK_DESCRIPTION` VARCHAR(255)    COMMENT '图书描述' ,
    `TOTAL_NUM` INT NOT NULL   COMMENT '总库存' ,
    `BORROWED_NUM` INT NOT NULL   COMMENT '已借出数量' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (BOOK_ID)
)  COMMENT = '图书';


CREATE UNIQUE INDEX ISBN_UNIQUE ON BOOK(BOOK_ISBN);

DROP TABLE IF EXISTS BORROW_LOG;
CREATE TABLE BORROW_LOG(
    `ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `BOOK_ISBN` VARCHAR(255)    COMMENT '图书ISBN码' ,
    `BOOK_NAME` VARCHAR(255)    COMMENT '图书名称' ,
    `BORROW_BY` VARCHAR(255)    COMMENT '借阅人' ,
    `BORROW_TIME` DATETIME    COMMENT '借阅时间' ,
    `EXPIRATION_TIME` DATETIME    COMMENT '到期时间' ,
    `RETURN_TIME` DATETIME    COMMENT '归还时间' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人（操作人）' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ID)
)  COMMENT = '借阅记录';

DROP TABLE IF EXISTS OPERATION_LOG;
CREATE TABLE OPERATION_LOG(
    `ID` INT NOT NULL AUTO_INCREMENT  COMMENT 'ID' ,
    `DESCRIPTION` VARCHAR(255)    COMMENT '操作内容' ,
    `STATUS` VARCHAR(255)    COMMENT '执行状态' ,
    `CREATED_BY` VARCHAR(32)    COMMENT '创建人(操作人)' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` VARCHAR(32)    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (ID)
)  COMMENT = '操作日志';

