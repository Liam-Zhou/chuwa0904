1. Find at least ONE dependency for each packaging type on https://mvnrepository.com/repos/central  

   1. Packaging type is `war` 

   2. Packaging type is `jar`

   3. Packaging type is `POM` 

   4. Packaging type is other than all above.

      ```java
      <dependencies>
              <!-- WAR dependency -->
              <dependency>
                  <groupId>org.apache.chemistry.opencmis</groupId>
                  <artifactId>chemistry-opencmis-server-bindings-war</artifactId>
                  <version>1.1.0</version>
                  <type>war</type>
              </dependency>
              <!-- JAR dependency -->
              <dependency>
                  <groupId>org.slf4j</groupId>
                  <artifactId>slf4j-api</artifactId>
                  <version>2.0.7</version>
              </dependency>
              <!-- POM dependency -->
              <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-parent</artifactId>
                  <version>3.0.0</version>
                  <type>pom</type>
              </dependency>
              <!-- Bundle dependency -->
              <dependency>
                  <groupId>org.apache.felix</groupId>
                  <artifactId>org.apache.felix.webconsole</artifactId>
                  <version>4.7.0</version>
              </dependency>
          </dependencies>
      
      ```

      

2. Explain the difference between `war`, `jar` and` pom`?

- **WAR** (Web Application Archive) is used for packaging **web applications**. It includes all necessary components (JSPs, Servlets, HTML, CSS, etc.) to run a web app on a server like Tomcat or JBoss.
- **JAR** (Java Archive) is typically used for **libraries** or standalone Java applications. It packages compiled Java classes and associated resources into one file for easy distribution and reuse.
- **POM** (Project Object Model) is a **Maven configuration file** used to manage dependencies, plugins, and project builds. It doesn't package the code but orchestrates how the project is built and its dependencies managed.
- In essence:
  - **WAR**: For web apps.
  - **JAR**: For libraries or standalone apps.
  - **POM**: For managing dependencies and build configurations in Maven projects.



3. Create a maven managed project in IntelliJ Idea, add above dependencies to your project

   1. Resolve all dependency-related errors:

      get error after adding new dendency/dependencies

   2. Explain how you resolve them 

      load maven changes after adding new dependency/dependencies

      

4. Build your project, even if it's empty

   1. Resolve all build-related issues 
      could not use mvn command

      solved by set up path to Maven in `.zshrc`:
      ```bash
      export M2_HOME=/opt/apache-maven-<version>
      export PATH=$M2_HOME/bin:$PATH
      ```

   2. Install your executable to local maven repository

​		command: `mvn install`

​		<img src="/Users/monicaq/Desktop/Screenshot 2024-09-09 at 2.46.01 PM.png" alt="Screenshot 2024-09-09 at 2.46.01 PM"/>

​	command: `cd ~/.m2/repository/<groupId>/<artifactId>/<version>`

![Screenshot 2024-09-09 at 3.07.18 PM](/Users/monicaq/Desktop/Screenshot 2024-09-09 at 3.07.18 PM.png)





5. Create a new module in your maven project, make 4.2) as a dependency of this module

   1. create a new module: `demo-child` inside existing module

   2. add parent inside child `pom.xml` denpendency/dependencies.
   3. command: `mvn clean install`

   ![Screenshot 2024-09-09 at 4.48.34 PM](/Users/monicaq/Desktop/Screenshot 2024-09-09 at 4.48.34 PM.png)

   

6. List Maven life cycles in order, compare them.

   1. **validate**: Validates the project is correct and all necessary information is available.

   2. **compile**: Compiles the source code of the project.

   3. **test**: Tests the compiled source code using a testing framework like JUnit.

   4. **package**: Packages the compiled code into a distributable format (e.g., JAR or WAR).

   5. **verify**: Runs checks to verify that the package is valid and meets quality standards.

   6. **install**: Installs the package into the local repository.

   7. **deploy**: Copies the final package to a remote repository for sharing with other developers.

      

7. Explain git merge vs git rebase

   **Git Merge**:

   - Combines the changes from one branch into another, creating a merge commit. The history of both branches is retained, and a new commit is created.
   - **Advantages**: Keeps the full history intact, including the points where branches diverge and converge.
   - **Disadvantages**: Can create complex history with many branches and merge commits.

   **Git Rebase**:

   - Moves or "replays" your commits onto another branch, rewriting the commit history. It results in a cleaner, linear history without merge commits.

   - **Advantages**: Clean, linear history that looks as if all the work was done in one continuous stream.

   - **Disadvantages**: Can rewrite history, which may cause problems if you're collaborating with others.

     

8. Explain Trunk-based developement git branching strategy. 

   **Trunk-Based Development (TBD)** is a strategy where all developers commit changes to a single "trunk" branch (often the `main` or `master` branch). Feature branches are kept short-lived, and developers aim to integrate their work with the trunk as frequently as possible, sometimes even daily.

   - Advantages:
     - Minimizes integration conflicts since code is regularly integrated into the trunk.
     - Ensures the trunk is always in a deployable state.
   - Disadvantages:
     - Requires solid CI/CD practices and automated testing to ensure stability.
     - Can be challenging for larger teams without good coordination.

9. Explain git reset options.

   **git reset** changes the state of your current branch by moving the HEAD pointer. It can operate in three different modes depending on how much you want to affect the working directory and staging area:

   1. --soft:
      - Only moves the HEAD to the specified commit, but leaves the index and working directory unchanged. Changes will appear as staged.
   2. --mixed(default):
      - Moves the HEAD to the specified commit and updates the index to match, but leaves the working directory untouched. Changes will appear as unstaged.
   3. --hard:
      - Moves the HEAD to the specified commit, updates the index and the working directory to match the specified commit. This **discards** any changes in the working directory, so use it with caution.







