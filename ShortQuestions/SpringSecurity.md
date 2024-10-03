# Spring Security

## 1. Annotations

## 2. Explain TLS, PKI, certificate, public key, private key, and signature.
### TLS (Transport Layer Security)
A protocol that ensures privacy and data integrity between applications over a network.

### PKI (Public Key Infrastructure)
A framework that manages digital certificates and public-key encryption, enabling secure communication and authentication.

### Certificate
A digital document that binds a public key to an identity, issued by a Certificate Authority (CA).

### Public Key
A cryptographic key that can be shared openly and is used to encrypt data or verify signatures.

### Private Key
A secret key known only to the owner, used to decrypt data or create digital signatures.

### Signature
A cryptographic method to verify the authenticity and integrity of a message or document, created using a private key.


## 3. Write a Spring security based application.

## 4. List all http status codes that related to authentication and authorization failures.
- **401 Unauthorized**
    - Indicates that the request lacks valid authentication credentials.

- **403 Forbidden**
    - The server understood the request, but it refuses to authorize it. This status is often returned when the user does not have the necessary permissions for the resource.

- **407 Proxy Authentication Required**
    - Similar to 401, but indicates that the client must first authenticate itself with the proxy.
  
- **419 Authentication Timeout**
    - Often used in frameworks to indicate that the session has expired due to inactivity, requiring the user to log in again.

- **451 Unavailable For Legal Reasons**
    - The server is refusing to process the request due to legal demands.

## 5. Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization
- **Authentication**
    - **Definition**: Verifying user identity.
    - **Purpose**: Confirming user credentials.
    - **Example**: Logging in with a username and password.
    - **Scope**: Applies to users, devices, or services.

- **Authorization**
    - **Definition**: Determining access to resources.
    - **Purpose**: Granting or denying permissions.
    - **Example**: Accessing a specific API endpoint.
    - **Scope**: Applies to resources and user roles/permissions.

## Key Components in Spring Security
1. **AuthenticationManager**: Validates user credentials.
2. **UserDetailsService**: Loads user-specific data (username, password, roles).
3. **SecurityContextHolder**: Stores the security context and authenticated user.
4. **GrantedAuthority**: Represents roles and permissions.
5. **AccessDecisionManager**: Makes decisions about resource access.
6. **SecurityFilterChain**: Filters incoming requests for security tasks.
7. **AuthenticationProvider**: Defines authentication mechanisms.
8. **WebSecurityConfigurerAdapter**: Customizes security settings for web applications.


## 6. Explain HTTP Session?
### Definition
`HttpSession` is an interface in the Java Servlet API that represents a session between a client and a server. It allows the server to maintain state across multiple requests from the same client.

### Purpose
- **Session Management**: It is used to track user sessions on a web application, enabling the server to remember information about the user across multiple requests.
- **Stateful Interaction**: Provides a way to store user-specific data, making web applications more interactive and user-friendly.

### Key Features
1. **Session ID**: Each session is identified by a unique session ID, which is usually stored in a cookie on the client's browser.
2. **Attribute Storage**: Developers can store objects as attributes in the session, allowing data to persist between requests (e.g., user preferences, shopping cart items).
3. **Timeout Management**: Sessions have a configurable timeout period after which they become invalid. This helps in managing resources and enhancing security.
4. **Concurrency Control**: Can handle concurrent access to the same session by multiple threads.

### Common Methods
- `getId()`: Returns the unique identifier for the session.
- `getAttribute(String name)`: Retrieves an attribute from the session.
- `setAttribute(String name, Object value)`: Adds or updates an attribute in the session.
- `invalidate()`: Invalidates the session, removing all attributes and preventing further access.

## 7. Explain Cookie?
### Definition
A **cookie** is a small piece of data stored on the client side (in the user's browser) by the web server. It is used to remember information about the user between requests.

### Purpose
- **Session Management**: Maintains user sessions (e.g., login status).
- **Personalization**: Stores user preferences and settings.
- **Tracking**: Monitors user behavior for analytics or advertising.

### Key Features
- **Name-Value Pair**: Cookies consist of a name and a value (e.g., `username=John`).
- **Expiration**: Cookies can have an expiration date, after which they are automatically deleted.
- **Domain and Path**: Specify which domain and path the cookie is valid for, controlling where it can be sent.
- **Secure and HttpOnly Flags**: Enhance security by restricting access to cookies (e.g., `Secure` for HTTPS only, `HttpOnly` to prevent JavaScript access).


## 8. Compare Session and Cookie?
### Definition
- **Session**: A server-side storage mechanism that maintains user data across multiple requests.
- **Cookie**: A client-side storage mechanism that holds small amounts of data in the user's browser.

### Storage Location
- **Session**: Data is stored on the server.
- **Cookie**: Data is stored on the client’s browser.

### Size Limit
- **Session**: No strict size limit, as it is stored on the server.
- **Cookie**: Typically limited to about 4 KB per cookie.

### Lifespan
- **Session**: Usually expires after a set period of inactivity or when the user logs out.
- **Cookie**: Can have a defined expiration date, which can range from a few minutes to several years.

### Security
- **Session**: More secure, as sensitive data is stored on the server. The session ID is sent as a cookie.
- **Cookie**: Less secure, as cookies can be accessed and manipulated by the client. Can be made more secure with flags like `Secure` and `HttpOnly`.

### Use Cases
- **Session**: Ideal for storing sensitive data (e.g., user authentication, shopping cart items).
- **Cookie**: Best for remembering user preferences, tracking sessions, and storing non-sensitive information.

### Example
- **Session**: Used to maintain login status or user-specific settings.
- **Cookie**: Used to remember the user's language preference or theme choice.

## 9. Find at least TWO websites who can be logged in using your Google Account, explain in detail on how Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool
### 1. YouTube
YouTube allows users to log in using their Google accounts. When you choose this option, YouTube redirects you to Google for authentication.

### 2. Spotify
Spotify also uses Google SSO to authenticate users, following a similar redirect process.


## 10. How do we use session and cookie to keep user information across the the application?
### Sessions
- **Purpose**: Store user data on the server for the duration of the user’s visit.
- **Mechanism**:
    - When a user logs in, a session is created on the server.
    - The server generates a session ID and sends it as a cookie to the client.
    - User data (like user ID or preferences) is stored in the session.
    - The session ID is used to retrieve this data on subsequent requests.

### Cookies
- **Purpose**: Store small amounts of user-related data in the client's browser.
- **Mechanism**:
    - After login, a cookie can be created to store user preferences or session IDs.
    - Cookies can be set to expire after a specific time or when the browser is closed.
    - The cookie is sent with each request to maintain user state and preferences.

### Combined Use
- **Sessions** are used for sensitive information, while **cookies** are used for non-sensitive data, providing a seamless experience by retaining user state across the application.

## 11. What is the spring security filter?
A Spring Security Filter is a component that intercepts requests and responses in a Spring web application to enforce security measures. Filters are part of the Spring Security framework and are responsible for various security-related tasks.
### Purpose
- **Authentication**: Verifies the identity of users by checking credentials.
- **Authorization**: Determines whether a user has permission to access specific resources.
- **Protection Against Attacks**: Guards against threats like CSRF (Cross-Site Request Forgery) and XSS (Cross-Site Scripting).

### How It Works
1. **Filter Chain**: Spring Security applies a chain of filters to incoming requests. Each filter performs a specific security function.
2. **Invocation Order**: Filters are executed in a defined order, allowing for layered security checks.
3. **Security Context**: Filters populate the security context with authentication and authorization details, which are used throughout the application.

### Common Filters
- **UsernamePasswordAuthenticationFilter**: Handles form-based login.
- **BasicAuthenticationFilter**: Supports HTTP Basic Authentication.
- **CsrfFilter**: Protects against CSRF attacks.
- **ExceptionTranslationFilter**: Handles security exceptions.


## 12. Explain bearer token and how JWT works.
## Bearer Token
A Bearer Token is an access token that allows the holder to access protected resources without needing additional credentials.
It is included in the HTTP Authorization header.


## JWT (JSON Web Token)
JWT is a compact, URL-safe token format used for securely transmitting information, commonly for authentication.
### Structure
1. **Header**: Contains metadata (e.g., signing algorithm).
2. **Payload**: Holds claims (user information, like user ID).
3. **Signature**: A hash created by signing the header and payload with a secret key.

### How It Works
1. **User Logs In**: User credentials are verified.
2. **Token Generation**: A signed JWT is created and sent to the client.
3. **Client Sends Token**: The JWT is included in the Authorization header for subsequent requests.
4. **Server Validates**: The server checks the token's signature to authorize access.

## 13. Explain how do we store sensitive user information such as password and credit card number in DB?
### 1. Passwords
- **Never Store Plaintext**: Always encrypt passwords.
- **Use Hashing**:
    - Convert passwords into a fixed-length string (hash).
    - Use strong algorithms like `bcrypt` or `Argon2`.
- **Add Salt**:
    - Use a random value added to the password before hashing to protect against attacks.

### 2. Credit Card Numbers
- **Encryption**:
    - Encrypt credit card numbers using strong algorithms like `AES`.
- **Tokenization**:
    - Replace the card number with a unique token for transactions, keeping the actual number secure.

## 14. Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?
### 1. UserDetailsService
- **Purpose**: Loads user-specific data.
- **Function**: Retrieves user information (like username, password, and roles) from a data source (e.g., database).
- **Method**: Typically uses the method `loadUserByUsername(String username)`.

### 2. AuthenticationProvider
- **Purpose**: Performs the actual authentication logic.
- **Function**: Validates user credentials by comparing the provided authentication details with stored user details.
- **Method**: Implements `authenticate(Authentication authentication)` method, returning a fully populated `Authentication` object if successful.

### 3. AuthenticationManager
- **Purpose**: Central interface for authentication.
- **Function**: Delegates authentication requests to multiple `AuthenticationProvider` implementations.
- **Method**: Uses the `authenticate(Authentication authentication)` method to handle authentication requests.

### 4. AuthenticationFilter
- **Purpose**: Intercepts HTTP requests for authentication.
- **Function**: Extracts authentication details (like username and password) from the request and passes them to the `AuthenticationManager`.
- **Example**: `UsernamePasswordAuthenticationFilter` is a common filter that handles login form submissions.


## 15. What is the disadvantage of Session? how to overcome the disadvantage?
## Disadvantages of Sessions
- **Scalability Issues**:
    - Sessions use server memory, which can be a problem as more users log in.

- **State Management**:
    - Sessions are stateful, making it hard to maintain user data across different servers.

- **Session Expiration**:
    - Sessions can expire after inactivity, which may disrupt the user experience.

- **Security Risks**:
    - Sessions can be vulnerable to attacks like session hijacking.

## How to Overcome Disadvantages
- **Use Stateless Authentication**:
    - Implement token-based methods like JWT to avoid server-side sessions.

- **Distributed Session Management**:
    - Use external stores like Redis to manage sessions across multiple servers.

- **Implement Session Timeout Notifications**:
    - Alert users before their session expires and allow them to extend it.

- **Secure Session Handling**:
    - Use HTTPS to protect session data and regularly change session IDs to enhance security.

## 16. how to get value from application.properties in Spring security?
Use the `@Value` annotation to access properties from `application.properties` in Spring Security application.
1. **Define Properties**: Add the following to `application.properties`:
   ```properties
   app.security.secret=mySecretKey
   app.security.timeout=30
   ```

2. **Use @Value Annotation**: Create a configuration class to access the properties:
   ```java
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.context.annotation.Configuration;

   @Configuration
   public class SecurityConfig {
       @Value("${app.security.secret}")
       private String secretKey;

       @Value("${app.security.timeout}")
       private int timeout;
   }
   ```

3. **Implement in Security Configuration**: Use the properties in your Spring Security setup:
   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().anyRequest().authenticated();
       // Utilize secretKey as needed in your security logic
   }
   ```
   

## 17. What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?
- `configure(HttpSecurity http)`: Manages web request security and access.
- `configure(AuthenticationManagerBuilder auth)`: Handles user authentication setup.
### 1. `configure(HttpSecurity http)`
- **Purpose**: Configures security for HTTP requests.
- **Role**:
    - Sets which URLs are secured and accessible.
    - Defines authentication methods (like form login).
    - Controls authorization rules (who can access what).

### 2. `configure(AuthenticationManagerBuilder auth)`
- **Purpose**: Configures authentication mechanisms.
- **Role**:
    - Specifies how user credentials are stored and validated.
    - Sets up user details services (e.g., in-memory, JDBC).

## 18. Reading
[reading](https://www.interviewbit.com/spring-security-interview-questions/#is-security-a-cross-cutting-concern)
