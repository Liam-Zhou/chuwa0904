# Unit Testing

## 1. Annotations

## 2. Lifecycle of JUnit
1. **@BeforeAll**: Runs once before all tests in the class.
2. **@BeforeEach**: Runs before each test method.
3. **@Test**: Executes the test method.
4. **@AfterEach**: Runs after each test method.
5. **@AfterAll**: Runs once after all tests in the class.


## 3. Explain parameterized testing?
Parameterized testing allows the same test to be run multiple times with different inputs. It helps in testing a method with various scenarios without writing multiple test cases.

## 4. Explain Mockito and PowerMock.
- **Mockito** is a mocking framework that enables the creation of mock objects for testing. It simplifies testing by allowing the simulation of dependencies and defining their behaviors. 
- **PowerMock** extends Mockito's capabilities to mock static, final, and private methods, which Mockito alone cannot do.

## 5. Compare @Mock and @InjectMocks
- **@Mock** creates mock instances of the dependencies. 
- **@InjectMocks** initializes the class under test and injects the mocks into it. 
- While @Mock is used for creating mocks, @InjectMocks is used for injecting these mocks into the target class.

## 6. Explain stubbing.
Stubbing is the process of defining the behavior of a mock object for specific method calls. It allows specifying what the mock should return when a method is called, thereby controlling the flow of the test.

## 7. What is Mockito ArgumentMatchers?
**Mockito ArgumentMatchers** are utility methods that match arguments passed to mock methods. They provide flexible ways to specify expectations, such as `anyString()`, `anyInt()`, and others, allowing more dynamic and reusable tests.

## 8. What is Hamcrest Matchers?
**Hamcrest Matchers** are a library for writing matchers that are used in assertions. They allow for expressive and readable test assertions, making it easier to validate conditions in tests using methods like `equalTo()`, `not()`, and more.

## 9. Compare @Spy and @Mock
- **@Spy** is used to create a spy of a real object, allowing partial mocking. It can call real methods unless overridden. 
- **@Mock**, on the other hand, creates a mock object that does not call any real methods unless specifically instructed.

## 10. Explain Assertion.
Assertions are statements in tests that verify whether a condition is true. They help in checking the expected outcome against the actual result. 
If an assertion fails, the test fails, indicating an issue in the code being tested.
