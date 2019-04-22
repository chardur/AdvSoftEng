# Build & Deploy Procedures

<!-- Table of contents generated at https://ecotrust-canada.github.io/markdown-toc/ -->

- [Build & Deploy Procedures](#build---deploy-procedures)
  * [Version, Build, Tag](#version--build--tag)
  * [Build Docker Image](#build-docker-image)
    + [Automatic Building During Maven Build](#automatic-building-during-maven-build)
      - [Configure IntelliJ Maven Properties](#configure-intellij-maven-properties)
    + [Manual Building](#manual-building)
    + [Docker Base Image Selection](#docker-base-image-selection)
  * [Deploy](#deploy)
    + [Rollback](#rollback)
    + [Initial Elastic Beanstalk Application Deploy](#initial-elastic-beanstalk-application-deploy)
  * [Create GitHub Release](#create-github-release)

Ideally, building and deploying would be automated through a DevOps / Delivery Tool such as Jenkins, etc. 
but due to time constraints these manual build and deploy instructions should be followed when a new version
of the API is ready to be released into Production.

## Version, Build, Tag
1. Ensure that all tests are passing
   * Run all Unit/Component Tests
   * Launch application and run all Acceptance Tests
2. Increment the `version` in the pom.xml
   * A [Semantic Versioning](https://semver.org/) scheme of `MAJOR.MINOR.PATCH` should be used
3. Perform a `maven clean install`. Ensure that the build resulted in a `BUILD SUCCESS`.
4. Commit & Push the version number change to Github. Do a Pull Request. Receive PR Approval and Merge PR.
5. Tag the Merge Commit in GitHub.

## Build Docker Image

### Automatic Building During Maven Build

A Docker Image _can_ be automatically created during the `install` phase of the maven build. By default this
functionality is disabled (mainly because of some configuration issues with Docker, Maven, and Windows). To
activate this feature, run the build with an additional `-Ddocker.skip=false` flag:
```bash
mvn clean install -Ddocker.skip=false
```
Following the maven build, you can verify the image was built and tagged properly using `docker images`:
   ```
   REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
   shmoozed/shmoozed-api   0.2.8               df4e936490a6        2 seconds ago       126MB
   shmoozed/shmoozed-api   latest              df4e936490a6        2 seconds ago       126MB
   openjdk                 8-jre-alpine        7e72a7dcf7dc        3 days ago          83.1MB

   ```

#### Configure IntelliJ Maven Properties

If using IntelliJ to launch the maven `clean` and `install` phases, additional configuration is needed so that
IntelliJ will launch maven the correct `-Ddocker.skip=false` flag. This configuration only needs to be performed
once.
1. File --> Settings...
2. Expand Build, Execution, Deployment --> Build Tolols --> Maven --> Runner
3. Click the `+` in the Properties panel in the right side of the settings window
4. Set the Name to `docker.skip` and the value to `false`. Click OK to add it.
5. Click the Apply button.
6. Click the OK button.
7. In the Right Side of IntelliJ, expand the `Maven Projects` tab
8. Expand shmoozed --> Lifecycle
9. Ctrl + Click to select both `clean` and `install`
10. Click the green "Run Maven Build" button at the top of the panel
11. A teminal window will appear with a bunch of log output
12. Ensure that it ends with a message of `BUILD SUCCESS` with output logs
13. Verify the image was built and tagged properly using `docker images`:
   ```
   REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
   shmoozed/shmoozed-api   0.2.8               df4e936490a6        2 seconds ago       126MB
   shmoozed/shmoozed-api   latest              df4e936490a6        2 seconds ago       126MB
   openjdk                 8-jre-alpine        7e72a7dcf7dc        3 days ago          83.1MB
   ```

### Manual Building

A Docker Image can be created manually using the below process.
1. Ensure that the [Build, Version, & Tag](#version-build-tag) have been completed
2. Perform a `docker build -t shmoozed/shmoozed-api:latest -t shmoozed/shmoozed-api:X.Y.Z .` from within 
the `/BackEnd/shmoozed/` directory.
   * Substitute the current `version` from the pom.xml in as the `X.Y.Z` tag version.
3. Ensure that the docker build resulted in a `Successfully built`.
   ```
   Sending build context to Docker daemon  43.58MB
   Step 1/4 : FROM java:8-jdk-alpine
    ---> 3fd9dd82815c
   Step 2/4 : VOLUME /tmp
    ---> Using cache 
    ---> 1a6a0e74fd18
   Step 3/4 : COPY target/*.jar app.jar
    ---> Using cache
    ---> 59f4b8cd483a
   Step 4/4 : ENTRYPOINT ["java","-jar","/app.jar"]
    ---> Using cache
    ---> 93e73e16e65d
   Successfully built 93e73e16e65d
   Successfully tagged shmoozed/shmoozed-api:latest
   ```
   * Note that if you are building the image on Windows you may get a Security Warning. This warning is safe to ignore for our application.
   ```SECURITY WARNING: You are building a Docker image from Windows against a non-Windows Docker...```
4. Verify the image was built and tagged properly using `docker images`
   ```
   REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
   shmoozed/shmoozed-api   0.2.8               df4e936490a6        2 seconds ago       126MB
   shmoozed/shmoozed-api   latest              df4e936490a6        2 seconds ago       126MB
   openjdk                 8-jre-alpine        7e72a7dcf7dc        3 days ago          83.1MB

   ```
5. Test the built image by performing `docker run --name shmoozed -p 5000:5000 -p 9000:9000 shmoozed/shmoozed-api`

### Docker Base Image Selection

We want to keep the Docker image which is created as small as possible so that it is faster to deploy. There are various java
base images which are available to run the application.

After pulling and building on all alpine-based image bases, we will be using the `openjdk:8-jre-alpine` image as it is the 
smallest size available at the time of writing for Java 8.

```
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
openjdk             8-jre-alpine        7e72a7dcf7dc        3 days ago          83.1MB
openjdk             8-jdk-alpine        2cfb1dc1f0c8        3 days ago          103MB
java                8-jre-alpine        fdc893b19a14        22 months ago       108MB
java                8-jdk-alpine        3fd9dd82815c        22 months ago       145MB
```

## Deploy

1. Follow all [Build, Version, & Tag](#version-build-tag) instructions
2. Log into AWS Account
3. Navigate to Elastic Beanstalk and find the `shmoozed-backend-api > Environments > ShmoozedBackendApi-env`
4. Click the `Upload and Deploy` button
5. Browse to `/BackEnd/shmoozed/target/shmoozed-X.Y.Z.jar` that was built previously using the `maven clean install` command
6. Click the `Deploy` button
7. Wait for the new JAR to be uploaded
8. AWS Elastic Beanstalk should display a message `Elastic Beanstalk is updating your environment`. Wait for it to complete the update.
9. Once the message disappears and the Health indicator goes Green and says `Ok` the application has been deployed and is ready to receive traffic
10. Perform several "sanity checks" against the application by exercising the API with Postman.

### Rollback

1. If something is wrong and the application needs to be rolled back, navigate to `shmoozed-backend-api > Application versions`.
2. Check the box of a previous JAR
3. Click the `Deploy` button
4. Verify the application is being deployed to the proper place. Click the `Deploy` button.
5. A message `Info - The deployment to ShmoozedBackendApi-env started successfully` messages should appear.
6. Navigate to the dashboard and ensure that the environment has gone Green and says `Ok`

### Initial Elastic Beanstalk Application Deploy

Based on instructions found at:
* https://medium.com/@ryanzhou7/running-spring-boot-on-amazon-web-services-for-free-f3b0aeec809
* A few additional pointers from https://medium.com/@autumn.bom/deploying-spring-boot-jar-application-on-beanstalk-java-se-platform-45d8d04608ae
* A few additional pointers from https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/ 

## Create GitHub Release
Document release and add binaries in Github...
