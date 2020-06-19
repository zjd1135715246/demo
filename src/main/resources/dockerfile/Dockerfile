FROM java:8
#镜像维护人
MAINTAINER 666@qq.com
#为jar包起别名
ADD demo.jar /docker-test.jar
#暴露调试端口，容器内部
EXPOSE 2233
#下面的address和上面的EXPOSE一致
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=2233","-Dspring.profiles.active=sit","/docker-test.jar"]
