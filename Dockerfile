FROM maven:3.8.5-jdk-11-slim AS MAVEN_BUILD

WORKDIR /home/app/

COPY pom.xml pom.xml
RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip

COPY src ./src
RUN mvn clean package -Dmaven.test.skip

#COPY . .
#RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11-jre-slim

#WORKDIR /hardemic

COPY --from=MAVEN_BUILD /home/app/target/hardemic-1.0-jar-with-dependencies.jar /usr/local/lib/hardemic.jar

CMD ["java","-jar","/usr/local/lib/hardemic.jar","cli"]
