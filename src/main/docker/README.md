# 创建部署环境

create a VM instance (ubuntu 18.10)

```bash
# install docker by snap
sudo snap install docker # version 18.06.1-ce
# clone main repo
git clone "https://github.com/bamboovir/muresearch_backend"
cd muresearch_backend/src/main/docker/
# db config in .env
# 后台启动服务
sudo docker-compose up -d
```

# 创建构建环境

## muresearchboost

```bash
# install docker by snap
sudo apt-get update && sudo apt-get upgrade

sudo snap install docker # version 18.06.1-ce
sudo apt install maven

git clone "https://github.com/bamboovir/muresearch_backend"
cd muresearch_backend

sudo mvn test # run test
sudo mvn package docker:build # build docker file

sudo docker login --username <***> --password <***>
image_id=$(sudo docker image ls | grep "muresearch/muresearchboost" | tr -s ' ' | cut -d ' ' -f 3)
curl https://registry.hub.docker.com//v1/repositories/bamboovir/muresearchboost/tags # check curr version
sudo docker tag $image_id bamboovir/muresearchboost:<version[1.00]>
sudo docker push bamboovir/muresearchboost:<version[1.00]>

```

## muresearchalgo

```bash
```