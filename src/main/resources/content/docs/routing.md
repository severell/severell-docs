# Routing
---
[TOC]

## Basic Routing

All Severell routes are defined in your `Route.java` file located in the `routes` package. This file is automatically
loaded by the framework at startup. You can attach middleware to any of these routes as well.

Once you have defined a route you can access it by navigating in your browser to `http://localhost:8009/user`.

```java
Router.Get("/user", "com.mylo.mylotest.controller.WelcomeController::user");
```

### Available Router Methods

The router allows you to register routes for the following HTTP verb.

```java
Router.Get(String path, String method)
Router.Post(String path, String method)
Router.Put(String path, String method)
Router.Patch(String path, String method)
Router.Delete(String path, String method)
Router.Options(String path, String method)
```

### CSRF Protection
Any request to a `POST` route must include a CSRF token field. Otherwise, that request will be rejected. 
See more in the [CSRF Documentation](/docs/csrf.html). You can add a CSRF to any HTML form like below.

```html
<form method="POST" action="/post">
    {{#csrf}}
    ...
</form>
```

## Route Parameters

Oftentimes you need to capture part of the URI within your route. For example, you may need to catch the user id. 
You can do that as follows.

```java
Router.Get("user/:id", "com.example.controller.WelcomeController::user");
``` 

You now have access to that parameter from within your controller. You can consume that as follows.
```java
public static void user(Request request) {
    request.getParam("id");
}
```

Route parameters always begin with `:` followed by a name. You can also have more than one route parameter in a route.

```java
Router.Get("user/:id/posts/:postid", "com.example.controller.WelcomeController::user");
```