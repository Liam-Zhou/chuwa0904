
### Short Answer
1. Compare Rest API methods (Get, Post, Put, Patch, Delete) in detail.
   - GET: retrieves data.
   - POST: creates data.
   - PUT: updates data entirely.
   - PATCH: allows partially updating data.
   - DELETE: removes data.


2. Compare Soap vs Rest API vs gRPC vs GraphQL in detail.

    - **soap**(Simple Object Access Protocol): is slow and strict, like mailing a letter
    - **rest api**(Representational State Transfer): is faster and simpler, like a phone call
    - **gprc**: It's super fast, and both people can talk at the same time. It’s good for short, quick messages back and forth, and it’s much faster than a regular phone call.
    - **graphQL**: gives you exactly what you need, like choosing food at a buffet.


3. Explain components in HTTP requests (header, body, uri, etc...), and what do they do?

    - url: The house address you want to visit
    - Header: How you introduce yourself when you knock on the door(Host+User-Agent+Accept+Content-Type+Authorization)
    - Body: The note you give that says what you want.
    - Method: What action you want to take, like asking for something or giving something (Get,Post,Put,Patch,Delete)
    - Query Parameter: Extra details about what you want (@PathVariable)
    - Response: The answer you get back
```
    
    url: http://localhost:8080/posts 
    Header:(Host+User-Agent+Accept+Content-Type+Authorization) 
    Body: {
       "postID":11,
        "title":"Forth title"
     }
    Method: Post
    Query Parameter:The data comes after the ? in the URL,It’s often used for filtering, sorting, or adding extra details to a request.
    
    
    
    @PostMapping
    public Blog addPost(@RequestBody Blog newPost){
         //Blog newPost = new Blog(3L,"new post");
        return newPost;
    }


```


4. Explain components in HTTP responses (header, body, etc...), and what do they do?
    - Status line:Contains the HTTP version, status code, and status message.
    - Headers: Provide additional details like content type, length, caching info, and cookies
    - Body: Contains the actual content or data being returned by the server (if applicable)
    - Status code: Indicates the result of the request, such as success (200)
    
    - ![img](img.png)

5. Compare SQL DB vs NoSQL DB in detail.
    - SQL: Structured, strict schema, good for complex queries, strong consistency.
    - NoSQL: Flexible, schema-less, good for large-scale, distributed data, often prioritizes scalability over consistency.

6. Explain types of SQL language, and their purpose?
    - DDL: Defines structure. (CREATE
      ALTER: To modify existing database structures.
      DROP: To delete tables, databases.
      TRUNCATE)
    - DML: Manipulates data(SELECT,INSERT,UPDATE,DELETE)
    - DCL: Controls access.
    - TCL: Manages transactions.(
      ROLLBACK,
      SAVEPOINT)

7. Explain 2xx, 4xx, 5xx HTTP status codes in detail, name some common and important codes, explain 1xx, 3xx HTTP status code in general.
    - 200:request succeeded
    - 400 bad request: incorrect url or missing sth in json body
    - 404 not found: incorrect url /pages -> /page
    - 500 server error: use @Controller but w/o @ResponseBody



### API Design
Design REST APIs for the following scenarios:
1. Find the customer's payments, like credit card 1, credit card 2, PayPal, Apple Pay.
2. Find the customer's history orders from 10/10/2022 to 10/24/2022.
3. Find the customer's delivery addresses.
4. If I also want to get the customer's default payment and default delivery address, what kind of API (URL) should be?
````
Code in Coding/rest-demo/src/main/java/restdemo/controller/ClientCotroller.java 
````
5. Find 2 collections of API examples, i.e., Twitter, PayPal, YouTube.
6. Design a collection of APIs for a Blog Website, please specify GET, POST, PUT, DELETE

````
Code in Coding/rest-demo/src/main/java/restdemo/controller/BlogController.java 
````
