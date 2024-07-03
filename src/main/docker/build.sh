#!/usr/bin/env bash

#if input parameters count less than 2, then print help, and exit
if [ $# -lt 1 ]; then
    echo "Usage: $0 <tag>"
    exit 1
fi


echo "Building image..."
cd ../../.. && mvn clean package
docker build -t 192.168.11.201:1881/datasafe/dataway:v$2 -f src/main/docker/Dockerfile target/