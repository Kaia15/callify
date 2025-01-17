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
3. HTTP(s) Communication: 
   - RestTemplate (Blocking I/O, Synchronous HTTP Requests) 
   - WebClient (Non-Blocking I/O, Asynchronous HTTP Requests)
   - FeignClient
   - Apache Kafka (**if you use Kafka, be EXTREMELY CAREFUL, tech eng will not forgive you if you say sth wrong rather nothing**).

## Database
1. Schemas (updated later on)

2. Relations:
   - User / Meetings: many to many, (one Meeting can have many User(s), one User can join many Meeting(s))
   - **ORM (Object Relation Mapping): Hibernate** (look up docs to see the difference between **Spring JPA**, **Spring JDBC**, and how these relate to Repository, which is used to connect bt server & database).
  
3. PostGRESQL
   - Try to set up following documentation: https://www3.ntu.edu.sg/home/ehchua/programming/sql/PostgreSQL_GetStarted.html.

4. Set up your application
   - Access via link: https://start.spring.io/ to download your spring boot application package.
   - Some packages added to download: **PomXML, data-jbc, jpa, SQL (Postgresql, MySQL), rest, web**, etc.
   - After installation, export .zip folder and open in VSCode.

## Folder Structure:
1. Repository:
   - Goal: Store all service-based repositories that extend JPA repository which connnect database through Hibernate (ORM)

2. Model:
   - Goal: Define all service entities (create schema) used in database.
   - Relationship with Repository: repository has its "default" methods to query declared database in Model.

3. Service:
   - Goal: Where to implement all Repository interfaces, and after implementation, all the methods are used in Controller.

4. Interface:
   - Goal: create an interface to "guide" corresponding service on the right track.
  
## **Project Root**

```
callify-spring-boot/
├── MeetingApp/                     # Handles all meeting-related operations
│   ├── .mvn/                       # Maven wrapper directory
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/io/callify_spring/MeetingApp/
│   │   │   │   ├── config/         # Configuration classes
│   │   │   │   ├── controller/     # REST API controllers
│   │   │   │   ├── dto/            # Data Transfer Objects for API communication
│   │   │   │   ├── model/          # Domain and entity models
│   │   │   │   ├── repository/     # Interfaces for database access
│   │   │   │   ├── service/        # Business logic and services
│   │   │   │   │   ├── IMeetingService.java
│   │   │   │   │   ├── MeetingService.java
│   │   │   │   ├── MeetingApplication.java  # Entry point of the MeetingApp
│   │   ├── resources/              # Resources such as application.properties
│   │   ├── test/                   # Unit and integration tests
.
.
│   ├── pom.xml                     # Maven project descriptor

├── UserApp/                        # Handles all user-related operations
│   ├── .mvn/                       # Maven wrapper directory
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/io/callify_spring/UserApp/
│   │   │   │   ├── config/         # Configuration classes
│   │   │   │   ├── controller/     # REST API controllers
│   │   │   │   ├── dto/            # Data Transfer Objects for API communication
│   │   │   │   ├── model/          # Domain and entity models
│   │   │   │   ├── repository/     # Interfaces for database access
│   │   │   │   ├── service/        # Business logic and services
│   │   │   │   ├── UserApplication.java  # Entry point of the UserApp
│   │   ├── resources/              # Resources such as application.properties
│   │   ├── test/                   # Unit and integration tests
.
.
│   ├── pom.xml                     # Maven project descriptor

```

## Naming Convention:
1. Variable Name in a class: lowercase first letter of the first word, uppercase all first letters of the following words.

## Annotations:
1. Model / Entity:
   - Recommend using `@Column` for each field of each model.
   - For unique fields like email, add props inside `@Column`
   - Use of Enum type to define subscription types applying on each User.

## Schema Design:
1. Follow strictly Zoom Developer APIs Doc: https://developers.zoom.us/docs/api/

## Querying:
1. Apply derived queries: 
   - Query derivation allows the developer to define method names in the repository interface that follow a naming convention, and the framework generates an appropriate query based on that method name. (defined in Repository Interface -- Repository Folder)

## Backend API(s):
1. Meeting Service: (PORT=8081)
   - **POST**: `http://localhost:8081/api/v1/meetings/`
   - **GET**: `http://localhost:8081/api/v1/meetings/`
   - **GET**: `http://localhost:8081/api/v1/meetings/{meetingId}`
   - **GET**: `http://localhost:8081/api/v1/meetings/{meetingId}/attendees` (Retrieve all attendeed ids who have attended/registered for the meeting)
   - **GET**: `http://localhost:8081/api/v1/meetings/{meetingId}/attendees/{attendeeId}` (Retrieve details on a specific user who has registered/attended for the meeting)

2. User Service: (PORT=8080)
   - **POST**: `http://localhost:8080/api/v1/users/`
   - **GET**: `http://localhost:8080/api/v1/users?offset={offset}&pagenum={pagenum}`
   - **GET**: `http://localhost:8080/api/v1/users/`
   - **POST**: `http://localhost:8080/api/v1/users/{userId}/meetings` (Create a meeting for a user)
   - **GET**: `http://localhost:8080/api/v1/users/{userId}/meetings` (Retrieve all user's meetings)
