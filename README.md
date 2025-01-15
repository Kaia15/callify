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

![callify-microservices](https://github.com/user-attachments/assets/4f86433d-34dd-4390-90f2-5a5944e0081a)

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

