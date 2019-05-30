# Cine App

Cine App is a web application to search information about movies.

It's based on Quarkus (Java) and Nuxt (VueJS) frameworks.

The data is from [The Movie Database (TMDb) API](https://developers.themoviedb.org/3/).

### With this web app, the users can:

* See​ ​ a ​ ​ list​ ​ of​ ​ upcoming​ ​ movies​ ​ - ​ ​ including​ ​ the​ ​ movies'​ ​ name,​ ​ poster​ ​ or​ ​ backdrop​ ​ image, genre​ ​ and​ ​ release date.​ ​ 
* Select​ ​ a ​ ​ specific​ ​ movie​ ​ to​ ​ see​ ​ its​ ​ details​ ​ (name,​ ​ poster​ ​ image,​ ​ genre,​ ​ overview​ ​ and
release​ ​ date)
* Search​ ​ for​ ​ movies​ ​ by​ ​ entering​ ​ a ​ ​ partial​ ​ or​ ​ full​ ​ movie​ ​ name.

## Prerequisites

Backend:
* [Maven 3.5.3+](https://maven.apache.org/install.html)
* [Java - OpenJDK 1.8+](https://adoptopenjdk.net)

Frontend:
* [Node v8.16.0](https://nodejs.org)
* [NPM 6.9.0](https://www.npmjs.com)

To generate production version
* [Docker 17.05.0-ce+](https://www.docker.com/get-started)

## Running the application in Development Mode

### First: Backend

Folder: *cine-api-q*

To run the application, use:

``` bash
./mvn compile quarkus:dev
```
Once started, it runs on:

http://localhost:8080/


### Second: Fronted

Folder: *cine-ui*

Install dependencies:

``` bash
$ npm install
```

Serve with hot reload:

```
$ npm run dev
```
Once started, you can access the UI:

http://localhost:3000/

## API Documentation

This project uses the Open API standards and publishes the API docs with Swagger UI.

http://localhost:8080/swagger-ui

To see the API Specs in Json format, access:

http://localhost:8080/openapi

## Building for Production

To build a production version, we need 3 steps :

* Build the frontend
* Build the backend
* Generate the Docker image

To do that, it's necessary:

Go to backend folder:
 
 ```bash
 cine-api-q
 ```

 Run the build script:

 ```bash
 ./build-with-docker-image.sh
  ```
> This script generates the frontend application, copy the generated files to backend resources folder and make the build of the backend module with the static files of frontend working as a SPA (Single Page Application).

After that, you can run the application with:

```bash
docker run -i --rm -p 8080:8080 quarkus/api-jvm:latest
```
> In the production version, the web application runs inside a Docker image only as a Java application based on Quarkus.

Avaiable on http://localhost:8080/

## Future Improvements

*Frontend*

* Refactoring to make more reusable components
* Improvements in the page flows to use Vuex (State) to not loose the information about selected page and search results when change from a page to other
* Show traillers in Youtube and other informations about the movies.
* Allow to share a link of a movie
* Better UI

*Backend*
* Tunning and improvements of cache strategy
* Improvements to run as a native docker image

## Contact

You can contact me by e-mail in this [address](mailto:leandrosilvaferreira@gmail.com)
