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



