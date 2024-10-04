
### List all of the annotations you learned from class and homework to annotaitons.md

### Explain TLS, PKI, certificate, public key, private key, and signature.

- **TLS** (Transport Layer Security) is a method used to keep data safe when it’s sent between two computers, like when you're browsing a website, by turning the information into code that can't be easily read
- PKI uses two types of keys: **public keys** and **private key** help keep information safe during communication
- A **certificate** is like a digital ID card for a website. It contains:
  - The public key.
  - Identity information about the website (like the domain name).
  - The CA’s signature (we’ll talk about CA next).
  - The expiration date (when the certificate is no longer valid).
  - This certificate is given to the website by a trusted organization called a Certificate Authority (CA)
- **signature**: A digital signature is a cryptographic stamp that certifies the certificate is authentic and trusted


### Write a Spring security based application, which provides https APIs (one simple get controller with empty response is good enough )instead of http, please generate a self-signed certificate to make your https TLS verfication work.
```angular2html
code in Coding/SpringSecurity-demo
```
what i have done for Spring security based application:
- Enabled Spring Security: add configuration class, activates Spring Security’s web security features and allows for custom security configurations.
- Configured URL-Level Security: In the filterChain method, you configured URL-level security using the HttpSecurity object.
- Created a Custom In-Memory User Store: This stores user credentials ( a user with username student and role User) in memory for authentication.
- Implemented Method-Level Security

what i have done for https:
- Configured Spring Boot for HTTPS: in application.properties, ensuring Spring Boot application uses HTTPS instead of HTTP by setting up SSL/TLS with the self-signed certificate
- Generated a Self-Signed Certificate:used the keytool command to create a self-signed certificate,he certificate was stored in a Java Keystore (JKS) file, and the key alias was set to myKeyAlias with a password (thePassword).


1. Pack your self-signed certificate in the form of jks file, as part of your application, name it properly
```angular2html
myKeyStore.jks
```
2. Test if you can verify your HTTPs api without importing the self-signed certificate to your local
   certificate chain, if not, explain why.
```angular2html
technically, yes, I was able to verify your HTTPS API without importing the certificate into the local certificate chain because both Postman and your browser allowed the connection despite the self-signed certificate.
```
3. Explain what did you do to make https call work, do NOT bypass TLS/SSL verfication in Postman (this
   is cheating)!

```angular2html
In postman, SSL certificate verification is off, this means Postman accepted the self-signed certificate without importing it
Reasons why still work: 
(a). The SSL certificate verification is off, Postman will ignore SSL validation, allowing the HTTPS call to succeed.
How to fix:
Add a Custom SSL Certificate in Postman

In browser, You are able to make the HTTPS request 
(b)because the browser doesn't block requests with self-signed certificates outright; it just warns you that the certificate is not from a trusted CA.
How to fix:
You would need to manually import the self-signed certificate into the browser’s or your local machine’s trusted root certificate store.
```

Tutorial: https://www.baeldung.com/spring-channel-security-https

### list all http status codes that related to authentication and authorization failures.
- 401 Unauthorized: (Authentication Fail)Failed or not provided 

- 403 Forbidden: (Authorization Fail)Succeeded but access is not allowed 

- 407 Proxy Authentication Required: Authentication is required by a proxy

- 419 Authentication Timeout (Unofficial/Non-standard): Timeout due to inactivity

- 451 Unavailable for Legal Reasons: Access restricted due to legal reasons

### Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization

- Authentication::who you are(AuthenticationManager,UserDetailsService,UserDetails,AuthenticationProvider)
- Authorization:what you are allowed to do(SecurityContext,GrantedAuthority)

**Spring Security Components for Authentication and Authorization**
- AuthenticationManager: The central interface for managing authentication(Key Method: authenticate(Authentication authentication))
- AuthenticationProvider: performing specific authentication logic, handle various types of authentication
- UserDetailsService: Spring Security uses UserDetailsService to fetch user details during the authentication process.(Key Method: UserDetails loadUserByUsername(String username))
- UserDetails: Spring Security uses UserDetails objects to check the user's credentials and to grant or deny access to specific resources.
- GrantedAuthority:  Each UserDetails object can contain multiple GrantedAuthority instances, representing the roles or permissions the user has
- SecurityContext: Holds authentication details of the current user.

### Explain HTTP Session?
An HTTP session is a way for a server to remember user data between different requests, like staying logged in while browsing a website.
### Explain Cookie?
A cookie is a small piece of data stored on the user's browser by a website to remember information, like login status or preferences, across visits.
### Compare Session and Cookie?
Storage Location:
```
Session: Data is stored on the server.
Cookie: Data is stored in the user's browser.
```
Lifetime:
```
Session: Typically expires when the user closes the browser or after a set time of inactivity.
Cookie: Can persist for a specified time, even after the browser is closed, depending on the expiration settings.
```
Security:

```
Session: Generally more secure as data is not directly accessible or modifiable by the client.
Cookie: Can be less secure since data is stored on the client side and could be modified or stolen if not properly secured.
```

Size:

```
Session: Can store larger amounts of data because it's stored on the server.
Cookie: Limited to about 4KB of data in the browser.
```

### Find at least TWO websites who can be logged in using your Google Account, explain in detail on how
   Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool:

https://www.baeldung.com/spring-channel-security-https


### How do we use session and cookie to keep user information across the the application?
We use sessions to store user information on the server, like login status, while they are using the app. When a user moves between pages, the server keeps track of them using a session ID.
We use cookies to store small pieces of information, like preferences or login tokens, directly in the user's browser. This helps the app remember the user even after they close and reopen the app, as long as the cookie hasn't expired.

### What is the spring security filter?
A Spring Security filter is a part of the security system that checks and processes incoming HTTP requests. It acts like a gatekeeper, ensuring that only authenticated and authorized users can access certain parts of the application.

### Explain bearer token and how JWT works.
JWT (JSON Web Token) is a type of bearer token that contains user data. It has three parts: a header, a payload (which holds user info), and a signature.
- The server creates the token.
- The client stores it and sends it in the request header.
- The server checks the token on each request to validate the user.


### Explain how do we store sensitive user information such as password and credit card number in DB?
- Hashing Password: Common algorithms are bcrypt or argon2, which are designed to make it hard to reverse the process. This way, even if the database is hacked, the real passwords are not exposed.
- credit card numbers are encrypted, only correct key can decrypt and access the actual data

### Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?(把这⼏
    个名字看熟悉也⾏)

### What is the disadvantage of Session? how to overcome the disadvantage?
1. Storing sessions on the server uses memory
2. Sessions expire after inactivity or browser closure
3. sessions may not be shared across servers
How to overcome:
1. using JWT(JWT is stored on the client side)
2. External Session Storage: Use Redis or a database to store sessions
3. Session Replication

### how to get value from application.properties in Spring security? 
use @Value to inject a property value from application.properties
```angular2html
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Value("${your.property.name}")
    private String yourPropertyValue;

    // Use this value in your security configuration
}

```
### What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?
- configure(HttpSecurity http): Defines security rules for HTTP requests, such as which URLs need authentication, how login/logout is handled, and how to protect against attacks like CSRF.
```angular2html
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //开启授权保护
        http
                // CSRF configuration outside of authorizeRequests
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(
                        authorize -> authorize
                                //匹配这个url
                                .requestMatchers("/getLoginUser3")
                                //拥有这个权限的用户可以访问以上url
                                //URL-level security
                                //if a request doesn't pass the URL-level security filters, it will be blocked, and the method-level security will never even be reached.
                                .hasAuthority("Admin")
                                //所有请求
                                .anyRequest()
                                //都需要登陆，没有配置Matchers只要登录成功就可以访问
                                .authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }
```
- configure(AuthenticationManagerBuilder auth):  Used to configure authentication directly
```angular2html
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("student")
        .password(passwordEncoder().encode("88888888"))
        .roles("USER");
}

```
### Reading。https://www.interviewbit.com/spring-security-interview-questions/#is-security-a-cross-cutting-concern

1. 1-12

2. 17 - 30


