阅读提示，阅读文档前如果格式错乱，请先安装如下插件

(.\assets\1545273808465.png)

### 一、开发前准备事项

1. 开发前请先阅读jtraining/doc目录下的编码规范
2. 开发前请先jtraining/doc目录下设置好开发环境

### 二、生产环境打包方式

1. 完整部署形式(测试阶段使用)

- 更新并提交所有代码

(.\assets\1545272066412.png)

- 对jtraining主模块执行 mvn install命令 暂时忽略掉test功能，保证所有模块全部

(assets/1545272133238.png)

- 对jtraining-rest工程执行 mvn clean package appassembler:assemble	保证全部SUCCESS

(assets/1545272185059.png)

- 拷贝target/jtraining-rest目录到linux环境（拷贝前一定要刷新）

  可以先拷贝到桌面，在进行zip打包，之后上传到服务器

- 执行启动命令

  `sh jtraining &` 

- 查看日志

(assets/1545272421028.png)

  1. 单独部署方式(正式阶段使用)

### 三、Rest接口针对时间类型传至方案，DTO VO 通用 解决方案：

​    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")

### 四、分页功能添加事项

​    1.在Service层调用Mapper方法之前先调用PageHelper.offsetPage(offset, limit);
​    2.如果需要返回总记录数调用第一步返回对象的getTotal()方法即可

### 五、Swagger插件编写注意事项

​    1.参照养老系统

### 六、前端传值合法与非法判断注意事项

​    1.去掉繁琐的多个if判断，采用Hibernate validator方式校验

### 七、针对异常信息处理

​    1.所有业务功能都需要抛出自定义异常，之后再全局异常处理模块添加对具体异常的处理

### 八、关于业务代码中固定值处理

​    1.业务代码中不允许出现有固定值判断，都需要定义成枚举或常亮来处理

### 九、添加操作日志功能

​    1.执行jtraining/doc/db/operator_log.db脚本
​    2.在Controller方法上添加 @OperatorLog(module = "模块名称", methods = "具体模块内某个功能")

### 十、关于测试用例

​    1.所有的测试用例都写在jtraining-rest/src/test/java目录下
​    2.DAO、Service、Controller都需要有测试用例
​    3.所有的测试用例继承BaseJunitTest类

### 十一、Mysql语法转换H2语法[备忘]

​    1.去掉备注
​    2.修改int类型为INTEGER类型
​    3.DateTime类型转换为Timestamp
​    4....待补充

### 十二、接口权限

​        框架：接口权限采用shiro+jwt框架
​        使用要求：在需要权限控制的接口上添加@RequiresAuthentication注解;
​        原理：
​    
​    
​    