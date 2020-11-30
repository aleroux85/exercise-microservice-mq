FROM openjdk:8
COPY . /usr/src/app
WORKDIR /usr/src/app
ENV CLASSPATH=amqp-client-5.7.1.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar:.
RUN javac Recieve.java
CMD ["java", "Recieve"]