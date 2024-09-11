# HW2

## 1. Find at least ONE dependency for each packaging type

### pom

~~~
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jdbc</artifactId>
    <version>10.1.28</version>
    <type>pom</type>
</dependency>
~~~

### jar

~~~
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jdbc</artifactId>
    <version>10.1.28</version>
    <type>jar</type>
</dependency>
~~~

### war

~~~
<dependency>
    <groupId>org.jvnet.hudson.main</groupId>
    <artifactId>hudson-war</artifactId>
    <version>1.394</version>
    <scope>test</scope>
    <type>war</type>
</dependency>
~~~

### ear

~~~
<dependency>
    <groupId>org.codehaus.cargo</groupId>
    <artifactId>simple-ear</artifactId>
    <version>1.10.14</version>
    <type>ear</type>
</dependency>
~~~

## 2. Difference between war jar and POM

**Jar** (Java Archive): A single Java's archived file that packs all it need (compiled Java classes, libraries and assets) to be run as a standalone application or can be used as dependencies for other Java projects. In type scope in dependency, by default, Maven looks for Jar if the type not specified. 

**War** (Web Application Archive): The file type that is used to deploy the Java Web Applications.

**POM** (Project Object Model): Maven read the POM.xml as it defines project dependencies, structure and build settings. POM is rigid in its structure compared to Gradle.

## 3. Create a maven managed project in IntelliJ Idea, add above dependencies to your project

### 3.1 Resolve Errors

**Dependency Tree**

```
[INFO] --- dependency:3.7.0:tree (default-cli) @ DependenciesPractice ---
[INFO] org.example:DependenciesPractice:pom:1.0-SNAPSHOT
[INFO] +- org.apache.tomcat:tomcat-jdbc:pom:10.1.28:compile
[INFO] |  \- org.apache.tomcat:tomcat-juli:jar:10.1.28:compile
[INFO] +- org.apache.tomcat:tomcat-jdbc:jar:10.1.28:compile
[INFO] +- org.jvnet.hudson.main:hudson-war:war:1.394:test
[INFO] \- org.codehaus.cargo:simple-ear:ear:1.10.14:compile
```

### 3.2 Explain

1. When just adding the dependency to POM, the IDE message shows that those dependencies could not be found. I will do a clean and compile to check if I can properly include the dependencies.

2. Maven by default search jar in the repository if you do not specify the type and it will not automatically try to find other file type. So, for those repository did not include jar file and you do not specify the type, it will throw errors.

## 4. Build your project

### 4.1 Resolve Issues

### 4.2 Install your executable to local maven repository

mvn clean install

```
[INFO] --- install:3.1.2:install (default-install) @ DependenciesPractice ---
[INFO] Installing D:\Development\chuwa0904\Coding\DependenciesPractice\pom.xml to C:\Users\Kirara\.m2\repository\org\example\DependenciesPractice\1.0-SNAPSHOT\DependenciesPractice-1.0-SNAPSHOT.pom
[INFO] Installing D:\Development\chuwa0904\Coding\DependenciesPractice\target\DependenciesPractice-1.0-SNAPSHOT.jar to C:\Users\Kirara\.m2\repository\org\example\DependenciesPractice\1.0-SNAPSHOT\DependenciesPractice-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.668 s
[INFO] Finished at: 2024-09-09T06:39:29-04:00
[INFO] ------------------------------------------------------------------------

```

## 5. Create a new module in your maven project, make 4.2) as a dependency of this module

1. Dependency of parent is inherit to the child. 

2. Artifacts Produced by the Parent Module have to be explicitly declared as dependency.

```
[INFO] --- dependency:3.7.0:tree (default-cli) @ DependenciesPractice ---
[INFO] org.example:DependenciesPractice:pom:1.0-SNAPSHOT
[INFO] +- org.apache.tomcat:tomcat-jdbc:pom:10.1.28:compile
[INFO] |  \- org.apache.tomcat:tomcat-juli:jar:10.1.28:compile
[INFO] +- org.apache.tomcat:tomcat-jdbc:jar:10.1.28:compile
[INFO] +- org.jvnet.hudson.main:hudson-war:war:1.394:test
[INFO] \- org.codehaus.cargo:simple-ear:ear:1.10.14:compile
[INFO]
[INFO] -------------------------< org.example:Task5 >--------------------------
[INFO] Building Task5 1.0-SNAPSHOT                                        [2/2]
[INFO]   from Task5\pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- dependency:3.7.0:tree (default-cli) @ Task5 ---
[INFO] org.example:Task5:jar:1.0-SNAPSHOT
[INFO] +- org.example:DependenciesPractice:jar:1.0-SNAPSHOT:compile
[INFO] +- org.apache.tomcat:tomcat-jdbc:pom:10.1.28:compile
[INFO] |  \- org.apache.tomcat:tomcat-juli:jar:10.1.28:compile
[INFO] +- org.apache.tomcat:tomcat-jdbc:jar:10.1.28:compile
[INFO] +- org.jvnet.hudson.main:hudson-war:war:1.394:test
[INFO] \- org.codehaus.cargo:simple-ear:ear:1.10.14:compile
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for DependenciesPractice 1.0-SNAPSHOT:
[INFO]
[INFO] DependenciesPractice ............................... SUCCESS [  1.379 s]
[INFO] Task5 .............................................. SUCCESS [  0.095 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.830 s

```

## 6. Maven lifecycle

When running each command for a specific phase, it will run all previous phases in the lifecycle.

**Validate**: Validate the project correction

**Compile**: Compile the source code

**Test**: Test the compile source through the testing framework. 

**Package**: Pack compiled code, libraries, dependencies to the specific file package format

**Verify**: Run and checks on results of integration tests to ensure quality criteria are met

**Install**: Install the package into local maven repository, for use of dependency in other project locally

**Deploy**: Done in the build enviornment, copies the final package to remote repository

## 7. Git Merge vs Git Rebase

Git merge does not change history of both branches. It merge the target branch into current and creates a new merge head if there are conflict.

Git rebase apply the current changes ahead of the top of another branch, making a liner history view.

## 8. Trunk-Based Development

Usually in agile development, developers develop features and merge them into main branch frequently. This ensure lower integration and maintenance difficulty, and reduce risk of long running branches.

## 9. Git reset

All move the head to the previous commit.

Hard:   Discard all the changes made in staging area and working directory. 

Soft:     Keep the changes in staging area and working directory

Mixed: Keep the changes in staging area and working directory. Move changes to unstaged state.

| Option        | Commit History | Staging Area (Index) | Working Directory      |
| ------------- | -------------- | -------------------- | ---------------------- |
| **`--soft`**  | Changed        | Unchanged            | Unchanged              |
| **`--mixed`** | Changed        | Cleared (Unstaged)   | Unchanged              |
| **`--hard`**  | Changed        | Cleared              | Cleared (Changes lost) |
