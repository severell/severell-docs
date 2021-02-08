## Getting Started with Severell

---
[TOC]

This guide is designed for beginners with Severell and assumes no prior knowledge. 

Severell is a web framework for the Java programming language and so some experience with Java is required.
I would suggest learning the basics of Java before beginning this guide. 

### Create a New Severell Project

To get the most out of this guide I suggest following along step by step and learning by doing. 

We are going to create a very simple blog application. To get started the very first thing we need to do is create a new
Severell project. The easiest way to do that is to head over to [Severell Initializer](https://initializer.severell.com). 
You'll need to enter a few bits of information. 

* **Group** -  uniquely identifies your project across all projects. A group ID should follow Java's package name rules. This means it starts with a reversed domain name you control.
For example `com.severell`. You may also choose a groupId that reflects your project hosting, in this case, something like `io.github.yourusername` or `com.github.yourusername`.
  

* **Artifact** -  is the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols.


* **Name** - Name of your project.


* **Description** - A brief description of your project.

Once you have filled that all out click `Generate`.

Extract the contents of the downloaded zip into a location you'll remember. 

#### Installing Postgresql

You will also need an installation of the Postgres database. You can find installation instructions at the [Postgresql website](https://www.postgresql.org/download/).


#### Installing Node.js and Npm

Finally, you'll need Node.js and NPM installed to manage your application's JavaScript.

Find the installation instructions at the [Node.js](https://nodejs.org/en/download/) website and verify it's installed correctly with the following command:

```bash
$ node --version
```

Node should include a version of NPM. Verify that it installed correctly
```bash
$ npm --version
```

#### Install Maven

Severell uses Maven to manage all the Java dependencies we need.

Find the installation instructions at the [Maven](http://maven.apache.org/install.html) website and verify it's
installed correctly with the following command:

```bash
$ mvn --version
```

### Up & Running - Hello World

Let's get our Severell Project up and running. `cd` into the root of your new project and run the following command.

```bash
$ mvn clean compile process-classes exec:java
```

This will compile all the Java code and do some post-compile processing and start our server.
Visit `localhost:8009` and you should see the following:

![Hello World](/static/images/helloworld.png)

### Displaying A List Of Posts

Our next step is to add a new route into our application to display our blog posts. In order to display a blog post
we will need to add a route, add a controller and create a view. First let's add our route. In our `routes/routes.java`
file add the following route.

```java
router.Get("/posts", PostsController.class, "index");
```

#### Create Controller For Posts

Don't worry if that is erring out your IDE. We will add our controller file now. We can use the Severell CLI tool
to easily create a new controller file. To start our CLI tool run the following command from the root of the project:

```bash
$ mvn severell:cli
```

This will start the Severell CLI tool, and we can now run our command to create a new Controller file.

```bash
$ make:controller PostsController
```

This will have created the `controller/PostsController.java` file that looks like this.

```java
package com.example.helloworld.controller;

import com.severell.core.http.Response;
import java.io.IOException;

public class PostsController {
    public void index(Response response) throws IOException {
      
    }
}
```

#### Create View For Posts

In order to display something in the browser we need to create view. Our view files are located in 
`src/main/resources/templates`.  Let's create mustache template file called `posts.mustache` and place the following
HTML in the file 

```html
<h1>Posts</h1>
```

Finally we need to tell our controller file to render this view. So, in `controller/PostsController.java` add the
following code.

```java
response.render("posts.mustache", new HashMap<String, Object>());
```
The `render` functions throws ViewException. So make sure to add that to your method signature and to also import
HashMap.

Now lets start our server again with `mvn clean compile process-classes exec:java`.

If you head over to `localhost:8009/posts` you should see our "Posts" header.

#### Connect to DB

Before we can go ahead and create the Posts table we need to set up our .env file with our database connection
properties. Our `.env` file is located at `src/main/resources`. Edit the following properties with your database 
information.

```text
DB_DRIVER=org.postgresql.Driver
DB_CONNSTRING=jdbc:postgresql://localhost:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

#### Create Posts Table

Now we need to create a database table that can store our blog posts. We will use the build in migration features to 
do this. Start the Severell CLI tool by running `mvn severell:cli`. To create a migrate run the following command in
the Severell CLI tool

```shell
make:migration -c posts create_posts
```

This will create a migration file located at `src/db/migrations` and will look something like this

```java
package migrations;

import com.severell.core.database.migrations.Blueprint;
import com.severell.core.database.migrations.MigrationException;
import com.severell.core.database.migrations.Schema;

public class m_2021_02_08_085044_create_posts {
    
    public static void up() throws MigrationException {
        Schema.create("posts", (Blueprint table) -> {
            
        } );
    }

    public static void down() throws MigrationException {
        
    }
}
```

Let's add a few lines to the migration to make our table. We will need an id, title and body.

```java
package migrations;

import com.severell.core.database.migrations.Blueprint;
import com.severell.core.database.migrations.MigrationException;
import com.severell.core.database.migrations.Schema;

import javax.xml.validation.Schema;

public class m_2021_02_08_085044_create_posts {

    public static void up() throws MigrationException {
        Schema.create("posts", (Blueprint table) -> {
            table.id();
            table.string("title");
            table.text("body");
            table.timestamps();
        });
    }

    public static void down() throws MigrationException {
        Schema.drop("posts");
    }
}
```

Back in our Severell CLI we can run `migrate`. Which will create our Posts table, along with a Users table. Now that we have
the posts table in our Database we can create a Model for it. Once again in our Severell CLI tool we can run the following
command to create a model.

```shell
make:model -t posts Post
```

This will create a model file `models/Post.java`.

#### Insert a new Post

Let's quickly insert a new post into our database by running the following SQL. Connect to your database using you own database
tool or command line and run the following SQL

```sql
INSERT INTO posts (title, body) VALUES ('First Post', 'Hello this is a post');
```

#### Displaying Our Posts

Now that we have a record in the database lets display it on our `/posts` route. First thing is we will need to change our
view to display a list of all the posts. So in our `post.mustache` view add the following code after our header.

```html
<ul>
{{#posts}}
    <li>{{title}}</li>
{{/posts}}
</ul>
```

For each post this will display the title in an Unordered list. Next we have to pass down the post data to our view. So head
over to our `controller/PostsController.java` file and add this code to retrieve a list of Posts.

```java
public void index(Response response) throws IOException, ViewException {
    List<Post> posts = new QPost().findList();
    HashMap<String, Object> map = new HashMap<String, Object>();

    map.put("posts", posts);
    response.render("posts.mustache", map);
}
```

In this example we are using the Ebean ORM and using their Query beans. Visit [here](https://ebean.io/docs/query/query-beans) to learn
more about them.

Finally, lets start our server `mvn clean compile process-classes exec:java` and head over to `localhost:8009/posts`. And 
you should see something like this:

![Posts](/static/images/posts.png)

### Display a Single Post

Now that we have a list of our Posts displaying it times to be able to view a single post and read the contents.
The first thing we need to do is create a route for viewing the single post. In our `routes/Routes.java` add the following
route just under your other Posts routes.

```java
Router.Get("/posts/:id", PostsController.class, "view");
```

Now we need to add a controller method for that route. In `controller/PostsController.java` add the following method
to retrieve and display a single post.

```java
public void view(Response response, Request request) throws IOException, ViewException {
    Post post = new QPost().where().id.eq(Integer.parseInt(request.param("id"))).findOne();
    HashMap<String, Object> map = new HashMap<String, Object>();

    map.put("post", post);
    response.render("post.mustache", map);
}
```

Now lets create a view for a single post. Create `post.mustache` in `src/main/resources/templates` and add the following
code.

```html
<h1>{{post.title}}</h1>
<div>{{post.body}}</div>
```

Finally, we need to edit our `posts.mustache` view to be able to navigate to each individual post. Update that view with
the following code.

```html
<h1>Posts</h1>
<ul>
    {{#posts}}
        <li><a href="/posts/{{id}}">{{title}}</a></li>
    {{/posts}}
</ul>
```

Start your server again with `mvn clean compile process-classes exec:java` and then visit `localhost:8009/posts` and click
on one of your posts. You should see something like this.

![Post](/static/images/post.png)