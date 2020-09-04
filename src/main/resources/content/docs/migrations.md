# Database Migrations
---
[TOC]

## Introduction
Migrations allow you to easily create and share your database schema with others and make it
easy to keep your database structure in sync. Think of it like version control for you database schema.

## Creating a Migration
To create migration you can use the `severell-cli` tool to easy scaffold one. 

```bash
severell-cli make:migration create_posts_table -c posts
```
The `-c` option means you want to create a new table called `posts` instead of modifying an existing 
table. Use the `-t` option to modify a table.

## Migration Structure

Migrations are stored in out `db` folder in the root of your project. They contain 2 methods. The
`up` and `down` methods.

```java
package migrations;

import com.mitchdennett.framework.database.migrations.Blueprint;
import com.mitchdennett.framework.database.migrations.MigrationException;
import com.mitchdennett.framework.database.migrations.Schema;

public class CreateUsersTable {

    public static void up() throws MigrationException {
        Schema.create("users", (Blueprint table) -> {
            table.id();
            table.string("name");
            table.string("email");
            table.string("password");
            table.timestamp("verified_at").nullable();
            table.timestamps();
        });
    }

    public static void down() throws MigrationException {
        Schema.drop("users");
    }

}
```

## Running Migrations

To run migrations you can use the following command.
```bash
severell-cli migrate
```
Since migrations only run one time the next time you run `severell-cli migrate` it will not run the same migrations again.
It will only run all outstanding migrations. 

### Rolling Back Migrations

If you need to rollback your last migration you can use the `migrate:rollback` command.
```bash
severell-cli migrate:rollback
```
This will rollback your last migration run. So it could include a number of migrations.
If you want to rollback all your migrations you can use the `migrate:reset` command.
```bash
severell-cli migrate:reset
``` 