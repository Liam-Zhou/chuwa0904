
# HW16 - Docker & Kubernetes

# Explanation of Concepts

### Docker Daemon

The Docker daemon (`dockerd`) is a background service responsible for managing Docker images, containers, networks, and volumes. It listens to Docker API requests and manages Docker objects.

### Docker Host

The Docker Host is the machine where the Docker daemon runs, which could be a physical or virtual machine.

### Docker Registry

Docker Registry is a centralized location to store and distribute Docker images. Docker Hub is a public registry, but you can also create private registries.

### Image/Image Tag

An image is a packaged application that includes everything it needs to run, including code, libraries, and environment settings. Tags are used to differentiate between versions of the image, e.g., `latest` or `v1.0`.

### Container

A container is a lightweight and portable environment in which applications run, isolated from the rest of the system.

### Volume

A Docker volume is a persistent data storage mechanism that allows you to store data outside of a container's lifecycle.

### Docker Namespace vs. Kubernetes Namespace

- **Docker Namespace**: Isolation provided to processes, filesystems, or other resources inside a container.
- **Kubernetes Namespace**: A virtual cluster inside a Kubernetes cluster, used to divide cluster resources among multiple users.

### Kubernetes Node

A Kubernetes Node is a worker machine where workloads (pods) run. It can be a physical machine or a virtual one.

### Kubernetes Pod

A Pod is the smallest deployable unit in Kubernetes, representing one or more containers that are tightly coupled.

### Kubernetes Service

A Kubernetes Service is an abstraction that defines a logical set of Pods and a policy to access them, usually through load balancing.

### Kubernetes ReplicaSet

A ReplicaSet ensures that a specified number of replicas of a Pod are running at all times. If a Pod fails, the ReplicaSet replaces it.

### Kubernetes Deployment

A Kubernetes Deployment automates the process of scaling and managing application updates. It ensures that the desired state of the application is maintained.
