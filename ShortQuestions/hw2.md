# Maven Dependency
## 1. Find one dependency for each packaging type
### 1. war 
```
<dependency>
    <groupId>org.jvnet.hudson.main</groupId>
    <artifactId>hudson-war</artifactId>
    <version>1.395</version>
    <scope>test</scope>
</dependency>
```

### 2. jar
```
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.17.0</version>
</dependency>
```

### 3. POM
```
<dependency>
    <groupId>fish.focus.uvms.maven</groupId>
    <artifactId>uvms-pom-arquillian-deps</artifactId>
    <version>3.16</version>
    <type>pom</type>
    <scope>test</scope>
</dependency>

```

### 4. Other
```
<dependency>
    <groupId>com.typesafe</groupId>
    <artifactId>config</artifactId>
    <version>1.4.3</version>
</dependency>

```

## 2. Difference between war, jar, and POM
- Jar is a packaged file format for Java executable applications.
It contains complied class files.
- War is a packaged format for web applications. It is used to deploy Java web applications on servers like Tomcat.
- POM is an XML file that defines project configuration, dependencies, plugins, and other settings.

## 3. Create a maven project and add above dependencies
### 1. Errors
I encountered dependency resolution issues due to missing artifacts and incorrect repository configurations.
### 2. How to resolve
To resolve them, I added the correct type for the WAR dependency, reloaded Maven, and ran `mvn clean install` and `mvn dependency:tree` to ensure all dependencies were correctly resolved and the project built successfully.


## 4. Build project
### 1. Build and resolve all build-related issues
I ran the following Maven commands to build the project and resolve any issues:
- `mvn clean`
- `mvn validate`
- `mvn compile`
- `mvn package`

### 2. Install executable to local maven repo
A JAR file is generated in the `target` directory. After running `mvn install`, the JAR file is installed to my local Maven repository at:
~/.m2/repository/org/example/HW2Project/1.0-SNAPSHOT/HW2Project-1.0-SNAPSHOT.jar


## 5. Create a new module in maven project
I created a new module and added the following snippet to its `pom.xml`:
```
<parent>
<groupId>org.example</groupId>
<artifactId>HW2Project</artifactId>
<version>1.0-SNAPSHOT</version>
</parent>
```
After running mvn clean install, a JAR file is successfully generated for the new module.
I have uploaded all projects to Coding/HW2Project.



## 6. List Maven life cycles in order,compare them.
Maven life cycles include validate, compile, test, package, verify, install, and deploy.
- **validate**: Validates the project structure and verifies that all necessary information is available.
- **compile**: Compiles the project's source code into bytecode.
- **test**: Runs tests against the compiled code to ensure functionality.
- **package**: Packages the compiled code into a distributable format, such as a JAR or WAR file.
- **verify**: Performs additional checks on the packaged code to ensure it meets quality criteria.
- **install**: Installs the package into the local Maven repository, making it available for other projects on the same machine.
- **deploy**: Deploys the package to a remote repository, making it available to other developers and projects.

## 7. Explain git merge vs git rebase.
- Git merge: combines changes from one branch into another, creating a new merge commit. This saves the history of both branches.
- Git rebase: Re-applies commits from one branch onto another base, creating a linear history. This simplifies the commit history but can rewrite commits.

## 8. Explain Trunk-based developement git branching strategy.
Developers work on a single main branch (trunk) with frequent, small changes. 
Feature branches are short-lived and merged back into the trunk quickly to maintain a continuous, stable codebase.

## 9. Explain git reset options.
- **`git reset`**
    - Resets the file in the staging area to match the last commit. Changes in the working directory remain unaffected.

- **`git reset --soft`**
    - Moves the HEAD pointer to the specified commit. Changes are kept in the staging area and working directory.

- **`git reset --hard <commit>`**
    - Moves the HEAD pointer to the specified commit. Changes in the staging area and working directory are discarded. This option can result in data loss if not used carefully.

- **`git reset --mixed <commit>`**
    - Moves the HEAD pointer to the specified commit. Changes are kept in the working directory but removed from the staging area. 












