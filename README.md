## 图书管理系统


### 角色及在系统中拥有的权限功能（业务需求）

#### 系统管理员
* 角色授权 - 给普通用户授予管理员权限
#### 图书管理员
*  图书入库
*  图书报废（破损 遗失）
*  登记读者借书
*  登记读者还书
*  搜索图书
#### 读者
*  搜索图书

### 表设计（books-mgt/db）
![](https://github.com/wangcaide/books-mgt/db/账户体系关系.png)
![](https://github.com/wangcaide/books-mgt/db/图书管理关系.png)  
业务表
图书表
图书类型表
操作记录表

账户权限相关表

使用的框架
SpringBoot + Spring Mvc + Spring Security?Shiro + Spring Cache + Mybatis-Plus