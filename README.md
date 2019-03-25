# spring-security
Spring boot SSO implementation


This is a working module of Spring Boot SSO over ZUUL proxy server. ZUUL is a server side proxy and can be used for mnany purposes. 
The module can be easiy extended with other functinalities which are crucial in microservice oriented architecture. 

All the resource servers make use of Authorization and Authentication server using Oauth2 JWT tokens. In this module I have used
Oauth2 authorization code grant flow along with JWT tokens. 

Initially when a user is authenticated but wants to call a secured resource he is redirected to the auth server where he needs to
put his credentials. These credentials can be stored in any kind of datastore (Here I have used in memory data store). Once these
credentials are validated a redirection occurs from auth server to the requested resource. Under the hood lot of operations takes 
place depending on the client and authorization flow itself. In this case a HTTP cookie is created in the browser and this cookie
is added to every subsequent requests. This cookie addition to the request is taken care by Zuul proxy server. Of course we can get 
control to this by using Zuul filters which are handy. Once the request reaches a resource server the token is validated and the
request is served.

TODO: 
- Add Discovery server
- Make all apps discoverable
- Centralize configuration
- Create multiple resource servers
- Support inter communication between resources.


####### Integration with another client ##########

resource-server:

This repository has authorization server, a client application (angular) and a resource server. A resource server is a just a microservice with a single protected resource or api (/resource/api/hello), you cannot acces this resource without authentication. We can have multiple of such resource servers all of them protected using the authorization server.

auth-client:

The auth-client is angular-ui running on Tomcat server. This is because we are using Oauth-Authorization code flow and we cannot use client id and secret as a part of Javascript which are used for authorization purposes. This is the reason angular-ui is made to run on Tomcat server. So for you to integarte a client application with authorization server it should be running on a server. The angular application is compiled with updated base-href and deploy-url as the compiled code is hosted on server having the its own context path. In here it was /stems and hence the angular-ui is compiled using (ng build --base-href=“/stems/” --deploy-url=“/stems/”). This is an importanat part when you have your own context path for the server. 
The client application should be registered with the authorization server with its redirection-uri, client-id and secret. Here in this sample authorization server I've used in memory client details but in production these will be saved in a database. There are few configuration on the client side of which access token uri, authorization uri, client id and secret are major (these are later sent to authorization server for getting authorization code and later for access token). 
There is also a proxy server as part of the client application for redirecting the client requests to a proper destination. This is optional but nice to have as you are having lots of resource servers and you don't want to hardcode these servers in your angular/client application.

authorization-server:

Important and ofcourse the central part is authorization-server. This is the server that handles all works related to security. This is where the client applications have the user to redirect if the user is not authenticated. Users are redirected to the login page hosted by the authorization-server were users enter their credentials and on providing valid credentials they are again redirected back to the client application based on the requested uri or the registered redirect uri. Here we can ask for users consent for authorizing the client application, users can then accept or deny and only after then they are redirected back to the client application. In this example implementation this is not the case as I have set autoapproval to true. This property can be handled in a different way in production. 
Few important things to note here are that if you have to integrate a client application with the authorization server it should be registered providing redirection uri, client-id and secret. This data in this example is stored inmemory so as user details. There are only two valid users who have usernames and passwords (uname: nithin & password: nithin, uname: admin & password: admin).
Once a user is successfully authenticated a new session gets created on the client side. This session has access token and whenever a request is made by the client application this session gets passed to the authorization server (taken care by server which is hosting the client application).  This is how authorization server will know if the request is a authenticated request or an unautheticated one. If it is not authenticated then client application should redirect user to the login page running on the authorization server.
authorization-server is also having an important resource/endpoint which is used by all resource-servers to validate if a request is authenticated or not. This end point is called as user-info-uri which returns the princpal. This information is essential for a resource server to validate a request. This validation is done for every request that reaches a resource server (a nice fit for caching). The response from the user-info-endpoint has details like username, access-token, user-roles etc which can be used by client applications (may be for handling a role based ui implementation). 
 
