#!/bin/bash

CONTAINER="docker-ut"
DIRECTORY="logs"
MYSQL="mysql-docker"

docker pull aurelienr/jdk8-mvn:latest

# Detect if container is running
RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

# Create container if does not exists
if [ $? -eq 1 ]; then
  echo "UNKNOWN - $CONTAINER does not exist."
  echo "Create container: $CONTAINER."
  docker create --name $CONTAINER --link mysql-container:mysql-docker -e "MYSQL_ROOT_PASSWORD=\"\""  aurelienr/jdk8-mvn:latest
fi

# Copy cloned repo to docker:/webapp
echo "Copy repo to $CONTAINER."
docker cp . $CONTAINER:webapp

echo "Copy dao.properties for testing environment"
cp -rf ./docker/dockers/mysql/dao.properties ./src/test/resources/properties/dao.properties
cp -rf ./docker/dockers/mysql/dao.properties ./src/main/resources/properties/dao.properties

# Start docker
docker start -a docker-ut

STARTED=$(docker inspect --format="{{ .State.StartedAt }}" $CONTAINER)
NETWORK=$(docker inspect --format="{{ .NetworkSettings.IPAddress }}" $CONTAINER)

echo "OK - $CONTAINER is running. IP: $NETWORK, StartedAt: $STARTED"

if [ ! -d "$DIRECTORY" ]; then
  mkdir logs
fi

docker cp $CONTAINER:webapp/target/surefire-reports ./logs
docker cp $CONTAINER:webapp/target/failsafe-reports ./logs
