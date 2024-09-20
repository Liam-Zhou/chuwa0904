## Homework 6 

### Short Questions

1. Compare REST API methods (GET, POST, PUT, PATCH, DELETE) in detail:

    - GET: Retrieves data from the server. 
    - POST: Submits data to the server to create a new resource. 
    - PUT: Updates or replaces an existing resource with the provided data. 
    - PATCH: Partially updates an existing resource with the provided data.
    - DELETE: Deletes a resource from the server.

2. Compare SOAP vs REST API vs gRPC vs GraphQL in detail:

    - SOAP: A protocol with strict standards for message format and security, often used for enterprise applications. 
    - REST API: A flexible architecture based on HTTP methods. It's lightweight and stateless, making it suitable for web services and public APIs.
    - gRPC: A high-performance RPC framework that uses HTTP/2 for transport and Protocol Buffers as the message format. It's more efficient than REST for microservices communication.
    - GraphQL: A query language that allows clients to specify exactly what data they need.

3. Explain components in HTTP requests (header, body, URI, etc.), and what do they do?

    - Header: Contains metadata about the request, such as content type, authentication credentials, and user-agent.
    - Body: Holds the actual data being sent to the server.
    - URI: Specifies the location of the resource the request is targeting.
    - Method: Indicates the action to be performed on the resource.

4. Explain components in HTTP responses (header, body, etc.), and what do they do?

    - Header: Contains metadata about the response, such as content type, status code, and server information.
    - Body: The actual content of the response, such as the data requested by the client.
    - Status Code: Indicates the outcome of the request (e.g., 200 OK, 404 Not Found).

5. Compare SQL DB vs NoSQL DB in detail:

    - SQL DB: Relational databases that store structured data in tables with predefined schemas.
    - NoSQL DB: Non-relational databases that handle unstructured or semi-structured data. They are more flexible in terms of schema and scale horizontally. 

6. Explain types of SQL language and their purpose:

    - DDL (Data Definition Language): Commands like CREATE, ALTER, DROP for defining or modifying database structure.
    - DML (Data Manipulation Language): Commands like SELECT, INSERT, UPDATE, DELETE for managing data.
    - DCL (Data Control Language): Commands like GRANT, REVOKE for managing access permissions.
    - TCL (Transaction Control Language): Commands like COMMIT, ROLLBACK for managing transactions.

7. Explain 2xx, 4xx, 5xx HTTP status codes in detail, name some common and important codes, explain 1xx, 3xx HTTP status codes in general:

    1. 2xx (Success): Indicates successful handling of the request.
        - 200 OK: The request was successful.
        - 201 Created: A resource was successfully created.
    2. 4xx (Client Errors): Indicates issues with the request sent by the client.
        - 400 Bad Request: The request is malformed.
        - 401 Unauthorized: Authentication is required.
        - 404 Not Found: The requested resource is not available.
    3. 5xx (Server Errors): Indicates issues on the server side.
        - 500 Internal Server Error: The server encountered an error.
        - 503 Service Unavailable: The server is temporarily unavailable.
    4. 1xx (Informational): Indicates the request was received and is being processed (e.g., 100 Continue).
    5. 3xx (Redirection): Indicates the client must take additional actions to complete the request (e.g., 301 Moved Permanently).

### API Design

1. Find the customer's payments (credit card 1, credit card 2, PayPal, Apple Pay):
    ```java
    GET /customers/{customerId}/payments
    ```

2. Find the customer's history orders from 10/10/2022 to 10/24/2022:
    ```java
    GET /customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24
    ```

3. Find the customer's delivery addresses:
    ```java
    GET /customers/{customerId}/addresses
    ```

4. Get customer's default payment and default delivery address:
    ```java
    GET /customers/{customerId}/defaults
    ```

5. Find 2 collections of APIs example (e.g., Twitter, PayPal, YouTube):

    - Twitter API: A collection of endpoints like GET /tweets, POST /statuses/update.
    - PayPal API: A collection of endpoints like GET /transactions, POST /payment.

6. Design a collection of APIs for a Blog Website (GET, POST, PUT, DELETE):

    - GET /posts: Retrieve all blog posts.
    - POST /posts: Create a new blog post.
    - PUT /posts/{postId}: Update an existing blog post.
    - DELETE /posts/{postId}: Delete a blog post. 