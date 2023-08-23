# todolist-repo

1. after checkout the main branch, navigate to /todolist-repo and run command below for build the docker image from Dockerfile and start the container to bring up the application.
   -  docker build -t todolist-app .
   -  docker run -p 8080:8080 todolist-app
2. after the container start successfully, go to http://localhost:8080/login to login with gmail.
   2.1 after login successfully, will redirect to http://localhost:8080/authenticated endpoint that return a jwt token string.
3. for api testing can use curl command below:
   3.1 GET List all TODO items: curl -X GET 'http://localhost:8080/v1/to-do-list' -H 'Authorization: Bearer {jwt token from step 2.1}'
   3.2 POST Add a TODO item: curl -X POST 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"taskName":"test1","status":1}'
   3.3 PUT Mark a TODO item as completed: curl -X PUT 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"id":1,"taskName":"test1","status":2}'
       status 1 : new
       status 2 : completed
   3.4 DELETE Delete a TODO item: curl -X DELETE 'http://localhost:8080/v1/to-do-list?id=1' -H 'Authorization: Bearer {jwt token from step 2.1}'
