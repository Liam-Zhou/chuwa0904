# HomeWork 6

Yuhang Li

## Short Questions

### Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.

- ***GET***
  **Purpose**: Retrieve data from the server.
  **Idempotent**: Yes, multiple identical requests will produce the same result.
  **Request Body**: No, data is passed through query parameters or URL path.
  **Usage**: Fetching resources or data from the server.
  **Example**: GET /users/123 retrieves the user with ID 123.

- ***POST***
  **Purpose**: Create a new resource on the server or submit data for processing.
  **Idempotent**: No, multiple identical requests may result in different outcomes.
  **Request Body**: Yes, data is sent in the body of the request.
  **Usage**: Creating a new resource, submitting a form, or processing a transaction.
  **Example**: POST /users with a request body containing user details creates a new user.

- ***PUT***
  **Purpose**: Update an existing resource or create a new resource if it doesn't exist.
  **Idempotent**: Yes, multiple identical requests will produce the same result.
  **Request Body**: Yes, data is sent in the body of the request.
  **Usage**: Updating an existing resource or creating a resource at a specified URL.
  **Example**: PUT /users/123 with a request body containing updated user details updates the user with ID 123.

- ***PATCH***
  **Purpose**: Apply partial updates to an existing resource.
  **Idempotent**: No, multiple identical requests may result in different outcomes.
  **Request Body**: Yes, data is sent in the body of the request.
  **Usage**: Updating specific fields of a resource without replacing the entire resource.
  **Example**: PATCH /users/123 with a request body containing partial user details updates only those fields of the user with ID 123.

- ***DELETE***
  **Purpose**: Remove a resource from the server.
  **Idempotent**: Yes, multiple identical requests will produce the same result.
  **Request Body**: No data is usually passed through URL path or query parameters.
  **Usage**: Deleting a resource.
  **Example**: DELETE /users/123 removes the user with ID 123.

### Compare **Soap** vs **RestAPI** vs **gRPC** vs **GraphQL** indetail.

| Feature                  | **SOAP**                           | **REST**                     | **gRPC**                      | **GraphQL**                        |
| ------------------------ | ---------------------------------- | ---------------------------- | ----------------------------- | ---------------------------------- |
| **Data Format**          | XML                                | JSON, XML                    | Protobuf                      | JSON                               |
| **Transport Protocol**   | HTTP, SMTP, TCP                    | HTTP                         | HTTP/2                        | HTTP (WebSocket for subscriptions) |
| **Interface Definition** | WSDL                               | None (documented with URIs)  | Protocol Buffers (IDL)        | GraphQL Schema (SDL)               |
| **Message Size**         | Verbose (XML)                      | Lightweight (JSON)           | Compact (binary)              | Lightweight (JSON)                 |
| **Performance**          | Slower (XML, heavy processing)     | Fast, but depends on network | Very fast (Protobuf + HTTP/2) | Fast, but depends on query         |
| **Security**             | WS-Security, SSL                   | OAuth, SSL                   | SSL/TLS, OAuth                | OAuth, SSL                         |
| **Streaming**            | No                                 | No                           | Yes (bidirectional)           | Yes (via subscriptions)            |
| **Contract**             | Strongly typed (WSDL)              | None                         | Strongly typed (IDL)          | Strongly typed (GraphQL schema)    |
| **Use Cases**            | Enterprise apps, financial systems | Web services, microservices  | Microservices, real-time apps | Complex queries, mobile/web apps   |



### Explain components in http requests (**header**,**body**,**uri** etc...), and what do they do?

| **Component**        | **Description**                                              | **Example**                                                  |
| -------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Request Line**     | Contains the HTTP method, URI, and HTTP version.             | `GET /products/123 HTTP/1.1`                                 |
| **Headers**          | Key-value pairs that provide metadata about the request (e.g., content type, auth). | `Content-Type: application/json`<br>`Authorization: Bearer token` |
| **Body**             | Contains the actual data being sent in the request, used mainly in `POST`, `PUT`, `PATCH`. | `{ "name": "John", "email": "john@example.com" }` (JSON format) |
| **URI**              | Identifies the resource on the server (can include path, query parameters, fragment). | `/products/123?sort=price#details`                           |
| **Query Parameters** | Key-value pairs in the URI to pass additional information to the server. | `/products?category=shoes&limit=10`                          |
| **HTTP Method**      | The action to perform on the resource (e.g., `GET`, `POST`, `PUT`, `DELETE`). | `POST` (to create a new resource)                            |
| **Status Line**      | In responses only, it provides the HTTP version, status code, and message. | `HTTP/1.1 200 OK`                                            |

### Explain components in http responses (**header**,**body** etc...), and what do they do?

| **Component**    | **Description**                                              | **Example**                                                  |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Status Line**  | Indicates the response's success or failure, including HTTP version, status code, and message. | `HTTP/1.1 200 OK`                                            |
| **Headers**      | Key-value pairs that provide metadata about the response (e.g., content type, cache control, cookies). | `Content-Type: application/json`<br>`Cache-Control: no-cache` |
| **Body**         | Contains the actual data being returned, like HTML, JSON, or binary content. | `{ "name": "John Doe", "email": "john.doe@example.com" }`    |
| **HTTP Version** | Specifies the HTTP protocol version used in the response.    | `HTTP/1.1`, `HTTP/2`                                         |

### Compare **SQL DB** vs **NoSQL DB** in detail

| **Aspect**         | **SQL (Relational Database)**                                | **NoSQL (Non-relational Database)**                          |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Data Model**     | Relational (Tables with rows and columns, normalized data)   | Non-relational (Key-value, document, column-family, or graph-based) |
| **Schema**         | Fixed schema, requires predefined structure                  | Dynamic schema, flexible structure                           |
| **Query Language** | SQL (Structured Query Language)                              | Varies (e.g., JSON-like queries in MongoDB, CQL in Cassandra) |
| **Transactions**   | ACID (Atomicity, Consistency, Isolation, Durability)         | BASE (Basically Available, Soft state, Eventual consistency) |
| **Scalability**    | Vertical scaling (adding resources to a single server)       | Horizontal scaling (distributing data across multiple servers) |
| **Use Cases**      | Structured data, complex queries, strong consistency (e.g., banking, ERP) | Big data, real-time applications, unstructured data (e.g., social media) |
| **Performance**    | High performance for complex queries, slower on large datasets | Optimized for large-scale, unstructured data and distributed systems |
| **Joins**          | Supports joins between tables                                | Typically avoids joins, uses denormalization or embedding    |
| **Data Integrity** | Enforces integrity via constraints (e.g., foreign keys)      | Managed at the application level, no enforced constraints    |
| **Storage**        | Row-based storage (optimized for transactional workloads)    | Varies by type (key-value, document, columnar, or graph)     |
| **Flexibility**    | Less flexible, schema changes require migration              | Highly flexible, schema can evolve without downtime          |
| **Consistency**    | Strong consistency via ACID transactions                     | Eventual consistency is common, strong consistency may reduce performance |
| **Examples**       | MySQL, PostgreSQL, Oracle, SQL Server                        | MongoDB, Cassandra, Redis, DynamoDB, Neo4j                   |

### Explain types of **SQL language**, and their purpose?

| **SQL Language Type** | **Purpose**                                                  | **Examples**                           |
| --------------------- | ------------------------------------------------------------ | -------------------------------------- |
| **DML**               | Manipulates and retrieves data in a database.                | `SELECT`, `INSERT`, `UPDATE`, `DELETE` |
| **DDL**               | Defines, modifies, and deletes the structure of database objects. | `CREATE`, `ALTER`, `DROP`, `TRUNCATE`  |
| **DCL**               | Manages access and permissions to the data.                  | `GRANT`, `REVOKE`                      |
| **TCL**               | Controls transactions and ensures data integrity.            | `COMMIT`, `ROLLBACK`, `SAVEPOINT`      |
| **DQL**               | Focuses on querying data.                                    | `SELECT`                               |

### Explain **2xx**, **4xx**, **5xx** http status codes in detail, name some common and important codes, explain 1xx, 3xx http status code in general.

| **Status Code Range** | **Category**  | **Explanation**                                              | **Common Codes**                                             |
| --------------------- | ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **1xx**               | Informational | Server has received the request and is processing it.        | `100 Continue`, `101 Switching Protocols`                    |
| **2xx**               | Success       | The request was successfully received, understood, and accepted. | `200 OK`, `201 Created`, `204 No Content`, `202 Accepted`    |
| **3xx**               | Redirection   | The client must take additional action to complete the request. | `301 Moved Permanently`, `302 Found`, `304 Not Modified`     |
| **4xx**               | Client Error  | The request contains bad syntax or cannot be fulfilled by the server. | `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `429 Too Many Requests` |
| **5xx**               | Server Error  | The server failed to fulfill a valid request due to an error. | `500 Internal Server Error`, `502 Bad Gateway`, `503 Service Unavailable`, `504 Gateway Timeout` |



## API Design

### 1.Find the customer's payments, like credit card1, credit card2, paypal, ApplePay.

#### **Endpoint:** Find Customer's Payments

- **URL:** `/api/customers/{customerId}/payments`
- **Method:** `GET`
- **Description:** Retrieves a list of payment methods associated with a specific customer.

#### **Request:**

- **Headers:**
  - `Authorization: Bearer <token>` (for authentication)
  - `Accept: application/json` (to specify the desired response format)
- **Path Parameters:**
  - `customerId` (string): The unique identifier of the customer.
- **Query Parameters:** (optional)
  - `type` (string): Filter by payment type (e.g., `credit_card`, `paypal`, `apple_pay`).

#### **Response:**

- **Status Code: 200 OK**
- **Status Code: 404 Not Found**
- **Status Code: 401 Unauthorized**

### 2.Find the customer's history orders from 10/10/2022 to 10/24/2022

#### **Endpoint: Get Customer’s Orders History**

- **URL:** `/api/customers/{customerId}/orders`
- **Method:** `GET`
- **Description:** Retrieves the customer's orders between two specified dates.

#### **Request:**

- **Headers:**
  - `Authorization: Bearer <token>` (for authentication)
  - `Accept: application/json` (to specify the desired response format)
- **Path Parameters:**
  - `customerId` (string): The unique identifier of the customer.
- **Query Parameters:**
  - `startDate` (string): The start of the date range (in `YYYY-MM-DD` format).
  - `endDate` (string): The end of the date range (in `YYYY-MM-DD` format).

#### **Example Request:**

- URL: `/api/customers/12345/orders?startDate=2022-10-10&endDate=2022-10-24`

#### **Response:**

- **Status Code: 200 OK**
- **Status Code: 404 Not Found**
- **Status Code: 400 Bad Request**
- **Status Code: 401 Unauthorized**



### 3.Find the customer's delievery addresses

#### **Endpoint: Get Customer’s Delivery Addresses**

- **URL:** `/api/customers/{customerId}/addresses`
- **Method:** `GET`
- **Description:** Retrieves a list of delivery addresses associated with a specific customer.

#### **Request:**

- **Headers:**
  - `Authorization: Bearer <token>` (for authentication)
  - `Accept: application/json` (to specify the desired response format)
- **Path Parameters:**
  - `customerId` (string): The unique identifier of the customer.

#### **Example Request:**

- URL: `/api/customers/12345/addresses`

#### **Response:**

- **Status Code: 200 OK**
- **Status Code: 404 Not Found**
- **Status Code: 401 Unauthorized**

### 4.If I also want to get customer's default payment and default delievery address, what kind of the API(URL) should be?

#### **Endpoint: Get Customer’s Default Payment and Address**

- **URL:** `/api/customers/{customerId}/defaults`
- **Method:** `GET`
- **Description:** Retrieves the default payment method and default delivery address for a specific customer.

#### **Request:**

- **Headers:**
  - `Authorization: Bearer <token>` (for authentication)
  - `Accept: application/json` (to specify the desired response format)
- **Path Parameters:**
  - `customerId` (string): The unique identifier of the customer.

#### **Example Request:**

- URL: `/api/customers/12345/defaults`

#### **Response:**

- **Status Code: 200 OK**

- **Status Code: 404 Not Found**

- **Status Code: 401 Unauthorized**

  

### 5.Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范

### 1. Twitter API

**Twitter API** uses a RESTful approach with a clear naming convention for its endpoints. Here's an overview of its structure:

#### **1.1 User Endpoints**

- Get User Profile
  - **URL:** `GET /2/users/{id}`
  - **Description:** Retrieves a user’s profile information by user ID.
- Get User Tweets
  - **URL:** `GET /2/users/{id}/tweets`
  - **Description:** Retrieves a list of tweets from a user by user ID.

#### **1.2 Tweet Endpoints**

- **Post a Tweet**
  - **URL:** `POST /2/tweets`
  - **Description:** Creates a new tweet.
- **Delete a Tweet**
  - **URL:** `DELETE /2/tweets/{id}`
  - **Description:** Deletes a tweet by tweet ID.

#### **1.3 Trends Endpoints**

- Get Trending Topics
  - **URL:** `GET /1.1/trends/place.json?id={woeid}`
  - **Description:** Retrieves trending topics for a specific location.

#### **Naming Conventions:**

- **Use plural nouns** for resource names (e.g., `users`, `tweets`).
- **Use verbs** for actions (e.g., `post`, `delete`).
- **Include resource IDs** in the URL for specific operations (e.g., `/users/{id}`).

### 2. YouTube Data API

**YouTube Data API** is used to interact with YouTube resources and follows its own naming conventions.

#### **2.1 Channel Endpoints**

- **Get Channel Information**
  - **URL:** `GET /youtube/v3/channels`
  - **Description:** Retrieves information about a channel.
- **List Videos on a Channel**
  - **URL:** `GET /youtube/v3/search`
  - **Description:** Searches for videos on a channel.

#### **2.2 Video Endpoints**

- **Get Video Details**
  - **URL:** `GET /youtube/v3/videos`
  - **Description:** Retrieves detailed information about a video.
- **Get Video Comments**
  - **URL:** `GET /youtube/v3/commentThreads`
  - **Description:** Retrieves comments for a specific video.

#### **2.3 Playlist Endpoints**

- **Get Playlist Items**
  - **URL:** `GET /youtube/v3/playlists`
  - **Description:** Retrieves items in a playlist.
- **Create a Playlist**
  - **URL:** `POST /youtube/v3/playlists`
  - **Description:** Creates a new playlist.

#### **Naming Conventions:**

- **Use plural nouns** for resource names (e.g., `channels`, `videos`, `playlists`).
- **Specify versioning** in the URL (e.g., `/youtube/v3/`).
- **Include query parameters** for filtering and specifying resources (e.g., `part`, `id`, `channelId`).





### 6.Designa collection of APIs for a Blog Website, please specify GET POST PUT DELETE

### 1. Blog Posts

#### **1.1 Get All Blog Posts**

- **URL:** `GET /api/posts`
- **Description:** Retrieves a list of all blog posts.
- Query Parameters:
  - `limit` (integer): Number of posts to return.
  - `offset` (integer): Pagination offset.

#### **1.2 Get a Single Blog Post**

- **URL:** `GET /api/posts/{postId}`
- **Description:** Retrieves details of a specific blog post by its ID.

#### **1.3 Create a New Blog Post**

- **URL:** `POST /api/posts`
- **Description:** Creates a new blog post.
- **Request Body:**

#### **1.4 Update an Existing Blog Post**

- **URL:** `PUT /api/posts/{postId}`
- **Description:** Updates an existing blog post by its ID.
- **Request Body:**

#### **1.5 Delete a Blog Post**

- **URL:** `DELETE /api/posts/{postId}`
- **Description:** Deletes a blog post by its ID.

### 2. Comments

#### **2.1 Get Comments for a Blog Post**

- **URL:** `GET /api/posts/{postId}/comments`
- **Description:** Retrieves comments for a specific blog post.
- Query Parameters:
  - `limit` (integer): Number of comments to return.
  - `offset` (integer): Pagination offset.

#### **2.2 Add a Comment to a Blog Post**

- **URL:** `POST /api/posts/{postId}/comments`
- **Description:** Adds a new comment to a specific blog post.
- **Request Body:**

#### **2.3 Update a Comment**

- **URL:** `PUT /api/posts/{postId}/comments/{commentId}`
- **Description:** Updates an existing comment by its ID.
- **Request Body:**

#### **2.4 Delete a Comment**

- **URL:** `DELETE /api/posts/{postId}/comments/{commentId}`
- **Description:** Deletes a comment by its ID.

### 3. User Profiles

#### **3.1 Get User Profile**

- **URL:** `GET /api/users/{userId}`
- **Description:** Retrieves a user’s profile information by user ID.

#### **3.2 Create a New User**

- **URL:** `POST /api/users`
- **Description:** Creates a new user profile.
- **Request Body:**

#### **3.3 Update User Profile**

- **URL:** `PUT /api/users/{userId}`
- **Description:** Updates an existing user profile by its ID.
- **Request Body:**

#### **3.4 Delete User Profile**

- **URL:** `DELETE /api/users/{userId}`
- **Description:** Deletes a user profile by its ID.

### 