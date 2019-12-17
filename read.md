**docker安装**

配置国内数据源
vim /etc/docker/daemon.json

{ 
 "registry-mirrors": 
 ["https://docker.mirrors.ustc.edu.cn",
 "https://registry.docker-cn.com"] 
 }
 
重启docker
 systemctl restart docker

查看镜像源是否生效
docker info




**redis创建集群**

redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006 --cluster-replicas 1