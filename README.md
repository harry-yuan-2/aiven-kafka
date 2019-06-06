# aiven-kafka
POC code to use Avien Kafka, written in Spring Boot/maven

Setup:

Make sure to setup client side certificates, etc. and change the application.properties file.

To run:

mvn spring-boot:run

It will produce a message called "Hello world 456" and persist into a Postgres SQL database, on Aiven.

PostgresSQL table script:

CREATE TABLE messages (
   ID serial PRIMARY KEY,
   body VARCHAR (255) NOT NULL,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP

);

Since this is a quick and dirty POC, only the happy path is coded. For production use, extra coding is necessary to handle consumer failure cases (such as database down, insert failed, etc.)

Another improvement might be using spring boot kafka integration to reduce the code base.




