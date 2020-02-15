**docker**

重启docker
1.systemctl restart docker

build image并推送到远程服务器
1.mvn package
2.docker:build

3.远程服务器启动项目（docker需开启2375端口）
docker run --name adminservice -d -p 8089:8089  -v /home/service/admin:/home/service/admin  admin:latest 

-v 宿主机路径:容器内路径
-d 守护
-p 宿主机端口:容器端口
--name 容器名称


##redis

###redis 集群
redis-cli --cluster create 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006 --cluster-replicas 1

###redis 单节点
src/redis-server redis.conf

**ZooKeeper服务命令**
1. 启动ZK服务:       bin/zkServer.sh start conf/zoo.cfg
2. 查看ZK服务状态:    sh bin/zkServer.sh status
3. 停止ZK服务:       sh bin/zkServer.sh stop
4. 重启ZK服务:       sh bin/zkServer.sh restart

先启动zk，再启动hbase

