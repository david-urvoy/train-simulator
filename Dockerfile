FROM maven:3.6.3-jdk-8
COPY . /usr/src/train-simulator
WORKDIR /usr/src/train-simulator
RUN mvn clean install
CMD ["mvn", "exec:java"]

