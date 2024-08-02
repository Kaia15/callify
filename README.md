# YZoomie

## Reference: https://github.com/ansulagrawal/zoom-clone/
## Tech stacks
1. Frontend: React.js, 
2. Backend: Spring Boot/Java, Nodemailer (SMTP)
   
## Frontend
1. Routes:
   - ```/signin```
   - ```/signup```

2. Hooks
   - useSignIn
   - useSignUp
   - useUser

## Backend
1. Services: userService, meetingService.
2. Architecture: Microservices OR Monolith? @ThienBui

## Database
1. Schemas:
   - User: userID, email, firstName, lastName, password
   - Meeting: meetingID, duration, startTime, endTime, passcode, members, url

2. Relations:
   - User / Meetings: many to many, (one Meeting can have many User(s), one User can join many Meeting(s))
  
3. PostGRESQL
   - Try to set up following documentation: https://www3.ntu.edu.sg/home/ehchua/programming/sql/PostgreSQL_GetStarted.html.

4. Set up your application
   - Access via link: https://start.spring.io/ to download your spring boot application package.
   - Some packages added to download: **PomXML, data-jbc, jpa, SQL (Postgresql, MySQL), rest, web**, etc.
   - After installation, export .zip folder and open in VSCode.
