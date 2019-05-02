# 创建部署环境

create a VM instance (ubuntu 18.10)

```bash
# install docker by snap
sudo snap install docker # version 18.06.1-ce
# clone main repo
git clone "https://github.com/bamboovir/muresearch_backend"
cd muresearch_backend/src/main/docker/
# db config in .env
# 清除之前的痕迹
sudo docker-compose rm -f
sudo docker system prune -a
# 后台启动服务
sudo docker-compose up -d
```

# 创建构建环境

## muresearchboost

```bash
sudo apt-get update && sudo apt-get upgrade

sudo snap install docker # version 18.06.1-ce
sudo apt install maven

git clone "https://github.com/bamboovir/muresearch_backend"
cd muresearch_backend

sudo mvn test # run test
sudo mvn package docker:build # build docker file

sudo docker login --username <***> --password <***>
image_id=$(sudo docker image ls | tr -s ' ' | cut -d ' ' -f 3 | head -n 1)
curl https://registry.hub.docker.com//v1/repositories/bamboovir/muresearchboost/tags # check curr version
sudo docker tag $image_id bamboovir/muresearchboost:<version[1.00]>
sudo docker push bamboovir/muresearchboost:<version[1.00]>

```

## muresearchalgo

```bash
sudo apt-get update && sudo apt-get upgrade
sudo apt install python3-pip
sudo -H pip3 install pipenv
sudo docker build
image_id=$(sudo docker image ls | tr -s ' ' | cut -d ' ' -f 3 | head -n 1)
sudo docker tag $image_id bamboovir/muresearchalgo:<version>
sudo docker push bamboovir/muresearchalgo:<version>
```

## muresearchui

```bash
sudo apt-get update && sudo apt-get upgrade
# we need nodejs && npm to build dist folder
sudo apt-get install nodejs
sudo apt-get install npm

git clone "https://github.com/MUResearchBoost/muresearchUI"
cd muresearchUI/
npm install
npm run build # now we get asset folder in ./dist

sudo docker-compose up -d
```