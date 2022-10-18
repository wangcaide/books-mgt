## 图书管理系统


### 角色及在系统中拥有的权限功能（业务需求）

#### 系统管理员
* 角色授权 - 给普通用户授予管理员权限 (todo 没有相关接口)
#### 图书管理员
*  图书入库
*  图书报废（破损 遗失）
*  登记读者借书
*  登记读者还书
*  搜索图书
#### 读者
*  搜索图书


### 表设计
#### 账户体系关系
![image](db/账户体系关系.gif)
#### 图书管理关系
![image](db/图书管理关系.gif)  


### 工程目录结构
```
books-mgt            //父工程
├── LICENSE
├── README.md
├── books-mgt-bom    //bom工程，jar包版本管理，为了方便父工程下多个子工程依赖的jar包的版本管理
│     └── pom.xml
├── books-mgt-web    //主工程
│     ├── pom.xml
│     └── src
│           ├── main
│           │     ├── java
│           │     └── resources
│           └── test
│               └── java
├── db               //数据库模型
│     ├── books-management-system.pdma.json   //PDManer 数据库建模定义文件
│     ├── books-mgt-mysql-ddl.sql
│     ├── 图书管理关系.png
│     └── 账户体系关系.png
└── pom.xml
```

### 工程技术框架选型
* SpringBoot 
* Spring Mvc 
* Spring Security 
* Spring Cache
* Mybatis-Plus
* Swagger
* Spring validation
* H2
#### 权限控制
权限控制是基于Spring Security做的针对(http method + url)为颗粒度进行权限控制
#### 操作日志
基于spring aop实现的操作日志，会将注解中配置的内容（带变量通配符）与实际变量值生成操作日志写入数据库操作日志表中<br>
具体代码实现详见com.github.wangcaide.common.log下代码
#### 统一异常处理
详见代码 com.github.wangcaide.common.exception.GlobalExceptionHandler
#### 统一返回值控制
详见代码 com.github.wangcaide.common.model.R
#### 基于环境的配置分离
* application.yaml
* application-dev.yaml
* application-prod.yaml
#### swagger 
选用的是knife4j，knife4j对swagger进行了一层封装，ui易用
#### 缓存
选用 Spring Cache，目前用的是内存缓存caffeine，可通过配置及依赖jar包切换其他的cache如redis
#### 校验
Spring validation
#### UI
由于周末有事，没有太多时间，只是把登陆页面写了。。。

### 快速开始
我本地的环境版本
* openjdk 11.0.15
* Maven 3.8.6
* git 2.33.0
* IntelliJ IDEA 2022.2.3
#### 启动服务
```
git clone https://github.com/wangcaide/books-mgt.git
cd books-mgt/books-mgt-bom
mvn install
cd ..
mvn install
cd books-mgt-web
mvn spring-boot:run
```
启动服务后，在浏览器上访问
```
http://localhost:8080/login.html
```
即可看到登陆页面

初始化数据中已添加了两个用户
* 18689778889/123456(图书管理员) 
* 18689778888/123456(读者)

登陆成功后会跳转到swagger页面
```
http://localhost:8080/doc.html
```

### 图书管理员 调用接口流程
使用18689778889/123456 账户登陆
##### 图书入库

图书相关 -> 图书入库 -> 调试 -> 请求参数
```
{
"bookDescription": "测试图书1",
"bookIsbn": "1234567890",
"bookName": "测试图书1",
"publisher": "新华出版社",
"totalNum": 10
}
```
##### 查询图书列表
图书相关 -> 查询图书列表 -> 调试 -> 请求参数

可输入 isbn/bookName/pageNum/pageSize进行查询

##### 图书报废-遗失 库存总数量减1 
图书相关 -> 图书报废-遗失 库存总数量减1 -> 调试 -> 请求参数

isbn输入之前入库所填的1234567890

执行完成后可再调用查询图书列表接口，可看到图书总数已减1

##### 借书
图书借阅相关 -> 借书 -> 调试 -> 请求参数
```
borrowBy -> 18689778888 (读者username)
expirationTime -> 2022-10-30
isbn -> 1234567890
```
##### 归还图书
图书借阅相关 -> 归还图书 -> 调试 -> 请求参数
```
borrowBy -> 18689778888 (读者username)
isbn -> 1234567890
```
##### 退出登陆
http://localhost:8080/logout

### 读者 调用接口流程
使用18689778888/123456 账户登陆
##### 查询图书列表
图书相关 -> 查询图书列表 -> 调试 -> 请求参数

可输入 isbn/bookName/pageNum/pageSize进行查询

**使用读者账户登陆，调用其他接口会被拦截**

### 用户注册
用户注册接口是没有权限控制的接口

登陆到swagger页面后，在浏览器打开新的tab页输入http://localhost:8080/logout退出登陆

然后在 账户 -> 账户注册 -> 调试 -> 请求参数
```
{
  "firstName": "四",
  "lastName": "李",
  "password": "123456",
  "phoneNumber": "18689778887",
  "username": ""
}
```
即可注册一个读者角色的账户