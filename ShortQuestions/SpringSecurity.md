# Spring Security Notes

### Annotations about spring security
- @EnableWebSecurity
- @EnableGlobalMethodSecurity
- @PreAuthorize("hasRole('ROLE_ADMIN')")
- @PostAuthorize("returnObject.owner == authentication.name")
- @PermitAll and @DenyAll

### TLS (Transport Layer Security)
- A protocol ensuring secure communication over the internet using encryption.
- Protects data integrity and privacy during transmission.

### PKI (Public Key Infrastructure)
- A framework for managing digital certificates and keys.
- Supports secure data exchange using public and private keys.

### Certificate
- A digital document that binds a **public key** to an entity (e.g., a server).
- Issued by a **Certificate Authority (CA)**, it verifies the identity of the entity.

### Public Key
- A key available to everyone to encrypt data or verify a signature.
- Part of an asymmetric key pair (with a **private key**).

### Private Key
- A secret key, kept private, used to decrypt data encrypted by the **public key** or to create a digital signature.
- Ensures secure communication and proves ownership.

### Signature
- A digital code generated using a **private key** to verify data authenticity.
- The receiver uses the **public key** to verify that the data is unaltered and sent by the owner of the private key.

### Creating a Spring Security Application with HTTPS Support
- Generate the JKS Keystore using keytool and place the **mysslcert.jks** file in the **src/main/resources**
- Configure Spring Boot to Use the Keystore in **application.properties**
- ```java
    server.port=8443
    server.ssl.key-store=classpath:mysslcert.jks
    server.ssl.key-store-password=your_keystore_password
    server.ssl.key-password=your_key_password
    server.ssl.key-store-type=JKS
    ```
- Export the Certificate as **mysslcert.crt** and configure Postman to Trust the Certificate


### Http status codes
- **401 Unauthorized**: Authentication required or failed.
- **403 Forbidden**: Authentication successful but access denied.

### Authentication and Authorization
- **Authentication**: Verifying user's identity.  
- **Authorization**: Granting access to resources based on user's identity.

### Spring Security Components

1. **AuthenticationManager**: Handles authentication logic.
2. **UserDetailsService**: Loads user-specific data for authentication.
3. **SecurityContextHolder**: Stores authentication details of the user.
4. **AccessDecisionManager**: Manages authorization decisions.
5. **GrantedAuthority**: Represents permissions for an authenticated user.

### HTTP Session and Cookies
1. **HTTP Session**: Server-side storage mechanism used to store user data across multiple HTTP requests in a session, identified by a session ID.
2. **Cookie**: Client-side data stored in the browser, used to maintain state between server and client, such as user preferences or session IDs.

### **Session vs. Cookie**:
   - **Storage Location**: Session data is stored server-side, while cookie data is stored client-side.
   - **Security**: Sessions are more secure as data is on the server; cookies can be vulnerable to client-side attacks.
   - **Lifetime**: Session expires when the user closes the browser or after a set timeout; cookies can have a longer lifespan defined by an expiration date.


**Session**:
1. Store user info server-side (`HttpSession`).
2. Send session ID to client in a cookie.
3. On subsequent requests, session ID cookie is sent to retrieve user data.

**Cookie**:
1. Store user info (e.g., token) client-side in a cookie.
2. Browser sends cookie with each request for user identification.

**Combined Use**:
- **Session** for secure, server-side data (e.g., sensitive info).
- **Cookie** for persistent login, storing session IDs or tokens.


###**Spring Security Filter**:
- A **chain of filters** that intercept HTTP requests before reaching your application's controllers.
- **Purpose**: To handle security-related tasks like **authentication**, **authorization**, **session management**, and **request validation**.
- Filters like **`UsernamePasswordAuthenticationFilter`**, **`BasicAuthenticationFilter`**, and **`ExceptionTranslationFilter`** process requests based on security rules.

- **How it works**:
  - Incoming requests pass through the filter chain.
  - Filters either allow, modify, or reject requests based on security policies.
  - After verification, requests proceed to the intended controller.

**Bearer Token**:
- Token used for authentication, sent in the `Authorization` header


**JWT (JSON Web Token)**:
- A **self-contained** token with `Header`, `Payload`, and `Signature`.
- **Process**:
1. Server generates JWT after user login.
2. Client includes JWT in headers for future requests.
3. Server verifies JWT to authorize access.

**Storing Sensitive Information**:
- **Password**: Use **hashing** with a strong algorithm (e.g., **bcrypt** or **Argon2**). Never store plaintext passwords.
- **Credit Card Number**: Encrypt using a secure encryption algorithm (e.g., **AES**), store only the **last 4 digits** if possible, and use **tokenization** to replace sensitive data.


**Disadvantage of Session**:
- **Scalability Issues**: Sessions are stored server-side, leading to increased memory usage and difficulty in scaling across multiple servers.

**Solution**:
- Use **distributed session management** with tools like **Redis** to share sessions across servers.
- Alternatively, use **stateless authentication** (e.g., **JWT**) to avoid server-side session storage.


**Get Value from `application.properties` in Spring Security**:

1. Use **`@Value` Annotation**:
   ```java
   @Value("${property.name}")
   private String propertyValue;


**`configure(HttpSecurity http)`**:
- Configures **HTTP security** like authentication rules, permitted URLs, CSRF, and session management.
- Defines what requests need authentication and the type of security (e.g., form login, HTTP Basic).

**`configure(AuthenticationManagerBuilder auth)`**:
- Configures **authentication mechanism** like in-memory, database, or LDAP.
- Sets **user details**, passwords, and the **authentication provider**.

