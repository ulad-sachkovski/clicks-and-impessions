# TechnicalTask: calculation of clicks and impressions

Requirements are located in techtask folder

## How to run
Pre-requirements:
* Java 17
* JDK installed and defined in environment variables
* Apache Spark installed and defined in environment variables
* Docker installed

Before running the application, run docker-compose.yaml file first
Then start the program

WARNING: 
On windows still some errors can happen while running the application
In my case, I added to options the following parameters:
* --add-exports java.base/sun.nio.ch=ALL-UNNAMED 
* --add-exports java.base/sun.security.action=ALL-UNNAMED

The application scans for already existing and newly added files to directory "raw_data"
All source files are located in directory "data"
Application automatically starts scanning "raw_data" directory, no action required
While application running, you can add new files to "raw_data" directory

## Chosen solutions and instruments


Challenge A: getting data from parquet files
Solution: Usage of Apache Spark as an instrument for Big Data analysis purposes

Challenge B: sending message with results
Solution: Usage of Apache Kafka to send messages

Challenge C: in runtime track new files in "raw_data" directory
Solution: Usage of WatchService within java.nio.file

Challenge D: somehow inform the business-logic, that new file was added
Solution: Usage of event-driven architecture with object ApplicationEventPublisher within org.springframework.context

Challenge E: do not read the file twice, including the case of several pods running
Solution: keep id of the file in relation database with serializable isolation level