# 16

## Now can be run with Compose-up![DockerCompose](..\img\DockerCompose.png)

## Bring up multinode-demo kubernetes context provided by Docker desktop

![Kube](..\img\Kube.png)

## Explain following concepts:
**Docker daemon** (dockered): The server-side component of docker engine that runs as background at host system.  It is responsible of creating, managing, and orchestrating containers.

**Docker host** is the virtual **machine** or a physical machine that is running docker engine / container. It manages and run the containers.

**Docker Registry** (local/remote) It is the place the stores and manages the docker images. 

- **Store**: User can upload their built docker image into registry.
- **Dispatch, deploy** the from docker registry.
- **Version Management** with tags

**Image/Image Tag**: The "class", the template of a container. It consists of all the running requirements files, dependencies, environment variables, configurations. Every time creating a container, starts from a image.

**Container**, the running instance of a docker image. 

**Volume** a persistent and shared data mechanism. When we stop or delete a container, data inside the container will be lost. With persistent container, even the container is deleted, data still remains.

**Docker Namespace vs Kubernetes Namespace**

- **Docker Namespace**  use the Linux core Namespace to implement the isolation of containers. It is operating system level functions,  provides isolation resources for each process, every process in the container can operate individually.

- **Kubernetes Namespace** is a logical partitioning mechanism within a Kubernetes cluster used to organize and manage resources (like Pods and Services), allowing for resource quotas, access control, and simplified resource management.

  - **Logical Isolation** within the cluster, allowing resources with the same name to exist in different Namespaces (e.g., two Pods with the same name in `namespace-a` and `namespace-b`).
  - **Resources Quotas (配额)**: Namespaces can have resource quotas (limits on CPU, memory, etc.) to ensure fair resource usage within the cluster.
  - **Simplified Resource Management**: Namespaces help organize and manage resources in large clusters, especially when multiple teams or projects share that same Kubernetes cluster.

- Comparison: Docker Namespace vs Kubernetes Namespace

  | Feature                 | **Docker Namespace**                                     | **Kubernetes Namespace**                          |
  | ----------------------- | -------------------------------------------------------- | ------------------------------------------------- |
  | **Purpose**             | OS-level isolation of resources (process, network, etc.) | Logical partitioning of resources in a cluster    |
  | **Function**            | Isolates processes, networks, filesystems, etc.          | Groups and isolates Kubernetes resources          |
  | **Scope**               | Operating System (Linux Kernel Level)                    | Kubernetes Cluster Level                          |
  | **Typical Use Case**    | Container security and resource isolation                | Multi-team, multi-environment resource management |
  | **Implementation**      | Based on Linux Namespace technology                      | Internal Kubernetes mechanism                     |
  | **Resource Management** | Process, network, mount, IPC, etc. isolation             | Pods, Services, Deployments, resource quotas      |

  ### 

**Kubernetes Node** is a worker machine in a Kubernetes cluster, responsible for running **Pods**. Can be either virtual or physical machine. A Kubernetes cluster consists of one or mode nodes, and each Node contains the services necessary to run Pods and is managed by the Kubernetes control panel (master node).

​	**Key Components of a Kubernetes Node**:

- **Kubelet**: This is an agent that runs on each Node. It communicates with the Kubernetes control plane (e.g., API server) and ensures that the containers in the Pod are running as expected.
- **Kube-proxy**: This is responsible for networking within the Node. It manages network rules and enables communication between different Pods across Nodes in the cluster.
- **Container Runtime**: This is the software responsible for running containers. Kubernetes supports several container runtimes like Docker, containerd, and CRI-O.
- **Node Components**: Each Node runs the components that handle networking, storage, and scheduling tasks.

​	Role of a Node:

- **Resource Provider**: A Node provides the CPU, memory, and storage resources that Kubernetes uses to schedule and run Pods.
- **Running Pods**: Each Node can host multiple Pods. The Pods are scheduled onto Nodes based on resource availability and specific scheduling rules defined in Kubernetes.

​	**Type of Node**:

- **Master Node**: responsible for managing the Kubernetes control plane, includes the scheduler, controller-manager, API server etc. It does not typically run application Pods.
- **Worker Node**: Responsible for running application workloads, i.e. Pods, They receive instructions from the master node on which pods to run and manage the lifecycle of those pods

**Kubernetes Pod** is the smallest unit in Kubernetes object model. It is a single instance of running process in the cluster. It encapsulates one or more containers, along with shared storage and networking. Pods are ephemeral and managed by the Kubernetes scheduler, and they run on nodes.

**Kubernetes Service** is a logical set of pods and a policy by which they can be accessed. Since pods in Kubernetes are ephemeral and can be created or destroyed dynamically, a service provides a stable endpoint (such as IP or DNS name) for accessing a group of Pods, even as individual Pods are replaced over time.

**Kubernetes ReplicaSet** is a contorller in Kubernetes that ensure a specified number of identical Pods are running at any given time. It maintains the desired number of Pod replicas across the cluster, ensure that if any Pod crashes or get deleted, new ones are automatically created to replace them.

**Kubernetes Deployment**

A Deployment in Kubernetes automatically manages ReplicaSets and adds more functionality:

**Benefits**: 

- **Allow for rollback and updates**
- **Common practice**
- **Simplified management**