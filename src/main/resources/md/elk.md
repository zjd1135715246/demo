

```shell
docker pull elasticsearch:7.1.1
docker pull kibana:7.1.1
docker pull logstash:7.1.1
docker pull mobz/elasticsearch-head:5

#配置网络
docker network create esnet
#启动es
docker run -d --name es  -p 9200:9200 -p 9201:9300  --network esnet -e "discovery.type=single-node" elasticsearch:7.1.1
#进入es容器
docker exec -it es bash

#修改yml 配置跨域 /config/elasticsearch.yml 增加两行
http.cors.enabled: true
http.cors.allow-origin: "*"
#启动es-head
docker run -d -p 9209:9100 --name es-head mobz/elasticsearch-head:5
#安装ik分词器
#1：进入es；2：在plugin里创建ik文件夹
docker exec -it es bash
#下载ik分词器7.1.1
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.1.1/elasticsearch-analysis-ik-7.1.1.zip

#解压
unzip -j elasticsearch-analysis-ik-7.1.1.zip
#启动kibana
docker run -d -p 9206:5601 -e ELASTICSEARCH_URL=http://127.0.0.1:9200 --name kibana --network esnet kibana:7.1.1
#修改kibana.yml 1:进入容器 /usr/share/kibana/config/kibana.yml
docker exec -it kibana /bin/bash
#将http://elasticsearch:9200 修改为 http://ip:9200 添加一行 i18n.locale: "zh-CN"
#启动logstash
docker run -d -p 9204:4567 -p 9205:9600 -p 9208:5044 --privileged=true --name logstash logstash:7.1.1
#修改配置文件 /config/logstash.yml 将http://elasticsearch:9200 修改为 http://ip:9200
docker exec -it logstash bash

#修改 /pipeline/logstash.conf

```

==logstash.conf==

```
input{
        tcp {
                mode => "server"
                port => 4567
                codec => json_lines
        }
}
#filter{
#    json{
#        source => "message"
#        remove_field => ["message"]
#    }
#}
output{
        elasticsearch{
                hosts=> ["ip:9200"]
                index => "com.jkd-%{+YYYY.MM.dd}"
                }
        stdout{codec => rubydebug}
    
}
```

