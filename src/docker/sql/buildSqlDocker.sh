#!/bin/bash

echo building ParkAppSql

#remove old ps/createImageBitmap
docker rm parkAppDb
docker rmi parkapp-mysql

#recreate docker
docker build -t parkapp-mysql .
docker run --name parkAppDb -p 3306:3306 -p33060:33060 -d parkapp-mysql