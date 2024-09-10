## 1. Find dependency for each packaging type:

- **war**: Jenkins WAR » 2.475 (<https://github.com/jenkinsci/jenkins>)
- **jar**: JUnit Jupiter API » 5.11.0 (<https://junit.org/junit5/>)
- **POM**: Spring Boot Dependencies » 3.3.3 (<https://spring.io/projects/spring-boot>)
- **ear**: OpenEJB :: Examples :: Simple EAR :: EAR Packaging » 7.1.3-TT.3

## 2. Explain the difference between war, jar, and POM

- **POM**: Describes the project structure, and its `<packaging></packaging>` will define it to build JAR or WAR files.

- **JAR** and **WAR** are both packaging formats. **JAR** is for general Java packaging, but **WAR** is specifically for web applications.

- **JAR** can be executed directly if they have a main class, **WAR** needs to be deployed to a server, and **POM** is not executable

## 7. Explain git merge vs git rebase

`git merge`and `git rebase` are both methods for integrating changes from one branch into another, but they have differences:

- `git merge` preserves history exactly as it happened.
`git rebase` rewrites history, making commit history leaner so that work is always based on the latest version, making it easier to revert to previous version.

- `git merge` creates a new merge commit.
`git rebase` creates new commits for each commit in the original branch.

## 8. Explain Trunk-based developement git branching strategy.

## 9. Explain git reset options.