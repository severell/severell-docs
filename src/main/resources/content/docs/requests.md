# Requests 
---
[TOC]

## Getting The Request

To get a `Request` object for the current HTTP request you can use dependency inject to pass it into 
your controller method. 

```java
package com.example.controller;

import com.mitchdennett.framework.http.Request;

public class PostController {

    public static void index(Request request) {
        String username = request.input("username");
    }

}
```

## Using The Request

The `Request` class in Severell is simply a wrapper around `HTTPServletRequest`. You have access to all the 
normal functionality along with some other methods to make developing a little easier.

### Getting Input Data

To get data from the request payload you can use the `input` method. 

```java
request.input("username");
```


