[toc]



### 系统命令

##### linux

###### 更换repo：

更换repo源：`/etc/yum.repos.d`
           替换后：`yum makecache`

###### 查看端口占用

查看端口占用 `netstat -aon|findstr "port"`

关闭占用端口的进程：`taskkill /pid pid`            (+/F强制关闭)

###### 查看开放的端口

查看所有开放的端口：`netstat -ntpl`
开放端口：`firewall-cmd --add-port=123/tcp --permanent`
刷新：   firewall-cmd --reload
查看是否开启：firewall-cmd --query-port=123/tcp

###### 开启远程登录

开启远程密码登录：  
    切换到root：sudo -i
    开启ssh：vi /etc/ssh/sshd_config
    重新加载配置：systemctl restart sshd.service
    
关闭setlinux:
    临时关闭:setenforce 0
    配置文件关闭: /etc/selinux/config  SELINUX=enforcing改为SELINUX=disabled

###### 查看历史命令

```
#查看历史命令
histroy
#执行历史命令
!对应的编号
```

###### 安装jdk

```
#1.下载
https://blog.csdn.net/WNsshssm/article/details/84315519
wget http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz

tar -zvxf jkd
#配置环境变量
vim /etc/profile
#在最后添加
export JAVA_HOME=/usr/local/jdk1.8.0_181  #jdk安装目录
 
export JRE_HOME=${JAVA_HOME}/jre
 
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
 
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
 
export PATH=$PATH:${JAVA_PATH}
#刷新配置
source /etc/profile
```



##### windows

###### windows清除登录缓存

windows清除登录缓存：net use * /del /y

#####    bat

###### bat打开文件夹

```
start "" "G:\test\demo\src\main\resources\md"
```



### docker

###### 安装docker

安装docker：

```
yum update
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum install docker-ce
```



​    

###### 安装docker-compose

安装docker-compose：

```shell
sudo curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)"  -o /usr/local/bin/docker-compose
sudo mv /usr/local/bin/docker-compose /usr/bin/docker-compose
sudo chmod +x /usr/bin/docker-compose

#或者
yum -y install epel-release
yum -y install python-pip
pip install docker-compose

#或者
sudo curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```



  

###### docker-compose启动    

docker-compose启动：`docker-compose up -d`

拷贝文件：docker cp /opt/test.js testtomcat：/usr/local/tomcat/webapps/test/js

###### docker制作镜像

docker生成镜像：docker build -f (dockefile路径) .
              docker build -t (镜像名) .

###### 启动容器

​    启动mysql:

==这里需要提前在宿主机创建mysql的配置文件（my.cnf）==

```
docker run -p 3307:3306 --name mysql_C -v /home/docker/mysql_C/my.conf:/etc/mysql/my.conf -v /home/docker/mysql_C/logs:/logs -v /home/docker/mysql_C/data:/var/lib/mysql -v /home/docker/mysql/my.cnf:/etc/mysql/my.cnf  -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0
```

​    启动nginx:

```
docker run -p 80:80 --name nginx -v /home/docker/nginx/conf.d:/etc/nginx/conf.d -d nginx


#nginx配置文件：

user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for" "$upstream_addr"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    upstream tomcat {
        server 127.0.0.1:8080;
        server 127.0.0.1:8080;
    }

    server {
        listen 80;
        server_name localhost;

        location / {
            proxy_pass http://tomcat;
            proxy_redirect off;
            index index.html index.htm;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Real-Port $remote_port;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        }

        location /static/ {
            alias /usr/share/nginx/html/;
        }
    }

    include /etc/nginx/conf.d/*.conf;
}
```

​    启动redis:

==这里需要提前在宿主机创建redis配置文件(redis.conf)==

```
docker run -d -p 6379:6379 --name redis -v /home/docker/redis/data:/data -v /home/docker/redis/conf/redis.conf/redis.conf:/usr/local/etc/redis/redis.conf/redis.conf  redis redis-server /usr/local/etc/redis/redis.conf/redis.conf --appendonly yes
```



kafka

```
version: '3'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    container_name: zookeeper
    ports: 
    - 2181:2181
  kafka1: 
    image: wurstmeister/kafka
    container_name: kafka1
    ports: 
    - 9092:9092
    environment: 
    - KAFKA_BROKER_ID=0
    - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
    - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://122.112.199.65:9092
    - KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092
  kafka2:
    image: wurstmeister/kafka
    container_name: kafka2
    ports:
    - 9093:9092
    environment:
    - KAFKA_BROKER_ID=1
    - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
    - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://122.112.199.65:9093
    - KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092

```







###### 配置docker镜像

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://m06wffpa.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

###### 查看docker容器在本地运行的位置

```
docker inspect 容器名
# MergeDir 对应的就是本地路径
```

###### docker强制删除镜像

```
docker image rm -f 镜像id
```



### redis
redis设置密码:config set requirepass [密码]


### mysql
mysql开启远程登录(8.0版本之前):GRANT ALL PRIVILEGES ON *.* TO 'zjd'@'%' IDENTIFIED BY '123456';
刷新配置：flush privileges;

mysql8.0后开启远程连接：
CREATE USER 'zjd'@'%' IDENTIFIED BY '123456';
grant all on *.* to 'zjd'@'%';



mysql8.0配置文件
```# Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; version 2 of the License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
   
#
# The MySQL  Server configuration file.
#
# For explanations see
# http://dev.mysql.com/doc/mysql/en/server-system-variables.html
   
[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
#更改加密方式
default_authentication_plugin=mysql_native_password
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
   
# Custom config should go here
!includedir /etc/mysql/conf.d/

```

### Git

恢复删除的文件：git reset HEAD (文件全路径) -> git checkout (文件全路径)

将现有项目和github关联：

```
git init
git add .
git commit -m ‘提交说明’
git remote add origin 远程仓库地址
git push -u origin master
```



### Kafka

查看列表：--list
查看详情：-describe -topic [name]
删除 ：--delete --topic [name]
     彻底删除：登录zookeeper /opt/zookeeper/bin   zkCli.sh -server
	 查看所有注册topic：ls /brokers/topics  删除未标记的topic：rmr /brokers/topics/【topic name】
	 查看标记已删除topic： ls /admin/delete_topics 删除已标记删除的topic：rmr /admin/delete_topics/【topic name】

##### java

启动jar

```
java -jar -agentlib:jdwp=transport=dt_socket server=y suspend=n address=1001 -Dspring.profiles.active=sit /apib.jar
```

