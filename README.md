# todolist-repo
<ol>
   <li>
         after checkout the main branch, navigate to /todolist-repo and run command below for build the docker image from Dockerfile and start the container to bring up the application.
      <ol>
         <li>-  docker build -t todolist-app .</li>
         <li>-  docker run -p 8080:8080 todolist-app</li>
      </ol>
   </li>
   <li>
      after the container start successfully, go to http://localhost:8080/login to login with gmail.
      <ol>
         <li>after login successfully, will redirect to http://localhost:8080/authenticated endpoint that return a jwt token string.</li>
      </ol>
   </li>
   <li>
      for api testing can use curl command below:
      <ol>
         <li><b>GET</b> List all TODO items: curl -X GET 'http://localhost:8080/v1/to-do-list' -H 'Authorization: Bearer {jwt token from step 2(i)}'</li>
         <li><b>POST</b> Add a TODO item: curl -X POST 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"taskName":"test1","status":1}'</li>
         <li><b>PUT</b> Mark a TODO item as completed: curl -X PUT 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"id":1,"taskName":"test1","status":2}'
            <ol>
               <li>status (1, new)</li>
               <li>status (2, completed)</li>
            </ol>
         </li>
         <li><b>DELETE</b> Delete a TODO item: curl -X DELETE 'http://localhost:8080/v1/to-do-list?id=1' -H 'Authorization: Bearer {jwt token from step 2.1}'</li>
      </ol>
   </li>
</ol>
