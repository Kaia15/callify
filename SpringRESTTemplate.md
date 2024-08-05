# Spring Boot Microservices Communication (RestTemplate or Webclient)


MyApp is a Spring Boot application consisting of two services, Service A and Service B. Both services use `RestTemplate` to communicate with each other. Service B fetches data from Service A using a REST endpoint.

## Project Structure

myapp/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ ├── myapp/
│ │ │ │ ├── MyAppApplication.java
│ │ │ ├── config/
│ │ │ │ ├── RestClientConfig.java
│ │ │ ├── servicea/
│ │ │ │ ├── controller/
│ │ │ │ │ └── ServiceAController.java
│ │ │ │ ├── service/
│ │ │ │ │ └── ServiceAService.java
│ │ │ ├── serviceb/
│ │ │ │ ├── controller/
│ │ │ │ │ └── ServiceBController.java
│ │ │ │ ├── service/
│ │ │ │ │ └── ServiceBService.java
│ │ │ ├── interceptors/
│ │ │ │ └── RestTemplateHeaderModifierInterceptor.java
│ │ ├── resources/
│ │ │ └── application.properties
└── pom.xml
