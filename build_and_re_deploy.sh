#!/bin/bash

/cho '<<<<<< - Stoping the containers -- if any is up and running - >>>>>>'
sudo docker stop shop-ee-mysql-db
sudo docker stop shop-ee-payara-full

echo '<<<<<< - Removing containers -- if any of them exists - >>>>>>'
sudo docker rm shop-ee-mysql-db
sudo docker rm shop-ee-payara-full

echo '<<<<<< - Building the application and running the tests - >>>>>>'
mvn clean install

cp StoreApp-ear/target/StoreApp-ear.ear docker/application_server/

echo '<<<<<< - Building database images - >>>>>>'
cd /home/harperkej/Desktop/Projects/StoreAppEE/StoreApEE/docker/db/Dockerfile
sudo docker build -t harperkej/shop-ee-mysql-db .

echo '<<<<<< - Building the application image - >>>>>>'
cd /home/harperkej/Desktop/Projects/StoreAppEE/StoreApEE/docker/application_server/Dockerfile
sudo docker build -t harperkej/shop-ee-payara-full .

echo '<<<<<< - Starting containers. - >>>>>>'
sudo docker-compose up

