## 1. Compare REST API methods (GET, POST, PUT, PATCH, DELETE) in detail.
- **GET**: Retrieves data from the server. Used for fetching resources.
- **POST**: Creates a new resource on the server. used for submitting data like form entries.
- **PUT**: Updates a resource or creates it if it doesn't exist. Used for replacing a resource with new data entirely.
- **PATCH**: Partially updates a resource.
- **DELETE**: Removes a resource from the server.


## 2. Compare SOAP vs REST API vs gRPC vs GraphQL in detail.
- **SOAP**: 
  - Uses XML for message format.
  - Built-in error handling and security
- **REST API**:
  - Uses standard HTTP methods
  - Supports multiple formats like JSON, XML, etc.
- **gRPC**:
  - Uses HTTP/2 and Protocol Buffers for communication
  - Supports bi-directional streaming
- **GraphQL**:
  - Query language for APIs
  - Allows clients to request specific data



## 3. Explain components in HTTP requests (header, body, URI, etc.), and what do they do?
- **Headers**: provide Metadata about the response and provide information on how the response should be processed
- **body**: the main content of the response
- **URI**: indicates the result of the request. It tells the client whether the request was successful or not

## 4. Explain components in HTTP responses (header, body, etc.), and what do they do?
- **Headers**: provide Metadata about the response and provide information on how the response should be processed
- **body**: the main content of the response

## 5.Compare SQL DB vs NoSQL DB in detail
- **SQL**: 
  - Relational databases
  - tables with rows and columns
  - Atomicity, Consistency, Isolation, Durability
- **NoSQL**: 
  - Non-relational databases
  - can store data in various forms
  - Scalability is generally horizontal

## 6. Explain types of SQL language and their purpose
- **DDL**: Defines the database schema.
  - Commands: CREATE, ALTER, DROP
- **DML**: Manages data within schema objects
  - Commands: SELECT, INSERT, UPDATE, DELETE
- **DCL**: Controls access to data
  - Commands: GRANT, REVOKE
- **TCL**: Manages transactions
  - Commands: COMMIT, ROLLBACK, SAVEPOINT

## 7. Explain 2xx, 4xx, 5xx HTTP status codes in detail; name some common and important codes
- **2xx**: 
  - 200 OK: The request was successful.
- **4xx**:
  - 400 Bad Request: The server couldn't understand the request due to invalid syntax.
  - 404 Not Found: The requested resource was not found.
- **5xx**:
  - 500 Internal Server Error: The server encountered an unexpected condition.

# API Design
## 1. find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
GET /customers/{customerId}/payments

## 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
GET /customers/{customerId}/orders
Parameters: startDate=2022-10-10&endDate=2022-10-24

## 3.  Find the customer's delivery addresses
GET /customers/{customerId}/addresses

## 4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be
GET /customers/{customerId}/default-info


## 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范
Use nouns for resource names /customers.
Use plurals for collections /orders


## 6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
- **GET /posts**: Retrieve all blog posts.
- **POST /posts**: Create a new blog post.
- **GET /posts/{postId}**: Retrieve a single blog post by ID.
- **PUT /posts/{postId}**: Update a blog post.
- **DELETE /posts/{postId}**: Delete a blog post.
