乐优商城是一个全品类的电商购物网站（B2C）。
用户可以在线购买商品，加入购物车，下单，秒杀商品
可以品论已购买商品
管理员可以在后台管理商品的上下架，促销活动
管理员可以监控商品销售状况
客服可以在后台处理退款操作

乐优商城笔记：https://blog.csdn.net/aa6272438/article/details/89304763
商品分类的帮助文档：http://localhost:8081/swagger-ui.html

总结：
1.解决MySQL 8.0java.sql.SQLException: Unknown system variable 'tx_isolation'异常
原因：从MySQL5.7升级到8.0之后报异常：SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@77e9cf18] was not registered fo……
解决方法：
 （1）把jdbc连接驱动升级下
 （2）驱动名称改为com.mysql.cj.jdbc.Driver
  (3)连接地址改为jdbc:mysql://127.0.0.1:3306/db_cms?useSSL=true&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8

