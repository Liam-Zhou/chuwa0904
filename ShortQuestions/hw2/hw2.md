
# Maven & Git & Project Homework

## 1. Find at least ONE dependency for each packaging type on https://mvnrepository.com/repos/central

1. Packaging type is `war`  
    ```
    <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.4.0</version>
    </dependency>
    ```
2. Packaging type is `jar`
    ```
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <type>jar</type>
    </dependency>
    ```
3. Packaging type is `POM`
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.3</version>
        <type>pom</type>
    </dependency>
    ```
4. Packaging type is other than all above.
    ```
    <dependency>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <type>maven-plugin</type>
    </dependency>
    ```

## 2. Explain the difference between `war`, `jar`, and `POM`
They are different packaging types.
- war (Web Application ARchive): WAR files are specifically designed for web applications. They include JAR files and other resources necessary for running a web application, such as JSPs, HTML files, JavaScript, and configuration files. The WAR file includes the web application structure necessary for setting up a web context, which the servlet container understands and can deploy.

- jar (Java ARchive): This is the standard format for packaging Java classes and resources. JAR files are essentially archives that contain compiled Java classes along with associated metadata and resources (like text, images, etc.). AR files are typically used for libraries that provide functionality without running independently. They can be included as dependencies in other projects.

- pom (Project Object Model): It is a configuration file (pom.xml) that contains information about the project and configuration details used by Maven to build the project.  A POM-packaged project is often used as a parent in multi-module projects. It aggregates elements of other projects as dependencies and standardizes configurations across a suite of modules or projects.



## 3. Create a Maven managed project in IntelliJ IDEA, add the above dependencies to your project

1. Resolve all dependency-related errors
2. Explain how you resolved them
Problem:   
![image](./hw2%20pictures/maven%20add%20dependendies.png)  
There is no error.



## 4. Build your project, even if it's empty

1. Resolve all build-related issues
2. Install your executable to the local Maven repository
![image](./hw2%20pictures/maven%20build%20project.png)    
Built successfully.  
![image](./hw2%20pictures/maven%20local%20respo.png)    
Built successfully.



## 5. Create a new module in your Maven project, make step 4.2) as a dependency of this module

1. Resolve all dependency-related issues
![image](./hw2%20pictures/maven%20new%20module.png)


## 6. List Maven life cycles in order, compare them.
validate->compile->test->package->integration->verify->install->deploy  
  
In Maven's build lifecycle, each phase plays a specific role in the software development process, building systematically upon the results of the previous phase. The validate phase ensures all project information is correct and available for the build to proceed, while the compile phase translates source code into bytecode. The test phase runs unit tests to verify functionality before the package phase creates distributable formats like JARs or WARs. integration-test then deploys these packages to an environment to validate integrated system behavior, followed by the verify phase which checks these results against quality criteria. The install phase stores the package in the local repository for reuse, and finally, the deploy phase pushes the package to a remote repository, making it available for production or further development stages. Each phase is crucial, ensuring thorough validation, compilation, testing, and deployment, crucial for maintaining high standards of software quality and reliability.



## 7. Explain `git merge` vs `git rebase`
- Git Merge: Combines changes from one branch into another, creating a merge commit. It preserves the history of both branches.
- Git Rebase: Moves or re-applies your branch’s commits on top of another branch. It creates a linear history without merge commits.

## 8. Explain Trunk-based development Git branching strategy.
Developers work on short-lived feature branches and frequently merge them into the main branch (trunk). This reduces long-lived branches and encourages frequent integration, promoting continuous integration and deployment.

## 9. Explain `git reset` options
- --soft: Resets HEAD to the specified commit, keeping the changes staged.
- --mixed: Resets HEAD and un-stages changes, but keeps the working directory intact.
- --hard: Resets HEAD, index, and working directory to the specified commit, discarding all changes.