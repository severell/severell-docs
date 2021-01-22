# Middleware
---
[TOC]

## Introduction
Middleware (or otherwise known as Filters) allows you to run pieces of code before and after the execution 
of your controller method. By default Severell includes a few middleware for you. There is the `AuthenticationMiddleware` that
will redirect a request if the user is not logged in. Other middleware include the `CSRFMiddleware` and the `SecureHeadersMiddleware`.

You can write additional middleware as needed such as a logging middleware that logs all incoming requests.

## Defining Middleware

To create your own middleware you can create your own class that implements Middleware. 

```java
package com.example.middleware;

import com.severell.core.http.MiddlewareChain;
import com.severell.core.http.Request;
import com.severell.core.http.Response;
import com.severell.core.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {
        System.out.println("Incoming request...");
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

import com.severell.core.http.MiddlewareChain;
import com.severell.core.http.Request;
import com.severell.core.http.Response;
import com.severell.core.middleware.Middleware;

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

import com.severell.core.http.MiddlewareChain;
import com.severell.core.http.Request;
import com.severell.core.http.Response;
import com.severell.core.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    @Override
    public void handle(Request request, Response response, MiddlewareChain middlewareChain) throws Exception {

        middlewareChain.next();
        
        //Write code here
    }
}
```

### Inject Dependencies In Middleware

Since middleware is resolved via the container you can inject whatever dependencies you need into the constructor. 

```java
package com.example.middleware;

import com.severell.core.drivers.Session;
import com.severell.core.http.MiddlewareChain;
import com.severell.core.http.Request;
import com.severell.core.http.Response;
import com.severell.core.middleware.Middleware;

public class AuthenticationMiddleware implements Middleware {

    private Session session;
    
    public AuthenticationMiddleware(Session session) {
        this.session = session;
    }

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

As you can see the `Session` parameter will get resolved and you can use it within your middleware. 

## Route Middleware & Default Middleware
There are two types of Middleware. Default and Route middleware. Route middleware only runs for the routes you specify.
On the other hand, default middleware runs on all routes. 

### Default Middleware
Default middleware run on every route and are defined in `Middleware.java`

```java
import com.severell.core.middleware.CsrfMiddleware;
import com.severell.core.middleware.SecureHeadersMiddleware;

public class Middleware {

    public static final Class[] MIDDLEWARE = new Class[]{
            CsrfMiddleware.class,
            SecureHeadersMiddleware.class,
    };
}
```

To add a new one simply append the class to the array.

### Route Middleware
Route middleware only run on the routes you specify. To add middleware to a route you can specify the 
middleware class on the route.
```java
Router.Get("user/:id", WelcomeController.class, "users").middleware(AuthMiddleware.class, ValidateMiddleware.class);
```