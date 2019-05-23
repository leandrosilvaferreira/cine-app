#!/usr/bin/env bash

mvn clean package -Pbuildui

docker build -f src/main/docker/Dockerfile.jvm -t quarkus/api-jvm:latest .


echo "To run the container,  use:"
echo "docker run -i --rm -p 8080:8080 quarkus/api-jvm:latest"