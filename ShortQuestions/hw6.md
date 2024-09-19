# Short Questions: #

### 1. **Compare REST API Methods (GET, POST, PUT, PATCH, DELETE):**

   - **GET**: Used to **retrieve** data from a server. It’s idempotent, meaning multiple identical requests will return the same result and doesn’t modify server state.
   - **POST**: Used to **send data** to the server to **create** a resource. It is non-idempotent, meaning sending the same request multiple times can result in different outcomes (like creating multiple resources).
   - **PUT**: Used to **update** a resource on the server. It is idempotent, meaning multiple identical requests will result in the same state on the server. Usually used when updating or replacing a resource completely.
   - **PATCH**: Similar to PUT, but used to make a **partial update** to a resource. It's not necessarily idempotent since the same patch may not always result in the same outcome depending on prior changes.
   - **DELETE**: Used to **remove** a resource from the server. It is idempotent, meaning once the resource is deleted, repeated DELETE requests will have no effect.

### 2. **Compare SOAP vs REST API vs gRPC vs GraphQL:**
   - **SOAP (Simple Object Access Protocol)**:
     - **Protocol**: Strict messaging protocol with built-in standards for error handling and security.
     - **Format**: XML-based.
     - **Overhead**: Heavy due to XML payload and additional headers.
     - **Use case**: Enterprise-level applications requiring strong security and transaction management.
   
   - **REST (Representational State Transfer)**:
     - **Style**: Architectural style using standard HTTP methods (GET, POST, etc.).
     - **Format**: Usually JSON, but can use XML, HTML, etc.
     - **Overhead**: Lighter than SOAP; simpler to implement and use.
     - **Use case**: Web services with stateless operations and when lightweight communication is preferred.
   
   - **gRPC**:
     - **Protocol**: Uses HTTP/2 for transport and protocol buffers (protobuf) for message format.
     - **Overhead**: Very lightweight, fast due to binary serialization (protobuf).
     - **Use case**: Real-time applications with low-latency requirements, microservices, mobile clients.
   
   - **GraphQL**:
     - **Query Language**: Client defines the structure of the response (exact fields it needs).
     - **Overhead**: Potentially higher on the server due to flexible queries but more efficient for the client as it only gets the requested data.
     - **Use case**: When clients need to query specific data or avoid over-fetching/under-fetching.

### 3. **Explain Components in HTTP Requests (Header, Body, URI, etc.):**
   - **URI (Uniform Resource Identifier)**: Specifies the resource location.
   - **Headers**: Metadata about the request (e.g., content type, authentication tokens, caching rules, etc.).
   - **Body**: Data sent with the request, usually in POST, PUT, or PATCH methods (e.g., JSON payload).

### 4. **Explain Components in HTTP Responses (Header, Body, etc.):**
   - **Headers**: Provide metadata about the response (e.g., content type, server info, caching).
   - **Body**: Contains the actual data returned by the server (e.g., HTML, JSON).
   - **Status Code**: Indicates the result of the request (e.g., 200 for success, 404 for not found).

### 5. **Compare SQL DB vs NoSQL DB in Detail:**
   - **SQL DB**:
     - **Structure**: Relational, schema-based (tables, rows, columns).
     - **Scalability**: Vertical scaling (increasing hardware power).
     - **ACID Compliance**: Strong consistency and transactional support.
     - **Use Case**: Applications needing structured data and complex queries (e.g., banking systems).
   
   - **NoSQL DB**:
     - **Structure**: Flexible, schema-less (key-value, document, graph).
     - **Scalability**: Horizontal scaling (adding more servers).
     - **Eventual Consistency**: Often provides eventual consistency over strong ACID guarantees.
     - **Use Case**: Applications needing high scalability, flexible schema (e.g., social networks, IoT data).

### 6. **Explain Types of SQL Language, and Their Purpose:**
   - **DDL (Data Definition Language)**: Defines the database schema (e.g., `CREATE`, `ALTER`, `DROP`).
   - **DML (Data Manipulation Language)**: Manipulates data within tables (e.g., `INSERT`, `UPDATE`, `DELETE`).
   - **DCL (Data Control Language)**: Controls access to data (e.g., `GRANT`, `REVOKE`).
   - **TCL (Transaction Control Language)**: Manages transactions (e.g., `COMMIT`, `ROLLBACK`).
   - **DQL (Data Query Language)**: Retrieves data (e.g., `SELECT`).

### 7. **Explain 2xx, 4xx, 5xx HTTP Status Codes in Detail, Names of Common and Important Codes, Explain 1xx, 3xx in General:**
   - **2xx (Success)**:
     - **200 OK**: Request succeeded, and response data is returned.
     - **201 Created**: Request succeeded, and a new resource has been created.
     - **204 No Content**: Request succeeded, but no content to return.
   - **4xx (Client Errors)**:
     - **400 Bad Request**: The request is malformed or invalid.
     - **401 Unauthorized**: Authentication is needed or failed.
     - **403 Forbidden**: The server understands the request but refuses to authorize it.
     - **404 Not Found**: The requested resource doesn’t exist.
   - **5xx (Server Errors)**:
     - **500 Internal Server Error**: The server encountered an unexpected condition.
     - **502 Bad Gateway**: The server received an invalid response from an upstream server.
     - **503 Service Unavailable**: The server is temporarily unable to handle the request.
   - **1xx (Informational)**: Indicates that the request was received and the process is continuing (e.g., 100 Continue).
   - **3xx (Redirection)**: Indicates further action is needed to complete the request (e.g., 301 Moved Permanently, 302 Found for redirections).



# API Design

### 1. **Find the Customer's Payments (Credit Card, PayPal, Apple Pay, etc.):**
   - **GET** `/customers/{customerId}/payments`
     - **Description**: Retrieve all payment methods (e.g., Credit Card 1, Credit Card 2, PayPal, Apple Pay) associated with a customer.
     - **Response**: List of payment methods for the specified customer.

### 2. **Find the Customer's History Orders from 10/10/2022 to 10/24/2022:**
   - **GET** `/customers/{customerId}/orders?from=2022-10-10&to=2022-10-24`
     - **Description**: Retrieve orders placed by the customer within the specified date range.
     - **Response**: List of orders between the dates for the specified customer.

### 3. **Find the Customer's Delivery Addresses:**
   - **GET** `/customers/{customerId}/addresses`
     - **Description**: Retrieve all delivery addresses associated with a customer.
     - **Response**: List of delivery addresses for the specified customer.

### 4. **Get Customer's Default Payment and Default Delivery Address:**
   - **GET** `/customers/{customerId}/defaults`
     - **Description**: Retrieve the customer's default payment method and default delivery address.
     - **Response**: Default payment method and delivery address for the customer.

### 5. **Find 2 Collection of API Examples (Naming Conventions):**
   - **Twitter API**:
     - **GET** `/users/{userId}/tweets`: Get the list of tweets posted by a user.
     - **POST** `/tweets`: Create a new tweet.
   - **PayPal API**:
     - **GET** `/customers/{customerId}/transactions`: Get all transactions for a customer.
     - **POST** `/payments`: Initiate a new payment for a customer.

### 6. **Design a Collection of APIs for a Blog Website (GET, POST, PUT, DELETE):**

- **Blog Posts**:
  - **GET** `/posts`: Retrieve all blog posts.
  - **GET** `/posts/{postId}`: Retrieve a specific blog post.
  - **POST** `/posts`: Create a new blog post.
  - **PUT** `/posts/{postId}`: Update a specific blog post.
  - **DELETE** `/posts/{postId}`: Delete a specific blog post.

- **Comments** (as sub-resource of posts):
  - **GET** `/posts/{postId}/comments`: Retrieve all comments for a specific post.
  - **POST** `/posts/{postId}/comments`: Add a new comment to a specific post.
  - **PUT** `/posts/{postId}/comments/{commentId}`: Update a specific comment.
  - **DELETE** `/posts/{postId}/comments/{commentId}`: Delete a specific comment.

- **Authors**:
  - **GET** `/authors`: Retrieve all authors.
  - **GET** `/authors/{authorId}`: Retrieve a specific author’s information.
  - **POST** `/authors`: Add a new author.
  - **PUT** `/authors/{authorId}`: Update an author's information.
  - **DELETE** `/authors/{authorId}`: Remove an author.

#### Notes on Path Variables and Sub-Resources:
- **Path Variable**: Use `customerId`, `postId`, etc., in the path to reference specific entities.
- **Sub-Resources**: For comments related to blog posts, use `/posts/{postId}/comments` because comments belong to a specific post.
- **Plural Usage**: Use plural nouns (`/posts`, `/authors`, etc.) for collections and sub-resources (`/comments` under `/posts`).

