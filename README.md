This sample project is for demonstrating how to use spring boot for implementing an authentication service, applying spring security. There are two authentication providers that using database authentication for username/password and JWT authentication for token.

1. The username/password authentication will be affected for POST - /login with request body 
{
  "username": "admin",
  "password": "admin"
}
There is no implementing for DB query yet so it is hard code to accept only admin/admin currently (in DatabaseUserDetailsService). It will return a token for client to use from then

2. JWT authentication will be affect for all implemented restful API, in this sample there's one API GET - /authenticate. If it uses generated token above in Authorization field of request header, it will pass the authentication. No need of using username/password

3. There 's also logout function via GET - /logout, using token above as well. However, when this API is called, the generated token will be no longer valid. User must call API /login with username/password for login then
