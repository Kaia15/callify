# Callify

## Reference: https://github.com/ansulagrawal/zoom-clone/

## Tech stacks
1. Frontend: React.js, 
2. Backend: Spring Boot/Java, Nodemailer (SMTP)
   
## Frontend
1. Routes:
   - ```/signin```
   - ```/signup```
   - ```/``` (Home page or Meeting Page, since Zoom Meeting will allow user join or create meeting at the same page).
2. Hooks
   - **useSignIn**
   - **useSignUp**
   - **useUser**
   - **useVideoCall** (where to fetch and launch Zoom Meeting with signature and token retrieved from backend).
     
## Backend
1. Services:
   - **userService**
   - **meetingService**
   - **authService**
2. Architecture: Microservices
3. HTTP(s) Communication: RestTemplate OR Apache Kafka (**if you use Kafka, be EXTREMELY CAREFUL, tech eng will not forgive you if you say sth wrong rather nothing**).
4.
my-monorepo/
├── user-service/
│   ├── controllers/
│   │   ├── UserController.java
│   │   └── HealthController.java
│   ├── services/
│   │   └── UserService.java
│   ├── repositories/
│   │   └── UserRepository.java
│   ├── models/
│   │   └── User.java
│   ├── config/
│   │   └── RestTemplateConfig.java
│   └── UserServiceApplication.java
├── auth-service/
│   ├── controllers/
│   │   ├── AuthController.java
│   │   └── HealthController.java
│   ├── services/
│   │   └── AuthService.java
│   ├── repositories/
│   │   └── AuthRepository.java
│   ├── models/
│   │   └── Authentication.java
│   ├── config/
│   │   └── RestTemplateConfig.java
│   └── AuthServiceApplication.java
├── meeting-service/
│   ├── controllers/
│   │   ├── MeetingController.java
│   │   └── HealthController.java
│   ├── services/
│   │   └── MeetingService.java
│   ├── repositories/
│   │   └── MeetingRepository.java
│   ├── models/
│   │   └── Meeting.java
│   ├── config/
│   │   └── RestTemplateConfig.java
│   └── MeetingServiceApplication.java
└── pom.xml

## Database
1. Schemas:
   - User: userID, email, firstName, lastName, password
   - Meeting: meetingID, duration, startTime, endTime, passcode, members, url

2. Relations:
   - User / Meetings: many to many, (one Meeting can have many User(s), one User can join many Meeting(s))
   - **ORM (Object Relation Mapping): Hibernate** (look up docs to see the difference between **Spring JPA**, **Spring JDBC**, and how these relate to Repository, which is used to connect bt server & database).
  
3. PostGRESQL
   - Try to set up following documentation: https://www3.ntu.edu.sg/home/ehchua/programming/sql/PostgreSQL_GetStarted.html.

4. Set up your application
   - Access via link: https://start.spring.io/ to download your spring boot application package.
   - Some packages added to download: **PomXML, data-jbc, jpa, SQL (Postgresql, MySQL), rest, web**, etc.
   - After installation, export .zip folder and open in VSCode.
