#!/bin/bash

read -n1 -p "Do you want to custom setting? [Y/N]?" answer
# Springboot Config File Location
# MONGO_INITDB_ROOT_USERNAME
# MONGO_INITDB_ROOT_PASSWORD
# SpringBoot Port

sudo docker pull mongo:3.4
sudo docker pull docker.elastic.co/elasticsearch/elasticsearch:6.6.0

sudo docker run -p 8080:8080 -d muresearch/muresearchboost:latest --name my-mu
sudo docker run -d --name my-mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=<***> -e MONGO_INITDB_ROOT_PASSWORD=<***> mongo --auth
sudo docker run --name my-es -d -p 9200:9200  -p 9300:9300 -e "discovery.type=single-node" -e cluster.name=elasticsearch -e http.port=9200 -e http.cors.enabled=true -e http.cors.allow-origin=
http://localhost:1358,http://127.0.0.1:1358 -e http.cors.allow-headers=X-Requested-With,X-Auth-Token,Content-Type,Content-Length,Authorization -e http.cors.allow-credentials=true docker.elastic.co/elasticsearch/
elasticsearch:6.6.0

