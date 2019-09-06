乐优商城是一个全品类的电商购物网站（B2C）。
用户可以在线购买商品，加入购物车，下单，秒杀商品
可以品论已购买商品
管理员可以在后台管理商品的上下架，促销活动
管理员可以监控商品销售状况
客服可以在后台处理退款操作

乐优商城笔记：https://blog.csdn.net/aa6272438/article/details/89304763
商品分类的帮助文档：http://localhost:8081/swagger-ui.html
Eureka注册中心：http://localhost:10086/
elasticsearch:http://127.0.0.1:9200/
kibana界面:http://127.0.0.1:5601/

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
       
2.解决报错java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present
    原因：今天在运行程序的时候，一直报“java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present”的错误，
    代码之前一直没有动过，唯一的改变的就是之前用的是jdk8，昨天卸载了jdk8，重装了jdk12.
    百度原因，发现是因为用了jdk12的缘故。因为JAXB-API是java ee的一部分，在jdk12中没有在默认的类路径中。从jdk9开始java引入了模块的概念，
    可以使用模块命令--add-modles java.xml.bind引入jaxb-api。也可以选择另一种解决方法，在maven里面加入下面依赖，可以解决这个问题：
  解决方法：
    <dependency>
        <groupId>javax.xml.bind</groupId>
        <artifactId>jaxb-api</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>com.sun.xml.bind</groupId>
        <artifactId>jaxb-impl</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jaxb</groupId>
        <artifactId>jaxb-runtime</artifactId>
        <version>2.3.0</version>
    </dependency>
    <dependency>
        <groupId>javax.activation</groupId>
        <artifactId>activation</artifactId>
        <version>1.1.1</version>
    </dependency>

3.@EnableDiscoveryClient注解和@EnableEurekaClient注解的相同点与不同点
    在启动类上添加注解@EnableDiscoveryClient 或@EnableEurekaClient并且
    加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上
    相同点：都是能够让注册中心能够发现，扫描到改服务
    不同点：@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心
    
4.启动Elasticsearch说明：
    1.先启动Elasticsearch，在安装目录的bin文件夹中启动elasticsearch.bat（以管理员身份运行）即可。
      这里需要注意一点就是，如果项目中需要用到分词器的话 要先将分词器的安装包解压后放在Elasticsearch目录的plugins目录下，然后重启Elasticsearch即可。 
    2.再启动kibana，在安装目录的bin文件夹中启动kibana.bat（以管理员身份运行）即可。
