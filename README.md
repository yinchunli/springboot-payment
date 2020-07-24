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
# Solutions

![image](https://user-images.githubusercontent.com/68702751/88400662-a8436900-cd96-11ea-92aa-b50b0c62b190.png)

![image](https://user-images.githubusercontent.com/68702751/88400675-ac6f8680-cd96-11ea-8baa-ef81eeaa56b0.png)

# Running the Application
To run the project:
mvn spring-boot:run

#Open API - Swagger-UI
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

![image](https://user-images.githubusercontent.com/68702751/88401159-5fd87b00-cd97-11ea-8bf3-23fc6aa9e8d7.png)

#Testing Via Postman
input json:

	Create payment POST /payments/{customerId}

![image](https://user-images.githubusercontent.com/68702751/88401260-80a0d080-cd97-11ea-9f04-40a955bc52d6.png)
	
	Update individual payment PUT /payments/{customerId}
![image](https://user-images.githubusercontent.com/68702751/88401475-ceb5d400-cd97-11ea-9dc2-19579976441d.png)
	
	Get Payment GET /payments
![image](https://user-images.githubusercontent.com/68702751/88402818-a6c77000-cd99-11ea-817a-a82300d79a12.png)

	Get individual payments GET /payments/{customerId}
![image](https://user-images.githubusercontent.com/68702751/88402743-8992a180-cd99-11ea-9dfd-60f56556877e.png)
	
	Delete individual contacts DELETE /payments/{customerId}
![image](https://user-images.githubusercontent.com/68702751/88402640-68ca4c00-cd99-11ea-9a7e-1cdc064d2267.png)
	
	GET	/payments/customer/history/{customerId}/{year}/{month} List all payments for customer on the month of the year
![image](https://user-images.githubusercontent.com/68702751/88401696-0cb2f800-cd98-11ea-88f1-e3c285d7f9c8.png)
	
	GET	/payments/customer/{customerId}/{year}/{fromMonth}/{toMonth}  List all payments for customer on the year in between
![image](https://user-images.githubusercontent.com/68702751/88401587-ed1bcf80-cd97-11ea-963b-5532e227d1c4.png)

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
