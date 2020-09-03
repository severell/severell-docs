# Middleware
---
[TOC]

## Introduction
Middleware (or otherwise known as Filters) allows you to run pieces of code before and after the execution 
of your controller method. By default Severell includes a few middleware for you. There is the `AuthenticationMiddleware` that
will redirect a request if the user is not logged in. Other middleware include the `CSRFMiddleware` and the `SecureHeadersMiddleware`.

You can write additional middleware as needed such as a logging middleware that logs all incoming requests.

## Defining Middleware

To create your own middleware you can use the command `severell-cli make:middleware`.

```bash
severell-cli make:middleware LogRequest
```

This will create a new class called `LogRequestMiddleware` and place it in your middleware package. 
This middleware is going to log all incoming requests to the server.

```java
package com.example.middleware;

import com.mitchdennett.framework.http.MiddlewareChain;
import com.mitchdennett.framework.http.Request;
import com.mitchdennett.framework.http.Response;
import com.mitchdennett.framework.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {
        System.out.println("Incoming Request...");
        middlewareChain.next();
    }
}
```

In this basic middleware, it will print `Incoming Request...` before all requests. You must call `middlewareChain.next()` if you want to continue 
with the request. If you want to stop the request, simply do not call `middlewareChain.next()`;

### Before & After Middleware

You can have middleware that runs before or after a request. For example, to have middleware execute ***before*** the request you define it like so.

```java
package com.example.middleware;

import com.mitchdennett.framework.http.MiddlewareChain;
import com.mitchdennett.framework.http.Request;
import com.mitchdennett.framework.http.Response;
import com.mitchdennett.framework.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {

        //Write code here

        middlewareChain.next();
    }
}

```

To have code execute after the request you define it like so.

```java
package com.example.middleware;

import com.mitchdennett.framework.http.MiddlewareChain;
import com.mitchdennett.framework.http.Request;
import com.mitchdennett.framework.http.Response;
import com.mitchdennett.framework.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {

        middlewareChain.next();
        
        //Write code here
    }
}
```

### Inject Dependencies In Middleware

Since middleware is resolved via the container you can inject whatever dependencies you need using the `@Inject` annotation. 

```java
package com.example.middleware;

import com.mitchdennett.framework.drivers.Session;
import com.mitchdennett.framework.http.MiddlewareChain;
import com.mitchdennett.framework.http.Request;
import com.mitchdennett.framework.http.Response;
import com.mitchdennett.framework.middleware.Middleware;

import javax.inject.Inject;

public class AuthenticationMiddleware implements Middleware {

    @Inject
    private Session session;

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {
        if(session.get("userid") == null) {
            response.redirect("/login");
        } else {
            middlewareChain.next();
        }
    }
}
```

As you can see the `Session` field will get resolved and you can use it within your middleware. 