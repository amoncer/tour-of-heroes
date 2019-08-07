FROM maven:3.6.1-jdk-8

COPY pom.xml /tmp/pom.xml

RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

WORKDIR /usr/src

COPY . .

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./target/tour-of-heroes-server-0.0.1-SNAPSHOT.jar"]
