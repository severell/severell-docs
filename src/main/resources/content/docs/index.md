# Installation

---
[TOC]

***Severell is currently in development and I don't recommend using it yet unless you want play around or contribute
to it's development***.

## Requirements

* Maven
* Java >= 11

The Severell framework uses Maven to install and manage all the dependencies. So, before installing Severell,
make sure you have maven installed. If you are using an IDE they most likely already have a version of Maven. 

And of course, you need a version of Java installed on your machine. 

### Installing 

First, you need to download the Severell CLI tool. You can download the binary over here [Severell CLI](https://github.com/mitchdennett/severell-cli/releases/download/0.0.1-alpha/severell-cli)
. It has only been tested on MacOS right now. 

Make sure you place the Severell CLI executable somehere on your `$PATH` so that is can be located by your system. Some common locations
include: 

* macOS/Linux: `/usr/local/bin`
* Windows: `Not Working Right Now`

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

