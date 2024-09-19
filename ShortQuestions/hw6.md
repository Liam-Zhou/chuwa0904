## Short Questions
### 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.
- **GET**: Retrieves data from the server. Used for fetching resources without modifying it.
- **POST**: Creates a new resource on the server. used for submitting data like form or creating user etc.
- **PUT**: Updates a resource or creates it. It will replace the entire resource.
- **PATCH**: Partially updates a resource. Only modifies the fields provided in the request.
- **DELETE**: Deletes a resource from the server. It enforces strict message structure and format using XML.

### 2. Compare Soap vs RestAPI vs gRPC vs GraphQL in detail.
1. **SOAP**: SOAP is a protocal, which means it defines its own set of rules for communication, unlike REST, which is an architectural style.
2. **RestAPI**: REST is an architectural style that uses standard HTTP methods (GET, POST, PUT, DELETE, etc.) for communication. It is resource-based, statekess, supports multiple formats and can do caching. It has its standard methods I mention above for CRUD operations.
3. **gRPC**: gRPC is an open-source remote procedure call (RPC) framework developed by Google. It uses Protocol Buffers and it's RPC-based. It supports bidirectional streaming (client-server or server-client), which makes it efficient for real-time communication.
4. **GraphQL**: GraphQL is a query language for APIs developed by Facebook, offering flexibility in how data is requested from the server.It uses client-defined queries. It use single endpoint to conmunicate to multiple resources.

### 3.  Explain components in http requests (header, body, uri etc...), and what do they do?
1. **Header**: Provide metadata about the request, such as authentication, content type, user agent, and caching information.
2. **Body**: Contains the data being sent to the server, typically in POST, PUT, or PATCH requests (e.g., JSON, XML, or form data).
3. **URI**: Specifies the resource the client wants to interact with, including the URL. It defines the target resource for the request.

### 4. Explain components in http responses (header, body etc ...), and what do they do?
1. **Header**: Provide metadata about the request, such as authentication, content type, user agent, and caching information.
2. **Body**: Contains the data being sent to the server, typically in POST, PUT, or PATCH requests (e.g., JSON, XML, or form data).

### 5. Compare SQL DB vs NoSQL DB in detail.
- **SQL**:
  - Relational (tables with rows/columns)
  - Fixed schema
  - single server

- **NoSQL**:
  - Non-relational (key-value, document, graph)
  - Schema-less or dynamic schema
  - distributed servers

### 6. Explain types of SQL language, and their purpose?
1. **DDL (Data Definition Language)**:  Used to define and manage database structure. Commands: CREATE, ALTER, DROP, TRUNCATE.
2. **DML (Data Manipulation Language)**: Used for data manipulation within tables. Commands: SELECT, INSERT, UPDATE, DELETE.
3. **DCL (Data Control Language)**: Manages access and permissions in the database. Commands: GRANT, REVOKE.
4. **TCL (Transaction Control Language)**: Manages transactions in the database. Commands: COMMIT, ROLLBACK, SAVEPOINT. 
5. **DQL (Data Query Language)**: Primarily used for querying data. Command: SELECT.

### 7. Explain 2xx, 4xx, 5xx HTTP status codes in detail. Name some common and important codes. Explain 1xx, 3xx HTTP status code in general.
- 2xx: Success.  codes indicate that the client’s request was successfully received, understood, and accepted by the server.
  - 200 OK. 201 Created. 202 Accepted. 204 No Content.

- 4xx: Client Errors. These status codes indicate that the error was caused by the client. The request contains bad syntax, is invalid, or cannot be fulfilled by the server due to client-side issues.
  - 400 Bad Request. 401 Unauthorized. 403 Forbidden. 404 Not Found. 429 Too Many Requests.

- 5xx: Server Errors:  indicate that the server has encountered an error or is incapable of fulfilling the request due to server-side issues.
  - 500 Internal Server Error. 501 Not Implemented. 502 Bad Gateway. 503 Service Unavailable. 504 Gateway Timeout

- 1xx: Informational Responses. These status codes indicate that the server has received the request and the client should continue the process. 

- 3xx: Redirection. These status codes indicate that further action is needed from the client to complete the request, often involving a redirection to a different URL.

## API Design
### 1. Find the customer's payments, like credit card 1, credit card 2, PayPal, Apple Pay.
URL: /customers/{customerId}/payments  
Method: GET  
Description: This API fetches all the payment methods associated with a specific customer.  
Example:
  - GET /customers/123/payments
  - Response: [{ "paymentType": "CreditCard", "details": "xxxx-xxxx-xxxx-1234" }, { "paymentType": "PayPal", "email": "customer@example.com" }]

### 2. Find the customer's history orders from 10/10/2022 to 10/24/2022.
URL: /customers/{customerId}/orders?startDate={start}&endDate={end}  
Method: GET  
Description: This API retrieves the orders placed by the customer within a specific date range.  
Example:
  - GET /customers/123/orders?startDate=2022-10-10&endDate=2022-10-24
  - Response: [{ "orderId": "123", "date": "2022-10-12", "total": 99.99 }, { "orderId": "124", "date": "2022-10-20", "total": 49.99 }]

### 3. Find the customer's delivery addresses.
URL: /customers/{customerId}/addresses  
Method: GET  
Description: This API retrieves all delivery addresses associated with the customer.  
Example:
  - GET /customers/123/addresses
  - Response: [{ "type": "Home", "address": "123 Main St, City, State" }, { "type": "Office", "address": "456 Elm St, City, State" }]

### 4. If I also want to get the customer's default payment and default delivery address, what kind of the API (URL) should be?
URL: /customers/{customerId}/defaults  
Method: GET  
Description: This API retrieves the customer's default payment method and default delivery address.  
Example:
  - GET /customers/123/defaults
  - Response: {"defaultPayment": { "paymentType": "CreditCard", "details": "xxxx-xxxx-xxxx-1234" }, "defaultAddress": { "type": "Home", "address": "123 Main St, City, State" }}

### 5. Find 2 collections of APIs examples, i.e., Twitter, PayPal, YouTube, etc.
Twitter API Collection  
  - /users/{userId}/tweets (GET): Retrieve a user's tweets.  
  - /tweets/{tweetId} (DELETE): Delete a specific tweet.  
  - /tweets (POST): Create a new tweet.  
  - /users/{userId}/followers (GET): Get a list of followers for a user.  

PayPal API Collection  
  - /payments/{paymentId} (GET): Get the details of a specific payment.  
  - /payments (POST): Create a new payment.  
  - /payments/{paymentId}/refund (POST): Refund a payment.  
  - /invoices/{invoiceId} (GET): Get the details of an invoice.  


### 6. Design a collection of APIs for a Blog Website. Please specify GET, POST, PUT, DELETE.

GET /posts: Fetch all blog posts.
GET /posts/{postId}: Fetch a specific blog post by ID.  
POST /posts: Create a new blog post.  
PUT /posts/{postId}: Update a specific blog post by ID.  
DELETE /posts/{postId}: Delete a specific blog post by ID.  









