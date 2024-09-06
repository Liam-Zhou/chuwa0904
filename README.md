# chuwa0904

# Chuwa Training


## How to record your assignments by using this repo:

### Clone this repo:
open your teminal, run below command.

```bash
cd your_work_dir
git clone https://github.com/Liam-Zhou/chuwa0904
```


### Create your feature branch to implement the assignment.

```bash
git branch firstName_lastName/notes
git checkout firstName_lastName/notes

example: 
HW1:
git checkout -b Jitong_Yan/hw1
git add .
git commit -m "commit_message"
git push origin Jitong_Yan/hw1
```
write your assignment under this feature branch.

**short questions assignments -> shortQuestions directory**

**coding assignments -> coding directory**

### How to Raise a PR
working on your homework branch, ie. **firstName_lastName/hw1**
```bash
git push origin firstName_lastName/hw1
```
then open your github, **Compare and Pull** or **New pull request**
```text
to **firstName_lastName/notes** from **firstName_lastName/hw1**
```
You should always raise your PRs to your own firstName_lastName/notes branch, **Please don't raise your PR to main branch**
