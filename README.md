# lumweb

#### 北京邮电大学国际学院电子商务及法律小学期作业——2019年。



## 使用方法

这是一个网站系统，包含前台和后台以及数据库。

#### step 1

将项目下载到本地，并导入到eclipse中。

项目文件夹名需要限定为LumWeb

#### step 2

安装mysql，版本需要在8.1以上。用户名为root，密码为123456。

在mysql中新建数据库lumweb, 使用mysqldump语句或mysql workbench导入位于根目录下的lumweb.sql

#### step 3

安装tomcat，版本越新越好。

为eclipse中的项目配置tomcat server。

#### step 4

尝试运行项目，如果出现没有找到的包，请按照错误提示谷歌或百度下载包。

#### step 5

使用浏览器访问http://localhost:8080/LumWeb/index.html查看前台界面。

访问http://localhost:8080/LumWeb/admin/login.html 查看管理员界面。

默认管理员账号密码均为123456


## 技术说明

#### 前端

前端比较垃圾，没有使用网页模板和js模板引擎，完全使用jquery拼接界面（这种做法不推荐）

前端框架使用了bootstrap

分页插件使用了bootstrap-paginator

提示框插件使用了自己魔改的toast

密码均采用md5加盐

#### 后端

利用原生java servlet实现了RESTful API

序列化/反序列化使用fastjson包。

数据库连接使用了mysql-connector-java

数据库操作使用了commons-dbutils-1.7

数据库连接池使用了c3p0

后端分为四层，代码比较垃圾。

## 已知问题

管理员权限系统失效

产品state信息为半成品，只能为hot或ok

文章列表分类为半成品，只能为fina和tech

商品图片加载较慢

fastjson包可能存在安全漏洞