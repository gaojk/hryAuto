功能结构图:
   hryAuto
    * hryrun
        * 测试运行(后台)
        * 测试类维护
        * 测试方法维护
        * 参数化
        * 监听测试报告
    * hryweb
        * 维护测试环境
        * 维护待测服务
        * 维护待测接口
        * 维护测试用例
        * 定制测试(远期)
        * 定制测试报告(远期)

主要使用技术:
    JDK 1.8
    Springboot
    Mybatis 持久层方案
    alibaba druid 连接池
    pagehelper SpringBoot starter 1.1.2 分页插件
    httpclient 私人共享库github:com.arronlong
    testng

快速启动项目:
    1.jdk1.8或以上
    2.安装idea,必要的设置(设置encoding=utf8,安装lombok插件)
    3.安装git,推荐新版本
      克隆项目到本地:git clone https://github.com/hryOrgan/hryAuto.git
    4.安装maven(非必需,idea自带maven插件)
    5.idea打开本地项目,将hryrun和hryweb设置为module,按maven包导入,hryweb项目需要在Facets标记Spring和Web属性
    6.maven依赖下载,可配置aliyun镜像
        <mirror>
            <id>nexus-aliyun</id>
            <mirrorOf>*</mirrorOf>
            <name>Nexus aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
        </mirror>
    7.module-hryrun:testng框架,直接在cases类中右键run,或者run testng.xml
    8.module-hryweb:springboot+mybatis框架,在Application文件上右键run即可


##模块:hryrun项目结构图

|__pom.xml-------------------------------------------------------maven核心配置文件
|__src-----------------------------------------------------------
|  |__main-------------------------------------------------------
|  |  |__java----------------------------------------------------
|  |  |  |__com--------------------------------------------------
|  |  |  |  |__haier---------------------------------------------
|  |  |  |  |  |__anno-------------------------------------------自定义注解类
|  |  |  |  |  |  |__Iuri.java-----------------------------------注解类:用于测试方法上,定义接口地址
|  |  |  |  |  |  |__ServiceKey.java-----------------------------注解类:用于测试类上,定义服务
|  |  |  |  |  |__cases------------------------------------------测试实现类
|  |  |  |  |  |  |__P2pTest.java--------------------------------
|  |  |  |  |  |  |__UserTest.java-------------------------------
|  |  |  |  |  |__enums------------------------------------------枚举类
|  |  |  |  |  |  |__AssertTypeEnum.java-------------------------
|  |  |  |  |  |  |__ContentTypeEnum.java------------------------
|  |  |  |  |  |  |__EnvEnum.java--------------------------------
|  |  |  |  |  |  |__HttpTypeEnum.java---------------------------
|  |  |  |  |  |  |__RequestMethodTypeEnum.java------------------
|  |  |  |  |  |  |__RequestParamTypeEnum.java-------------------
|  |  |  |  |  |  |__ResponseTypeEnum.java-----------------------
|  |  |  |  |  |__mapper-----------------------------------------mybatis映射文件
|  |  |  |  |  |  |__QueryAllVoMapper.java-----------------------
|  |  |  |  |  |  |__QueryAllVoMapper.xml------------------------
|  |  |  |  |  |  |__TcaseCustomMapper.java----------------------
|  |  |  |  |  |  |__TcaseCustomMapper.xml-----------------------
|  |  |  |  |  |  |__TcaseMapper.java----------------------------
|  |  |  |  |  |  |__TcaseMapper.xml-----------------------------
|  |  |  |  |  |  |__TenvdetailCustomMapper.java-----------------
|  |  |  |  |  |  |__TenvdetailCustomMapper.xml------------------
|  |  |  |  |  |  |__TenvdetailMapper.java-----------------------
|  |  |  |  |  |  |__TenvdetailMapper.xml------------------------
|  |  |  |  |  |  |__TenvMapper.java-----------------------------
|  |  |  |  |  |  |__TenvMapper.xml------------------------------
|  |  |  |  |  |  |__TiMapper.java-------------------------------
|  |  |  |  |  |  |__TiMapper.xml--------------------------------
|  |  |  |  |  |  |__TserviceMapper.java-------------------------
|  |  |  |  |  |  |__TserviceMapper.xml--------------------------
|  |  |  |  |  |__mytest-----------------------------------------
|  |  |  |  |  |  |__MyTest.java---------------------------------
|  |  |  |  |  |  |__MyTest2.java--------------------------------
|  |  |  |  |  |__po---------------------------------------------pojo,封装数据库表对象
|  |  |  |  |  |  |__QueryAllVo.java-----------------------------
|  |  |  |  |  |  |__Tcase.java----------------------------------
|  |  |  |  |  |  |__TcaseCustom.java----------------------------
|  |  |  |  |  |  |__TcaseExample.java---------------------------
|  |  |  |  |  |  |__Tenv.java-----------------------------------
|  |  |  |  |  |  |__Tenvdetail.java-----------------------------
|  |  |  |  |  |  |__TenvdetailCustom.java-----------------------
|  |  |  |  |  |  |__TenvdetailExample.java----------------------
|  |  |  |  |  |  |__TenvExample.java----------------------------
|  |  |  |  |  |  |__Ti.java-------------------------------------
|  |  |  |  |  |  |__TiExample.java------------------------------
|  |  |  |  |  |  |__Tservice.java-------------------------------
|  |  |  |  |  |  |__TserviceExample.java------------------------
|  |  |  |  |  |__util-------------------------------------------工具类
|  |  |  |  |  |  |__AssertUtil.java-----------------------------
|  |  |  |  |  |  |__DataProviderUtil.java-----------------------
|  |  |  |  |  |  |__DBUtil.java---------------------------------
|  |  |  |  |  |  |__HryHttpClientUtil.java----------------------
|  |  |  |  |  |  |__JsonUtil.java-------------------------------
|  |  |  |  |  |  |__PropertiesUtil.java-------------------------
|  |  |__resources-----------------------------------------------配置文件
|  |  |  |__log4j.properties-------------------------------------log4j
|  |  |  |__mybatis-config.xml-----------------------------------mybatis
|  |  |  |__test.properties--------------------------------------配置测试环境
|  |  |  |__testng.xml-------------------------------------------testng执行

##模块:hryweb项目结构图
|__pom.xml-------------------------------------------------------maven配置
|__src-----------------------------------------------------------
|  |__main-------------------------------------------------------
|  |  |__java----------------------------------------------------
|  |  |  |__com--------------------------------------------------
|  |  |  |  |__haier---------------------------------------------
|  |  |  |  |  |__Application.java-------------------------------springboot启动类,右键run即可
|  |  |  |  |  |__aspect-----------------------------------------spring aop
|  |  |  |  |  |__config-----------------------------------------配置类@Configuration
|  |  |  |  |  |  |__LoginConfig.java----------------------------登录拦截器配置
|  |  |  |  |  |__controllers------------------------------------控制器@Controller
|  |  |  |  |  |  |__ImportController.java-----------------------
|  |  |  |  |  |  |__TenvController.java-------------------------
|  |  |  |  |  |  |__TiController.java---------------------------
|  |  |  |  |  |  |__TserviceController.java---------------------
|  |  |  |  |  |  |__UserController.java-------------------------
|  |  |  |  |  |__enums------------------------------------------枚举类
|  |  |  |  |  |  |__AssertTypeEnum.java-------------------------
|  |  |  |  |  |  |__ContentTypeEnum.java------------------------
|  |  |  |  |  |  |__EnvEnum.java--------------------------------
|  |  |  |  |  |  |__HttpTypeEnum.java---------------------------
|  |  |  |  |  |  |__RequestMethodTypeEnum.java------------------
|  |  |  |  |  |  |__RequestParamTypeEnum.java-------------------
|  |  |  |  |  |  |__ResponseTypeEnum.java-----------------------
|  |  |  |  |  |  |__StatusCodeEnum.java-------------------------
|  |  |  |  |  |__exception--------------------------------------异常类
|  |  |  |  |  |  |__HryException.java---------------------------自定义异常类
|  |  |  |  |  |  |__HryExceptionHandle.java---------------------
|  |  |  |  |  |__interceptor------------------------------------拦截器
|  |  |  |  |  |  |__LoginInterceptor.java-----------------------登录拦截器
|  |  |  |  |  |__mapper-----------------------------------------mybatis mapper及mapper.xml
|  |  |  |  |  |  |__QueryAllVoMapper.java-----------------------
|  |  |  |  |  |  |__QueryAllVoMapper.xml------------------------
|  |  |  |  |  |  |__TcaseCustomMapper.java----------------------
|  |  |  |  |  |  |__TcaseCustomMapper.xml-----------------------
|  |  |  |  |  |  |__TcaseMapper.java----------------------------
|  |  |  |  |  |  |__TcaseMapper.xml-----------------------------
|  |  |  |  |  |  |__TenvdetailCustomMapper.java-----------------
|  |  |  |  |  |  |__TenvdetailCustomMapper.xml------------------
|  |  |  |  |  |  |__TenvdetailMapper.java-----------------------
|  |  |  |  |  |  |__TenvdetailMapper.xml------------------------
|  |  |  |  |  |  |__TenvMapper.java-----------------------------
|  |  |  |  |  |  |__TenvMapper.xml------------------------------
|  |  |  |  |  |  |__TiCustomMapper.java-------------------------
|  |  |  |  |  |  |__TiCustomMapper.xml--------------------------
|  |  |  |  |  |  |__TiMapper.java-------------------------------
|  |  |  |  |  |  |__TiMapper.xml--------------------------------
|  |  |  |  |  |  |__TserviceMapper.java-------------------------
|  |  |  |  |  |  |__TserviceMapper.xml--------------------------
|  |  |  |  |  |  |__UserMapper.java-----------------------------
|  |  |  |  |  |  |__UserMapper.xml------------------------------
|  |  |  |  |  |__po---------------------------------------------pojo
|  |  |  |  |  |  |__ImportInterfaceResult.java------------------
|  |  |  |  |  |  |__QueryAllVo.java-----------------------------
|  |  |  |  |  |  |__Tcase.java----------------------------------
|  |  |  |  |  |  |__TcaseCustom.java----------------------------
|  |  |  |  |  |  |__TcaseExample.java---------------------------
|  |  |  |  |  |  |__Tenv.java-----------------------------------
|  |  |  |  |  |  |__Tenvdetail.java-----------------------------
|  |  |  |  |  |  |__TenvdetailCustom.java-----------------------
|  |  |  |  |  |  |__TenvdetailExample.java----------------------
|  |  |  |  |  |  |__TenvExample.java----------------------------
|  |  |  |  |  |  |__Ti.java-------------------------------------
|  |  |  |  |  |  |__TiCustom.java-------------------------------
|  |  |  |  |  |  |__TiExample.java------------------------------
|  |  |  |  |  |  |__Tservice.java-------------------------------
|  |  |  |  |  |  |__TserviceExample.java------------------------
|  |  |  |  |  |  |__User.java-----------------------------------
|  |  |  |  |  |__response---------------------------------------返回体
|  |  |  |  |  |  |__Result.java---------------------------------自定义统一返回体
|  |  |  |  |  |__service----------------------------------------Service(Spring annotation @Service)
|  |  |  |  |  |  |__impl----------------------------------------
|  |  |  |  |  |  |  |__ImportServiceImpl.java-------------------
|  |  |  |  |  |  |  |__TenvServiceImpl.java---------------------
|  |  |  |  |  |  |  |__TiServiceImpl.java-----------------------
|  |  |  |  |  |  |  |__TserviceServiceImpl.java-----------------
|  |  |  |  |  |  |  |__UserServiceImpl.java---------------------
|  |  |  |  |  |  |__ImportService.java--------------------------
|  |  |  |  |  |  |__TcaseService.java---------------------------
|  |  |  |  |  |  |__TenvService.java----------------------------
|  |  |  |  |  |  |__TiService.java------------------------------
|  |  |  |  |  |  |__TserviceService.java------------------------
|  |  |  |  |  |  |__UserService.java----------------------------
|  |  |  |  |  |__testng-----------------------------------------testng
|  |  |  |  |  |  |__cases---------------------------------------测试类
|  |  |  |  |  |  |__factory-------------------------------------工厂类,工厂化提供测试类
|  |  |  |  |  |  |__listener------------------------------------监听器
|  |  |  |  |  |  |__xml-----------------------------------------testng.xml配置测试(No used)
|  |  |  |  |  |__util-------------------------------------------工具类
|  |  |  |  |  |  |__HryHttpClientUtil.java----------------------
|  |  |  |  |  |  |__ImportUtil.java-----------------------------
|  |  |  |  |  |  |__JsonUtil.java-------------------------------
|  |  |  |  |  |  |__ReflectUtil.java----------------------------
|  |  |  |  |  |  |__ResultUtil.java-----------------------------
|  |  |__resources-----------------------------------------------
|  |  |  |__application.yml--------------------------------------springboot 核心配置文件
|  |  |  |__static-----------------------------------------------静态资源目录