### 1. Compare Rest API methods ( Get , Post , Put , Patch , Delete ) in detail.

| **Method** | **Purpose**            | **Idempotent** | **Safe** | **Request Body** | **Response Body** | **Use Case**                   |
| ---------- | ---------------------- | -------------- | -------- | ---------------- | ----------------- | ------------------------------ |
| **GET**    | Retrieve data          | Yes            | Yes      | No               | Yes               | Fetching user details          |
| **POST**   | Create new resource    | No             | No       | Yes              | Yes               | Creating a new user            |
| **PUT**    | Update or replace data | Yes            | No       | Yes              | Yes               | Updating user details          |
| **PATCH**  | Partially update data  | Yes            | No       | Yes              | Yes               | Updating only a specific field |
| **DELETE** | Delete a resource      | Yes            | No       | No               | Optional          | Deleting a user                |

### 2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.

| **Aspect**         | **SOAP**                  | **REST API**             | **gRPC**                       | **GraphQL**              |
| ------------------ | ------------------------- | ------------------------ | ------------------------------ | ------------------------ |
| **Type**           | Protocol                  | Architectural style      | RPC Framework                  | Query language           |
| **Transport**      | HTTP, SMTP, JMS           | HTTP                     | HTTP/2                         | HTTP                     |
| **Data Format**    | XML                       | JSON, XML                | Protocol Buffers (binary)      | JSON                     |
| **Performance**    | Slower (XML overhead)     | Faster (JSON, stateless) | Very fast (binary, HTTP/2)     | Efficient (but can vary) |
| **Security**       | WS-Security, SSL/TLS      | SSL/TLS, OAuth           | TLS                            | OAuth, SSL/TLS           |
| **State**          | Stateful or Stateless     | Stateless                | Stateless                      | Query can manage state   |
| **Caching**        | Limited                   | Supported                | Not typically needed           | Harder to implement      |
| **Use Case**       | Enterprise apps (banking) | Web apps, mobile apps    | High-performance microservices | Complex data querying    |
| **Error Handling** | Built-in (faults)         | HTTP status codes        | Status codes + custom errors   | Custom error handling    |

### 3. Explain components in http requests (header, body, uri etc...), and what do they do?

| **Component**    | **Purpose**                                                                                                        |
| ---------------- | ------------------------------------------------------------------------------------------------------------------ |
| **Request Line** | Specifies the HTTP method, the URI of the resource, and the HTTP version.                                          |
| **Headers**      | Provide metadata about the request (e.g., authentication, content type, caching instructions).                     |
| **URI**          | Identifies the specific resource on the server (can include query parameters).                                     |
| **Body**         | Contains the data being sent to the server (used in POST, PUT, PATCH).                                             |
| **HTTP Method**  | Defines the action to be taken on the resource (GET to retrieve, POST to create, PUT to update, DELETE to remove). |

### 4. Explain components in http responses (header, body etc...), and what do they do?

| **Component**    | **Purpose**                                                                                                          |
| ---------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Status Line**  | Provides the HTTP version, status code, and status message to indicate the outcome of the request.                   |
| **Headers**      | Provide metadata about the response (e.g., content type, caching instructions, server details).                      |
| **Body**         | Contains the actual content or data requested by the client (optional in responses to some methods, like DELETE).    |
| **Status Codes** | Indicates the outcome of the request with standard codes like 200 (success), 404 (not found), or 500 (server error). |

### 5. Compare SQL DB vs NoSQL DB in detail

| **Aspect**         | **SQL Databases**                                     | **NoSQL Databases**                                    |
| ------------------ | ----------------------------------------------------- | ------------------------------------------------------ |
| **Data Structure** | Relational (tables, rows, columns)                    | Non-relational (document, key-value, column, graph)    |
| **Schema**         | Fixed schema, predefined before data entry            | Schema-less, flexible data models                      |
| **Scalability**    | Vertical scaling (scale-up)                           | Horizontal scaling (scale-out)                         |
| **Transactions**   | ACID-compliant, strong consistency                    | BASE model, eventual consistency, limited transactions |
| **Performance**    | Optimized for complex queries but may need tuning     | Optimized for high-volume, fast data access            |
| **Use Case**       | Structured data, relational integrity (e.g., banking) | Unstructured, distributed data (e.g., social media)    |
| **Examples**       | MySQL, PostgreSQL, Oracle, SQL Server                 | MongoDB, Cassandra, Redis, Neo4j                       |

### 6. Explain types of SQL language, and their purpose?

| **SQL Language Type** | **Commands**                          | **Purpose**                                                           |
| --------------------- | ------------------------------------- | --------------------------------------------------------------------- |
| **DDL**               | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` | Defines database schema and structures (tables, indexes, etc.).       |
| **DML**               | `INSERT`, `UPDATE`, `DELETE`          | Manipulates data stored in the database (adding, updating, deleting). |
| **DQL**               | `SELECT`                              | Queries and retrieves data from the database.                         |
| **DCL**               | `GRANT`, `REVOKE`                     | Controls access and permissions to database objects (security).       |
| **TCL**               | `COMMIT`, `ROLLBACK`, `SAVEPOINT`     | Manages transactions to ensure data consistency and integrity.        |

### 7. Explain 2xx, 4xx, 5xx http status codes in detail, name some common and important codes, explain 1xx, 3xx http status code in general.

- 1xx codes are informational and rarely seen by end users.
- 2xx codes indicate successful responses.
- 3xx codes indicate redirection, and the client may need to take additional steps.
- 4xx codes represent client-side errors like bad requests or authentication issues.
- 5xx codes represent server-side errors or failures in processing the request.

## API Design

**1. find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay**

| **HTTP Method**  | **Endpoint**                                   | **Description**                               |
| ---------------- | ---------------------------------------------- | --------------------------------------------- |
| `GET`            | `/customers/{customerId}/payments`             | Get all payment methods for a customer.       |
| `GET`            | `/customers/{customerId}/payments/{paymentId}` | Get a specific payment method for a customer. |
| `POST`           | `/customers/{customerId}/payments`             | Add a new payment method for a customer.      |
| `PUT` or `PATCH` | `/customers/{customerId}/payments/{paymentId}` | Update an existing payment method.            |
| `DELETE`         | `/customers/{customerId}/payments/{paymentId}` | Delete a payment method.                      |

**2. Find the customer's history orders from 10/10/2022 to 10/24/2022**

- HTTP Method: `GET`
- URL: `/customers/{customerId}/orders`
- Query Parameters: `startDate`, `endDate` (used to filter the orders by date range)
- Description: Retrieves all orders placed by a customer between two dates (e.g., from `10/10/2022` to `10/24/2022`).

**3. find the customer's delievery addresses**
| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|------------------------------------------------|-----------------------------------------------------------|
| `GET` | `/customers/{customerId}/addresses` | Get all delivery addresses for a customer. |
| `GET` | `/customers/{customerId}/addresses/{addressId}`| Get a specific delivery address for a customer. |
| `POST` | `/customers/{customerId}/addresses` | Add a new delivery address for a customer. |
| `PUT` or `PATCH`| `/customers/{customerId}/addresses/{addressId}`| Update a specific delivery address for a customer. |
| `DELETE` | `/customers/{customerId}/addresses/{addressId}`| Delete a delivery address for a customer. |

**4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?**

| **HTTP Method** | **Endpoint**                                | **Description**                                                          |
| --------------- | ------------------------------------------- | ------------------------------------------------------------------------ |
| `GET`           | `/customers/{customerId}/payments/default`  | Get the customer's default payment method.                               |
| `GET`           | `/customers/{customerId}/addresses/default` | Get the customer's default delivery address.                             |
| `GET`           | `/customers/{customerId}/defaults`          | Get the customer's default payment method and delivery address together. |

**5. Find 2 collection of APIs example.**

| **API Collection** | **HTTP Method** | **Endpoint**                     | **Description**                                      |
| ------------------ | --------------- | -------------------------------- | ---------------------------------------------------- |
| **Twitter**        | `GET`           | `/users/{userId}/tweets`         | Get a list of tweets for a specific user.            |
| **Twitter**        | `POST`          | `/users/{userId}/tweets`         | Post a new tweet for a specific user.                |
| **YouTube**        | `GET`           | `/channels/{channelId}/videos`   | Get a list of videos for a specific YouTube channel. |
| **YouTube**        | `POST`          | `/playlists/{playlistId}/videos` | Add a video to a specific playlist.                  |

**6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE**
| **HTTP Method** | **Endpoint** | **Description** |
|-----------------|-----------------------------------------------|--------------------------------------------------------|
| `GET` | `/posts` | Get all blog posts. |
| `GET` | `/posts/{postId}` | Get a specific blog post. |
| `POST` | `/posts` | Create a new blog post. |
| `PUT` | `/posts/{postId}` | Update a blog post. |
| `DELETE` | `/posts/{postId}` | Delete a blog post. |
| `GET` | `/posts/{postId}/comments` | Get all comments for a blog post. |
| `POST` | `/posts/{postId}/comments` | Add a comment to a blog post. |
| `DELETE` | `/posts/{postId}/comments/{commentId}` | Delete a comment from a blog post. |
| `GET` | `/categories` | Get all blog categories. |
| `GET` | `/categories/{categoryId}` | Get a specific category. |
| `POST` | `/categories` | Add a new blog category. |
| `PUT` | `/categories/{categoryId}` | Update an existing category. |
| `DELETE` | `/categories/{categoryId}` | Delete a category. |
