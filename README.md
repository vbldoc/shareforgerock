# Program(R) Version 0.0.1 REST API

## Usage notes:
This REST API expose and endpoint using spring mvc, spring boot, jslt library mockito and junit (see maven dependencies for more detail)

## Installation requirements:
Java 1.8 to run executable jar file

Setup java and ant environments:      
- [java install](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

2. Locate devicefeatures-0.0.1-SNAPSHOT.jar file over project directory

3. Run commnad to start the REST API server:java -jar devicefeatures-0.0.1-SNAPSHOT.jar

4. Go to http://localhost:8080/swagger-ui.html to see the swagger documentation and model to execute the post operation
   
Note: Also after execute step 3, you could use POSTMAN with the next endpoint http://localhost:8080/api/v1/devices/transformDevice



## Important structure for REST API project:

- com.forgerock.core.devicefeatures.controllers   **(Main rest controller where the post method is created)**
- com.forgerock.core.devicefeatures.domain **(Device and Featuring device model clasess)**
- com.forgerock.core.devicefeatures.service    **(Interface service and service implementation)**
- README.md  **(This file)**
