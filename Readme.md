# Project Title
A simple website built with Spring boot showing top stories of the day using Hacker-News api.<br>
Below are the three endpoints
- `/top-stories`
- `/comments/{id}`<br>
  where id is the 'id of a story'
- `/past-stories`

*NOTE* - Click on the stories for more info<br>

Reference - [Hacker News](https://github.com/HackerNews/API)

## Getting Started
Clone the github Repo and import in intellij (or your choice of IDE)

### Prerequisites
- Java 8
- gradle-4.6
- mongodb
- Intellij (or your choice of IDE)

### Installing
- Install java 8<br>
  You can use [sdman](https://sdkman.io/install) and choose 8.0.252-zulu as java<br>
  `sdk install java 8.0.252-zulu`
- Install mongoDb [link](https://www.digitalocean.com/community/tutorials/how-to-install-mongodb-on-ubuntu-18-04)

## Deployment on Docker
Please make sure Docker is installed on your system<br>
Below are the commands to get the app up on local docker container
- `./gradlew clean build`
- `docker build -t hacker-news/docker .`
- `docker run --network host -p 9080:9080 hacker-news/docker`

*Addtional Note* -> MongoDb should be running on local for above docker command to run locally

## Built With
* [Spring boot ](https://spring.io/projects/spring-boot) - The framework used
* [Gradle](https://gradle.org/) - Dependency Management

## Authors
* **Shubham Sharma** - *Owner* - [Github](https://github.com/shubham-shar)

## Pending Improvements
- `/comments/{id}` takes a lot of time to render. Thinking of caching.

## Acknowledgments
- [Spring Guides](https://spring.io/guides/gs/spring-boot-docker/)
- [Hacker News](https://github.com/HackerNews/API)
