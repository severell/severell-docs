# Database Models
---
[TOC]

## Introduction

Severell uses the Ebean ORM and uses their models. See [Ebean Docs](https://ebean.io/docs/)

## Creating a model

To make creating a model easier you can use the `make:model` command. 

```bash
severell-cli make:model User -t users
```

This command will make a model class called `User.java` based off the `users` table and place it in your `models` package