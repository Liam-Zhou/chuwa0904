1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.
    | Method  | Purpose                                        | Idempotency | Use Case Examples                         |
    |---------|------------------------------------------------|-------------|-------------------------------------------|
    | **GET** | Retrieves data from the server.                | Yes         | Fetching a list of users.                 |
    | **POST**| Submits new data to the server.                | No          | Creating a new user.                      |
    | **PUT** | Replaces or updates a resource on the server.  | Yes         | Updating a user profile completely.       |
    | **PATCH**| Partially updates a resource on the server.   | No          | Updating a user's email address.          |
    | **DELETE**| Removes a resource from the server.          | Yes         | Deleting a user.                          |

2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.
    | Feature              | SOAP                           | REST                        | gRPC                                | GraphQL                              |
    |----------------------|--------------------------------|-----------------------------|-------------------------------------|--------------------------------------|
    | **Protocol**         | Strict protocol (XML-based)    | Architectural style         | RPC framework (HTTP/2)              | Query language for APIs              |
    | **Data Format**      | XML                            | JSON, XML, YAML, etc.       | Protocol Buffers (binary)           | JSON                                 |
    | **Transport**        | HTTP, SMTP, etc.               | HTTP                        | HTTP/2                             | HTTP                                 |
    | **Performance**      | Slower (heavy payloads)        | Moderate (depends on data)  | Faster (binary serialization)       | Flexible (depends on queries)        |
    | **Security**         | WS-Security                    | OAuth, JWT, HTTPS           | TLS, OAuth                         | OAuth, JWT, HTTPS                    |
    | **Statefulness**     | Can be stateful or stateless   | Stateless                   | Typically stateless                | Stateless                            |
    | **Use Case**         | Enterprise-level services      | Web services, microservices | Low latency, inter-service comm.    | Flexible data fetching for clients   |
3. Explain components in http requests (header, body, uri etc...), and what do they do?
    | Component | Description                                            | Example                                                          |
    |-----------|--------------------------------------------------------|------------------------------------------------------------------|
    | **Request Line**| Contains the method, URI, and HTTP version.      | `GET /api/v1/users HTTP/1.1`                                      |
    | **Headers**| Metadata about the request, like content type and auth.| `Content-Type: application/json`, `Authorization: Bearer token123` |
    | **Body**   | Data sent with the request (for POST, PUT, PATCH).    | `{ "name": "John Doe", "email": "john@example.com" }`             |
    | **URI**    | Identifies the resource on the server.                | `/api/v1/users/123`                                               |
4. Explain components in http responses (header, body etc...), and what do they do?
    - **Status Line**	Indicates the HTTP version, status code, and status message.	**HTTP/1.1 200 OK**
    - **Headers**	Metadata about the response, like content type, server info, etc.	**Content-Type: application/json, Server: Apache**
    - **Body**	The main content of the response, such as HTML, JSON, XML, etc.	**{ "id": 1, "name": "John Doe" }**
5. Compare SQL DB vs NoSQL DB in detail
    | Feature              | SQL Database                                | NoSQL Database                          |
    |----------------------|---------------------------------------------|-----------------------------------------|
    | **Data Model**       | Relational (tables with rows and columns)   | Non-relational (document, key-value, graph, column-family) |
    | **Schema**           | Fixed schema; predefined structure          | Dynamic schema; flexible structure      |
    | **Scalability**      | Vertical (adding more power to a single server) | Horizontal (adding more servers)        |
    | **Data Integrity**   | Strong ACID (Atomicity, Consistency, Isolation, Durability) | Eventual consistency, BASE (Basically Available, Soft state, Eventually consistent) |
    | **Query Language**   | SQL (Structured Query Language)             | Varies (e.g., MongoDB's query language, Cassandra Query Language) |
    | **Use Case**         | Complex queries, transactions, and consistency | Large-scale, distributed data, and flexible data models |
    | **Examples**         | MySQL, PostgreSQL, Oracle                   | MongoDB, Cassandra, Redis               |
6. Explain types of SQL language, and their purpose?
    | Type             | Full Form                     | Purpose                                            | Example Statements                   |
    |------------------|-------------------------------|----------------------------------------------------|--------------------------------------|
    | **DDL**          | Data Definition Language      | Defines and manages database schema and structure. | `CREATE`, `ALTER`, `DROP`, `TRUNCATE`|
    | **DML**          | Data Manipulation Language    | Manipulates data stored in the database.           | `INSERT`, `UPDATE`, `DELETE`, `SELECT` |
    | **DCL**          | Data Control Language         | Controls access to data in the database.           | `GRANT`, `REVOKE`                    |
    | **TCL**          | Transaction Control Language  | Manages transactions within the database.          | `COMMIT`, `ROLLBACK`, `SAVEPOINT`    |
7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain 1xx,3xx http status code in general.
    - **2xx**: Successful Responses
        - 200 OK: The most common response, indicating that the request was successful.
        - 201 Created: Often used in POST requests when a new resource is created, such as creating a new user.
        - 202 Accepted: Indicates that the request has been accepted for processing, but the processing has not been completed (useful for async operations).
        - 204 No Content: Typically used in DELETE operations when the resource was successfully deleted.
    - **4xx**: Client Error Responses
        - 400 Bad Request: Indicates that the server could not understand the request due to malformed syntax.
        - 401 Unauthorized: Indicates that authentication is required and has either failed or not been provided.
        - 403 Forbidden: Indicates that the server understood the request but refuses to authorize it.
        - 404 Not Found: Indicates that the server could not find the requested resource.
        - 409 Conflict: Indicates a conflict in the request, such as a duplicate record.
        - 429 Too Many Requests: Indicates that the user has sent too many requests in a given time frame.
    - **5xx**: Server Error Responses
        - 500 Internal Server Error: A generic error message when the server encounters an unexpected condition.
        - 501 Not Implemented: Indicates that the server does not support the functionality required to fulfill the request.
        - 502 Bad Gateway: Indicates that the server received an invalid response from an upstream server.
        - 503 Service Unavailable: Indicates that the server is temporarily unable to handle the request (e.g., maintenance or overload).
        - 504 Gateway Timeout: Indicates that the server did not receive a timely response from an upstream server.
    - **1xx**: Informational Responses
    - **3xx**: Redirection Responses
8. Restful APIs Design
    1. REST API for Finding Customer's Payments
        - Endpoint: /api/customers/{customerId}/payments
            * Method: GET
            * Description: Retrieve a list of the customer's payment methods.
            * Example URL: /api/customers/123/payments
    2. REST API for Finding Customer's Order History
        - Endpoint: /api/customers/{customerId}/orders

            * Method: GET
            * Description: Retrieve a list of the customer's orders within a specified date range.
            * Query Parameters: startDate and endDate
            * Example URL: /api/customers/123/orders?startDate=2022-10-10&endDate=2022-10-24
    3. REST API for Finding Customer's Delivery Addresses
        - Endpoint: /api/customers/{customerId}/addresses

            * Method: GET
            * Description: Retrieve a list of the customer's delivery addresses.
            * Example URL: /api/customers/123/addresses
    4. REST API for Getting Default Payment and Delivery Address
        - Endpoint: /api/customers/{customerId}/defaults

            * Method: GET
            * Description: Retrieve the customer's default payment method and default delivery address.
            * Example URL: /api/customers/123/defaults
    5. Collections of API Examples
        - Twitter API Naming Conventions:
            - Base URL: https://api.twitter.com/
            - Example Endpoints:
                - GET /2/tweets/{id}: Retrieve a specific tweet by ID.
                - POST /2/tweets: Post a new tweet.
                - GET /2/users/{id}/followers: Get the followers of a user.
        - PayPal API Naming Conventions:
            - Base URL: https://api.paypal.com/
            - Example Endpoints:
                - POST /v2/checkout/orders: Create a new order.
                - GET /v2/checkout/orders/{order_id}: Get details of a specific order.
                - POST /v1/payments/payment: Make a payment.
    6. Collection of APIs for a Blog Website
        1. **Create a New Blog Post**
        - **Endpoint**: `/api/posts`
        - **Method**: `POST`
        - **Description**: Create a new blog post.
        - **Request Body**:
            ```json
            {
            "title": "My New Post",
            "content": "This is the content of the blog post.",
            "authorId": "123"
            }
            ```
        - **Response**:
            ```json
            {
            "postId": "456",
            "message": "Blog post created successfully."
            }
            ```

        2. **Retrieve All Blog Posts**
        - **Endpoint**: `/api/posts`
        - **Method**: `GET`
        - **Description**: Retrieve a list of all blog posts.
        - **Response**:
            ```json
            [
            {
                "postId": "456",
                "title": "My New Post",
                "content": "This is the content of the blog post.",
                "authorId": "123"
            },
            {
                "postId": "457",
                "title": "Another Post",
                "content": "More content here.",
                "authorId": "124"
            }
            ]
            ```

        3. **Retrieve a Specific Blog Post**
        - **Endpoint**: `/api/posts/{postId}`
        - **Method**: `GET`
        - **Description**: Retrieve a specific blog post by ID.
        - **Example URL**: `/api/posts/456`
        - **Response**:
            ```json
            {
            "postId": "456",
            "title": "My New Post",
            "content": "This is the content of the blog post.",
            "authorId": "123"
            }
            ```

        4. **Update a Blog Post**
        - **Endpoint**: `/api/posts/{postId}`
        - **Method**: `PUT`
        - **Description**: Update an existing blog post.
        - **Example URL**: `/api/posts/456`
        - **Request Body**:
            ```json
            {
            "title": "Updated Post Title",
            "content": "Updated content of the blog post."
            }
            ```
        - **Response**:
            ```json
            {
            "postId": "456",
            "message": "Blog post updated successfully."
            }
            ```

        5. **Delete a Blog Post**
        - **Endpoint**: `/api/posts/{postId}`
        - **Method**: `DELETE`
        - **Description**: Delete a specific blog post.
        - **Example URL**: `/api/posts/456`
        - **Response**:
            ```json
            {
            "postId": "456",
            "message": "Blog post deleted successfully."
            }
            ```

        6. **Retrieve Comments for a Blog Post**
        - **Endpoint**: `/api/posts/{postId}/comments`
        - **Method**: `GET`
        - **Description**: Retrieve all comments for a specific blog post.
        - **Example URL**: `/api/posts/456/comments`
        - **Response**:
            ```json
            [
            {
                "commentId": "789",
                "postId": "456",
                "content": "Great post!",
                "authorId": "125"
            }
            ]
            ```

        7. **Create a Comment for a Blog Post**
        - **Endpoint**: `/api/posts/{postId}/comments`
        - **Method**: `POST`
        - **Description**: Create a new comment for a specific blog post.
        - **Example URL**: `/api/posts/456/comments`
        - **Request Body**:
            ```json
            {
            "content": "This is a comment.",
            "authorId": "126"
            }
            ```
        - **Response**:
            ```json
            {
            "commentId": "790",
            "message": "Comment created successfully."
            }
            ```