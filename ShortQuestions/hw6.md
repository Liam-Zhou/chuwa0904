## 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.
### GET (Read)
Purpose: Retrieve information from the server.\
Idempotency: Yes, it is idempotent (multiple identical requests will produce the same result).\
Use Case: Fetch a resource or a collection of resources from the server (e.g., getting user data).\
Request Body: Typically not used; the data is passed in the URL as query parameters or path variables.\
Caching: GET requests can be cached as they do not alter server data.\
Response: 
Entire Collection: 200 OK, list of customers. Use pagination, sorting and filtering to navigate big lists.\
Specific Item: 200 (OK), single customer. 404 (Not Found), if ID not found or invalid.\
Content: The requested resource in JSON, XML, or HTML.\
Example: GET /api/users/123 – Fetches the user with ID 123.\
### POST (Create & Save)
Purpose: Submit data to the server to create a new resource.\
Idempotency: No, it is not idempotent (sending the same data twice may create two resources).\
Use Case: Creating a new resource, such as adding a new user or item.\
Request Body: Yes, includes the data for the new resource in the body (usually in JSON or XML).\
Caching: POST requests are generally not cached.\
Entire Collection: 201 Created, 'Location' header with link to /customers/{id} containing new ID.\
Specific Item: 404 (Not Found), 409 (Conflict) if resource already exists.\
Content: A representation of the created resource, or location to access it.\
Example: POST /api/users – Creates a new user with the data provided in the request body.\
### PUT (Update/Replace)
Purpose: Replace an existing resource with new data, or create the resource if it doesn’t exist.\
Idempotency: Yes, it is idempotent (repeated requests with the same data will result in the same state).\
Use Case: Updating a resource entirely (e.g., replacing all attributes of a user).\
Request Body: Yes, contains the full data of the resource being updated.\
Caching: Typically not cached, though some responses can be cached.\
Entire Collection: 405 (Method Not Allowed), unless you want to update/replace every resource in the entire collection.\
Specific Item: 200 OK (for successful update) or 201 Created (if the resource was created). Or 204 (No Content). 404 (Not Found), if ID not found or invalid.\
Content: The updated resource or a confirmation message.\
Example: PUT /api/users/123 – Replaces the entire user record for user with ID 123.\
### PATCH (Partial Update/Modify)
Purpose: Apply partial modifications to an existing resource.\
Idempotency: Yes, it is idempotent (applying the same changes repeatedly will result in the same state).\
Use Case: When you only need to update certain attributes of a resource, not the whole resource.\
Request Body: Yes, includes only the changes to the resource, not the full data.\
Caching: Generally not cached.\
Entire Collection: 405 (Method Not Allowed), unless you want to modify the collection itself.\
Specific Item: 200 OK (for successful update) or 204 No Content (if no content is returned). 404 (Not Found), if ID not found or invalid.\
Content: The modified resource or a confirmation message.\
Example: PATCH /api/users/123 – Updates specific fields (e.g., email or name) of the user with ID 123.\
### DELETE (Delete)
Purpose: Remove a resource from the server.\
Idempotency: Yes, it is idempotent (sending the same DELETE request repeatedly results in the same state: the resource is deleted or doesn’t exist).\
Use Case: Deleting a resource like removing a user or record.\
Request Body: Typically not used; the target resource is identified by the URL.\
Caching: DELETE requests are not cached.\
Entire Collection: 405 (Method Not Allowed), unless you want to delete the whole collection—not often desirable.\
Specific Item: 200 (OK). 404 (Not Found), if ID not found or invalid.\
Example: DELETE /api/users/123 – Deletes the user with ID 123.\

## 2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.
### SOAP (Simple Object Access Protocol)
SOAP is a protocol for exchanging structured information in web services using XML. It was one of the earliest methods used for building APIs.\
Key Features:\
Protocol: SOAP is a protocol, meaning it strictly defines how messages should be formatted and transmitted.\
Transport: SOAP typically uses HTTP/HTTPS but can work over other protocols such as SMTP.\
Message Format: Uses XML for messaging, which results in more verbose payloads compared to other formats.\
Error Handling: Provides standardized error handling using SOAP Faults.\
Security: Supports WS-Security, which provides built-in standards for security features like encryption, authentication, and authorization.\
Statefulness: SOAP can support stateful operations, although stateless is more common.\
Advantages:\
Standardized: SOAP is highly standardized, with well-defined standards for messaging, security, and transactional support.\
Extensibility: Supports additional protocols like WS-Security for secure messaging and WS-ReliableMessaging for guaranteed message delivery.\
Built-in error handling: With SOAP Faults, there’s a clear and standardized way to handle errors.\
Disadvantages:\
Verbose: SOAP’s reliance on XML makes it more heavyweight, resulting in larger message sizes.\
Complex: More complex to implement and parse compared to REST or gRPC.\
Slower: Due to the large payload sizes and strict standards, SOAP can be slower in performance.\

### REST (Representational State Transfer)
REST is an architectural style rather than a protocol. It uses HTTP and various web technologies (like JSON) to create lightweight and scalable web services.\
Key Features:\
Protocol: REST is not a protocol but an architectural style. REST APIs commonly use HTTP/HTTPS.\
Transport: Primarily uses HTTP/HTTPS.\
Message Format: REST APIs can exchange data in multiple formats such as JSON, XML, or even plain text, though JSON is the most common.\
Statelessness: REST APIs are stateless, meaning each request from a client must contain all the information needed to process the request.\
Caching: REST APIs support caching to improve performance for frequently requested data.\
Advantages:\
Lightweight: REST is less resource-intensive than SOAP and can return data in lightweight formats like JSON.\
Flexibility: Can work with multiple data formats (JSON, XML, etc.) and supports multiple types of calls (GET, POST, PUT, DELETE, PATCH).\
Scalability: Designed to scale efficiently for web applications due to its stateless nature.\
Simplicity: Easier to implement and use than SOAP.\
Disadvantages:\
Less standardized: REST is an architectural style, so different implementations can vary significantly.\
Limited built-in security: Relies on underlying protocols like HTTPS for security, unlike SOAP which has built-in security standards.\
Over-fetching/Under-fetching: REST endpoints often return more or less data than needed due to their fixed structure.\

### gRPC (gRPC Remote Procedure Call)
gRPC is an open-source framework initially developed by Google that uses HTTP/2 for transport and Protocol Buffers (protobufs) for data serialization.\
Key Features:\
Protocol: gRPC uses HTTP/2 for its transport protocol and Protocol Buffers (binary format) for serialization.\
Message Format: Uses Protocol Buffers, which are smaller and faster than JSON and XML.\
Streaming: Supports both client-side and server-side streaming, as well as bidirectional streaming.\
IDLS (Interface Definition Language): gRPC uses .proto files to define service methods and message types, enabling easy cross-language support.\
Authentication: Supports advanced features such as token-based authentication and SSL/TLS for secure communication.\
Advantages:\
High Performance: gRPC is faster due to HTTP/2 and binary data format (Protocol Buffers), making it more suitable for high-throughput environments.\
Strongly Typed: Enforces strong typing through .proto definitions, reducing ambiguity and enabling better error checking.\
Streaming: Full-duplex streaming (bi-directional) allows continuous data exchange between client and server in real-time.\
Cross-language Support: Provides seamless interoperability across multiple languages (e.g., Python, Go, Java, C++).\
Disadvantages:\
Binary Protocol: Protocol Buffers require serialization and deserialization, which can be harder to debug compared to human-readable formats like JSON.\
Not as simple as REST: Setting up gRPC can be more complex due to its requirement for Protocol Buffers and HTTP/2.\
Limited Browser Support: HTTP/2 is not universally supported by all browsers for gRPC, making it less suitable for web applications without workarounds.\

### GraphQL (Graph Query Language)
GraphQL is a query language and runtime for executing queries against your API. It allows clients to request exactly the data they need and nothing more.\
Key Features:\
Protocol: GraphQL is not a protocol; it is a query language and can work over HTTP/HTTPS or WebSockets.\
Message Format: Uses JSON for both queries and responses.\
Single Endpoint: Unlike REST, GraphQL uses a single endpoint for all interactions, allowing the client to define the structure of the response.\
Client-specified Queries: Clients specify exactly what data they need, reducing over-fetching and under-fetching.\
Real-time Support: Supports real-time data fetching through subscriptions using WebSockets.\
Advantages:\
Flexible Querying: Clients can request exactly the data they need, reducing the amount of data transferred over the network.\
Single Endpoint: All data is accessed from a single endpoint, simplifying the client-side application.\
Efficient Data Fetching: Prevents both over-fetching and under-fetching, making it highly efficient for front-end development.\
Real-time: Subscriptions enable real-time updates to clients, useful for live data or notifications.\
Disadvantages:\
Complexity: GraphQL queries can become complex, especially for beginners.\
Performance Issues: Highly flexible queries can lead to N+1 query problems if not optimized correctly.\
Caching Challenges: REST relies heavily on HTTP caching mechanisms, while GraphQL requires more sophisticated client-side caching strategies. (e.g., Apollo Client).\
Overhead for Small Applications: GraphQL can introduce unnecessary complexity for simple APIs where REST would be a better fit.\

## 3. Explain components in http requests (header, body, uri etc...), and what do they do?
HTTP headers are key-value pairs that convey metadata about the request. They appear after the request line and provide important information to both the client and server.\
Example: 
```
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0)
Accept: application/json
Authorization: Bearer <token>
```
The body is optional in HTTP requests and is typically used with methods like POST, PUT, and PATCH to send data to the server (e.g., form data, JSON payload, files).\
Example:
```
POST /api/v1/users HTTP/1.1
Host: www.example.com
Content-Type: application/json

{
  "name": "John",
  "email": "john@example.com"
}
```

## 4. Explain components in http responses (header, body etc...), and what do they do?
HTTP response headers are key-value pairs that provide additional information about the response and the server’s behavior. They inform the client about the format of the data, caching policies, authentication, and more.\
Example:
```
Content-Type: application/json
Content-Length: 256
Cache-Control: no-cache
Set-Cookie: sessionId=abc123; HttpOnly; Secure
```
The body is an optional part of an HTTP response and contains the actual data requested by the client. This is where the server sends the requested resource, such as a web page, JSON data, an image, or a file.\
Example (JSON format):
```
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## 5. Compare SQL DB vs NoSQL DB in detail
| SQL    | NoSQL |
| -------- | ------- |
| RELATIONAL DATABASE MANAGEMENT SYSTEM (RDB-MS)  | Non-relational or distributed database system.    |
| These databases have fixed or static or predefined schema | They have dynamic/flexble schema     |
| These databases are NOT suited for hierarchical data storage.    | These databases are best suited for hierarchical data storage. (==JSON style==)  |
| These databases are best suited for complex queries | These databases are NOT so good for complex queries |
| Vertically Scalable (server 32g -> 128g) | Horizontally Scalable (one server -> two server) |
| Follows ACID property | Follows CAP(consistency, availability, partition tolerance) |
| Examples: MySQL, PostgreSQL, Oracle, MS-SQL Server,SQLite(In memory DB) etc. | Examples: MongoDB, Graph DB, HBase, Neo4j,AWS Neptune, Cassandra etc. |

## 6. Explain types of SQL language, and their purpose? 
| SQL Type	| Main Commands	| Purpose |
| -------- | ------- | ------- |
|DDL (Data Definition Language)	| CREATE, ALTER, DROP, TRUNCATE	| Defines the structure of the database (tables, schemas).|
| DML (Data Manipulation Language) | SELECT, INSERT, UPDATE, DELETE	| Manages and manipulates the data within the database.
| DCL (Data Control Language) | GRANT, REVOKE |	Manages access and permissions to database objects.|
| TCL (Transaction Control Language) | COMMIT, ROLLBACK, SAVEPOINT | Controls transactions to ensure data consistency.|
| DQL (Data Query Language) | SELECT | Retrieves data from the database.|
| Embedded SQL	| N/A | Executes SQL within application code for database interaction.|

## 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain 1xx, 3xx http status code in general.
### 200 OK
The request succeeded. The result meaning of "success" depends on the HTTP method:\
GET: The resource has been fetched and transmitted in the message body.\
HEAD: The representation headers are included in the response without any message body.\
PUT or POST: The resource describing the result of the action is transmitted in the message body.\
TRACE: The message body contains the request message as received by the server.\
### 201 Created
The request succeeded, and a new resource was created as a result. This is typically the response sent after POST requests, or some PUT requests.\
### 202 Accepted
The request has been received but not yet acted upon. It is noncommittal, since there is no way in HTTP to later send an asynchronous response indicating the outcome of the request. It is intended for cases where another process or server handles the request, or for batch processing.\
### 203 Non-Authoritative Information
This response code means the returned metadata is not exactly the same as is available from the origin server, but is collected from a local or a third-party copy. This is mostly used for mirrors or backups of another resource. Except for that specific case, the 200 OK response is preferred to this status.\
### 204 No Content
There is no content to send for this request, but the headers may be useful. The user agent may update its cached headers for this resource with the new ones.\
### 205 Reset Content
Tells the user agent to reset the document which sent this request.\
### 206 Partial Content
This response code is used when the Range header is sent from the client to request only part of a resource.\
### 207 Multi-Status (WebDAV)
Conveys information about multiple resources, for situations where multiple status codes might be appropriate.\
### 208 Already Reported (WebDAV)
Used inside a <dav:propstat> response element to avoid repeatedly enumerating the internal members of multiple bindings to the same collection.\
### 226 IM Used (HTTP Delta encoding)
The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance.\
### 400 Bad Request
The server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).\

### 401 Unauthorized
Although the HTTP standard specifies "unauthorized", semantically this response means "unauthenticated". That is, the client must authenticate itself to get the requested response.\

### 402 Payment Required Experimental
This response code is reserved for future use. The initial aim for creating this code was using it for digital payment systems, however this status code is used very rarely and no standard convention exists.\

### 403 Forbidden
The client does not have access rights to the content; that is, it is unauthorized, so the server is refusing to give the requested resource. Unlike 401 Unauthorized, the client's identity is known to the server.\

### 404 Not Found
The server cannot find the requested resource. In the browser, this means the URL is not recognized. In an API, this can also mean that the endpoint is valid but the resource itself does not exist. Servers may also send this response instead of 403 Forbidden to hide the existence of a resource from an unauthorized client. This response code is probably the most well known due to its frequent occurrence on the web.\

### 405 Method Not Allowed
The request method is known by the server but is not supported by the target resource. For example, an API may not allow calling DELETE to remove a resource.\

### 406 Not Acceptable
This response is sent when the web server, after performing server-driven content negotiation, doesn't find any content that conforms to the criteria given by the user agent.\

### 407 Proxy Authentication Required
This is similar to 401 Unauthorized but authentication is needed to be done by a proxy.\

### 408 Request Timeout
This response is sent on an idle connection by some servers, even without any previous request by the client. It means that the server would like to shut down this unused connection. This response is used much more since some browsers, like Chrome, Firefox 27+, or IE9, use HTTP pre-connection mechanisms to speed up surfing. Also note that some servers merely shut down the connection without sending this message.\

### 409 Conflict
This response is sent when a request conflicts with the current state of the server.\

### 410 Gone
This response is sent when the requested content has been permanently deleted from server, with no forwarding address. Clients are expected to remove their caches and links to the resource. The HTTP specification intends this status code to be used for "limited-time, promotional services". APIs should not feel compelled to indicate resources that have been deleted with this status code.\

### 411 Length Required
Server rejected the request because the Content-Length header field is not defined and the server requires it.\

### 412 Precondition Failed
The client has indicated preconditions in its headers which the server does not meet.\

### 413 Payload Too Large
Request entity is larger than limits defined by server. The server might close the connection or return an Retry-After header field.\

### 414 URI Too Long
The URI requested by the client is longer than the server is willing to interpret.\
### 500 Internal Server Error
The server has encountered a situation it does not know how to handle.\

### 501 Not Implemented
The request method is not supported by the server and cannot be handled. The only methods that servers are required to support (and therefore that must not return this code) are GET and HEAD.\

### 502 Bad Gateway
This error response means that the server, while working as a gateway to get a response needed to handle the request, got an invalid response.\

### 503 Service Unavailable
The server is not ready to handle the request. Common causes are a server that is down for maintenance or that is overloaded. Note that together with this response, a user-friendly page explaining the problem should be sent. This response should be used for temporary conditions and the Retry-After HTTP header should, if possible, contain the estimated time before the recovery of the service. The webmaster must also take care about the caching-related headers that are sent along with this response, as these temporary condition responses should usually not be cached.\

### 504 Gateway Timeout
This error response is given when the server is acting as a gateway and cannot get a response in time.\

### 505 HTTP Version Not Supported
The HTTP version used in the request is not supported by the server.\

### 506 Variant Also Negotiates
The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself, and is therefore not a proper end point in the negotiation process.\

### 507 Insufficient Storage (WebDAV)
The method could not be performed on the resource because the server is unable to store the representation needed to successfully complete the request.\

### 508 Loop Detected (WebDAV)
The server detected an infinite loop while processing the request.\

### 510 Not Extended
Further extensions to the request are required for the server to fulfill it.\

### 511 Network Authentication Required
Indicates that the client needs to authenticate to gain network access.\

### 100 Continue
### 101 Switching Protocols
### 102 Processing (WebDAV)
### 103 Early Hints

### 300 Multiple Choices
### 301 Moved Permanently
### 302 Found
### 303 See Other
### 304 Not Modified
### 305 Use Proxy Deprecated
### 306 unused
### 307 Temporary Redirect
### 308 Permanent Redirect