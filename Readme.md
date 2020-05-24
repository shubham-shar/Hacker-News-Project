# Project Title
A simple website built with Spring boot showing top stories of the day using Hacker-News api.

## Getting Started
Clone the github Repo and import in intellij (or your choice of IDE)

### Prerequisites
- Java 8
- gradle-4.6
- mongodb
- Intellij (or your choice of IDE)

### Installing
- Install java 8
  You can use [sdman](https://sdkman.io/install) and choose 8.0.252-zulu as java
  `sdk install java 8.0.252-zulu`
- Install mongoDb [link](https://www.digitalocean.com/community/tutorials/how-to-install-mongodb-on-ubuntu-18-04)

## Deployment on Docker
Please make sure Docker is installed on your system
Below are the commands to get the app up on local
- `./gradlew clean build`
- `docker build -t hacker-news/docker .`
- `docker run --network host -p 9080:9080 hacker-news/docker`

*Addtional Note* -> MongoDb should be running on local for above docker command to run locally

## Built With
* [Spring boot ](https://spring.io/projects/spring-boot) - The framework used
* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Shubham Sharma** - *Owner* - [Github](https://github.com/shubham-shar)

## Acknowledgments
- [Spring Guides](https://spring.io/guides/gs/spring-boot-docker/)
- [Hacker News](https://github.com/HackerNews/API)
