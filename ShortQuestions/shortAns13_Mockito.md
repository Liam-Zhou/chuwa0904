### What is the lifecycle of JUnit?
- In JUnit, lifecycle methods include @BeforeAll, @AfterAll (run once before/after all tests), and @BeforeEach, @AfterEach (run before/after each test). This helps set up or tear down test environments.
### Explain parameterized testing?
- Allows running the same test with different parameters. In JUnit, use @ParameterizedTest and @ValueSource or other annotations to pass different input values into the same test method.
### Explain Mockito and PowerMock.
- Mockito: A Java framework for creating mock objects in unit tests. It helps simulate dependencies.
- PowerMock: An extension of Mockito used for mocking static methods, constructors, and final methods that Mockito alone cannot handle.
### Compare @Mock and @InjectMock.
- @Mock: Creates a mock instance of a class or interface
- @InjectMocks: Automatically injects the mocks (created by @Mock) into the class under test
### Explain stubbing.
- The process of defining behavior for mock methods using when(...).thenReturn(...). It allows you to simulate how a method should behave during a test.
### What is Mockito ArgumentMatchers?
- Used to define flexible input conditions for mock methods. For example, anyInt(), anyString() allow matching arguments without specifying exact values in stubs or verifications.
### What are Hamcrest Matchers?
- A library used for writing readable assertions in tests. Common matchers include assertThat(value, is(expected)), equalTo(), notNullValue(), and more for comparing test results.
### Compare @Spy and @Mock.
- @Spy: Wraps a real instance and allows selective mocking of its methods while keeping the rest intact.
- @Mock: Fully mocks an object where every method's behavior can be defined or overridden.

### Explain Assertion.
- A way to check if the result of a test is as expected. Common assertions include assertEquals(), assertTrue(), assertNotNull() to validate the behavior of your code.