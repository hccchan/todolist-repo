# todolist-repo
<ol>
   <li>
         after checkout the main branch, navigate to <code>/todolist-repo</code> and run command below for build the docker image from Dockerfile and start the container to bring up the application.
      <ol>
         <li><code>docker build -t todolist-app .</code></li>
         <li><code>docker run -p 8080:8080 todolist-app</code></li>
      </ol>
   </li>
   <li>
      after the container start successfully, go to <code>http://localhost:8080/login</code> to login with <b>gmail</b>.
      <ol>
         <li>after login successfully, will redirect to <code>http://localhost:8080/authenticated</code> endpoint that return a jwt token string.</li>
      </ol>
   </li>
   <li>
      for api testing can use curl command below:
      <ol>
         <li><b>GET</b> List all TODO items: </br><code>curl -X GET 'http://localhost:8080/v1/to-do-list' -H 'Authorization: Bearer {jwt token from step 2(i)}'</code></li>
         <li><b>POST</b> Add a TODO item: </br><code>curl -X POST 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"taskName":"test1","status":1}'</code></li>
         <li><b>PUT</b> Mark a TODO item as completed: </br><code>curl -X PUT 'http://localhost:8080/v1/to-do-list' -H 'Content-Type: application/json' -H 'Authorization: Bearer {jwt token from step 2.1}' -d '{"id":1,"taskName":"test1","status":2}'</code>
            <table>
               <tr>
                  <th>status</th>
                  <th>desc</th>
               </tr>
               <tr>
                  <td>1</td>
                  <td>new</td>
               </tr>
               <tr>
                  <td>2</td>
                  <td>completed</td>
               </tr>
            </table>
         </li>
         <li><b>DELETE</b> Delete a TODO item: </br><code>curl -X DELETE 'http://localhost:8080/v1/to-do-list?id=1' -H 'Authorization: Bearer {jwt token from step 2.1}'</code></li>
      </ol>
   </li>
</ol>
