# HW6

Haifeng Yang

# Short Question

## 1. Compare Rest API methods (Get, Post, Put, Patch, Delete) in details

### GET

**Purpose**: is used to retrieve data from server. 

**Usage**: Used to fetch a resource or a list of resources.

**Request Body**: None (since it only get data)

**Idempotency 幂等性**: Yes (make multiple identical get request will have identical same effect). 

**Safe**: Yes

**Response**: return a 200 ok status with the resource data

~~~ http
GET /users/123
~~~



### POST

**Purpose**: send data to server to create new resource

**Usage**: typically create new resource

**Request Body**: Contains data to be added to the server (e.g., form data, JSON).

**Idempotency 幂等性**: No (make multiple identical post request will create multiple new resources). 

**Safe**: No (modifies server state by creating new resources)

**Response**: return a  `201 Created` status with the resource data, along with newly created resource in response body or a `Location` header with a link to the newly created resource

~~~http
POST /users
{
  "name": "John Doe",
  "email": "john@example.com"
}
~~~



### PUT

**Purpose**: Update an existing resource or create a resource if it does not exist.

**Usage**: Replaces the entire resource with the data provided in the request.

**Request Body**: Contains the complete data that needs to be updated.

**Idempotency 幂等性**: Yes (multiple identical PUT requests will produce the same result—replacing the resource with the same data).

**Safe**: No (modifies resource on sercer)

**Response**: return a  `200 OK` status with the resource data, along with newly created resource in response body or a `Location` header with a link to the newly created resource 

~~~http
PUT /users/123
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30
}
~~~

### PATCH

**Purpose**: Partially update an existing resources

**Usage**: Used when only specific fields of a resource need to be updated, rather than replacing the whole resources

**Request Body**: Contains only data that needs to be updated.

**Idempotency 幂等性**: Yes (multiple identical PATCH will produce same result).

**Safe**: No (modifies part of resourcae on server)

**Response**: return a  `200 OK` status with the resource data, along with newly created resource in response body or a `Location` header with a link to the newly created resource 

~~~http
PATCH /users/123
{
  "age": 31
}
~~~



### DELETE

**Purpose**: Remove a resource from the server

**Usage**:  Delete an existing resource

**Request Body**: None (the resource identifier is provided in the URL)

**Idempotency 幂等性**: Yes (multiple identical delete will have same result).

**Safe**: No (delete resourcae on server)

**Response**: Usually reponse `204 No Content` or `200 OK` to indicate success or `404 not found` if resource not exist

~~~http
DELETE /users/123
~~~

### Summary of Key Differences:

| Method     | Purpose                        | Request Body        | Idempotent | Safe | Entire Collection (eg. /customers)                           | Specific Items(eg. /customers/{id})    |
| ---------- | ------------------------------ | ------------------- | ---------- | ---- | ------------------------------------------------------------ | -------------------------------------- |
| **GET**    | Retrieve resource(s)           | None                | Yes        | Yes  | 200 OK                                                       | 200, single customer. 404 if not found |
| **POST**   | Create a new resource and Save | Yes (resource data) | No         | No   | 201 Created                                                  | 404, 409(Conflict) if already exist    |
| **PUT**    | Replace the entire resource    | Yes (complete data) | Yes        | No   | 405 (Method not allowed), unless want to replace entire collection | 200, 204(No Content), 404              |
| **PATCH**  | Partially update a resource    | Yes (partial data)  | Yes        | No   | 405 (Method not allowed), unless want to modify the collection itself | 200, 204(no content), 404              |
| **DELETE** | Delete a resource              | None                | Yes        | No   | 405 (Method not allowed), unless want to delete whole collection | 200, 404                               |

### Additional Notes:

GET PUT PATCH DELETE are all ideopotent because if they call repeatedly with the same data, the outcome will remain the same. POST is not idempotent because it creates anew resource each time.

Only GET is safe

## 2. Compare Soap vs Rest API vs gRPC vs GraphQL

These are API styles or frameworks used to facilitate communication between applications or systems. They can operate over different communication protocals such as HTTP, WebSockets, etc.

SOAP and REST are most often  used with HTTP protocol.

gRPC with HTTP/2 are high-performance service-to-service communication

GraphQL operates over HTTP but provides flexibility in how data is queried 

### Comparison Summary

| Feature            | **SOAP**                         | **REST**                      | **gRPC**                            | **GraphQL**                                    |
| ------------------ | -------------------------------- | ----------------------------- | ----------------------------------- | ---------------------------------------------- |
| **Protocol**       | Protocol                         | Architectural Style           | RPC Framework                       | Query Language for APIs                        |
| **Data Format**    | XML                              | JSON, XML, etc.               | Protobuf (binary)                   | JSON                                           |
| **Transport**      | HTTP, SMTP, etc.                 | HTTP                          | HTTP/2                              | HTTP, WebSocket                                |
| **Action**         | Command/Response                 | Resource-Oriented             | Method-Oriented                     | Client-Defined Queries                         |
| **Security**       | WS-Security                      | HTTPS (OAuth, etc.)           | SSL/TLS                             | HTTPS (OAuth, etc.)                            |
| **Performance**    | Slower due to XML overhead       | Medium (depends on data size) | High (compact binary format)        | Can be fast, but complex queries may slow down |
| **Error Handling** | SOAP Fault                       | HTTP Status Codes             | gRPC Status Codes                   | Structured Error Object                        |
| **Streaming**      | No                               | No                            | Yes (supports streaming)            | Yes (subscriptions via WebSockets)             |
| **Use Cases**      | Enterprise applications, banking | Web apps, cloud services      | Microservices, IoT, mobile services | Client-driven APIs, flexible data queries      |

### SOAP (Simple Object Access Protocol)  file too large

SOAP is a protocol used for exchanging structured information in web services. It uses **XML** to encode requests and responses and works over various transport protocols like HTTP, SMTP, or TCP. SOAP comes with built-in security (WS-Security), reliability, and ACID-compliance, making it ideal for critical, complex applications.

- Use Case
  - Large enterprises that need security, reliability, and transactional support.
  - Examples: Banking systems, financial transactions, and other services requiring strict standards and security.

### REST (Representational State Transfer) widely used in web

REST is an architectural style for creating web services. It uses standard HTTP methods (GET, POST, PUT, DELETE) and often relies on JSON for data exchange, though it can support various formats like XML. REST is stateless and resource-based, with a focus on simplicity and scalability.

### gRPC (Google Remote Procedure Call) Firebase Firestore

gRPC is a high-performance, open-source RPC framework developed by Google. It uses Protocol Buffers (Protobuf) for efficient data serialization and HTTP/2 for transport, which allows multiplexing and bidirectional streaming. gRPC is suited for internal microservice-to-microservice communication and low-latency systems.

Use Case:
High-performance, low-latency applications, especially for microservices architectures.
Examples: Internal communication between services in distributed systems, mobile apps, and IoT systems.

### GraphQL (Graph Query Language)

GraphQL is a query language for APIs that allows clients to request exactly the data they need, avoiding over-fetching or under-fetching. It provides a flexible data model and supports real-time updates (via subscriptions). GraphQL is schema-based, meaning the structure of data is predefined, but the client defines what data is returned in a request.

- Use Case
  - Applications where clients need flexible, customizable data retrieval.
  - Examples: Social media platforms, complex data-driven applications (e.g., Facebook, GitHub's API).

## 3. Explain componenets in http requests(header, body, uri, etc.), and what do they do.

### Request Line

HTTP Method(GET POST PUT PATCH DELETE) specifies what client wants to perform.

URI(Uniform Resource Identifier): The path to the resource you are trying to access on the server

HTTP Version: Specifies the http version of being used

~~~http
GET /users/123 HTTP/1.1
~~~



### Header

**Common Request Headers:**

- **Host**: Specifies the domain name of the server (e.g., `Host: www.example.com`).
- **User-Agent**: Provides information about the client (e.g., browser type, version).
- **Content-Type**: Specifies the format of the data being sent in the request body (e.g., `application/json`).
- **Authorization**: Contains credentials for authentication (e.g., a token).
- **Accept**: Specifies the media types the client can understand (e.g., `application/json`).

- meta data about the request
- HTTP Headers are NOT part of the URL
- if it's information about the request or about the client, then the header is appropriate
- headers are hidden to end-users
- globally data
- restrict Dos-attack by detecting authorization on it's header, because a header can be accessed before the body is downloaded

~~~http
GET /users/123 HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0
Authorization: Bearer token
Accept: application/json
~~~



### Body

Contains **Actual data** being sent to the server. It is mostly used in POST, PUT and PATCH requests to send data to the server, such as from inputs, JSON data or file uploads. **Body ** is typically empty in GET requests.

~~~json
{
  "name": "John Doe",
  "email": "john@example.com"
}
~~~



### URI (Uniform Resource Identifier)

**Path**: The location of the resource e.g. /user/123

**Query Parameter, Query String**: It is part of URI, appended after `?`. Key-value pairs added to the URI, usually used to filter or provide additional information

### A full request 

~~~http
POST /api/users HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0
Content-Type: application/json
Authorization: Bearer someAccessToken

{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
~~~

- Method: `POST` (Sending data to create a new user)
- URI: /api/users
- Headers: Include `Host` `User-Agent` `Content-Type` `Authorization`
- Body: JSON data with user information

## 4. Explain Components in http reponses (header, body, etc.) and what do they do.

### Status

It is the first line of response. Has

- HTTP Version
- Status Code `HTTP/1.1 200 OK` 
- Reason phrase: "OK" "Not Found"

### Response Header

Like request header, has meta info

- Content-Type: Specifies the MIME type of the body (e.g., `text/html`, `application/json`, `image/png`).
- Content-Length: The size of the response body in bytes
- Date: The date and time when the response was sent.
- Set-Cookie: Allows the server to send cookies to the client, which can be stored and sent back in future requests.
- Server: Information about the server that generated the response.
- Cache-Control: Directives for caching, such as `no-cache` or `max-age`.
- Xframe specifies the rate it accepts:  
- iframe allows or deny the iframe

### Response Body

The actual data requested by client. Can be content like HTML, JSON or XML, Image, Video or other media. 

They body is optional - responses like 204 No Content does not have body

### Optional Components

Cookie: As part of the `Set-Cookie` header, cookies can be sent from the server to the client for session management or to store user preferences.

Redirect information: If the response is a `3xx` status (redirection), the `Location` header may tell the client where to go for the next request.

## 5. Compare SQL DB vs NoSQL DB in detail

Most obviously, one is structured, while NoSQL is non structured. They differ in the structure, flexibility, performance, and use cases.

#### SQL Databases:

- **Relational Model**: SQL databases use a **relational** data model, where data is organized into **tables** (also called relations). Each table consists of **rows** (records) and **columns** (fields).
- **Schema**: SQL databases require a **predefined schema** (structure of tables, columns, and data types). This schema enforces data integrity and consistency.
- **Normalization**: Data is usually normalized into multiple related tables to avoid redundancy.

**Example**: Tables for a school database might include `Students`, `Courses`, and `Enrollments`, each with defined columns (e.g., `student_id`, `course_name`, `grade`).

#### NoSQL Databases:

- Non-relational Model

  : NoSQL databases use various non-relational data models such as:

  - **Document-based** (e.g., MongoDB): Data is stored as JSON-like documents.
  - **Key-value stores** (e.g., Redis): Data is stored as key-value pairs.
  - **Column-family stores** (e.g., Cassandra): Data is stored in columns rather than rows.
  - **Graph databases** (e.g., Neo4j): Data is stored as nodes and relationships.

- **Flexible Schema**: NoSQL databases allow for a **dynamic schema**. Data can have different fields across records, providing flexibility in data structure.

- **Denormalization**: Data is often stored in a more **denormalized** way, meaning related data is stored together to reduce the need for joins.

Reasons for SQL:

- Structured data
- Strict schema
- Relational data
- Need for complex joins
- Transactions
- Clear patterns for scaling (?)
- More established: developers, community, code, tools, etc
- Lookups by index are very fast;

Reasons for NoSQL: (easier for Scale)

- Semi-structured data
- Dynamic or flexible schema(implicit schema)
- Non-relational data
- No need for complex joins
- Store many TB (or PB) of data
- Very data intensive workload
- Very high throughput for IOPS (QPS)

| Feature            | SQL Databases                                                | NoSQL Databases                                   |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------- |
| **Data Model**     | Relational (tables)                                          | Document, Key-Value, Graph, Wide-Column           |
| **Schema**         | Fixed, predefined schema                                     | Dynamic, flexible schema                          |
| **Scalability**    | Vertical (mostly)                                            | Horizontal (scales easily)                        |
| **Transactions**   | ACID-compliant (strong consistency)                          | BASE (eventual consistency in some cases)         |
| **Query Language** | Standard SQL                                                 | Various (JSON-like queries, CQL, etc.)            |
| **Performance**    | Good for complex queries (joins)                             | High speed for large datasets, avoids joins       |
| **Best Use Cases** | Structured data, complex queries                             | Large-scale, distributed, unstructured data       |
| **Examples**       | MySQL, PostgreSQL, Oracle, MS-SQL, Server,SQLite(In memory DB) etc. | MongoDB, Cassandra, Redis, Neo4j, Graph DB, HBase |

In summary, **SQL databases** are ideal for structured data with strong consistency requirements, while **NoSQL databases** are better suited for large-scale applications with unstructured data, where flexibility and horizontal scalability are key. Each has its strengths depending on the use case.



## 6. Explain types of SQL languages and their purpose

### DDL (Data Definition Language)

More used in structure database objects. 

Common used commands `CREATE` `ALTER` `DROP` `TRUNCATE` `DELETE` 

~~~sql
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Age INT
);
~~~

### DML (Data Manipulation Language)

DML is used for **manipulating data** stored in the database. It allows you to insert, update, delete, and retrieve data in the database tables.

Common used commands: `INSERT` `UPDATE` `DELETE`

### DCL (Data Control Language)

DCL commands are used to **control access** to the data in the database, including granting or revoking permissions to users and roles. This is mainly for database security and access control.

Commonly used Commands: GRANT, REVOKE

### TCL (Transaction Control Language)

TCL is used to manage **transactions** in a database. A transaction is a logical unit of work that consists of one or more SQL statements executed as a single entity. TCL ensures that SQL commands grouped as a transaction are treated as a single unit of work, ensuring **data integrity**.

Commonly used Commands: `COMMIT` `ROLLBACK` `SAVEPOINT` `SET TRANSACTION`

### DQL (Data Query Language)

`SELECT` What we commonly used to query data.

**Conclusion:**

Each type of SQL language plays a crucial role in managing and manipulating data within a relational database. DDL manages the structure, DML manipulates the data, DCL controls access, TCL manages transactions, and DQL is used for querying the data. Understanding these different categories helps in writing efficient and secure SQL queries for interacting with databases.

| **SQL Sub-language**                   | **Purpose**                                            | **Common Commands**                                  |
| -------------------------------------- | ------------------------------------------------------ | ---------------------------------------------------- |
| **DDL (Data Definition Language)**     | Define or modify the database schema                   | `CREATE`, `ALTER`, `DROP`, `TRUNCATE`                |
| **DML (Data Manipulation Language)**   | Manipulate data within the tables                      | `INSERT`, `UPDATE`, `DELETE`                         |
| **DCL (Data Control Language)**        | Control access and permissions                         | `GRANT`, `REVOKE`                                    |
| **TCL (Transaction Control Language)** | Manage transactions for data consistency and integrity | `COMMIT`, `ROLLBACK`, `SAVEPOINT`, `SET TRANSACTION` |
| **DQL (Data Query Language)**          | Retrieve data from the database                        | `SELECT`                                             |



## 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain 1xx, 3xx http status code in general

### 2xx (Success)

Indicates the request was successfully received, understood and processed by the server

- 200 OK: a successful GET request returns the requested resources.
- 201 Created, The request has been fulfilled, and a new resource has been created as a result. Typically used with **POST** requests when a new resource is created on the server.
- 204 No Content: The server successfully processed the request, but there is no content to return. Commonly used with **DELETE** requests.

### 4xx (Client Errors)

Indicates error with the request from **client** either due to invalid syntax or inability to perform the request. Usually cased by bad request by clients.

- **400 Bad Request**: Cannot perform the request due to malformed syntax or an invalid request
  - **Example**: A client sends a malformed JSON in a `POST` request.
- **401 Unauthorized**: The request requires user authentication. The client needs to provide valid credentials to access the resource.
  - **Example**: A user tries to access a protected resource without logging in.
- **403 Forbidden**: The server understood the request, but the client does not have permission to access the resource.
  - **Example**: A user tries to access an admin page without the necessary privileges.
- **404 Not Found**: The server cannot find the requested resource. This could mean the URL is incorrect or the resource has been deleted.
  - **Example**: A user navigates to a webpage that no longer exists.
- **405 Method Not Allowed**: The HTTP method used in the request is not allowed for the requested resource.
  - **Example**: A `POST` request is made to a resource that only supports `GET`.
- **429 Too Many Requests**: The user has sent too many requests in a given amount of time (rate-limiting).
  - **Example**: A client makes too many API calls in a short period.

### 5xx (Server Errors)

Indicate the server encountered an error while processing the request. Errors are the result o f an issue on the **server** side.

**500 Internal Server Error**: The server encountered an unexpected condition that prevented it from fulfilling the request. This is a generic error message for any unhandled server error.

- **Example**: A server misconfiguration or unhandled exception in the server-side code.

**502 Bad Gateway**: The server, while acting as a gateway or proxy, received an invalid response from the upstream server.

- **Example**: A proxy server trying to reach an upstream server, but the upstream server sends an error.

**503 Service Unavailable**: The server is currently unable to handle the request due to temporary overload or maintenance.

- **Example**: A web service is down for maintenance, and the server is temporarily unavailable.

**504 Gateway Timeout**: The server, while acting as a gateway or proxy, did not receive a timely response from the upstream server.

- **Example**: A proxy server times out while waiting for a response from the backend server.

### 1xx (Informational Responses)

**1xx** indicate that request has been received and is being processed, but there is no final response yet. These are **informational responses** used in some advanced HTTP communications and are less commonly seen in typical web interaction.

### 3xx (Redirection) 

The **3xx** status codes indicate that further action is needed from the client to complete the request, typically because the resource has moved or requires a different URL. These codes are used to handle **redirections**.

### Summary Table of Key HTTP Status Codes

| **Status Code**               | **Category**       | **Description**                                              |
| ----------------------------- | ------------------ | ------------------------------------------------------------ |
| **200 OK**                    | 2xx (Success)      | Request succeeded                                            |
| **201 Created**               | 2xx (Success)      | Resource was successfully created                            |
| **204 No Content**            | 2xx (Success)      | Request succeeded, no content to return                      |
| **400 Bad Request**           | 4xx (Client Error) | Malformed request, syntax error                              |
| **401 Unauthorized**          | 4xx (Client Error) | Authentication required                                      |
| **403 Forbidden**             | 4xx (Client Error) | Access to the resource is forbidden                          |
| **404 Not Found**             | 4xx (Client Error) | Resource not found                                           |
| **405 Method Not Allowed**    | 4xx (Client Error) | HTTP method not allowed for the resource                     |
| **429 Too Many Requests**     | 4xx (Client Error) | Client has made too many requests (rate-limiting)            |
| **500 Internal Server Error** | 5xx (Server Error) | Generic server error                                         |
| **502 Bad Gateway**           | 5xx (Server Error) | Server received an invalid response from an upstream server  |
| **503 Service Unavailable**   | 5xx (Server Error) | Server is temporarily unavailable (overloaded or maintenance) |
| **504 Gateway Timeout**       | 5xx (Server Error) | Server acting as gateway timed out waiting for an upstream server |

# API Design

## Design for the following scenarios

### 1. Find the customer's payments like credit card 1, credit card 2, paypal, apple pay.

**Get** method is used to retrieve data

~~~http
GET /api/customers/{customerId}/payments
~~~

Response

~~~json
{
  "customerId": "12345",
  "payments": [
    {
      "type": "credit_card",
      "provider": "VISA",
      "last4": "1234",
      "expiry": "2024-09",
      "active": true
    },
    {
      "type": "apple_pay",
      "device": "iPhone",
      "active": false
    },
    {
      "type": "paypal",
      "email": "user@example.com",
      "active": true
    },
  ]
}

~~~



~~~http
GET /api/customers/12345/payments?type=credit_card
~~~

Response

~~~json
{
  "customerId": "12345",
  "payments": [
    {
      "type": "credit_card",
      "provider": "VISA",
      "last4": "1234",
      "expiry": "2024-09",
      "active": true
    },
    {
      "type": "credit_card",
      "provider": "MasterCard",
      "last4": "5678",
      "expiry": "2025-03",
      "active": true
    }
  ]
}

~~~

~~~http
GET /api/customers/12345/payments?type=paypal
~~~

~~~http
GET /api/customers/12345/payments?type=apple_pay
~~~



### 2. Find the cutormer's history orders from 10/10/2022 to 10/24/2022

**Get** method is used to retrieve data

~~~http
GET /api/v1/customers/{customer_id}/orders
~~~

Request:

~~~http
GET /api/v1/customers/12345/orders?start_date=2022-10-10&end_date=2022-10-24 HTTP/1.1
Host: api.example.com
Authorization: Bearer <token>
Content-Type: application/json
~~~

Response

~~~json
{
  "customer_id": 12345,
  "orders": [
    {
      "order_id": 98765,
      "order_date": "2022-10-12",
      "total_amount": 150.00,
      "items": [
        {
          "item_id": 1,
          "name": "Laptop",
          "quantity": 1,
          "price": 150.00
        }
      ]
    },
    {
      "order_id": 98766,
      "order_date": "2022-10-18",
      "total_amount": 50.00,
      "items": [
        {
          "item_id": 2,
          "name": "Mouse",
          "quantity": 2,
          "price": 25.00
        }
      ]
    }
  ]
}

~~~



### 3. Find the customer's delivery addresses

~~~http
GET /api/v1/customers/{customer_id}/addresses
~~~

~~~http
GET /api/v1/customers/12345/addresses
~~~

Request

~~~http
GET /api/v1/customers/12345/addresses HTTP/1.1
Host: api.example.com
Authorization: Bearer <token>
Content-Type: application/json
~~~

### 4. If I also want to get custormer's default payment and default delivery address, what kind of API(URL) should be.

We can either use a single endpoint to return both default, it is more efficient than returning separate response using 2 API calls

~~~http
GET /api/v1/customers/{customer_id}/defaults
~~~

~~~http
GET /api/v1/customers/12345/defaults
~~~

~~~http
GET /api/v1/customers/12345/defaults HTTP/1.1
Host: api.example.com
Authorization: Bearer <token>
Content-Type: application/json
~~~

### 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube, etc. --命名规范

### Twitter

- API Version /2/
- Resource actions (e.g., posting a tweet, following a user) are performed by HTTP methods like POST, GET, and DELETE.
- Path parameters (e.g., {user_id}).

~~~http
GET /2/users/{user_id}/tweets
~~~

### PayPal

~~~http
POST /v2/checkout/orders
~~~

~~~http
POST /v2/checkout/orders/{order_id}/capture
~~~

~~~http
GET /v2/checkout/orders/{order_id}
~~~

- Version `/v1/`, `/v2/`
- Resource and action separation: POST for creation, GET for retrieval
- Path parameter {order_id}

### 6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE

### GET

~~~http
GET /api/v1/posts
~~~

We may or not want to ban access to some Endpoint like **GET** lists of posts may return too much data. We can just **not implement** this endpoint. Or we can return a `403 Forbidden` or `404 not found` to indicate it is not allowed.

~~~http
GET /api/v1/posts?author_id=123&page=1&limit=10
~~~

~~~http
GET /api/v1/posts/{post_id}
~~~



### POST

Used to create a new post, request detail is in the request body

~~~http
POST /api/v1/posts
~~~

Request Body

~~~json
{
  "title": "New Blog Post Title",
  "content": "This is the content of the blog post...",
  "author_id": 123,
  "category_id": 5,
  "tags": ["tech", "coding"]
}
~~~

**PUT**

Update existing post

~~~http
PUT /api/v1/posts/{post_id}
~~~

~~~json
{
  "title": "Updated Blog Post Title",
  "content": "This is the updated content...",
  "category_id": 6,
  "tags": ["tech", "development"]
}
~~~

### DELETE

~~~http
DELETE /api/v1/posts/{post_id}
~~~

Request body

~~~json
{
  "title": "Updated Blog Post Title",
  "content": "This is the updated content...",
  "category_id": 6,
  "tags": ["tech", "development"]
}
~~~



## Design APIs for the following features (思考：path variable 怎么⽤？有sub resources, 哪些地⽅该⽤复数)

For the above design, there is a general pattern.

- Use Path variables for identifiers: Use path variables to represent specific resources by unique identifer (e.g., `{post_id}`, `{user_id}`).
- Use plural Nouns for Collections (一般复数在collection命名时候使用，代表有sub resources): /posts, /comments, /users
- Sub resources: /post/{postid}/comments

