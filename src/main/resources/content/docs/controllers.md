# Controllers 
---
[TOC]

## Introduction

Controllers are where the bulk of your request related code is stored. Controllers group
request handling logic into a single class allowing you to organize your code in a clean way. 

## Basic Controller

#### Start CLI Tool
First you need to start the Severell CLI tool by running:
```bash
mvn severell:cli
```

To create a controller you can use the `make:controller` command. 

```bash
make:controller PostController
``` 

This will create a controller called `PostController` in your `controller` package. 

```java
package com.example.controller;

import com.mitchdennett.framework.http.Request;

public class PostController {

    public void index() {
        //Put request handling logic here. 
    }

}
```

### Dependency Injection

Since controller methods are resolved by the container you can inject any dependencies you may need 
into your controller methods. A common use case for this is to get the `Request` and `Response` objects.

```java
package com.example.controller;

import com.severell.core.http.Request;
import java.util.HashMap;

public class PostController {

    public void index(Request request, Response response) {
        response.render("post.mustache", new HashMap<>());
    }

}
```

The `Request` and `Response` object will get resolved and passed into your controller method. 