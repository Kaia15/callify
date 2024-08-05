# Spring Boot Microservices Communication (RestTemplate or Webclient)

myapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── myapp/
│   │   │           │   ├── MyAppApplication.java
│   │   │           ├── config/
│   │   │           │   ├── RestClientConfig.java
│   │   │           ├── servicea/
│   │   │           │   ├── controller/
│   │   │           │   │   └── ServiceAController.java
│   │   │           │   ├── service/
│   │   │           │   │   └── ServiceAService.java
│   │   │           ├── serviceb/
│   │   │           │   ├── controller/
│   │   │           │   │   └── ServiceBController.java
│   │   │           │   ├── service/
│   │   │           │   │   └── ServiceBService.java
│   │   │           ├── interceptors/
│   │   │           │   └── RestTemplateHeaderModifierInterceptor.java
│   │   ├── resources/
│   │   │   └── application.properties
└── pom.xml
