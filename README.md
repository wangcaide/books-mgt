# books-mgt
图书管理系统需求
角色及在系统中拥有的权限功能

系统管理员
角色授权 - 给普通用户授予管理员权限

图书管理员
图书入库
{
"bookDescription": "测试图书1",
"bookIsbn": "1234567890",
"bookName": "测试图书1",
"publisher": "新华出版社",
"totalNum": 10
}
图书报废（破损 遗失）
isbn=1234567890
登记读者借书
isbn=1234567890
登记读者还书
isbn=1234567890
搜索图书
查看图书详情（介绍 库存数量 已借数量）

读者
搜索图书

业务表
图书表
图书类型表
操作记录表

账户权限相关表

使用的框架
SpringBoot + Spring Mvc + Spring Security?Shiro + Spring Cache + Mybatis-Plus