## 公交安全管理系统

### 一、概述

##### 项目目标

设计开发一个可用的、具有前台交互界面的数据库管理系统。要求基于题目给予的基本要求，跟随数据库系统的生命周期，较为系统地开展数据库设计，并完成交互界面的前后端开发。

##### 数据库有关语义

1. 公交公司有若干个车队，每个车队下有若干条线路；

2. 公交公司有若干辆汽车，每辆车属于一条线路；

3. 每个车队有一名队长，他只有管理工作，不开车；

4. 每条线路有若干名司机，其中有一名路队长，除开车外，还承担管理工作；每名司机只在一条线路上开车；

5.  司机开车时会产生违章，包含：闯红灯、未礼让斑马线、压线、违章停车等；
   
6. 队长、路队长负责将司机的违章信息输入到系统，包含：司机、车辆、车队、线路、站点、时间、违章等。

##### 交互平台基本要求

开发一个公交安全管理系统来对数据库进行访问， 可以使用Java、 Python、 C等集成开发环境。
系统实现功能如下：

1. 录入司机基本信息， 如工号、 姓名、 性别等；
2. 录入汽车基本信息， 如车牌号、 座数等；
3.  录入司机的违章信息；
4.  查询某个车队下的司机基本信息；
5.  查询某名司机在某个时间段的违章详细信息；
6. 查询某个车队在某个时间段的违章统计信息， 如： 2次闯红灯、 4次未礼让斑马线等。  



#### 二、程序开发环境及应用环境

1. 操作系统: windows 10

2. 编程语言: sql，java

3. 开发工具: ERwin，Mysql，IntelliJ IDEA，Excel

   

#### 三、数据库设计流程

##### 1. 需求分析（系统数据和功能）

- 数据流图

此处只列出作业要求中的查询，实际对线路车队等还可考虑的需求较多。后续使表能实现各类需求，前端接口覆盖作业要求中的需求，若实际情况中后续需满足新的需求，添加前端接口即可。

- 数据字典

##### 2. 概念结构设计（E-R图设计）

使用ERwin设计，画出ER图。

##### 3. 逻辑结构设计（E-R图转换为关系模型）

- 关系模型

将er图转换为关系模式，分析每个关系模式的表名、列名、列类型、主码、外码和其他约束，并进行范式分析。

- 数据库中建表

建表时注意符合关系完整性规则，设置非空、主码、外码、用户定义的约束，其中外码设置级联删除修改。

- 建立索引

对现实中查询次数最多的违章记录表按司机工号升序建立普通索引。

- 创建视图和数据库角色

对司机、路队长、队长三种身份的权限进行分析，创建视图和数据库角色。将对应视图的权限授予角色，后期只需将角色授予用户，用户即拥有对应的所以权限。



#### 四、前端界面展示

注：测试集从西安交通网筛选，通过csv样式导入

- 查询功能

  ![image-20220103210713267](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103210713267.png)

  ![image-20220103210901873](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103210901873.png)

  

- 录入功能

  ![image-20220103211043091](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103211043091.png)

  



#### 五、应用程序设计中遇到的问题及解决方法

- 数据库设计中的问题。

  设计中出现的问题主要围绕外码发生。

  1. 有两张表互相存在外码的情况

     初始设计中，司机表中的线路号是线路表中线路号的外码，线路表中的路队长工号是司机表中工号的外码。导致两张表在创建和添加数据时互相限制，很难处理，且这种问题也是应该避免的。后续将司机表中的线路这一列删去，添加了司机线路表，用于存储司机与线路的对应关系，解决了以上问题。

     同时在检查需求时发现，队长只有管理职能，不开车，所以此处司机表本就不应该包含线路号。

  2. 有几张表循环存在外码的情况

     初始设计中，车队表中有司机表的外码，线路表中有车队表的外码，司机表中有线路表的外码，构成了循环。用以上同样的方法解决了这一问题。

  3. 司机线路表的问题

     ![image-20220103194919416](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103194919416.png)

     为解决上述问题而新建了司机线路表。此表的两列都为外码。

     理论上外码不能是码，此表依赖为a->b，主码也不能为（a，b）。但实际开发中，这些限制都是自己添加的，数据库开发工具并不限制，sql语句都可以运行成功。此处设计时将（a，b）设为主码。

     

- 程序设计中的问题。

  1. 赋予用户权限不生效

     通过sql语句查看角色权限正常，但在前端用户调用时不生效。查询资料发现将角色赋予用户后初始需要启用角色权限。![image-20220103200625999](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103200625999.png)

     

  2. 视图中包含多个表时无法对视图进行插入或修改

     以队长为例，创建了队长_司机视图，该视图包含了该队长车队下的司机信息。而由于司机信息由司机表和司机线路表构成，视图中包含两个表的信息，可以查询但不能插入或修改。

     后续将插入时插入的司机只能为该队长车队下的司机这一限制在java中实现。![image-20220103201828020](D:\_a-study\study\markdown\数据库系统实验报告.assets\image-20220103201828020.png)

     

  3. Excel数据复制输入不满足数据库DATETIME格式。

     前端测试输入时从Excel数据复制时间数据发现数据库报错格式不对，后续检查发现Excel单元格格式默认的不满足数据库DATETIME格式，改为手打输入。

     

#### 六、总结

- 收获

  对数据库设计和运用数据库的工程开发有了一个整体的体验。虽然课程中理论的过程给的很清晰，但在实际开发中遇到了各种问题。除了报告中列出的主要问题，开发中遇到的语句错误，语句不生效等小问题很多。也在开发的过程中发现先前设计的错误和不恰当的地方，反复修改了多次。在实践后对课程内容有了更深的理解，也发现了一些之前漏听或没有理解的知识点，查漏补缺了。

  实验中提到的需求离实际生活中的需求还有一定距离，在开发时强烈感觉到数据库设计时需要考虑很多，也需要有条理地考虑，一步步推进，尽量减少大面积的返工。特别是在需求分析时一定要反复检查，本次实验中因为刚开始没有注意到队长只有管理职能，不开车，重新设计了部分数据库。

  同时在开发前端时，也学习了在java中嵌入sql语句的方法，对整个开发流程有了了解。

  

#### 七、附录：建立数据库和应用程序的主要代码

1. 数据库

   ```sql
   -- 建表
   DROP DATABASE PUBLICTRAFFICSYSTEM;
   CREATE DATABASE PUBLICTRAFFICSYSTEM
   CHARACTER SET utf8 COLLATE utf8_general_ci;
   USE PUBLICTRAFFICSYSTEM;
   
   CREATE TABLE 司机表(
     工号		CHAR(6),
     姓名		CHAR(3) NOT NULL,
     性别		CHAR(1) NOT NULL,
     出生年份	YEAR NOT NULL,
     职位		CHAR(3) NOT NULL,
     PRIMARY KEY (工号),
     CONSTRAINT 性别 CHECK (性别 IN('男','女')),
     CONSTRAINT 出生年份 CHECK (出生年份 BETWEEN 1960 AND 2003),
     CONSTRAINT 职位 CHECK (职位 IN('司机','队长','路队长'))
   );
   
   CREATE TABLE 车队表 (
     车队号	INT,
     队长工号	CHAR(6) NOT NULL,
     PRIMARY KEY (车队号),
     FOREIGN KEY (队长工号) REFERENCES 司机表(工号),
     CONSTRAINT 车队号 CHECK (车队号 BETWEEN 1 AND 999)
   );
   
   CREATE TABLE 线路表 (
     线路号		INT,
     线路名		CHAR(20) NOT NULL,
     车队号		INT NOT NULL,
     路队长工号	CHAR(6) NOT NULL,
     PRIMARY KEY (线路号),
     FOREIGN KEY (车队号) REFERENCES 车队表(车队号),
     FOREIGN KEY (路队长工号) REFERENCES 司机表(工号),
     CONSTRAINT 线路表_线路号 CHECK (线路号 BETWEEN 1 AND 999)
   );
   
   CREATE TABLE 司机线路表 (
     工号		CHAR(6),
     线路号	INT NOT NULL,
     PRIMARY KEY (工号,线路号),
     FOREIGN KEY(工号) REFERENCES 司机表(工号) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY(线路号) REFERENCES 线路表(线路号) ON DELETE CASCADE ON UPDATE CASCADE
     
   );
   
   CREATE TABLE 站点表 (
     站点名	CHAR(20),
     站点位置	CHAR(40) NOT NULL,
     PRIMARY KEY (站点名)
   );
   
   CREATE TABLE 线路车站表 (
     线路号	INT,
     站点编号	INT NOT NULL,
     站点名	CHAR(20) NOT NULL,
     PRIMARY KEY (线路号,站点编号),
     FOREIGN KEY (站点名) REFERENCES 站点表(站点名) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT 线路车站表_线路号 CHECK (线路号 BETWEEN 1 AND 999),
     CONSTRAINT 站点编号 CHECK (站点编号 BETWEEN 1 AND 50)
   );
   
   CREATE TABLE 汽车表 (
     车牌号	CHAR(8),
     车龄		INT NOT NULL,
     车型		CHAR(5) NOT NULL,
     座位数	INT NOT NULL,
     年检月份	INT NOT NULL,
     线路号	INT NOT NULL,
     PRIMARY KEY(车牌号),
     FOREIGN KEY(线路号) REFERENCES 线路表(线路号) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT 车龄 CHECK (车龄 BETWEEN 0 AND 20),
     CONSTRAINT 座位数 CHECK (座位数 BETWEEN 10 AND 40),
     CONSTRAINT 年检月份 CHECK (年检月份 BETWEEN 1 AND 12)
   );
   
   CREATE TABLE 处罚表(
     违章级别	INT,
     处罚		CHAR(20) NOT NULL,
     PRIMARY KEY(违章级别),
     CONSTRAINT 违章级别 CHECK (违章级别 BETWEEN 1 AND 12)
   );
   
   CREATE TABLE 违章表(
     违章名	CHAR(20),
     违章级别	INT NOT NULL,
     PRIMARY KEY(违章名),
     FOREIGN KEY(违章级别) REFERENCES 处罚表(违章级别)
   );
   
   CREATE TABLE 违章记录表(
     违章编号			CHAR(8),
     违章司机工号		CHAR(6) NOT NULL,
     违章汽车车牌号	CHAR(8) NOT NULL,
     违章名			CHAR(20) NOT NULL,
     违章时间			DATETIME NOT NULL,
     违章地点			CHAR(20) NOT NULL,
     PRIMARY KEY(违章编号),
     FOREIGN KEY(违章司机工号) REFERENCES 司机表(工号) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY(违章汽车车牌号) REFERENCES 汽车表(车牌号) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY(违章名) REFERENCES 违章表(违章名) ON DELETE CASCADE ON UPDATE CASCADE
   );
   ```

   ```sql
   -- 创建索引 
   CREATE INDEX 司机违章记录 ON 违章记录表(违章司机工号 ASC);
   ```

   ```sql
   -- 创建视图
   CREATE VIEW 司机_司机表 AS
     SELECT 司机表.*,线路号
     FROM 司机表,司机线路表
     WHERE  司机表.工号 = 司机线路表.工号 AND
   		 司机表.工号 = left(user(), 6);
     
   CREATE VIEW 司机_违章记录表 AS
     SELECT *
     FROM 违章记录表
     WHERE 违章司机工号 = left(user(), 6);
   
   CREATE VIEW 路队长_司机表 AS
     SELECT 司机表.*,司机线路表.线路号
     FROM 司机表,司机线路表,线路表
     WHERE 司机表.工号 = 司机线路表.工号 AND
   		司机线路表.线路号 = 线路表.线路号 AND
   		路队长工号 = left(user(), 6);
     
   CREATE VIEW 路队长_汽车表 AS
     SELECT 汽车表.*
     FROM 汽车表,线路表
     WHERE 汽车表.线路号 = 线路表.线路号 AND
   		路队长工号 = left(user(), 6);
     
   CREATE VIEW 路队长_违章记录表 AS
     SELECT 违章记录表.*
     FROM 违章记录表,司机线路表,线路表
     WHERE 违章司机工号 = 司机线路表.工号 AND
   		司机线路表.线路号 = 线路表.线路号 AND
           路队长工号 = left(user(), 6);
     
   CREATE VIEW 队长_司机表 AS
   SELECT 司机表.*,司机线路表.线路号
   FROM 司机表,司机线路表,线路表,车队表
   WHERE 司机表.工号 = 司机线路表.工号 AND
   	司机线路表.线路号 = 线路表.线路号 AND
   	线路表.车队号 = 车队表.车队号 AND
   	队长工号 = left(user(), 6);
     
   CREATE VIEW 队长_汽车表 AS
     SELECT 汽车表.*
     FROM 汽车表,线路表,车队表
     WHERE 汽车表.线路号 = 线路表.线路号 AND
   		线路表.车队号 = 车队表.车队号 AND
           队长工号 = left(user(), 6);
   
   CREATE VIEW 队长_违章记录表 AS
     SELECT 违章记录表.*
     FROM 违章记录表,司机线路表,线路表,车队表
     WHERE 违章司机工号 = 司机线路表.工号 AND
   		司机线路表.线路号 = 线路表.线路号 AND
           线路表.车队号 = 车队表.车队号 AND
           队长工号 = left(user(), 6);
   ```

   ```sql
   -- 创建角色
   DROP ROLE 司机;
   DROP ROLE 路队长;
   DROP ROLE 队长;
   CREATE ROLE 司机;
   CREATE ROLE 路队长;
   CREATE ROLE 队长;
   ```

   ```sql
   -- 授权权限给角色
   GRANT SELECT ON TABLE 司机_司机表 TO 司机;
   GRANT SELECT ON TABLE 司机_违章记录表 TO 司机;
   GRANT SELECT ON TABLE 路队长_司机表 TO 路队长;
   GRANT SELECT ON TABLE 路队长_汽车表 TO 路队长;
   GRANT SELECT ON TABLE 路队长_违章记录表 TO 路队长;
   GRANT SELECT ON TABLE 队长_司机表 TO 队长;
   GRANT SELECT ON TABLE 队长_汽车表 TO 队长;
   GRANT SELECT ON TABLE 队长_违章记录表 TO 队长;
   GRANT UPDATE,INSERT,DELETE ON TABLE 司机表 TO 队长;
   GRANT UPDATE,INSERT,DELETE ON TABLE 司机线路表 TO 队长;
   GRANT UPDATE,INSERT,DELETE ON TABLE 汽车表 TO 队长;
   GRANT UPDATE,INSERT ON TABLE 违章记录表 TO 队长;
   ```

   

2. 应用程序

```java
// 连接数据库函数    
public Login(String USER,String PASS) throws SQLException,ClassNotFoundException{
        this.USER = USER;
        this.PASS = PASS;
        Class.forName(JDBC_DRIVER);
        // 连接数据库
        System.out.println("正在连接数据库...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("连接成功");
}
```

```java
// 查询函数
public ResultSet searchMysql(String sql) throws SQLException {
        System.out.println("正在查询...");
        stmt = conn.createStatement();
        // 结果存在rs返回
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
}
```

