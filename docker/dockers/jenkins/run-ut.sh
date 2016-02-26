#!/bin/bash
set -x

CONTAINER="docker-ut"
DIRECTORY="logs"

docker pull aurelienr/jdk8-mvn:latest

# Detect if container is running
RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

# Create container if does not exists
if [ $? -eq 1 ]; then
  echo "UNKNOWN - $CONTAINER does not exist."
  echo "Create container: $CONTAINER."
  docker create --name $CONTAINER aurelienr/jdk8-mvn:latest
fi

# Copy cloned repo to docker:/webapp
echo "Copy repo to $CONTAINER."
docker cp . $CONTAINER:webapp


# If not running, run it
if [ "$RUNNING" == "false" ]; then
  echo "CRITICAL - $CONTAINER is not running."
  docker start -a docker-ut
fi

STARTED=$(docker inspect --format="{{ .State.StartedAt }}" $CONTAINER)
NETWORK=$(docker inspect --format="{{ .NetworkSettings.IPAddress }}" $CONTAINER)

echo "OK - $CONTAINER is running. IP: $NETWORK, StartedAt: $STARTED"

<<<<<<< HEAD
if [ ! -d "$DIRECTORY" ]; then
=======
if [! -d "$DIRECTORY" ]; then
>>>>>>> 26ea56358e1e40d006b71afb6c96b5dff2809ee9
  mkdir logs
fi

docker cp $CONTAINER:webapp/target/surefire-reports ./logs
