# Routing
---
[TOC]

## Basic Routing

All Severell routes are defined by annotating your controller methods with the following annotation.

```java
@Route(path = "/", method = HttpMethod.GET);
```

So a typical controller file will look something like this. 

```java
    @Route(path = "/", method = HttpMethod.GET)
    public void index(Request request, Response resp) throws IOException, ViewException {

    }
```

### Available Router Methods

The router allows you to register routes for the following HTTP verb.

```java
GET
POST
PUT
PATCH
OPTIONS
DELETE
```

### CSRF Protection
Any request to a `POST` route must include a CSRF token field. Otherwise, that request will be rejected. 
See more in the [CSRF Documentation](/docs/csrf). You can add a CSRF to any HTML form like below.

```html
<form method="POST" action="/post">
    @tag.csrf(token = csrf)
    ...
</form>
```

## Route Parameters

Oftentimes you need to capture part of the URI within your route. For example, you may need to catch the user id. 
You can do that as follows.

```java
@Route(path = "/user/:id", method = HttpMethod.GET)
``` 

You now have access to that parameter from within your controller. You can consume that as follows.
```java
public static void user(Request request) {
    request.param("id");
}
```

Route parameters always begin with `:` followed by a name. You can also have more than one route parameter in a route.

```java
@Route(path = "/user/:id/posts/:postid", method = HttpMethod.GET)
```
## Adding Route Middleware

To add Middleware to a route you can another annotation to your method. Route middleware is only run on 
the route you set it on.
```java
@Route(path = "/user/:id", method = HttpMethod.GET)
@Middleware({AuthenticationMiddleware.class, ValidateMiddlware.class})
```