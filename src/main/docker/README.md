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

```bash
# install docker by snap
sudo apt-get update && sudo apt-get upgrade
sudo snap install docker # version 18.06.1-ce
sudo apt install maven
```