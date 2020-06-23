[toc]



### 系统命令

##### linux

###### 更换repo：

更换repo源：/etc/yum.repos.d
           替换后：yum makecache

###### 查看端口占用

查看端口占用 netstat -aon|findstr "port"

关闭占用端口的进程：taskkill /pid pid            (+/F强制关闭)

###### 查看开放的端口

查看所有开放的端口：netstat -ntpl
开放端口：firewall-cmd --add-port=123/tcp --permanent
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

##### windows

###### windows清除登录缓存

windows清除登录缓存：net use * /del /y
    
### docker

###### 安装docker

安装docker：
    yum update
    yum install -y yum-utils device-mapper-persistent-data lvm2
    yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    yum install docker-ce

###### 安装docker-compose

安装docker-compose：
    sudo curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)"  -o /usr/local/bin/docker-compose
    sudo mv /usr/local/bin/docker-compose /usr/bin/docker-compose
    sudo chmod +x /usr/bin/docker-compose

###### docker-compose启动    

docker-compose启动：docker-compose up -d        

拷贝文件：docker cp /opt/test.js testtomcat：/usr/local/tomcat/webapps/test/js

###### docker制作镜像

docker生成镜像：docker build -f (dockefile路径) .
              docker build -t (镜像名) .

###### 启动容器

​    启动mysql:docker run -p 3307:3306 --name mysql_C -v /home/docker/mysql_C/my.conf:/etc/mysql/my.conf -v /home/docker/mysql_C/logs:/logs -v /home/docker/mysql_C/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0
​    启动nginx:docker run -p 80:80 --name nginx -v /home/docker/nginx/conf.d:/etc/nginx/conf.d -d nginx
​    启动redis:docker run -p 6379:6379 --name redis -v /home/docker/redis/data:/data -v /home/docker/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf  -d redis redis-server /usr/local/etc/redis/redis.conf/redis.conf --appendonly yes



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





