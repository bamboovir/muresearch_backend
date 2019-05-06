# install docker and docker-compose

```bash
# https://docs.docker.com/install/linux/docker-ce/ubuntu/

sudo aa-remove-unknown
AppArmor (Application Armor) is a Linux security module that protects an operating system and its applications from security threats. To use it, a system administrator associates an AppArmor security profile with each program. Docker expects to find an AppArmor policy loaded and enforced. Check default profiles with:
```

# 创建部署环境

create a VM instance (ubuntu 18.10)

```bash
# Uninstall old versions docker
sudo apt-get remove docker docker-engine docker.io containerd runc
sudo apt-get update
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

# install docker
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# clone main repo
git clone "https://github.com/bamboovir/muresearch_backend"
 cd muresearch_backend/deploy/
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
image_id=$(sudo docker image ls | grep muresearch | tr -s ' ' | cut -d ' ' -f 3 | head -n 1)
curl https://registry.hub.docker.com//v1/repositories/bamboovir/muresearchboost/tags # check curr version
sudo docker tag $image_id bamboovir/muresearchboost:<version[1.00]>
sudo docker push bamboovir/muresearchboost:<version[1.00]>

```

## muresearchalgo

```bash
#sudo apt-get update && sudo apt-get upgrade
#sudo apt install python3-pip
#sudo -H pip3 install pipenv
sudo docker build .
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

## backup and restore

```bash
sudo apt-get install mongo-tools
```