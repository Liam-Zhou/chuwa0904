# Docker

## Docker Daemon
- **Definition**: A background service that manages Docker containers, images, networks, and volumes on the host machine.
- **Functionality**: Listens for API requests and manages Docker objects.

## Docker Host
- **Definition**: The physical or virtual machine where the Docker daemon runs and manages Docker containers.
- **Functionality**: Provides resources (CPU, memory) for running containers.

## Docker Registry
- **Definition**: A storage and distribution system for Docker images.
- **Common Examples**: Docker Hub (public), private registries (e.g., AWS ECR, Google Container Registry).

## Image / Image Tag
- **Image**: A read-only template used to create containers, containing the application code, libraries, and dependencies.
- **Image Tag**: A label to differentiate versions of images (e.g., `myapp:1.0`).

## Container
- **Definition**: A lightweight, executable package that includes everything needed to run an application (code, runtime, libraries).
- **Functionality**: Isolated environment for running applications, based on Docker images.

## Volume
- **Definition**: A persistent data storage mechanism for Docker containers.
- **Functionality**: Allows data to persist beyond the lifecycle of containers and share data between containers.

# Kubernetes Concepts

## Docker Namespace vs Kubernetes Namespace
- **Docker Namespace**: Used for resource isolation (e.g., process IDs, network stacks) in Docker.
- **Kubernetes Namespace**: A way to divide cluster resources between multiple users or projects, providing isolation within the same cluster.

## Kubernetes Node
- **Definition**: A physical or virtual machine in a Kubernetes cluster that runs pods.
- **Functionality**: Can be a worker node (runs applications) or a master node (controls the cluster).

## Kubernetes Pod
- **Definition**: The smallest deployable unit in Kubernetes, representing a single instance of a running process.
- **Functionality**: Can contain one or more containers that share storage and network resources.

## Kubernetes Service
- **Definition**: An abstraction that defines a logical set of pods and a policy for accessing them.
- **Functionality**: Provides a stable endpoint (IP address) for clients to access the pods, enabling load balancing and service discovery.

## Kubernetes ReplicaSet
- **Definition**: Ensures a specified number of pod replicas are running at any given time.
- **Functionality**: Monitors the pod instances and creates or deletes them as needed to maintain the desired count.

## Kubernetes Deployment
- **Definition**: A higher-level abstraction that manages ReplicaSets and provides declarative updates to applications.
- **Functionality**: Facilitates rolling updates, rollbacks, and scaling of applications.
