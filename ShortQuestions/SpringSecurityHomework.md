
# Spring Security Homework

## 1. List all of the annotations you learned from class and homework.
```markdown
### Spring Security Annotations
- `@EnableWebSecurity`: Enables Spring Security’s web security support.
- `@PreAuthorize`: Restricts access to a method based on the user’s role or authority.
- `@Secured`: Specifies that a user must have one or more roles to access a method.
- `@RolesAllowed`: Limits method access based on user roles.
- `@EnableGlobalMethodSecurity`: Enables method-level security.
- `@WithMockUser`: Simulates a user for testing purposes.
```

## 2. Explain TLS, PKI, certificate, public key, private key, and signature.
```markdown
### TLS
Transport Layer Security (TLS) ensures secure communication over the internet by encrypting data between client and server.

### PKI
Public Key Infrastructure (PKI) manages digital certificates and public-key encryption. It involves creating, storing, and distributing digital certificates.

### Certificate
A digital certificate contains the public key and information about the holder (subject) and issuer.

### Public Key
Used to encrypt data or verify signatures, public keys are available for anyone to access.

### Private Key
Used to decrypt data and sign messages, private keys are kept secure and never shared.

### Signature
A digital signature validates the authenticity of data, proving the sender’s identity and ensuring the data was not tampered with.
```

## 3. Write a Spring Security-based application with HTTPS.
![img.png](img.png)

### Explanation
Without importing the self-signed certificate to your local certificate chain, browsers or clients like Postman will not trust the certificate because it is not signed by a known Certificate Authority.


## 4. List all HTTP status codes related to authentication and authorization failures.
```markdown
### HTTP Status Codes
- `401 Unauthorized`: Indicates that authentication is required and has failed or has not been provided.
- `403 Forbidden`: Indicates that the request was valid, but the server is refusing to respond to it, usually due to insufficient permissions.
```

## 5. Compare authentication and authorization.
```markdown
### Authentication
Verifies the user’s identity, typically through login credentials.

### Authorization
Determines what the authenticated user is allowed to do in the system (access control).

### Spring Security Components
- `UserDetailsService`: Loads user-specific data.
- `AuthenticationProvider`: Validates credentials.
- `AuthenticationManager`: Manages authentication operations.
- `AuthenticationFilter`: Filters HTTP requests for authentication purposes.
```

## 6. Explain HTTP session.
```markdown
### HTTP Session
An HTTP session stores user-specific data on the server to persist information across multiple requests, typically identified using a session ID sent in cookies.
```

## 7. Explain Cookie.
```markdown
### Cookie
A cookie is a small piece of data sent from the server to the browser, stored locally, and included in subsequent HTTP requests to the server, enabling state management.
```

## 8. Compare Session and Cookie.
```markdown
### Session
- Stored server-side.
- Provides higher security as the session ID is shared, not the actual data.

### Cookie
- Stored client-side.
- Used for small amounts of data and allows tracking without requiring server-side resources.
```

## 9. Google SSO (Single Sign-On) Example


## 10. How do we use session and cookie to keep user information across the application?
```markdown
### Session and Cookie Usage
Sessions store user-specific data server-side, and a session ID is passed in cookies, allowing the server to identify the user across multiple requests.
```

## 11. What is the Spring Security filter?
```markdown
### Spring Security Filter
The Spring Security filter is responsible for intercepting incoming requests and applying authentication and authorization rules before the request reaches the application.
```

## 12. Explain Bearer Token and how JWT works.
```markdown
### Bearer Token
Bearer tokens are sent in the `Authorization` header in HTTP requests, often in the format `Bearer <token>`.

### JWT (JSON Web Token)
JWT consists of three parts: Header, Payload, and Signature. It is used for securely transmitting information between parties.
```

## 13. How do we store sensitive user information such as passwords in the database?
```markdown
Passwords are stored in hashed format using strong hashing algorithms like BCrypt to ensure they are not stored in plain text.
```

## 14. Compare `UserDetailService`, `AuthenticationProvider`, `AuthenticationManager`, and `AuthenticationFilter`.
```markdown
- `UserDetailsService`: Loads user data from the database.
- `AuthenticationProvider`: Validates credentials.
- `AuthenticationManager`: Orchestrates authentication by managing multiple `AuthenticationProvider` instances.
- `AuthenticationFilter`: Filters requests and ensures authentication.
```

## 15. What is the disadvantage of Session? How to overcome it?
```markdown
### Disadvantages of Session
Sessions are stored on the server, which can lead to scalability issues.

### Solution
Use token-based authentication (e.g., JWT), which removes the need for server-side session storage.
```

## 16. How to get value from `application.properties` in Spring Security?
```markdown
Use the `@Value` annotation to inject property values into your Spring beans.

```java
@Value("${app.jwt-secret}")
private String jwtSecret;
```


## 17. What is the role of `configure(HttpSecurity http)` and `configure(AuthenticationManagerBuilder auth)`?
```markdown
- `configure(HttpSecurity http)`: Configures security aspects such as access rules, login, and logout settings.
- `configure(AuthenticationManagerBuilder auth)`: Configures authentication mechanisms like in-memory, LDAP, or database authentication.
```

