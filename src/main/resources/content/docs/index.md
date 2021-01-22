# Installation

---
[TOC]

## Requirements

* Maven
* Java >= 11

The Severell framework uses Maven to install and manage all the dependencies. So, before installing Severell,
make sure you have maven installed. If you are using an IDE they most likely already have a version of Maven. 

And of course, you need a version of Java installed on your machine. 

### Installing 
There are two ways to install Severell and get started. You can use our Initializer web app or you can use the Maven archetype command.

#### Initializer
Visit [Initializer](https://initializer.severell.com) to get your base project. Enter the required information and extract the project folder from the downloaded ZIP

#### Maven
The other way to get started is by using the Maven archetype command.
```bash
mvn -B archetype:generate
    -DarchetypeGroupId=com.severell 
    -DarchetypeArtifactId=severell-archetype 
    -DarchetypeVersion=0.0.1 
    -DgroupId=***YOUR-GROUPID***
    -DartifactId=***YOUR-ARTIFACTID***
    -Dversion=1.0-SNAPSHOT
```

