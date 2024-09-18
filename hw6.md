
### Short answer
#### 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.
GET: When you need to retrieve information from the server.
POST: When you need to create a new resource on the server.
PUT: When you need to completely replace an existing resource with new data.
PATCH: When you need to partially update an existing resource.
DELETE: When you need to remove a resource from the server.

#### 2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.
SOAP is best suited for enterprise-level applications that require strict standards, robust security, and complex transactions.
REST is ideal for simpler, lightweight web APIs and works well in modern web and mobile applications.
gRPC is perfect for high-performance, real-time systems, and internal microservices communication due to its speed and efficiency.
GraphQL excels when you need flexible and efficient data retrieval, especially when clients need specific data and can handle complex relationships.

#### 3. Explain components in http requests (header, body, uri etc...), and what do they do?
Request Line: Includes HTTP method, URI, and HTTP version.
Headers: Provide metadata and additional information such as content type, authentication, caching, etc.
URI: Identifies the resource to be acted upon, optionally including query parameters.
Body: Contains the data to be sent to the server (used with POST, PUT, and PATCH methods).
Cookies: Client-side data sent to the server, often for session management.

#### 4. Explain components in http responses (header, body etc...), and what do they do?
Status Line: Indicates the result of the request with an HTTP version, status code, and reason phrase.
Headers: Provide metadata about the response, such as content type, server information, and caching policies.
Body: Contains the actual data or content sent back to the client (optional for some requests).
Cookies: Sent from the server to the client to store session or other data persistently.

#### 5. Compare SQL DB vs NoSQL DB in detail
**1. Data Model:**
SQL (Relational): Structured in tables with predefined schemas and relationships between data (e.g., using foreign keys and joins).
NoSQL (Non-Relational): Flexible data models, such as document, key-value, wide-column, or graph, with dynamic schemas.
**2. Query Language:**
SQL: Uses structured query language (SQL) for complex queries, with support for joins and multi-table operations.
NoSQL: Uses APIs or database-specific query languages (e.g., MongoDB’s query language), with limited support for joins.
**3. Scalability:**
SQL: Scales vertically (upgrading hardware), with more complex horizontal scaling (sharding).
NoSQL: Designed for horizontal scaling (adding more nodes), making it easier to handle large distributed datasets.
**4. ACID vs. BASE:**
SQL: Follows ACID principles (Atomicity, Consistency, Isolation, Durability) for strong consistency.
NoSQL: Often follows BASE (Basically Available, Soft state, Eventual consistency) for better availability.
**5. Performance:**
SQL: Suited for transactional applications and structured data, but can face performance issues with very large datasets.
NoSQL: Excels in performance for high-volume, unstructured data and real-time applications, but may sacrifice consistency.
**6. Flexibility:**
SQL: Rigid schema, better for structured and predictable data models.
NoSQL: Schema-less, offering greater flexibility for evolving data structures.
**7. Use Cases:**
SQL: Ideal for financial systems, ERP, and applications requiring complex queries and relationships.
NoSQL: Best for big data, real-time applications, and scenarios where data structure changes frequently (e.g., social media, IoT).

#### 6. Explain types of SQL language, and their purpose?
**DDL**		Defines the structure of the database (tables, schemas, etc.)
**DML**		Manipulates data within the tables (retrieve, add, modify, delete records)
**DCL**		Controls user access and permissions to the database
**TCL**		Manages transactions, ensuring data integrity
**DQL**		Retrieves data from the database

#### 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain 1xx, 3xx http status code in general.
200 OK: The request has succeeded, and the requested resource is sent in the response. It is the most commonly returned status code.
201 Created: The request has been fulfilled, and a new resource has been created (commonly used with POST requests).
202 Accepted: The request has been accepted for processing, but the processing is not complete yet.
403 Forbidden: The client is authenticated but does not have permission to access the requested resource.
404 Not Found: The server cannot find the requested resource.
405 Method Not Allowed: The HTTP method (e.g., POST, GET) used in the request is not allowed for the resource.
500 Internal Server Error: A generic error message indicating that the server encountered an unexpected condition that prevented it from fulfilling the request.
501 Not Implemented: The server does not support the functionality required to fulfill the request (e.g., an unrecognized HTTP method).
502 Bad Gateway: The server, acting as a gateway or proxy, received an invalid response from the upstream server.

1xx (Informational): The request is received, and the process is continuing.
2xx (Success): The request was successfully received, understood, and accepted.
3xx (Redirection): Further action is required to complete the request.
4xx (Client Error): The request contains errors or lacks proper credentials.
5xx (Server Error): The server failed to fulfill a valid request.

### API design

#### 1. find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
Endpoint: /api/v1/customers/{customerId}/payments
Method: GET

#### 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
Endpoint: /api/v1/customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24
Method: GET

#### 3. find the customer's delievery addresses
Endpoint: /api/v1/customers/{customerId}/addresses
Method: GET

#### 4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?
Endpoint: /api/v1/customers/{customerId}/defaults
Method: GET

#### 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范
**1. PayPal API Naming Convention:**
Base URL: https://api.paypal.com
Common Endpoints:
/v1/payments/payment (create payment)
/v1/payments/payment/{paymentId} (get payment details)
/v1/payments/sale/{saleId}/refund (refund a sale)

**2. Twitter API Naming Convention:**
Base URL: https://api.twitter.com
Common Endpoints:
/2/tweets (create or fetch tweets)
/2/users/{id} (retrieve user details)
/2/tweets/search (search tweets)

#### 6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE

**Create a Blog Post**
Endpoint: /api/v1/posts
Method: POST

**Get All Blog Posts**
Endpoint: /api/v1/posts
Method: GET

**Get a Single Blog Post**
Endpoint: /api/v1/posts/{postId}
Method: GET

**Update a Blog Post**
Endpoint: /api/v1/posts/{postId}
Method: PUT

**Delete a Blog Post**
Endpoint: /api/v1/posts/{postId}
Method: DELETE
