# Hw6

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