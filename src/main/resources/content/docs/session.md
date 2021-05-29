# Session 
---
[TOC]

## Introduction

Sessions provide a way to store information about a user across multiple requests. Currently,
we support memory based sessions and Redis backed session but more options are coming. 

## Using The Session

### Get Session
To get an instance of the `Session` simply add it as a parameter of your controller method.

```java
public static void index(Session session) {
    session.put("userid", 123);
}
```

### Storing Data

To store data in the session simply call
```java
session.put("key", "value");
```

### Retrieving Data

To retrieve data from the session call.
```java
session.get("key");
```
This will return the value as an instance of `Object`. We provide helper methods to retrieve 
the values as String etc...
```java
session.getString("key");
```
