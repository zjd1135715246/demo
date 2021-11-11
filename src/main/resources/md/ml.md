[toc]



### 系统命令

##### linux

###### 更换repo：

更换repo源：```

mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak 

wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo 

``/etc/yum.repos.d`

​           替换后：`yum makecache`

###### 查看端口占用

查看端口占用 `netstat -aon|findstr "port"`

关闭占用端口的进程：`taskkill /pid pid`            (+/F强制关闭)

lsof -i:[端口]

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



##### mac







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



######  修改镜像本地存储位置

```shell
cd /etc/systemd/system/multi-user.target.wants
vim docker.service
ExecStart=/usr/bin/dockerd --graph=/usr/data/docker

#加上    --graph=/usr/data/docker
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
docker run -p 80:80 --name nginx -v /home/docker/nginx/nginx.conf:/etc/nginx/nginx.conf -d nginx


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
#反向代理配置
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
    
 #反向代理文件
    autoindex on;# 显示目录
	autoindex_exact_size on;# 显示文件大小
	autoindex_localtime on;# 显示文件时间

	server {
		listen       1234 default_server;
		listen       [::]:123 default_server;
		server_name  _;
		root         /share/;

		location / {
		}

		error_page 404 /404.html;
			location = /40x.html {
		}

		error_page 500 502 503 504 /50x.html;
			location = /50x.html {
		}
	}
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

###### nifi

```
docker run -d -p 8080:8080 --name nifi apache/nifi
```

###### vsftp

```
docker pull fauria/vsftpd 

docker run -d -v /root/docker/ftp:/home/vsftpd \
-p 20:20 -p 21:21 -p 21100-21110:21100-21110 \
-e FTP_USER=zzz -e FTP_PASS=zzz \
-e PASV_ADDRESS=192.168.131.130 \
-e PASV_MIN_PORT=21100 -e PASV_MAX_PORT=21110 \
--name ftp --restart=always fauria/vsftpd


```

###### gitlab

```
docker pull gitlab/gitlab-ce
docker run -d  -p 1443:443 -p 180:80 -p 1222:22 --name gitlab  -v /usr/data/docker/gitlab/config:/etc/gitlab -v /usr/data/docker/gitlab/logs:/var/log/gitlab -v /usr/data/docker/gitlab/data:/var/opt/gitlab gitlab/gitlab-ce

vim /usr/data/docker/gitlab/config/gitlab.rb
	external_url 'http://localhost'
	

vim /usr/data/docker/gitlab/data/gitlab-rails/etc/gitlab.yml
	host: localhost
	port: 180
```

###### jenkins

```
docker pull jenkins/jenkins
docker run -d -p 10240:8080 -p 10241:50000 -v /usr/data/docker/jenkins:/var/jenkins_home -v /etc/localtime:/etc/localtime --name jenkins jenkins/jenkins

cd /usr/data/docker/jenkins
# sed -i 's/www.google.com/www.baidu.com/g' default.json
# sed -i 's/updates.jenkins-ci.org\/download/mirrors.tuna.tsinghua.edu.cn\/jenkins/g' default.json

```





```
docker run --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server

docker run --network=bridge --restart=always --name nacos -p 8848:8848 --env-file=/home/docker/nacos/env.list -v /home/docker/nacos/logs:/home/nacos/logs -v /home/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -d nacos/nacos-server:1.1.4

docker run --network=host \
--restart=always \
--name nacos-02 -p 8849:8848 \
--env-file=/home/docker/nacos2/env.list \
-v /home/docker/nacos2/logs:/home/nacos/logs \
-v /home/docker/nacos2/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-d nacos/nacos-server:1.1.4

docker run --network=host \
--restart=always \
--name nacos-03 -p 8850:8848 \
--env-file=/home/docker/nacos/env.list \
-v /home/docker/nacos/logs:/home/nacos/logs \
-v /home/docker/nacos3/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-d nacos/nacos-server:1.1.4
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

### java

```
java -jar -agentlib:jdwp=transport=dt_socket server=y suspend=n address=1001 -Dspring.profiles.active=sit /apib.jar

远程debug：-Xdebug -Xrunjdwp:transport=dt_socket,address=9527,server=y,suspend=n
```



### 安装node

```
#下载 tar包解压

创建软连接

ln -s /usr/data/node/bin/node /usr/local/bin

ln -s /usr/data/node/bin/npm /usr/local/bin

```





### other

```
import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

import java.text.SimpleDateFormat

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "com.jkd.common.entity;"
typeMapping = [
        (~/(?i)tinyint|smallint|mediumint/)      : "Integer",
        (~/(?i)int/)                             : "Long",
        (~/(?i)bool|bit/)                        : "Boolean",
        (~/(?i)float|double|decimal|real/)       : "Double",
        (~/(?i)decimal/)                         : "BigDecimal",
        (~/(?i)datetime|timestamp|date|time/)    : "Date",
        (~/(?i)blob|binary|bfile|clob|raw|image/): "InputStream",
        (~/(?i)/)                                : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    new File(dir, className + ".java").withPrintWriter { out -> generate(out, className,table.getName(), fields) }
}

def generate(out, className,tableName, fields) {
    out.println "package $packageName"
    out.println ""
    out.println "import lombok.*;"
    out.println "import com.baomidou.mybatisplus.annotation.IdType;"
    out.println "import com.baomidou.mybatisplus.annotation.TableId;"
    out.println "import com.baomidou.mybatisplus.annotation.TableField;"
    out.println "import com.baomidou.mybatisplus.annotation.TableName;"
    out.println ""
    Set types = new HashSet()
    fields.each() {
        types.add(it.type)
    }

    if (types.contains("Date")) {
        out.println "import java.util.Date;"
    }

    if (types.contains("InputStream")) {
        out.println "import java.io.InputStream;"
    }
    if(types.contains("BigDecimal")){
        out.println "import java.math.BigDecimal;"
    }
    out.println ""
    out.println "/**"
    out.println " *@author zzz"
    out.println " *@date "+ new SimpleDateFormat("YYYY-MM-dd").format(new Date())+" "
    out.println " */"
    out.println ""
    out.println "@Data"
    out.println "@ToString"
    out.println "@NoArgsConstructor"
    out.println "@AllArgsConstructor"
    out.println "@TableName(\"$tableName\")"
    out.println "public class $className {"
    out.println ""


    fields.each() {
        if (it.annos != "") out.println "  ${it.annos}"
        if("id".equals(it.name))out.println "  @TableId(\n" +
                "        value = \"id\",\n" +
                "        type = IdType.AUTO\n" +
                "  )"
        out.println "  private ${it.type} ${it.name};"
    }
    out.println ""
    out.println "//这里是为了方便mapper的sql编写"
    out.print "//"
    /*fields.each() {
        out.println ""
        out.println "  public ${it.type} get${it.name.capitalize()}() {"
        out.println "    return ${it.name};"
        out.println "  }"
        out.println ""
        out.println "  public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
        out.println "    this.${it.name} = ${it.name};"
        out.println "  }"
        out.println ""
    }*/
    fields.each() {
        out.print "${it.realName}" + ","
    }
    out.println ""
    out.print "//"
    fields.each() {
        out.print "#{"+ "${it.name}"+ "}" +","
    }

    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           realName : col.getName(),
                           name : javaName(col.getName(), false),
                           type : typeStr,
                           annos: "@TableField(value = \" "+col.getName()+"\")"]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}


```

###### 添加桌面快捷方式

```
[Desktop Entry]
Name=IntelliJ IDEA
Comment=IntelliJ IDEA
Exec=/usr/data/idea/ideaIU-2020.3.1/idea/bin/idea.sh
Icon=/usr/data/idea/ideaIU-2020.3.1/idea/bin/idea.png
Terminal=false
Type=Application
Categories=Developer;
##
sudo chmod +x idea.desktop
```

