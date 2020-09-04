# Responses 
---
[TOC]

## Setting Headers

To set headers on a Response you can simply call the `headers` method and pass a HashMap of headers.

```java
HashMap<String, String> headers = new HashMap<String, String>();
headers.put("X-HEADER-ONE", "Header");

response.headers(headers);
```

## Redirects

To send a redirect you can call the `redirect` method.

```java
response.redirect("/home");
``` 

## View Response
You are able to return a `Mustache` template view from the Response class as well.

```java
response.render("index.mustache", null);
```


