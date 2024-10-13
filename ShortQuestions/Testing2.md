# Testing 2

## Explain and compare following concepts, provide specific examples when doing comparison:

### Testing Related Concepts:
1. **Unit Testing**:
    - **Definition**: Focuses on testing individual components or methods in isolation.
    - **Example**: Testing a single function in a Java class using `assertTrue` or `assertEquals`.
    - **Comparison**: Smaller in scope compared to integration tests; doesn’t test how components work together.

2. **Functional Testing**:
    - **Definition**: Validates that software functions as expected according to requirements.
    - **Example**: Testing the login functionality of a web app, ensuring that the user can successfully log in with valid credentials.
    - **Comparison**: Tests the behavior from the user's perspective, unlike unit testing which tests internal code logic.

3. **Integration Testing**:
    - **Definition**: Tests the interaction between different modules or components.
    - **Example**: Testing how the login service interacts with the database and user profile service.
    - **Comparison**: Focuses on interaction between components, while unit testing focuses on isolated parts.

4. **Regression Testing**:
    - **Definition**: Ensures that new code changes haven’t broken existing features.
    - **Example**: After adding a new feature to a shopping cart system, running tests to verify older features still work.
    - **Comparison**: Involves rerunning old tests, while unit testing and functional testing typically focus on new code.

5. **Smoke Testing**:
    - **Definition**: A preliminary test to check basic functionality before more in-depth testing.
    - **Example**: Verifying that the application launches and the login page loads successfully after a new build.
    - **Comparison**: A quick and basic check, whereas other tests (like functional or unit tests) are more thorough.

6. **Performance Testing**:
    - **Definition**: Evaluates how the system performs under a specific load or stress.
    - **Example**: Testing how many users the system can handle before it slows down or crashes.
    - **Comparison**: Different from functional testing, as it focuses on speed, scalability, and stability rather than correct behavior.

7. **A/B Testing**:
    - **Definition**: Compares two versions of a feature to determine which performs better with users.
    - **Example**: Testing two different landing page designs to see which one results in more sign-ups.
    - **Comparison**: Different from other testing methods because it’s focused on user preference and behavior analysis.

8. **User Acceptance Testing (UAT)**:
    - **Definition**: Final testing done by the end-users to ensure the system meets business needs.
    - **Example**: Having a client test a new feature in an application before release to ensure it aligns with their expectations.
    - **Comparison**: Focuses on business requirements and user needs, unlike integration or unit tests, which focus on technical correctness.


### Environment Related Concepts:
1. **Development**:
    - **Definition**: The environment where developers write and test code.
    - **Example**: Running unit tests on new code changes locally.
    - **Comparison**: Development is less stable and changes frequently compared to production.

2. **QA (Quality Assurance)**:
    - **Definition**: A controlled environment where testers validate code for bugs and issues.
    - **Example**: Running regression tests in the QA environment after new features are added.
    - **Comparison**: QA is more stable than development but is not yet ready for end-users like the production environment.

3. **Pre-prod/Staging**:
    - **Definition**: A mirror of the production environment used for final testing before release.
    - **Example**: Conducting user acceptance tests in a staging environment to ensure all features are working as expected.
    - **Comparison**: Staging is nearly identical to production, whereas development and QA environments might have differences in setup.

4. **Production**:
    - **Definition**: The live environment where the software is used by end-users.
    - **Example**: The live version of a website that customers interact with.
    - **Comparison**: Production is the most stable environment, unlike development or QA, which are meant for testing.