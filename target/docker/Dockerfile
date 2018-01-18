FROM java:8

MAINTAINER Jesús Castro (51v4n), x51v4n@gmail.com 

ADD nb-springboot-docker.jar /app.jar

EXPOSE 8080

CMD java -Djava.security.edg=file:/dev/./urandom -jar /app.jar
