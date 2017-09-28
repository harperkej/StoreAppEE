#!/bin/bash

echo '<<<<<< - Stoping the containers -- if any is up and running - >>>>>>'
docker stop shop-ee-mysql-db
docker stop shop-ee-payara-full

echo '<<<<<< - Removing containers -- if any of them exists - >>>>>>'
docker rm shop-ee-mysql-db
docker rm shop-ee-payara-full

set -e

echo '<<<<<< - Building the application and running the tests - >>>>>>'
mvn clean install

cp StoreApp-ear/target/StoreApp-ear.ear docker/application_server/

echo '<<<<<< - Building docker images. - >>>>>'
docker-compose build

echo '<<<<<< - Starting containers. - >>>>>>'
docker-compose up -d

rm docker/application_server/StoreApp-ear.ear

echo 'The docker host: '
sudo docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' "shop-ee-payara-full"

