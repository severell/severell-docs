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
There are two ways to install Severell. You can use our native CLI tool or you can use the Maven archetype command.

#### CLI Tool
First, you need to download the Severell CLI tool. You can download the binary over here [Severell CLI](https://github.com/severell/severell-cli/releases/tag/0.0.1-alpha.2)
. Choose the right one for your operating system. Once downloaded rename the file to `severell-cli`.

##### MacOS
You'll need to make the program executable run `chmod 550 severell-cli`. You will also need to right-click and open it before trying to run
it in your terminal. This is to get MacOS to trust the program. 

##### Linux 
You'll need to make the program executable run `chmod 550 severell-cli`

Make sure you place the Severell CLI executable somewhere on your `$PATH` so that is can be located by your system. Some common locations
include: 

* macOS/Linux: `/usr/local/bin`

Once installed, run `severell-cli` to make sure you have installed it correctly. If installed correctly you should see the following
output:

```bash
The Severell CLI tool empowers developers to quickly scaffold applications

Usage:
  severell [command]

Available Commands:
  create      Create a new severell project
  help        Help about any command
  load        Load Commands From Project
  version     Print the version number of Hugo

Flags:
  -h, --help   help for severell

Use "severell [command] --help" for more information about a command.

``` 
Next, we can create a new Severell project. Run the `severell-cli create` to create a fresh Severell installation.
For example, `severell-cli create blog` will create a directory called blog containing a Severell project with all dependencies
already installed.

```bash
severell-cli create blog
```

#### Maven
The other way to get started is by using the Maven archetype command.
```bash
mvn -B archetype:generate
    -DarchetypeGroupId=com.severell 
    -DarchetypeArtifactId=severell-archetype 
    -DarchetypeVersion=0.0.1-SNAPSHOT 
    -DgroupId=***YOUR-GROUPID***
    -DartifactId=***YOUR-ARTIFACTID***
    -Dversion=1.0-SNAPSHOT
```

