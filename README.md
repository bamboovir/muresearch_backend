## MU Research Boost
---

## About Document

描述API的swagger元数据
http://localhost:8080/v2/api-docs
在线渲染swagger元数据
https://editor.swagger.io/
生成文档
http://localhost:8080/swagger-ui.html

## About Route

首页
GET / -> HTML 繁复的搜索页面

词云路由
GET /api/wordcloud/ -> {} 
POST /api/wordcloud/ {} -> {}

图路由
GET /api/graph/user/ -> {}
GET /api/graph/people/ -> {}
GET /api/graph/subject/ -> {}
GET /api/graph/publication/ -> {}

自动补全路由
POST /api/autocomplete/ string -> {} 
POST /api/autocomplete/people/ string -> {}
POST /api/autocomplete/user/ string -> {}
POST /api/autocomplete/publication/ string -> {}
POST /api/autocomplete/subject/ string -> {}

搜索路由
GET /search/ -> HTML 简洁的搜索页面
POST /search/ {} -> HTML 搜索结果
POST /search/people/ {} -> HTML
POST /search/user/ {} -> HTML
POST /search/publication/ {} -> HTML
POST /search/subject/ {} -> HTML

POST /api/search/ {} -> {}        通用搜索
POST /api/search/people/ {} -> {} 针对人的搜索
POST /api/search/user/ {} -> {} 针对用户的搜索
POST /api/search/publication/ {} -> {} 针对出版物的搜索
POST /api/search/subject/ {} -> {} 针对科目的搜索

People
 
GET /people/ -> HTML (首页) （获取最近命中最多的人） （搜索框）
GET /people/id/{id} -> HTML 个人页面
- 个人信息的展示
    getPeopleById
- 跳转到用户页面
    getPeopleById
    getPublicationByPeople
    if getPeopleById.isUser is True:
        goto getUserById(userId)
- 和这个人相关的？？？

User

GET /user/{username} -> HTML
GET /user/id/{id} -> HTML 

Subject 

GET /subject/{subject_name} -> HTML
GET /subject/id/{id} -> HTML
GET /api/subject/{id} -> {}

Publication

GET /publication/{id} -> HTML
GET /api/publication/{id} -> {}

Token

/login


## 将npm run build 生成的 dist 目录资源与Spring Boot整合

```
dist 目录的结构

C:.
│   index.html
│
└───static
    │   header.jpg
    │   header1.jpg
    │   Sierra.jpg
    │
    ├───css
    │       app.0a53381fac10c202bb80c5818f33fedc.css
    │       app.0a53381fac10c202bb80c5818f33fedc.css.map
    │
    └───js
            app.1473b254e1a5b6610703.js
            app.1473b254e1a5b6610703.js.map
            manifest.2ae2e69a05c33dfc65f8.js
            manifest.2ae2e69a05c33dfc65f8.js.map
            vendor.95bfc2f10c8581d94c9c.js
            vendor.95bfc2f10c8581d94c9c.js.map

```

## 在Spring Boot项目的资源目录使用这种呈现方式

```
C:.
│   application.properties
│
└───static
    │   api.html
    │   index.html
    │
    └───static
        │   header.jpg
        │   header1.jpg
        │
        ├───css
        │       app.0a53381fac10c202bb80c5818f33fedc.css
        │       app.0a53381fac10c202bb80c5818f33fedc.css.map
        │
        └───js
                app.1473b254e1a5b6610703.js
                app.1473b254e1a5b6610703.js.map
                manifest.2ae2e69a05c33dfc65f8.js
                manifest.2ae2e69a05c33dfc65f8.js.map
                vendor.95bfc2f10c8581d94c9c.js
                vendor.95bfc2f10c8581d94c9c.js.map

```

## 添加 HTTPS 支持
使用自己生成的证书

```bash
keytool -genkey -alias muresearchboost -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650
```

```
1.-storetype 指定密钥仓库类型 
2.-keyalg 生证书的算法名称，RSA是一种非对称加密算法 
3.-keysize 证书大小 
4.-keystore 生成的证书文件的存储路径 
5.-validity 证书的有效期
```

## Package image

```bash
# OS : Ubuntu 18.04 LTS
sudo snap install docker
sudo apt install maven
# maven 打包跳过测试
# mvn package -Dmaven.test.skip=true
sudo mvn package docker:build -Dmaven.test.skip=true
sudo docker images
# muresearch/muresearchboost   latest              d0a3d0749bf0        2 minutes ago       187MB
# openjdk                      8-jdk-alpine        c9a60ce9cfed        4 weeks ago         103MB
sudo docker run -p 8080:8080 -d muresearch/muresearchboost:latest
# Print log
sudo docker run -d -p 8080:8080 -t muresearch/muresearchboost:latest
# Guardian view output
sudo docker container logs <hash>
```

## 使用指定位置的配置文件

```bash
java -jar myjar.jar --spring.config.location=D:\wherever\application.properties
```
## Useful Link

```
https://githubuniverse.com/workshops/?utm_source=github&utm_medium=banner&utm_campaign=ww-dotcom-universe-20180919&utm_content=dash-workshops
https://spring.io/guides/gs/spring-boot-docker/
https://spring.io/guides/gs/spring-boot-docker/
https://docs.spring.io/spring-data/elasticsearch/docs/3.0.10.RELEASE/reference/html/
https://www.vojtechruzicka.com/documenting-spring-boot-rest-api-swagger-springfox/
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
https://github.com/apache/spark/blob/master/examples/src/main/java/org/apache/spark/examples/ml/JavaLDAExample.java
https://searchcode.com/codesearch/view/97662092/
https://zerogravitylabs.ca/lda-topic-modeling-spark-mllib/
https://github.com/eBay/Spark/blob/master/examples/src/main/java/org/apache/spark/examples/mllib/JavaLDAExample.java
https://blog.csdn.net/poised/article/details/50385796
http://spark.apache.org/docs/latest/mllib-clustering.html#latent-dirichlet-allocation-lda
https://www.ibm.com/developerworks/cn/opensource/os-cn-spark-practice6/index.html
https://my.oschina.net/woter/blog/1843755
https://docs.spring.io/spring-data/mongodb/docs/2.1.x-SNAPSHOT/reference/html/
https://docs.spring.io/spring-data/elasticsearch/docs/3.0.10.RELEASE/reference/html/
https://stackoverflow.com/questions/40286549/spring-boot-security-cors
https://stackoverflow.com/questions/30184764/aggregation-support-for-spring-data-elastic-search
```
