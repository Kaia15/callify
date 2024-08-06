# Zoom Integration Note

## Zoom SDK Setup (App Marketplace)
1. Register Zoom Account -> go to Developer Dashboard
2. Select "Manage" -> "Build App" -> "General App"
3. After filling important info, and creating successfully, retrieve ```API_KEY``` & ```API_SECRET``` in ```local.env``` (temporary hard-code)
4. Add Domain URL ```localhost:3000``` to make Zoom config with frontend.

   - Now, Zoom only runs Backend at port 4000
   - Spring Boot is backend server, look up more documentation to convert port 4000 to 8080 for meetingService.
6. Turn on Zoom App SDK (play with Dropdown menu and find this option to turn it on), + Meeting + Phone.

## Backend
1. Try to config ```meetingService``` with port ```4000``` since Zoom (not 100% sure) only allows port ```4000```.
2. In ```meetingService```, rewrite some functions in code sample by Zoom to align.
3. MUST-DO: ```git clone https://github.com/zoom/meetingsdk-auth-endpoint-sample``` (Node.js).
