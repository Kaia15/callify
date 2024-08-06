# Zoom Integration Note

## Zoom SDK Setup (App Marketplace)
1. Register Zoom Account -> go to Developer Dashboard
2. Select "Manage" -> "Build App" -> "General App"
3. After filling important info, and creating successfully, retrieve ```API_KEY``` & ```API_SECRET``` in ```local.env``` (temporary hard-code)
4. Add Domain URL ```localhost:3000``` to make Zoom config with frontend.

   - Now, Zoom only runs Backend at port 4000
   - Spring Boot is backend server, look up more documentation to convert port 4000 to 8080 for meetingService.
6. Turn on Zoom App SDK (play with Dropdown menu and find this option to turn it on), + Meeting + Phone.
