# HW2

Yuhang Li

# 1. Find **at least ONE** dependency for each packaging type on https://mvnrepository.com/repos/central 

Here are examples of dependencies for each packaging type from the [Maven Central Repository](https://mvnrepository.com/repos/central):

### 1. **Packaging type: WAR**
   - **Dependency**: `org.primefaces:primefaces-showcase`
     - **Version**: 8.0
     - **Packaging**: WAR
     - **Link**: [PrimeFaces Showcase](https://mvnrepository.com/artifact/org.primefaces/primefaces-showcase/8.0)

### 2. **Packaging type: JAR**
   - **Dependency**: `org.springframework.boot:spring-boot-starter-web`
     - **Version**: 3.1.0
     - **Packaging**: JAR
     - **Link**: [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.1.0)

### 3. **Packaging type: POM**
   - **Dependency**: `org.apache.maven:maven`
     - **Version**: 3.8.7
     - **Packaging**: POM
     - **Link**: [Apache Maven](https://mvnrepository.com/artifact/org.apache.maven/maven/3.8.7)

### 4. **Packaging type: Other (e.g., Eclipse Plugin)**
   - **Dependency**: `org.eclipse.jetty:jetty-eclipse-plugin`
     - **Version**: 9.4.51.v20231031
     - **Packaging**: Eclipse Plugin
     - **Link**: [Jetty Eclipse Plugin](https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-eclipse-plugin/9.4.51.v20231031)

# 2. Explain the difference between war , jar , and POM
The primary differences between WAR, JAR, and POM packaging types in the context of Java projects revolve around their usage and structure:

### 1. **JAR (Java ARchive)**
   - **Purpose**: JAR files are used to package Java libraries, utilities, or standalone applications.
   - **Contents**: A JAR file typically contains compiled `.class` files, along with optional resources (e.g., images, configuration files), and a manifest file that describes the contents and entry point of the application if it's executable.
   - **Use Case**: JAR files are commonly used to distribute reusable Java libraries or entire applications that can be run on the JVM. For example, utility libraries like `spring-boot-starter-web` or standalone applications.
   - **Packaging**: It’s ideal for class files, packaged as a simple, lightweight, and reusable unit.

   **Example**: If you want to create a reusable Java library, you'd package it as a JAR.

---

### 2. **WAR (Web Application Archive)**
   - **Purpose**: WAR files are used for packaging web applications that are meant to be deployed to a servlet container (e.g., Tomcat, Jetty).
   - **Contents**: A WAR file contains not just `.class` files and resources like a JAR, but also web-specific directories like `WEB-INF`, `web.xml` (deployment descriptor), and potentially JSP files, HTML, CSS, JavaScript, and other assets required by web applications.
   - **Use Case**: WAR files are used when deploying Java-based web applications that need to run on a web server or application server. They provide a structure for packaging servlets, JSP pages, and web components.
   - **Packaging**: It’s designed for web applications that interact over HTTP and are to be deployed on web servers.

   **Example**: If you build a Java web application with servlets or Spring MVC, you would package it as a WAR for deployment.

---

### 3. **POM (Project Object Model)**
   - **Purpose**: POM files are not archives in the traditional sense but are XML files used by Maven to manage project dependencies, configurations, and plugins.
   - **Contents**: A POM file contains metadata about the project (like its version, dependencies, build settings, and plugin configurations). In some cases, POM packaging is used when the project itself does not produce an artifact like a JAR or WAR but instead serves as a parent project or a bill of materials (BOM).
   - **Use Case**: POM packaging is often used for parent projects or projects that manage dependencies for other submodules. The parent POM can centralize dependency versions and configurations across multiple submodules or projects.
   - **Packaging**: It’s for managing dependencies or settings in multi-module projects, rather than producing a deployable artifact.

   **Example**: If you have a multi-module project, you might have a parent POM file with packaging type POM to manage shared settings.

---

### Summary of Differences:
- **JAR** is used for packaging libraries or applications that can be run or included in other projects.
- **WAR** is specifically for packaging web applications for deployment to a server.
- **POM** is an XML file for dependency management, and in some cases, POM packaging is used for parent projects that do not produce direct deployable artifacts like JAR or WAR files.



# 3. Create a maven managed project in IntelliJ Idea, add above dependencies to your project

![Screenshot 2024-09-24 at 4.31.24 PM](/Users/liyuhang/Library/Application Support/typora-user-images/Screenshot 2024-09-24 at 4.31.24 PM.png)

### **How I Resolved Errors:**

- **Dependency Version Issues**: For some dependencies (especially WAR), resolving the correct version from the Maven Central repository ensured compatibility with the project.
- **Optional WAR Dependency**: Marked the `primefaces-showcase` WAR dependency as optional, since it's typically meant for showcasing UI components, not core application functionality.
- **Eclipse Plugin Configuration**: Ensured that Eclipse plugins are correctly configured by using an appropriate version and confirming the Maven repository availability.
- **Maven Reload**: Reloading the Maven project and performing a **clean** and **install** step ensured all dependencies were properly downloaded and linked.

# 4. Build your project, even if it's empty

## 1. Resolve all build-related issues

![Screenshot 2024-09-24 at 4.37.16 PM](/Users/liyuhang/Library/Application Support/typora-user-images/Screenshot 2024-09-24 at 4.37.16 PM.png)

## 2.Install your executable to local maven repository

To install your executable (the JAR or WAR artifact of your Maven project) into the **local Maven repository**, follow these steps:

### 1. **Install via Maven Command**:
   Maven provides a command to install your built artifact (JAR, WAR, etc.) into your local Maven repository (`~/.m2/repository` by default):

   - Use the IntelliJ terminal.
   - Run the following Maven command from the root of your project:

   ```bash
   mvn clean install
   ```

### What This Does:
- **`clean`**: Removes any previously compiled artifacts (optional).
- **`install`**: Builds your project and installs the resulting artifact (JAR or WAR) into the local Maven repository located at `~/.m2/repository`.

![Screenshot 2024-09-24 at 4.41.00 PM](/Users/liyuhang/Library/Application Support/typora-user-images/Screenshot 2024-09-24 at 4.41.00 PM.png)

### 2. **Verify the Installation**:
   After running the command, Maven will:
   - Build the project.
   - Place the built artifact (JAR, WAR, or any other packaging type) in your local Maven repository under a folder that matches your **groupId** and **artifactId**.

   To verify, navigate to your local repository:
   - Location (macOS): `~/.m2/repository`

   In this folder, you could see directories corresponding to your project’s `groupId`, `artifactId`, and `version`.

![Screenshot 2024-09-24 at 4.45.04 PM](/Users/liyuhang/Library/Application Support/typora-user-images/Screenshot 2024-09-24 at 4.45.04 PM.png)

![Screenshot 2024-09-24 at 4.44.43 PM](/Users/liyuhang/Library/Application Support/typora-user-images/Screenshot 2024-09-24 at 4.44.43 PM.png)





# 5. Create a new module in your maven project, make4.2 ) as a dependency of this module

In pom.xml, add module:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>HW2</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

Also, in parent pom.xml, add:

```xml
<packaging>pom</packaging>
```



# 6. List Maven life cycles in order, compare them.

Maven has three built-in life cycles, each consisting of a series of phases that define the process of building and managing projects. The three life cycles are **default**, **clean**, and **site**. Here’s a breakdown of each life cycle, its phases, and a comparison:

### 1. Default Life Cycle
The default life cycle is the primary life cycle used for building and deploying your application. It consists of the following phases:

- **validate**: Check if the project is correct and all necessary information is available.
- **compile**: Compile the source code of the project.
- **test**: Test the compiled source code using a suitable testing framework.
- **package**: Package the compiled code into a distributable format (e.g., JAR, WAR).
- **verify**: Run any checks to verify the package is valid and meets quality standards.
- **install**: Install the package into the local Maven repository for use as a dependency in other projects.
- **deploy**: Copy the final package to the remote repository for sharing with other developers and projects.

### 2. Clean Life Cycle
The clean life cycle is used to remove all files generated by the default life cycle. Its main phases include:

- **pre-clean**: Perform actions required before the clean phase.
- **clean**: Remove all files generated by the previous build.
- **post-clean**: Perform actions required after the clean phase.

### 3. Site Life Cycle
The site life cycle is responsible for generating project documentation and reports. Its phases are:

- **pre-site**: Prepare for the site generation.
- **site**: Generate the project’s site documentation.
- **post-site**: Perform actions required after the site has been generated.
- **site-deploy**: Deploy the generated site to a web server.

### Comparison of Life Cycles

| Life Cycle | Purpose                                   | Phases                                                    | When to Use                                             |
| ---------- | ----------------------------------------- | --------------------------------------------------------- | ------------------------------------------------------- |
| Default    | Build and deploy the project              | validate, compile, test, package, verify, install, deploy | Most common for everyday project tasks                  |
| Clean      | Remove build artifacts                    | pre-clean, clean, post-clean                              | Before a new build to ensure a clean slate              |
| Site       | Generate and deploy project documentation | pre-site, site, post-site, site-deploy                    | When you need to create or update project documentation |

### Summary
- **Default** is the core life cycle for building and deploying applications.
- **Clean** focuses on removing generated files to ensure a fresh build environment.
- **Site** handles the creation and deployment of project documentation.

Each life cycle serves a distinct purpose in the Maven build process, allowing developers to efficiently manage different aspects of project development.



# 7. Explain git merge vs git rebase

Git merge and Git rebase are both methods for integrating changes from one branch into another, but they do so in different ways and have different implications for the project's commit history. Here’s a breakdown of each:

### Git Merge
- **Definition**: Merging takes the contents of a source branch and integrates them with a target branch. It creates a new "merge commit" in the target branch that combines the changes from both branches.
- **How It Works**:
  1. You switch to the target branch (e.g., `main`).
  2. You execute the command `git merge <source-branch>`.
  3. Git combines the changes and creates a new commit on the target branch that has two parent commits (the latest commit of both branches).
- **Advantages**:
  - Retains the complete history of changes, showing when branches were created and merged.
  - Easier to understand the context of the changes and branch structure.
- **Disadvantages**:
  - Can create a messy commit history with multiple merge commits, especially in collaborative environments.
  - Makes it harder to understand the linear progression of commits.

### Git Rebase
- **Definition**: Rebasing involves moving or "replaying" the commits from one branch onto another. Instead of creating a new merge commit, it rewrites the commit history to make it appear as if the changes were made in a straight line.
- **How It Works**:
  1. You switch to the branch you want to rebase (e.g., `feature-branch`).
  2. You execute the command `git rebase <target-branch>`.
  3. Git takes each commit from the `feature-branch` and replays it on top of the `target-branch`, effectively changing the base of the `feature-branch`.
- **Advantages**:
  - Creates a cleaner, more linear commit history, making it easier to follow.
  - Simplifies the integration of changes by eliminating unnecessary merge commits.
- **Disadvantages**:
  - Alters commit history, which can lead to confusion if not used properly, especially in shared branches.
  - More complex in resolving conflicts, as conflicts must be resolved for each commit being rebased.

### Key Differences

| Feature             | Git Merge                            | Git Rebase                         |
| ------------------- | ------------------------------------ | ---------------------------------- |
| Commit History      | Creates a merge commit               | Rewrites commit history            |
| Parent Commits      | Has two parent commits               | Has a single parent commit         |
| Use Case            | Better for preserving history        | Better for a clean, linear history |
| Conflict Resolution | Conflicts resolved once in the merge | Conflicts resolved for each commit |
| Recommended for     | Collaborative workflows              | Individual feature branches        |

### When to Use Each
- **Use Git Merge** when:
  - You want to preserve the complete history of how changes were made.
  - You're working in a collaborative environment and want to maintain a clear record of branches and merges.

- **Use Git Rebase** when:
  - You want a clean and linear commit history.
  - You're working on your own feature branch and want to incorporate the latest changes from the main branch without cluttering the history with merge commits.

Both methods have their place in a Git workflow, and understanding their differences can help you choose the best approach for your project's needs.

# 8. Explain Trunk-based developement git branching strategy.

**Trunk-Based Development (TBD)** is a Git branching strategy that emphasizes frequent integration of changes into a single, central branch, often referred to as the "trunk" (or `main` or `master`). This approach is designed to facilitate continuous delivery and reduce the complexity of managing multiple long-lived branches. Here’s a detailed explanation of Trunk-Based Development:

### Key Principles of Trunk-Based Development

1. **Single Main Branch**:
   - In TBD, there is typically one primary branch (the trunk) where all code changes are integrated. Developers branch off this trunk for short-lived feature branches.

2. **Short-Lived Feature Branches**:
   - Developers create branches to work on features, bug fixes, or experiments, but these branches should be short-lived—ideally, merging back into the trunk within a day or two.
   - The goal is to minimize divergence from the trunk and reduce the potential for integration issues.

3. **Frequent Integration**:
   - Developers are encouraged to integrate their changes back into the trunk frequently (multiple times a day, if possible). This practice helps identify integration problems early.

4. **Automated Testing**:
   - Continuous integration (CI) practices are often employed, where automated tests run on each commit to the trunk. This ensures that the codebase remains stable and functional after every integration.

5. **Feature Toggles**:
   - To manage incomplete features, developers may use feature toggles (or feature flags). This allows them to merge incomplete features into the trunk while keeping them disabled for users, enabling continuous integration without disrupting the production environment.

6. **Deployment Readiness**:
   - The trunk is always in a deployable state. Changes should be made with the understanding that they could be deployed at any time, ensuring that the codebase is always production-ready.

### Advantages of Trunk-Based Development

- **Reduced Complexity**: 
  - With fewer long-lived branches, there’s less overhead in managing and merging branches, leading to a simpler workflow.

- **Faster Feedback**:
  - Frequent integration and automated testing provide quick feedback on code changes, making it easier to catch bugs and integration issues early.

- **Better Collaboration**:
  - Developers are more aware of each other's work, leading to improved communication and collaboration.

- **Continuous Delivery**:
  - By keeping the trunk in a deployable state, organizations can adopt continuous delivery practices, allowing for faster releases and updates.

### Disadvantages of Trunk-Based Development

- **Integration Challenges**:
  - Although the strategy aims to minimize integration issues, frequent changes can still lead to conflicts if multiple developers are working on the same area of the codebase.

- **Requires Discipline**:
  - Teams must be disciplined about keeping branches short-lived and integrating frequently, which may require a cultural shift for some organizations.

- **Complex Feature Development**:
  - For larger or more complex features that cannot be completed quickly, managing their development alongside the main trunk can be challenging.

### Best Practices

- **Adopt CI/CD**:
  - Implement continuous integration and continuous deployment (CI/CD) practices to automate testing and deployment processes.

- **Use Feature Toggles Wisely**:
  - Utilize feature toggles to manage incomplete features while keeping the trunk stable and deployable.

- **Regular Stand-Ups**:
  - Conduct regular stand-up meetings to ensure all team members are aware of ongoing work and potential conflicts.

- **Automated Testing**:
  - Invest in automated tests to ensure that every change pushed to the trunk is validated for stability and functionality.

### Conclusion

Trunk-Based Development is a powerful Git branching strategy that promotes a culture of collaboration and continuous delivery. By encouraging frequent integration and maintaining a single, stable trunk, teams can streamline their development processes, reduce complexity, and deliver features more rapidly. However, it requires discipline and a commitment to maintaining a deployable codebase.

# 9. Explain git reset options.

`git reset` is a powerful command in Git used to undo local changes and manage the commit history. It alters the current branch by moving the HEAD pointer to a specified commit. There are three primary options for `git reset`: **soft**, **mixed**, and **hard**. Each option determines how the command affects the working directory, staging area, and commit history. Here’s a detailed explanation of each option:

### 1. `git reset --soft <commit>`
- **Description**: Moves the HEAD pointer to the specified commit without changing the working directory or the staging area.
- **Effect**:
  - The changes made in the commits after the specified commit are left in the staging area (index), allowing you to amend them or create a new commit.
- **Use Case**: Use this option when you want to undo a commit but keep the changes staged for a new commit.
- **Example**:
  ```bash
  git reset --soft HEAD~1
  ```
  This command undoes the last commit, keeping the changes staged.

### 2. `git reset --mixed <commit>` (default)
- **Description**: Moves the HEAD pointer to the specified commit and resets the staging area to match that commit, but leaves the working directory unchanged.
- **Effect**:
  - Changes made after the specified commit are kept in the working directory but are unstaged. This means you can review and modify them before staging again.
- **Use Case**: Use this option when you want to undo a commit and unstage the changes for further modification.
- **Example**:
  ```bash
  git reset --mixed HEAD~1
  ```
  This command undoes the last commit, unstaging the changes while keeping them in the working directory.

### 3. `git reset --hard <commit>`
- **Description**: Moves the HEAD pointer to the specified commit and resets both the staging area and working directory to match that commit.
- **Effect**:
  - All changes made after the specified commit are discarded. This includes any changes in the staging area and working directory.
- **Use Case**: Use this option when you want to completely discard changes and revert to a specific commit.
- **Example**:
  ```bash
  git reset --hard HEAD~1
  ```
  This command undoes the last commit and discards all changes associated with it.

### Summary of Effects

| Option              | Changes in Staging Area | Changes in Working Directory |
| ------------------- | ----------------------- | ---------------------------- |
| `--soft`            | Keeps changes staged    | Unchanged                    |
| `--mixed` (default) | Unstages changes        | Unchanged                    |
| `--hard`            | Discards changes        | Discards changes             |

### Additional Considerations
- **Warning**: Using `git reset --hard` can lead to data loss, as it permanently removes changes. Always ensure that you don't need the changes before executing this command.
- **Reflog**: If you accidentally use `git reset` and want to recover lost commits, you can use `git reflog` to find the previous state of your HEAD and reset to it.

### Conclusion
Understanding the different options of `git reset` is crucial for effectively managing your commit history and handling local changes in your Git repository. Each option serves a specific purpose, and using them appropriately can help you maintain a clean and organized project history.
