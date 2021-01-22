# Database Migrations
---
[TOC]

## Introduction
Migrations allow you to easily create and share your database schema with others and make it
easy to keep your database structure in sync. Think of it like version control for you database schema.

## Creating a Migration
To create migration you can use the `severell-cli` tool to easy scaffold one. First to access the CLI tool you need
to run 

```bash
mvn severell:cli
```

This will start the Severell cli tool and from there you can create your first migration.

```bash
make:migration create_posts_table -c posts
```
The `-c` option means you want to create a new table called `posts` instead of modifying an existing 
table. Use the `-t` option to modify a table.

## Migration Structure

Migrations are stored in out `db` folder in the root of your project. They contain 2 methods. The
`up` and `down` methods.

```java
package migrations;

import com.severell.core.database.migrations.Blueprint;
import com.severell.core.database.migrations.MigrationException;
import com.severell.core.database.migrations.Schema;

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

To run migrations you can use the following command in the Severell cli tool.
```bash
migrate
```
Since migrations only run one time the next time you run `severell-cli migrate` it will not run the same migrations again.
It will only run all outstanding migrations. 

### Rolling Back Migrations

If you need to rollback your last migration you can use the `migrate:rollback` command.
```bash
migrate:rollback
```
This will rollback your last migration run. So it could include a number of migrations.
If you want to rollback all your migrations you can use the `migrate:reset` command.
```bash
migrate:reset
``` 