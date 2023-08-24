# getting start

- after checkout the `main` branch, navigate to <code>/todolist-repo</code> and run command below for build the docker image from `Dockerfile` and start the container to bring up the application.
   ```bash
      docker build -t todolist-app .
      docker run -p 8080:8080 todolist-app
   ```
- after the container start successfully, go to <code>http://localhost:8080/login</code> to login with `gmail`.
  - after login successfully, will redirect to <code>http://localhost:8080/authenticated</code> endpoint that return a jwt token string.

- for api testing can use curl command below:
     - <b>GET</b> List all TODO items: 
       ```bash
        curl -X GET 'http://localhost:8080/v1/to-do-list' -H 'Authorization: Bearer {jwt token string}'
       ```
     - <b>POST</b> Add a TODO item:
       ```bash
        curl -X POST 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token string}' -d '{"taskName":"test1","status":1}'
       ```
     - <b>PUT</b> Mark a TODO item as completed:
       ```bash
        curl -X PUT 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token string}' -d '{"id":1,"taskName":"test1","status":2}'
       ```
     - <b>DELETE</b> Delete a TODO item:
       ```bash
        curl -X DELETE 'http://localhost:8080/v1/to-do-list?id=1' -H 'Authorization: Bearer {jwt token string}'
       ```
