# Docker and Kubenetes Notes

### Some concepts
- Docker daemon： dockerd is the daemon of docker, which responsible for managing images, containers, volumes and networks.
- Docker host: the computer / virtual machine that runs the dameon
- Docker Registry: Just like github, it's a palce where we can store and share images. It can be private or public, the most famous one is DockerHub
- Image/Image Tag: A static template that contains everything needed to run an application, including the operating system, application code, dependencies, and configurations.   
  **[registry]/[repository]:[tag]** registry is optional, Name + Version(default is latest), example: ***ubuntu:22.04***. 
- Container: A running instance of an image, with its own process and runtime environment.
- Volume: A storage where we can store data persistantly and can be shared by multiple containers.
- Docker file/compose: A script containing instructions to build a Docker image including the base image, dependencies, configuration settings. Compose can manage multiple containers
- Docker Namespace: Docker Namespace is a feature provided by the Linux kernel to isolate resources in containers.
- Kubenetes Namespace: K8s Namespace is a logical partitioning mechanism in a Kubernetes cluster used to organize, manage, and isolate resources like Pods, Services, and other objects. 
- Kubenetes Node: A worker machine(physical or virtual machine).
- Kubernetes Pod: A smallest deployable unit in K8s. One pod can have more than one container.
- Kubernetes Service: An abstraction that provides network access to pods. like LoadBalancer, Traffic Routing and Static IP/Port(using label selector). 
- Kubernetes ReplicaSet: A controller that ensures a specified number of Pod replicas are running at all times. Will create new one when any Pod is deleted.
- Kubernetes Deployment: A higher-level abstraction used to manage the lifecycle of applications. It provides features like rolling updates, rollbacks. 
- K8s is Pod based. 


### Build your Spring-Producer-Consumer application as a Docker image