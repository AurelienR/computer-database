#!/bin/bash

CONTAINER="docker-ut"
DIRECTORY="logs"
MYSQL="mysql-docker"

echo "##################################################################################"
echo "#                                                                                #"
echo "#                     Run Unit Test - run-ut.sh                                  #"
echo "#                                                                                #"
echo "##################################################################################"


echo ""
echo "##################################################################################"
echo "# Step 1 - Run MYSQL docker                                                      #"
echo "##################################################################################"

# Detect if mysql container is running
echo "LOG- Check $MYSQL container is running."
MYSQL_RUNNING=$(docker inspect --format="{{ .State.Running }}" $MYSQL 2> /dev/null)

# If Mysql container is not running
if [ $? -eq 0  ] && [ "$MYSQL_RUNNING" == "false" ]  ; then
  echo "LOG - $MYSQL container already exists"
  echo "LOG - Starting $MYSQL container."
  docker start -d -a $MYSQL
else
  echo "LOG - $MYSQL container does not exist"
  echo "LOG - Remove $MYSQL container."
  docker rm $MYSQL
  echo "LOG - Run $MYSQL container."
  docker run --name $MYSQL -e "MYSQL_ROOT_PASSWORD=admin" -d mysql:5.5
fi

echo "LOG - Wait 10sec to $MYSQL container to run"
sleep 10

echo "LOG- Docker ps check:"
docker ps

echo ""
echo "##################################################################################"
echo "# Step 2 - Create JDK-MVN container                                              #"
echo "##################################################################################"

echo "LOG- Pull last image of aurelienr/jdk8-mvn:latest."
docker pull aurelienr/jdk8-mvn:latest > pull.txt

res=$(grep "Image is up to date" pull.txt)

# Detect if mysql container is running
echo "LOG- Check $CONTAINER container is running."
MVN_RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

# Create container if does not exists
if [ ${#res} -eq 0 ] || [ $? -eq 1 ] ; then
  echo "LOG - $CONTAINER container does not exist."
  echo "LOG - Remove $CONTAINER container."
  docker rm $CONTAINER
  echo "LOG - Create container: $CONTAINER."
  docker create --name $CONTAINER --link $MYSQL aurelienr/jdk8-mvn:latest
fi

echo "LOG- Docker ps check:"
docker ps

echo ""
echo "##################################################################################"
echo "# Step 3 - Copy cloned repo                                                      #"
echo "##################################################################################"
# Copy cloned repo to docker:/webapp
echo "LOG - Copy repo to webapp."
docker cp . $CONTAINER:webapp

echo ""
echo "##################################################################################"
echo "# Step 4 - Generate dao.properties                                               #"
echo "##################################################################################"
# Copy dao properties for JDBC Test connection
echo "LOG - Generating dao.properties for testing environment"
MYSQL_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' $MYSQL)

#We then use this ip to create the connection.properties file
echo "url = jdbc:mysql://$MYSQL_IP:3306/computer-database-db?zeroDateTimeBehavior=convertToNull" > src/main/resources/properties/dao.properties
echo "driver = com.mysql.jdbc.Driver" >> src/main/resources/properties/dao.properties
echo "nomutilisateur = admincdb" >> src/main/resources/properties/dao.properties
echo "motdepasse = qwerty1234" >> src/main/resources/properties/dao.properties
echo "MinConnectionsPerPartition = 5" >> src/main/resources/properties/dao.properties
echo "MaxConnectionsPerPartition = 10" >> src/main/resources/properties/dao.properties
echo "PartitionCount = 2"  >> src/main/resources/properties/dao.properties
cp src/main/resources/properties/dao.properties src/test/resources/dao.properties

echo ""
echo "##################################################################################"
echo "# Step 5 - Start JDK-MVN container                                               #"
echo "##################################################################################"

# Start mvn docker
echo "LOG - $CONTAINER container is not running."
echo "LOG - start $CONTAINER."
docker start -a $CONTAINER


echo "LOG- JDK-MVN infos:"
STARTED=$(docker inspect --format="{{ .State.StartedAt }}" $CONTAINER)
NETWORK=$(docker inspect --format="{{ .NetworkSettings.IPAddress }}" $CONTAINER)
echo "OK - $CONTAINER is running. IP: $NETWORK, StartedAt: $STARTED"

echo "LOG- Docker ps check:"
docker ps

echo ""
echo "##################################################################################"
echo "# Step 6 - Copy logs                                                             #"
echo "##################################################################################"

# Copy logs
if [ ! -d "$DIRECTORY" ]; then
  echo "LOG- Create log directory"
  mkdir logs
fi

echo "Copying logs:"
echo "Copying from webapp/target/surefire-reports ... "
docker cp $CONTAINER:webapp/target/surefire-reports ./logs
echo "Copying from webapp/target/failsafe-reports ... "
docker cp $CONTAINER:webapp/target/failsafe-reports ./logs

echo ""
echo "##################################################################################"
echo "# Step 7 - Stop containers                                                       #"
echo "##################################################################################"
# Stop all dockers
docker stop $MYSQL
docker stop $CONTAINER
