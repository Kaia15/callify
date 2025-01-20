## Zoom SDK Setup (App Marketplace)

1. General Steps:
   - Register Zoom Account -> go to Developer Dashboard

2. Frontend Integration:
   - Select "Manage" -> "Build App" -> "General App"
   - After filling important info, and creating successfully, retrieve ```API_KEY``` & ```API_SECRET``` in ```local.env``` (temporary hard-code)
   - Add Domain URL ```localhost:3000``` to configure Zoom-SDK. 

3. Backend Intgeration:
   - Select "Manage" -> "Build App" -> "Server to Server Oauth App"
   - After filling all information, choose necessary scopes (create,list & delete meetings)
   - Use ACCOUNT_ID, CLIENT_ID, CLIENT_SECRET to authorize our app (generate access tokens) with Zoom Meeting SDK 
   - Note: ZOOM_API_URL = "https://api.zoom.us/v2/users/{userId}/meetings"; (replace userId with own email/id found in Zoom Portal)


4. Sample JSON Object (response) (ZoomService in Backend):
```
{"uuid":"clyxpEKmTtiZCcMbXXKxpA==",
"id":71244335569, // This is meeting number 
"host_id":"rmTHYtpcT5G8qbHQKgOR7A","host_email":"baotranongtran@gmail.com","topic":"Test Meeting","type":1,"status":"waiting","timezone":"America/Los_Angeles","created_at":"2025-01-20T03:20:24Z","start_url":"https://us04web.zoom.us/s/71244335569?zak=eyJ0eXAiOiJKV1QiLCJzdiI6IjAwMDAwMSIsInptX3NrbSI6InptX28ybSIsImFsZyI6IkhTMjU2In0.eyJpc3MiOiJ3ZWIiLCJjbHQiOjAsIm1udW0iOiI3MTI0NDMzNTU2OSIsImF1ZCI6ImNsaWVudHNtIiwidWlkIjoicm1USFl0cGNUNUc4cWJIUUtnT1I3QSIsInppZCI6ImY3MTdkOGYzNWI5ODRiNDhiMjY1ZjI5NzZmNTE3NjI4Iiwic2siOiIzNDI3MzIwNDAzNDIwNzI3MTA2Iiwic3R5IjoxLCJ3Y2QiOiJ1czA0IiwiZXhwIjoxNzM3MzUwNDI0LCJpYXQiOjE3MzczNDMyMjQsImFpZCI6IlBXdTNWNjQtUjZ1Nk91YXB5QVdHdUEiLCJjaWQiOiIifQ.U-aeI8fT4-Y5Q8MzTTsux7D5NQMRfKgHuI9mnHJZLao","join_url":"https://us04web.zoom.us/j/71244335569?pwd=g5yqH6splfGBITz8m6XkUwFzqChFY0.1","password":"123456","settings":{"host_video":false,"participant_video":false,"audio":"voip","auto_recording":"none"}}
```