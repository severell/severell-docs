# Routing
---
[TOC]

## Routing Overview
Setting up routes is really simple, but there are a few things you need to know first. Routes in Severell are pre-processed before
runtime, so we don't have to use runtime reflection. In order for this to happen we make use of the `process-classes` maven
lifecycle phase. During this phase we take the routes defined in your `Routes.java` and generate a source file called
`RouteBuilder.java`. If for some reason your routes are not behaving as expected make sure you have run `mvn compile process-classes` after
editing your routes. See [IDE Configuration](/docs/ide-configuration) to set up your IDE so it always runs `process-classes`
before running your program. 

## Basic Routing

All Severell routes are defined in your `Route.java` file located in the `routes` package. This file is automatically
loaded by the framework at startup. You can attach middleware to any of these routes as well.

Once you have defined a route you can access it by navigating in your browser to `http://localhost:8009/user`.

```java
Router.Get("/user", WelcomeController.class, "user");
```

### Available Router Methods

The router allows you to register routes for the following HTTP verb.

```java
Router.Get(String path, Class cl, String method)
Router.Post(String path, Class cl, String method)
Router.Put(String path, Class cl, String method)
Router.Patch(String path, Class cl, String method)
Router.Delete(String path, Class cl, String method)
Router.Options(String path, Class cl, String method)
```

### CSRF Protection
Any request to a `POST` route must include a CSRF token field. Otherwise, that request will be rejected. 
See more in the [CSRF Documentation](/docs/csrf). You can add a CSRF to any HTML form like below.

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
Router.Get("user/:id", WelcomeController.class, "user");
``` 

You now have access to that parameter from within your controller. You can consume that as follows.
```java
public static void user(Request request) {
    request.param("id");
}
```

Route parameters always begin with `:` followed by a name. You can also have more than one route parameter in a route.

```java
Router.Get("user/:id/posts/:postid", WelcomeController.class, "user");
```
## Adding Route Middleware

To add Middleware to a route you can pass a list of middleware classes to the route. Route middleware is only run on 
the route you set it on.
```java
Router.Get("user/:id", WelcomeController.class, "users").middleware(AuthMiddleware.class, ValidateMiddleware.class);
```