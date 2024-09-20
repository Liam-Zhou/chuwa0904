# Hw6
## Shorts Questions
### Question 1
* Get: Read the data without implementing changes
* Post: Create and Save a new resourse
* Put: update or replace an existing resourse
* Patch: Update or modify an existing resource
* Delete: Delete an existing resource
The difference bewtten Put and Patch is is Put replace an exisitng data entirely but Patch modifies some parts of the data.

### Question 2
#### SOAP
* SOAP is highly structured which making it suitable for large, enterprise applications that need guaranteed reliability and security.
* SOAP can work over multiple protocols (HTTP, SMTP, etc.).
* The downside is the XML payloads are heavy, making SOAP slower and more resource-intensive.
### REST
* Rest is easy to use due to its adherence to HTTP methods and URL-based resource representation.
* Rest is able to use json and compatible to xml. Json is compact, making REST APIs faster and less resource-intensive than SOAP.
* Rest is supported by virtually all modern web and mobile applications.
* There is no binding contract on the structures use of messaging for REST so it might cause chaos.
#### gRPC
* gRPC is faster and more efficient than JSON/XML because of the compact binary format (Protobuf) and HTTP/2 for multiplexing.
* gRPC supports client-side, server-side, and bidirectional streaming.
* gRPC enforces strong contracts using Protocol Buffers, reducing potential errors.
* gRPC supports multiple programming languages with generated code from Protobuf definitions.
- gRPC protocol Buffers and gRPC concepts require more learning compared to REST/GraphQL.

#### GraphQL
* In graphQL, clients can specify exactly what data they need, reducing over-fetching and under-fetching problems inherent in REST.
* Unlike REST, which might have multiple endpoints, GraphQL uses a single endpoint for all queries.
* Schema for GraphQL strongly defines the types and structure of the API, allowing for clear validation.
- GraphQL's complex queries can negatively impact performance by causing slower response times or more intensive server resource usage.
- Caching can be more difficult compared to REST, as GraphQL’s flexibility in queries makes standard HTTP caching less effective.

### Question 3
#### Request Line:
* Contains HTTP method, URI, HTTP version
* Example: GET /users HTTP/1.1
* The request line tells the server what operation the client wants to perform on the resource and provides the location of that resource on the server.
#### HTTP Headers
* Headers are key-value pairs that provide additional information about the request, such as metadata, content type, authorization tokens, and client capabilities.
* Header Element:
  * Host: Specifies the domain name of the server (e.g., Host: www.example.com)
  * Content-Type: Indicates the format of the request body (e.g., application/json or text/html).
  * User-Agent: Identifies the client software making the request (e.g., browser, API client).
  * Authorization: Contains credentials (e.g., tokens) to authenticate the client with the server (e.g., Authorization: Bearer </token>).
  * Accept: Informs the server of the formats the client is willing to accept in the response (e.g., Accept: application/json).
  * Example:
    ```
    Host: www.example.com
    Content-Type: application/json
    User-Agent: Mozilla/5.0
    Authorization: Bearer abc123
    ```
#### URI
* The URI identifies the resource being requested. It typically includes:
  * Scheme: The protocol (e.g., http or https).
  * The server’s domain name (e.g., www.example.com).
  *  The specific resource path on the server (e.g., /users).
  *  Optional key-value pairs appended to the URI to provide additional data to the server (e.g., ?id=123&sort=asc).
* The URI tells the server what resource the client wants to access and, optionally, provides extra data in the form of query parameters to refine the request.

#### Body
* application/json: Commonly used for APIs, where the body is a JSON object.
* application/x-www-form-urlencoded: Used for form submissions, where the body contains key-value pairs in a URL-encoded format.
* The body carries the actual data to be processed by the server, such as new information to create a resource (POST) or updated data (PUT/PATCH).

### Question 4
1. Status Line:
   * HTTP version
   * Status code: A three-digit code that indicates the result of the request (200-599)
   * Reason phrase: A brief description of the status code
2. HTTP Headers
   * Content-Type: Indicates the format of the data in the response body (e.g., application/json, text/html, image/png).
   * Content-Length: Specifies the size of the response body in bytes.
   * Cache-Control: Dictates the caching behavior (e.g., no-cache, max-age=3600).
   * Set-Cookie: Sends cookies to the client, which can be used for session management (e.g., Set-Cookie: sessionId=abc123; Path=/).
   * Provides information about the server software handling the request (e.g., Server: Apache/2.4.1).
   * Used in redirect responses to provide the URL to redirect the client to (e.g., Location: https://www.example.com).
3. Response Body (Payload)
   * The body contains the actual data or content being sent in response to the client’s request. Not all responses have a body
   * application/json: Common for API responses, where data is structured in JSON format.
   * text/html: For responses that contain HTML content (typically web pages).
   * application/xml: Another structured format, often used in SOAP-based services.
   * image/jpeg, image/png: For binary image data.
  
### Question 5
#### SQL Database
* Data Structure: SQL databases are relational
* Schema: SQL databases enforce a strict schema. 
* Relationships: SQL databases use primary and foreign keys to establish relationships between tables.
#### NoSQL Databases
* Data Structure: NoSQL databases are non-relational and can store data in various formats like documents, typically in JSON or BSON format
* Schema: NoSQL database data can be stored without a predefined structure, allowing flexibility handling of unstructured data.
* Graph-based: Stores data in graph structures, suitable for relationships 

### Question 6
* DDL (Data Definition Language): DDL is used to define or alter the structure of a database and its objects, such as tables, indexes, schemas, and views. (e.g. CREATE, ALTER, DROP, TRUNCATE, )
* DML (Data Manipulation Language): DML is used to manipulate data stored within the database. These commands handle data retrieval, insertion, updating, and deletion of records (e.g. SELECT, INSERT, UPDATE, DELETE)
* DCL (Data Control Language): DCL is used to control access to data within the database. It handles user permissions and roles, ensuring that users have the right access to database objects. (e.g. GRANT, REVOKE)
* TCL (Transaction Control Language):TCL commands manage transactions within the database. A transaction is a group of SQL operations that are executed as a single unit, ensuring that the database remains in a consistent state.(e.g. COMMMIT, ROLLBACK, SAVEPOINT, SET TRANSACTION)

### Question 7
* 1xx – Informational Responses: This class of status codes indicates that the request was received, and the server is continuing to process it. 
* 2xx – Success: This class of status codes indicates that the client's request was successfully received, understood, and accepted by the server.
  1. 200 OK: The request has succeeded, and the server has returned the requested data (in the case of GET), accepted the changes (in the case of PUT), or processed the input (in the case of POST).
  2. 201 Created:  The request has been fulfilled and resulted in the creation of a new resource.
  3. 202 Accepted: The request has been accepted for processing, but the processing has not been completed yet. 
  4. 204 No Content:The server successfully processed the request, but there is no content to return
* 3xx – Redirection：This class of codes indicates that further action needs to be taken by the client to complete the request. This is often because the resource has been moved to a new location, or a redirection is required.
* 4xx – Client Errors：It indicates that there was a problem with the request from the client. These errors are typically caused by incorrect request formatting, missing authentication, or permission issues.
  1. 400 Bad Request: The server cannot process the request due to a client error.
  2. 401 Unauthorized: The request requires user authentication.
  3. 403 Forbidden: he client does not have permission to access the requested resource, even if authentication is provided.
  4. 404 Not Found: The server cannot find the requested resource.
  5. 405 Method Not Allowed: The method specified in the request is not allowed for the resource 
* 5xx – Server Errors: The 5xx class of status codes indicates that the server failed to fulfill a valid request 
  1. 500 Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.
  2. 502 Bad Gateway: The server, while acting as a gateway or proxy, received an invalid response from the upstream server.
  3. 503 Service Unavailable: The server is currently unavailable (overloaded or down for maintenance). It suggests that the client should try again later.
  4. 504 Gateway Timeout: The server, while acting as a gateway or proxy, did not receive a timely response from the upstream server.
   
## API Design
1. GET api/customers/{costumer_id}/payments
2. GET api/customers/{customer_id}/orders?start-date=2022-10-10&end-date=2022-10-24
3. GET api.customers/{customer_id}/addresses
4. * GET api.customers/{customer_id}/addresses/default
   * GET api/customers/{costumer_id}/payments/default
5. 
  * Twitter(X): Base URL: https://api.x.com/1.1/collections/
    * Example: GET https://api.x.com/1.1/collections/entries.json?id={id}
  * Paypal: Base URL: https://api-m.sandbox.paypal.com/v1/
    * Example: GET https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true
6. Base URL: /api/
  * POST
    * /api/authors/posts
    * /api/authors/posts/{postId}/comments
  * GET
    * /api/authors/posts/{postId}
    * /api/authors/posts/{postId}/comments
  * PUT
    * /api/authors/posts/{postId}
    * /api/authors/posts/{postId}/comments
  * Delete
    * /api/authors/posts/{postId}
    * /api/authors/posts/{postId}/comments