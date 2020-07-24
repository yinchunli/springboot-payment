# Introduction

Build a simple Microservice using SpringBoot to extract and store Claim History across 

# Design:

System bases on SpringBoot’s Microservices/Data/Restful API, and H2.

# Assumption
We allow duplication of number or name.. only id is primary key. we assume the requirement is completed.

# Installation
User needs to have maven and java8 installed in local environment in order to run this application, this application will be run on windows system.
please follow these steps:
 ```
   git clone https://github.com/yinchunli/springboot-payment
   mvn clean install 
   mvn spring-boot:run
``` 

# Running the Application
To run the project:
mvn spring-boot:run

#Open API - Swagger-UI
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

#Testing Via Postman
input json:

	Create payment POST /payments/{customerId}
	
	Update individual payment PUT /payments/{customerId}
	
	Get Payment GET /payments
	
	Get individual payments GET /payments/{customerId}
	
	Delete individual contacts DELETE /payments/{customerId}
	
	GET	/payments/customer/history/{customerId}/{year}/{month} List all payments for customer on the month of the year
	
	GET	/payments/customer/{customerId}/{year}/{fromMonth}/{toMonth}  List all payments for customer on the year in between

#Logs
Lomok sl4j is used in application level log, and log level is set to info level. Hibernate SQL is turned on. 

# Current Status and Enhancement

Application is successfully up running, Full filled the requirement on server side, directly service call from standalone application.

Rest Service is operated correctly on top of SpringBoot

CRUD function is operated correctly on H2

Service side implementation on all functions

System has been unit test which preformed all mock service call based on the require ment

# To Be Enhanced
Because of the time limitation, I have left some features for future enhancement which is not specified in the documents.
(1) Security, it would be nice to add Spring Security in
(2) Validation, input and output validation
(3) Exception Handler along with log mechanism.
(4) Build docker image, and push to docker hub, so the application can be easily retrieved by user.

```
yingchun.li@Atlanta
```
