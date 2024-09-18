# Database & REST API 
## 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.
- Get
  - Purpose: Retrieve data from the server.
  - Example: Fetch user details (GET /users/123)
- Post
  - Purpose: Create a new resource on the server.
  - Example: Add a new user (POST /users)
- Put
  - Purpose: Completely replace an existing resource.
  - Example: Replace all user details (PUT /users/123)
- Patch
  - Purpose: Partially update an existing resource.
  - Example: Update the user's email (PATCH /users/123)
- Delete
  - Purpose: Remove a resource from the server.
  - Example: Delete a user (DELETE /users/123)


## 2. Compare SOAP vs REST API vs gRPC vs GraphQL in detail
- SOAP
    - Type: Protocol
    - Purpose: Reliable, structured communication in enterprise applications.
    - Protocol: XML-based, strict structure.
    - Transport: Can use HTTP, SMTP, etc.
    - Use Case: Enterprise apps, bank transactions.
    - Features: Heavyweight, built-in security, reliable.

- REST API
    - Type: Architectural Style
    - Purpose: Simplify communication over the web using standard HTTP methods.
    - Style: HTTP-based, commonly uses JSON.
    - Transport: Always over HTTP.
    - Use Case: Web services, simple APIs.
    - Features: Lightweight, stateless, easy to use.

- gRPC
    - Type: Framework
    - Purpose: High-performance communication between microservices.
    - Protocol: Uses Protocol Buffers (binary format).
    - Transport: HTTP/2, supports streaming.
    - Use Case: Microservices, low-latency systems.
    - Features: High-performance, strongly typed.

- GraphQL
    - Type: Query Language
    - Purpose: Flexible data fetching, allowing clients to specify exactly what data they need.
    - Query Language: JSON, allows specific data requests.
    - Transport: Usually over HTTP.
    - Use Case: Flexible APIs, fetch specific data.
    - Features: Single endpoint, reduces over-fetching.

## 3. Explain components in HTTP Requests (header,body,url etc...),and what do they do?
- URI: Identifies the resource
  - Example: /users/123 or https://example.com/api/users/123
 
- HTTP Method: Defines the action to be performed. Including GET, POST, PUT, PATCH, DELETE

- Headers: Provide metadata about the request. Headers can also include authentication tokens and other important data.
  - Examples: Content-Type, Authorization, Accept
- Body: Contains data sent with the request (optional).
    - Example: { "name": "John Doe" }

- Query Parameters: Provide additional details or filters. Key-value pairs added to the end of the URI for refining the request.
    - Example: ?sort=asc&limit=10


## 4. Explain components in HTTP Responses (header, body, etc.), and what do they do?
- Status Code: Indicates the result of the request.
    - Example: 200 OK, 404 Not Found

- HTTP Version: Specifies the protocol version used in the response.
    - Example: HTTP/1.1, HTTP/2

- Headers: Provide metadata about the response, such as content type or cookies.
    - Examples: Content-Type, Content-Length, Set-Cookie

- Body: Contains the actual data returned by the server (optional).
    - Example: { "name": "John Doe", "age": 30 } (JSON data)

## 5. Compare SQL DB vs NoSQL DB in detail
- SQL Databases:
    - Structure: Relational, uses tables with rows and columns.
    - Schema: Fixed schema, requires defining structure beforehand.
    - Query Language: Uses SQL (Structured Query Language).
    - Data Integrity: Enforces ACID properties (Atomicity, Consistency, Isolation, Durability).
    - Use Cases: Best for structured data, transactions, and systems requiring data consistency.
    - Examples: MySQL, PostgreSQL, Oracle.

- NoSQL Databases:
    - Structure: Non-relational, can use key-value, document, graph, or columnar models.
    - Schema: Dynamic schema, flexible structure.
    - Query Language: Varies by database, often uses APIs or query languages specific to the DB.
    - Scalability: Easily scalable, often distributed.
    - Data Integrity: Follows CAP theorem (Consistency, Availability, Partition tolerance).
    - Use Cases: Ideal for big data, high-throughput workloads, and scenarios where flexibility is needed.
    - Examples: MongoDB, Cassandra, Redis.


## 6. Explain types of SQL language, and their purpose?
1. DDL (Data Definition Language):
    - Defines database structure (tables, indexes).
    - Examples: CREATE, ALTER, DROP, TRUNCATE.
    - Use: To create or modify tables, indexes, and database schemas.


2. DML (Data Manipulation Language):
    - Manages data within tables.
    - Examples: INSERT, UPDATE, DELETE, SELECT.
    - Use: To retrieve, add, modify, or delete data from tables.

3. DCL (Data Control Language):
    - Controls access to data.
    - Examples: GRANT, REVOKE.
    - Use: To give or remove user permissions and manage access control.

4. TCL (Transaction Control Language):
    - Manages transactions and data integrity.
    - Examples: COMMIT, ROLLBACK, SAVEPOINT.
    - Use: To control transaction behavior, commit or undo changes.

## 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain1xx, 3xx http status code in general.
- 1xx (Informational): The request is received and processing. 
  - Example: 102 Processing means resource is being processed.
- 2xx (Success): Request was successful. Common Codes:
  - 200 OK: Request completed successfully.
  - 201 Created: Resource created.
  - 204 No Content: Success with no data to return.
- 3xx (Redirection): Further action required to complete the request. 
  - Example:301 means Moved Permanently / redirect.
- 4xx (Client Error): Client-side errors, application mistakes. Common Codes:
  - 400 Bad Request: Invalid request format.
  - 401 Unauthorized: Authentication needed.
  - 403 Forbidden: Access denied.
  - 404 Not Found: Resource does not exist.
  - 405 Method Not Allowed: Request method not supported.
- 5xx (Server Error): Server-side failures. Common Codes:
  - 500 Internal Server Error: General server error.
  - 502 Bad Gateway: Invalid response from upstream server.
  - 503 Service Unavailable: Server is overloaded or down.
  - 504 Gateway Timeout: Server took too long to respond.



# API Design
## 1. Find the customer's payments, like credit card1, credit card2, paypal, ApplePay.
- Description: Retrieve all payment methods for a specific customer.
- Endpoint:  
`GET api/customers/{customerId}/payments`
- Response:
```json
{
  "payments": [
    { "type": "creditcard", "id": "creditCard1", "details": "Visa ending in 1234" },
    { "type": "creditcard", "id": "creditCard2", "details": "MasterCard ending in 5678" },
    { "type": "paypal", "id": "paypal1", "details": "john.doe@example.com" },
    { "type": "applepay", "id": "applepay1", "details": "Apple Pay ID" }
  ]
}
```


## 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
- Description: Retrieve all orders for a customer within the specified date range.
- Endpoint:  
`GET api/customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24`
- Response:
```json
{
  "orders": [
    { "orderId": "order1", "date": "2022-10-10", "amount": 99.00 },
    { "orderId": "order2", "date": "2022-10-22", "amount": 88.00 },
    { "orderId": "order3", "date": "2022-10-24", "amount": 77.00 }
  ]
}
```

## 3. Find the customer's delivery addresses
- Description: Retrieve all delivery addresses for a specific customer.
- Endpoint:  
`GET api/customers/{customerId}/addresses`
- Response:
```json
{
  "addresses": [
    { "id": "addr1", "street": "111 Street", "city": "Abc", "state": "CA", "zip": "12345", "country": "USA"},
    { "id": "addr2", "street": "222 Street", "city": "Abc", "state": "CA", "zip": "12346", "country": "USA"}
  ]
}
```

### 4. Get customer's default payment and default delivery address
- Description: Retrieve the default payment method for a customer.
- Endpoint 1:  
`GET api/customers/{customerId}/payments/default`
- Response 1:
```json
{
  "defaultPayment": 
    { "type": "creditcard", "id": "creditCard1", "details": "Visa ending in 1234" }
}
```
- Description: Retrieve the default address for a customer.
- Endpoint 2:  
  `GET api/customers/{customerId}/addresses/default`
- Response 2:
```json
{
  "defaultAddress": 
    { "id": "addr1", "street": "111 Street", "city": "Abc", "state": "CA", "zip": "12345", "country": "USA"}
}
```

## 5. 2 collection of APIs Examples (ie. Twitter, Paypal, Youtube etc)
Twitter API:
- Retrieve tweets for a specific user:
`GET /users/{userId}/tweets`: 
- Create a new tweet: 
`POST /tweets`
- Retrieve a specific tweet:
`GET /tweets/{tweetId}`
- Delete a tweet:
`DELETE /tweets/{tweetId}`

YouTube API:
- Retrieve details of a specific video:
`GET /videos/{videoId}`
- Upload a new video: 
`POST /videos`
- Retrieve a list of video comments:
`GET /videos/{videoId}/comments`
-Delete a specific video:
`DELETE /videos/{videoId}`

### 6. API Collection for a Blog Website
- Retrieve all blog posts: 
`GET /blogs`

- Retrieve a single blog post by ID:
`GET /blogs/{postId}`

- Create a new blog post:
`POST /blogs`

- Update an existing blog post by ID:
`PUT /blogs/{postId}`

- Delete a blog post by ID:
`DELETE /blogs/{postId}`
