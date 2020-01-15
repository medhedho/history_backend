FROM jeanblanchard/java:8
COPY ./target/back-0.0.1-SNAPSHOT.jar back.jar
CMD java -jar back.jar
EXPOSE 8080