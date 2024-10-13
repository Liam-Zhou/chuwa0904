## Testing related:

#### Unit Testing(White-box):

What: Testing individual components (methods or functions) in isolation.
Example: In your test, you’re testing getHttpResponse from HttpClientService in isolation by mocking the HTTP connection and response.

#### Functional Testing(Black-box testing (doesn’t concern itself with the internal structure of the code).):

What: Testing the functionality of a system against the defined requirements.(Tests a specific feature or functionality, which may involve multiple classes working together.)
Example: Ensuring HttpClientService properly fetches and processes data from the GitHub API according to expected behavior.

#### Integration Testing(white-box or black-box):

What: Testing how different components work together.
Example: Testing the actual interaction between HttpClientService, HttpURLConnection, and ObjectMapper without mocks, ensuring they work as a whole.
The goal is to ensure that the individual parts (which might have passed their own functional tests) work together correctly when integrated
**Integration vs Functional**: 


#### Regression Testing(Black-box):

What: Ensuring new code changes don’t break existing functionality.
Example: After updating HttpClientService, you re-run your tests to verify that existing features, like fetching GitHub data, still work.

#### Smoke Testing(Black-box):

What: A quick test to check if the most basic functions of an application work.
Example: Running a quick test to see if HttpClientService can make an HTTP request and return a response without testing every detail.

#### Performance Testing(Black-box):

What: Testing how the system performs under certain conditions, like load or response times.
Example: Testing how fast HttpClientService can fetch data from GitHub, especially when handling multiple requests.

#### A/B Testing(Black-box):

What: Comparing two different versions of a feature to see which performs better.
Example: Comparing two different ways of fetching and processing data from GitHub to see which version gives better performance or usability.

#### User Acceptance Testing (UAT)(Black-box):

What: Testing by end-users to verify if the system meets their needs.
Example: A real user tests HttpClientService to ensure it correctly fetches and displays GitHub repositories as expected.

## Environment related:

#### Development:

What: The environment where code is written and tested by developers.
Example:  Write and run your tests for HttpClientService in your development environment.

#### QA (Quality Assurance):

What: The environment where testers verify that the code works as expected.
Example: QA testers test HttpClientService to ensure it meets the requirements before release.

#### Pre-prod/Staging:

What: A replica of the production environment where final testing is done before the actual release.
Example: Testing HttpClientService in a staging environment that closely mimics the live environment to catch issues before going live.

**Staging vs Production**:
- Staging is a pre-production environment where the final round of testing takes place before the software is released to users.
- Mainly developers, testers, and possibly some internal stakeholders. The general public or actual end-users do not access this environment.
- Comprehensive tests are run here, including functional, integration, performance, 
- Production is the live environment where the software is actually used by end-users.
- Sometimes "canary releases" or "A/B tests" are conducted in production to test features with a small subset of users before a full rollout.


#### Production:

What: The live environment where the actual users interact with the system.
Example: HttpClientService is live and fetching data from GitHub for real users in production.