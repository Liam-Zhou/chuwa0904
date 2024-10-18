### Build your Spring-Producer-Consumer application as a Docker image.

##### Save and load above image on local.
![dockerImage](../Imagehw16/dockerImage.png)
##### Push your image to your repository under Docker hub registry.
![dockerhubRepo](../Imagehw16/dockerhubRepo.png)
![dockerPushRepo](../Imagehw16/dockerPushRepo.png)
![dockerPushRepo2](../Imagehw16/dockerPushRepo2.png)

##### Add this image to the existing docker-compose.yml, together with other services.

![ImageToYml](../Imagehw16/ImageToYml.png)
##### Test if your container works as expected, test controllers via Postman.

![postmanDocker](../Imagehw16/postmanDocker.png)

##### Map a volume between container and host, you can map any path you want.

![dockerCompose](../Imagehw16/dockerCompose.png)
```angular2html
code in Coding/Spring-Producer-Consumer/docker-compose.yml
```

##### Bring up multinode-demo kubernetes context provided by Docker desktop.

![ImageToYml](../Imagehw16/ImageToYml.png)
##### Figure out how to enable Kubernetes dashboard.
![ImageToYml](../Imagehw16/ImageToYml.png)


### K8S
![multinode-demo](../Imagehw16/multinode-demo.png)
![k8sDashboard](../Imagehw16/k8sDashboard.png)
1. Docker creates the containers.
   - Docker is responsible for creating the container for your Spring app, Kafka, and MySQL.
2. Kubernetes manages the containers (using Docker to create and run them when needed).
   - Kubernetes (instruct Docker) manages these containers, ensuring they run as expected inside pods
3. Scenario: 
   - Each service you define in the docker-compose.yml file runs in a separate container( Docker creates the containers)
   - Kafka: Runs in its own container.
     MySQL: Runs in its own container.
     Zookeeper: Runs in its own container.
     Spring-Producer-Consumer App: Runs in its own container.
   - Deploy your Spring-Producer-Consumer project (which includes the Spring app, Kafka, MySQL) in a Kubernetes cluster
   - **A Pod is the smallest unit in Kubernetes. Each Pod runs one or more containers**
     Have a Pod for your Spring-Producer-Consumer app, and inside this pod, you’d run the Docker container for your Spring app.
     have another Pod for Kafka and another for MySQL.
   - **Nodes are the machines (either physical or virtual) where your Pods run.**
     Kubernetes may distribute the Kafka pod to Node 1, the MySQL pod to Node 2, and the Spring app pod to Node 3 to balance the load.
   - A Service in Kubernetes provides a stable network endpoint for your Pods. Even if the Pods change or move around nodes, the Service ensures they are reachable.
     You would create a Service for your Spring-Producer-Consumer app to expose it to users.
     You’d also create a Service for Kafka so that your Spring app can send messages to Kafka.
     Another Service would be created for MySQL to ensure your Spring app can access the database.
   - **A ReplicaSet ensures that a specific number of Pod replicas are running at any given time**
     three instances of Spring app running (to handle more traffic). I’d define a ReplicaSet that ensures three Pods of the Spring app are always running.
     If one of those Pods crashes, Kubernetes will automatically create a new Pod to replace it.
   - **A Deployment is a higher-level Kubernetes object that manages ReplicaSets and helps  with scaling, updates, and rollbacks.**
     create a Deployment for your Spring-Producer-Consumer app, specifying that you want three replicas of the Spring app Pod. Kubernetes will then handle creating the Pods, ensuring they stay up, and replacing them if needed.
     I can update my app by changing the image version in the Deployment, and Kubernetes will roll out the new version without downtime (by updating one Pod at a time).
   - **A Namespace in Kubernetes is like a virtual cluster inside your physical cluster.** 
   - It helps  organize and separate different projects or environments (like dev, test, and production).
     In the project, if I wanted to separate the development environment from the production environment, I could use different namespaces.
     For example, I could have a dev namespace for testing  Spring app and a prod namespace for the live version of the app.






- Docker Daemon: Runs Docker commands and manages containers for your app, Kafka, and MySQL.
- Docker Host: The machine running your containers.
- Docker Registry: Where your Spring app image is stored (e.g., Docker Hub).
- Image/Image Tag: Version of app (e.g., latest for the Spring app).
- Container: Runs your app, Kafka, and MySQL in isolation.
- Volume: Persists data, e.g., MySQL database storage.
- Docker Namespace: Isolates your containers internally.
- Kubernetes Namespace: Logically groups resources in a Kubernetes cluster.
- Kubernetes Node: The worker machine that runs  pods.
- Kubernetes Pod: Runs a single instance of your app, Kafka, or MySQL.
- Kubernetes Service: Exposes your app, Kafka, and MySQL to be accessed by others.
- Kubernetes ReplicaSet: Ensures a specific number of  Spring app replicas are always running.
- Kubernetes Deployment: Manages the lifecycle and updates of  Spring app.
