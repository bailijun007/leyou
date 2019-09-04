乐优商城是一个全品类的电商购物网站（B2C）。
用户可以在线购买商品，加入购物车，下单，秒杀商品
可以品论已购买商品
管理员可以在后台管理商品的上下架，促销活动
管理员可以监控商品销售状况
客服可以在后台处理退款操作

乐优商城笔记：https://blog.csdn.net/aa6272438/article/details/89304763
商品分类的帮助文档：http://localhost:8081/swagger-ui.html

总结：
1.解决Mysql8 报错：ERROR 1193 (HY000): Unknown system variable 'tx_isolation'
    原因：是因为系统安装的数据库版本太高，而项目中导入的jdbc 的jar包版本太低，两者不一致所导致的
    (1)在pom.xml文件中将MySQL Connector / J版本升级为8：
       <!-- MySQL Connector / J是MySQL的官方JDBC驱动程序。 -->
       <dependency>
                   <groupId>mysql</groupId>
                   <artifactId>mysql-connector-java</artifactId>
                   <version>8.0.15</version> //这里使用自己数据库版本   
       </dependency>
       （2）驱动名称改为com.mysql.cj.jdbc.Driver
       （3）连接地址改为jdbc:mysql://127.0.0.1:3306/数据库名?useSSL=true&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8
       例如：
              datasource:
                url: jdbc:mysql://127.0.0.1:3306/yun6?useSSL=true&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8
                username: root
                password: 12345678
                driver: com.mysql.cj.jdbc.Driver
       


