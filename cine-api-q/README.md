# Cine App - BackEnd

> Backend using the Quarkus framework.

> Quarkus is a Java based framework.

This module is responsible to access [The Movie Database (TMDb) API](https://developers.themoviedb.org/3/) and expose some of the endpoints to the Frontend Module.

### Highlights

* Hot reload on development mode
* Fast bootstrap
* Cache
* Rest Client
* Rest Endpoints
* Docker as JVM based image or Native based image
* Publish the API docs with OpenAPI pattern
* Swagger UI embedded

## Prerequisites

* [Maven 3.5.3+](https://maven.apache.org/install.html)
* [Java - OpenJDK 1.8+](https://adoptopenjdk.net/)

## Running the application in Development Mode

Folder: *cine-api-q*

To run the application, use:

``` bash
./mvnw compile quarkus:dev
```
Once started, you can request the provided endpoint:

``` bash
http://localhost:8080/
```

## Development Mode

*quarkus:dev* runs Quarkus in development mode. This enables hot deployment with background compilation, which means that when you modify your Java files and/or your resource files and refresh your browser, these changes will automatically take effect. This works too for resource files like the configuration property file. Refreshing the browser triggers a scan of the workspace, and if any changes are detected, the Java files are recompiled and the application is redeployed; your request is then serviced by the redeployed application. If there are any issues with compilation or deployment an error page will let you know.

This will also listen for a debugger on port 5005. If your want to wait for the debugger to attach before running you can pass *-Ddebug* on the command line. If you don’t want the debugger at all you can use *-Ddebug=false*.

## Packaging and run the application
The application is packaged using:

```bash
 ./mvnw package
 ```
 
 It produces 2 jar files:

*api-1.0-SNAPSHOT.jar* - containing just the classes and resources of the projects, it’s the regular artifact produced by the Maven build;

*api-1.0-SNAPSHOT-runner.jar* - being an executable jar. Be aware that it’s not an über-jar as the dependencies are copied into the target/lib directory.

You can run the application using: 

```java
java -jar target/api-1.0-SNAPSHOT-runner.jar
```

## Using Maven as tooling

Tips about the use of Maven as a buil tool in Quarkus: 
<https://quarkus.io/guides/maven-tooling.html>

## More about Quarkus

There is documentation published at <https://quarkus.io> (docs' [sources are here](https://github.com/quarkusio/quarkus/tree/master/docs/src/main/asciidoc)).