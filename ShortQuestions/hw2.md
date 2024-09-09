# Question 1
* war: servlet
* jar: JUnit
* pom: SpringWeb
other: Maven 3 plugin
# Explain the difference between war , jar , and POM
war is for package Java class files and resources, ususally for standalone Java applications in other projects. jar is for web-based Java application which also complies web components. POM project can only defines the configuration of a Maven project. It is not for compiling or packaging code.

# Question 3
I have no problem of adding all the dependency above to the project 

# Question 4
Build was successful with no issues
```
[INFO] Building jar: C:\Users\nicho\IdeaProjects\untitled1\target\untitled1-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.651 s
[INFO] Finished at: 2024-09-08T23:58:36-07:00
[INFO] ------------------------------------------------------------------------
```
Install was also successful
```
[INFO] --- install:3.1.2:install (default-install) @ untitled1 ---
[INFO] Installing C:\Users\nicho\IdeaProjects\untitled1\pom.xml to C:\Users\nicho\.m2\repository\org\example\untitled1\1.0-SNAPSHOT\untitled1-1.0-SNAPSHOT.pom
[INFO] Installing C:\Users\nicho\IdeaProjects\untitled1\target\untitled1-1.0-SNAPSHOT.jar to C:\Users\nicho\.m2\repository\org\example\untitled1\1.0-SNAPSHOT\untitled1-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.660 s
[INFO] Finished at: 2024-09-09T00:02:53-07:00
```

# Question 5
I add a module and add dependency
```
<dependency>
    <groupId>org.example</groupId>
    <artifactId>untitled1</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```
It needs me to make the parent pom package as POM and I add:

```
<packaging>pom</packaging>
```
# Question 6
* Build Lifecycle
* Clean Lifecycle
* Site Lifecycle

Build Lifecycle Manages the entire build process ,Clean Lifecycle is just for cleaning up files from previous builds, and site lifecycle focus on generate project documentation and reports.

# Question 7
## Merge
Merge is to combine changes from one branch to another, which will preserve the complete history.

## Rebase
rebase will apply the changes from one branch to another, making a linear history.

# Question 8
For trunk-based development there is always a single core branch that is constantly got merged by the short-lived feature branches.

# Question 9
`git reset` is for undo changed and moving head to a different commit. There are three parameters of `git reset`:
* git reset --mixed \<commit>: default action to reset, it reset the index to the specific commit, unstages any changes, but you still the changes in the working directory.
* git reset --soft  \<commit>: different than `--mixed` it won't unstages any changes
* git reset --hard  \<commit>: this will remove both the changes of staging and working directory

# Extra
My code for maven is in Coding/untitled1