FROM maven:3.6.3-jdk-13



RUN mkdir /home/yumfoodapi

WORKDIR /home/yumfoodapi/

COPY .mvn/ .mvn/

COPY .settings/ .settings/

COPY src/ src/

COPY .classpath ./

COPY .factorypath ./

COPY .project ./

COPY mvnw* ./

COPY pom.xml ./

# RUN mvn -DMYSQL_DATABASE_HOST=${MYSQL_DATABASE_HOST} -DMYSQL_DATABASE_PORT=${MYSQL_DATABASE_PORT} -DMYSQL_DATABASE_NAME=${MYSQL_DATABASE_NAME} clean install

EXPOSE 8080

## THE LIFE SAVER
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait

CMD /wait \
    && mvn -DMYSQL_DATABASE_HOST=${MYSQL_DATABASE_HOST} -DMYSQL_DATABASE_PORT=${MYSQL_DATABASE_PORT} -DMYSQL_DATABASE_NAME=${MYSQL_DATABASE_NAME} clean install \
    && java -jar ./target/yumfood-api-1.0.0-SNAPSHOT.jar