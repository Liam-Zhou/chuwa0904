# hw6 Database & Rest API

## 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.

1. GET: Retrieve data from the server.
2. POST: Submit data to the server to create a new resource.
3. PUT: Update or replace an existing resource.
4. PATCH: Partially update an existing resource.
5. DELETE: Remove a resource from the server.
   
## 2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.

1. SOAP (Simple Object Access Protocol): SOAP is a protocol for exchanging structured information in web services, primarily relying on XML.
2. REST API is an architectural style that allows developers to interact with web services by using standard HTTP methods.
3. gRPC (Google Remote Procedure Call): gRPC is a high-performance RPC (Remote Procedure Call) framework developed by Google, it uses Protocol Buffers (Protobuf) as the interface definition language.
4. GraphQL (Graph Query Language): GraphQL is a query language for APIs, developed by Facebook, which allows clients to request exactly the data they need. Unlike REST, where you retrieve fixed resources, GraphQL provides flexibility in querying the exact data requirements.

## 3. Explain components in http requests (header, body, uri, etc...),and what do they do?

Components in http request: Request line, header, body.

1. Request line: Method, URI, HTTP version. Method indicates the action to be performed (e.g., GET, POST, PUT, DELETE). URI (Uniform Resource Identifier) specifies the resource being targeted. HTTP Version defines the HTTP protocol version (e.g., HTTP/1.1 or HTTP/2).
2. Header: Provide additional metadata about the request, such as content type, authentication, cache control, and more.
3. Body: Contains the data being sent to the server (mainly used in POST, PUT, PATCH requests).

## 4. Explain components in http responses (header, bodyetc...), and what do they do?

Components in http response: Status line, header, body, cookies.

1. Status line (HTTP version, Status code, reason phrase): Provides the client with information about the result of the request, including whether it was successful or if there was an error. 
2. Header: Headers in the response provide additional information about the response, such as the type of content, caching policies, and server details.
3. Body: The body contains the actual data or resource that was requested, such as HTML, JSON, XML, or binary data like images or files.
4. Cookies: Cookies are pieces of data that the server can send back to the client, often for session tracking or storing user preferences. These are set in the Set-Cookie response header.
   
## 5. Compare SQLDB vs NoSQLDB in detail

1. SQL is relational database management system (RDBMS). NoSQL is non-relational database system.
2. SQL uses SQL query language for database access. NoSQL doesn’t use SQL query language as its primary query language.
3. SQL organizes data into tables with rows and columns. NoSQL has more flexible data models, such as document, key-value, column-family, and graph databases.
4. SQL is suitable for applications requiring complex queries and transactions. NoSQL is suitable for applications with large volumes of unstructured or semi-structured data.

## 6. Explain types of SQL language, and their purpose?

1. Data Definition Language (DDL): Used to define and manage the structure of the database and its objects, such as tables, indexes, and constraints.
2. Data Manipulation Language (DML): Used to manipulate data stored within the database (e.g., inserting, updating, or deleting records).
3. Data Query Language (DQL): Used to retrieve data from the database.
4. Data Control Language (DCL): Used to control access to the data in the database, ensuring that only authorized users can perform specific actions.
5. Transaction Control Language (TCL): Used to manage transactions in the database, ensuring that they are executed safely and consistently.

## 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain1xx, 3xx http status code in general.

1XX Informational Responses. For example, 102 indicates the resource is being processed.

2XX success status. E.g. 200/204(created) etc. (Mostly okay):

1. 200: OK. The request was successful, and the server is returning the requested resource or performing the requested operation.
2. 201: Created. The request was successful, and as a result, a new resource has been created. (POST, PUT)
3. 202: Accepted. The request has been accepted for processing, but the processing has not been completed. This status is useful for asynchronous operations.

3XX indicates redirection. This means that the client must take additional actions to complete the request, usually by following a redirect to a different URL.

1. 301: Moved Permanently. The requested resource has been permanently moved to a new URL, and future requests should be directed to the new location.
2. 302: Found (Temporary Redirect). The resource is temporarily located at a different URL, but the original URL should still be used for future requests.

4XX indicates client-side errors.

1. 400: Bad Request. The server cannot process the request because it is malformed or contains incorrect syntax.
2. 401: Unauthorized. Authentication is required to access the resource, but it has not been provided or is invalid.
3. 403: Forbidden. The server understood the request, but it refuses to authorize it. The client does not have permission to access the resource.
4. 404: Not Found. The requested resource could not be found on the server.

5XX indicates server-side errors.

1. 502: Bad Gateway. The server, acting as a gateway or proxy, received an invalid response from the upstream server.

# Rest API Design

1. Find the Customer's Payments (CreditCard1, CreditCard2, PayPal, ApplePay, etc.)

    Endpoint: GET /customers/{customerId}/payments. Retrieves all payment methods (CreditCard1, CreditCard2, PayPal, ApplePay, etc.) associated with the customer.

    Request: Method: GET. URL: /customers/{customerId}/payments. Path Parameter: customerId (The unique identifier of the customer)

    Response (Example):
    ```
    {
    "customerId": "12345",
    "paymentMethods": [
        {
        "type": "CreditCard",
        "last4": "1234",
        "cardType": "Visa",
        "expiryDate": "2025-05"
        },
        {
        "type": "PayPal",
        "email": "user@example.com"
        },
        {
        "type": "ApplePay",
        "deviceId": "ap_device_456"
        }
    ]
    }
    ```

2. Find the Customer's History Orders from 10/10/2022 to 10/24/2022

    Endpoint: GET /customers/{customerId}/orders, Retrieves the customer’s order history within the specified date range.

    Request: Method: GET. URL: /customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24. Path Parameter: customerId. Query Parameters: startDate (e.g., 2022-10-10), endDate (e.g., 2022-10-24)

    Response (Example):
    ```
    {
    "customerId": "12345",
    "orders": [
        {
        "orderId": "98765",
        "orderDate": "2022-10-11",
        "status": "Delivered",
        "totalAmount": 150.00
        },
        {
        "orderId": "87654",
        "orderDate": "2022-10-20",
        "status": "Shipped",
        "totalAmount": 200.00
        }
    ]
    }
    ```

3. Find the Customer's Delivery Addresses

    Endpoint: GET /customers/{customerId}/addresses. Retrieves all delivery addresses associated with the customer.
    Request: Method: GET. URL: /customers/{customerId}/addresses. Path Parameter: customerId

    Response (Example):
    ```
    {
    "customerId": "12345",
    "deliveryAddresses": [
        {
        "addressId": "address_1",
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "postalCode": "10001",
        "country": "USA"
        },
        {
        "addressId": "address_2",
        "street": "456 Oak St",
        "city": "Los Angeles",
        "state": "CA",
        "postalCode": "90001",
        "country": "USA"
        }
    ]
    }
    ```

4. Find Customer's Default Payment and Default Delivery Address
    
    Endpoint: GET /customers/{customerId}/default. Retrieves the default payment method and default delivery address for the customer.

    Request: Method: GET. URL: /customers/{customerId}/default. Path Parameter: customerId

    Response (Example):
    ```
    {
    "customerId": "12345",
    "defaultPaymentMethod": {
        "type": "CreditCard",
        "last4": "1234",
        "cardType": "Visa",
        "expiryDate": "2025-05"
    },
    "defaultDeliveryAddress": {
        "addressId": "address_1",
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "postalCode": "10001",
        "country": "USA"
    }
    }
    ```
5. Find 2 Collection of API Examples

    Twitter API:

    Base URL: https://api.twitter.com

    GET: /tweets/{id} – Fetches a specific tweet by ID.

    POST: /tweets – Posts a new tweet.

    DELETE: /tweets/{id} – Deletes a tweet by ID.

    YouTube API:

    Base URL: https://www.googleapis.com/youtube/v3

    GET: /videos?part=snippet&id={videoId} – Retrieves video details.

    POST: /videos?part=snippet – Uploads a video.

    DELETE: /videos?id={videoId} – Deletes a video by ID.

6. Design a Collection of APIs for a Blog Website
    
    - Get all blog posts:

        Endpoint: GET /posts. Retrieves a list of all blog posts.

        Response (Example):
        ```
        [
        {
            "postId": "1",
            "title": "First Blog Post",
            "author": "John Doe",
            "publishedDate": "2023-01-01",
            "content": "This is the content of the first post."
        },
        {
            "postId": "2",
            "title": "Second Blog Post",
            "author": "Jane Smith",
            "publishedDate": "2023-02-01",
            "content": "This is the content of the second post."
        }
        ]
        ```

    - Get a single blog post: 

        Endpoint: GET /posts/{postId}. Retrieves a specific blog post by ID.

        Response (Example):
        ```
        {
        "postId": "1",
        "title": "First Blog Post",
        "author": "John Doe",
        "publishedDate": "2023-01-01",
        "content": "This is the content of the first post."
        }
        ```

    - Create a new blog post

        Endpoint: POST /posts. Creates a new blog post.
        Request Body (Example):
        ```
        {
        "title": "New Blog Post",
        "author": "John Doe",
        "content": "This is the content of the new post."
        }
        ```

        Response (Example):
        ```
        {
        "postId": "3",
        "message": "Post created successfully."
        }
        ```

    - Update an existing blog post

        Endpoint: PUT /posts/{postId}. Updates an existing blog post.

        Request Body (Example):
        ```
        {
        "title": "Updated Blog Post",
        "author": "John Doe",
        "content": "This is the updated content of the post."
        }
        ```

        Response (Example):
        ```
        {
        "postId": "1",
        "message": "Post updated successfully."
        }
        ```

    - Delete a blog post

        Endpoint: DELETE /posts/{postId}. Deletes a specific blog post by ID.

        Response (Example):
        ```
        {
        "postId": "1",
        "message": "Post deleted successfully."
        }
        ```




