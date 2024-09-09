1. 1. guava (jar)
   2. tomcat-embed-core (war)
   3. spring-core (pom)
2. 1. jar (Java ARchive):
      Default: Creates a JAR file, which is the standard format for packaging Java classes and associated resources. This is the default packaging type if none is specified.
   2. war (Web Application Archive):
      For web applications: Creates a WAR file, which is used for packaging web applications and includes web-related resources like servlets, JSPs, and configuration files.
   3. pom (Project Object Model):
      For projects that only serve as a parent POM: This packaging type is used for projects that act as a parent POM, managing common configurations and dependencies for other projects but do not produce an artifact.
3. See below
4. ...
5. ...
6. **Phases:**

   1. **validate**: Validate the project's structure and configuration.
   2. **compile**: Compile the source code.
   3. **test**: Run tests against the compiled source code.
   4. **package**: Package the compiled code into a distributable format (e.g., JAR, WAR).
   5. **verify**: Perform any additional verification (e.g., integration tests) to ensure the package is valid.
   6. **install**: Install the package into the local Maven repository.
   7. **deploy**: Deploy the package to a remote repository.
7. `git merge` combines changes from one branch into another by creating a merge commit that preserves the history of both branches, showing branch points and merge points. In contrast,`git rebase` rewrites the commit history by re-applying commits from one branch on top of another, resulting in a linear history without merge commits. While merging maintains the original branch structure and context, rebasing creates a cleaner, linear history but can complicate conflict resolution and potentially rewrite history, which may be problematic for shared branches.
8. Trunk-Based Development (TBD) focuses on a single main branch where developers frequently integrate changes, using short-lived feature branches and feature flags to manage work. This strategy minimizes integration challenges, supports continuous integration and deployment, and maintains a stable codebase by avoiding long-lived branches and ensuring regular testing and feedback.
9. * **`--soft`**: Keeps changes in the staging area and working directory, allowing you to amend or recommit.
   * **`--mixed`**: Unstages changes but keeps them in the working directory, allowing you to modify or review before re-staging.
   * **`--hard`**: Discards all changes, resetting both the staging area and working directory to the specified commit.

# Console history

```
'xinzhang@DN51t53p hw2-example_project % mvn install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] hw2-example_project                                                [pom]
[INFO] example_module                                                     [jar]
[INFO]
[INFO] ------------------< org.example:hw2-example_project >-------------------
[INFO] Building hw2-example_project 1.0-SNAPSHOT                          [1/2]
[INFO]   from pom.xml
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] --- install:3.1.2:install (default-install) @ hw2-example_project ---
[INFO] Installing /Users/xinzhang/Code/chuwa/hw2-example_project/pom.xml to /Users/xinzhang/.m2/repository/org/example/hw2-example_project/1.0-SNAPSHOT/hw2-example_project-1.0-SNAPSHOT.pom
[INFO]
[INFO] ---------------------< org.example:example_module >---------------------
[INFO] Building example_module 1.0-SNAPSHOT                               [2/2]
[INFO]   from example_module/pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ example_module ---
[INFO] Copying 0 resource from src/main/resources to target/classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ example_module ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 1 source file with javac [debug target 17] to target/classes
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ example_module ---
[INFO] skip non existing resourceDirectory /Users/xinzhang/Code/chuwa/hw2-example_project/example_module/src/test/resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ example_module ---
[INFO] Recompiling the module because of changed dependency.
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ example_module ---
[INFO]
[INFO] --- jar:3.4.1:jar (default-jar) @ example_module ---
[INFO] Building jar: /Users/xinzhang/Code/chuwa/hw2-example_project/example_module/target/example_module-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- install:3.1.2:install (default-install) @ example_module ---
[INFO] Installing /Users/xinzhang/Code/chuwa/hw2-example_project/example_module/pom.xml to /Users/xinzhang/.m2/repository/org/example/example_module/1.0-SNAPSHOT/example_module-1.0-SNAPSHOT.pom
[INFO] Installing /Users/xinzhang/Code/chuwa/hw2-example_project/example_module/target/example_module-1.0-SNAPSHOT.jar to /Users/xinzhang/.m2/repository/org/example/example_module/1.0-SNAPSHOT/example_module-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for hw2-example_project 1.0-SNAPSHOT:
[INFO]
[INFO] hw2-example_project ................................ SUCCESS [  0.130 s]
[INFO] example_module ..................................... SUCCESS [  0.909 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.098 s
[INFO] Finished at: 2024-09-09T15:02:46-07:00
[INFO] ------------------------------------------------------------------------
```
