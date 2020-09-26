# Mail
---
[TOC]

## Introduction
Severell provides a simple API for sending email. Included currently are a few drivers for sending email
via SMTP and a Log driver for when in development. 

## Configuration
Configuration is made through your `.env` file with the following options.
```cmd
MAIL_DRIVER=Log | SMTP
MAIL_HOST=
MAIL_PORT=
MAIL_USERNAME=
MAIL_PASSWORD=
MAIL_SMTP_SSL= true | fale
MAIL_SMTP_STARTTLS= true | false
```

## Sending Email

### Configuring From Address

First we are going to look at how to configure the "sender" or the from email address.
All you need to do is call the `from` method and pass in the sender's email address. 

```java
public void index(Mail mail) {
    mail.from("example@email.com").text("Hello World!").send();
}
```

### Subject

To add a subject to your email simply call the `subject` method and pass in the subject. 

```java
public void index(Mail mail) {
    mail.from("example@email.com")
        .text("Hello World!")
        .subject("Subject Here")
        .send();
}
```

### Plain Text Email
To send a plain text email you can pass your email body into the `text` method.
```java
public void index(Mail mail) {
    mail.from("example@email.com")
        .text("Hello World!")
        .subject("Subject Here")
        .send();
}
```

### HTML Emails
You are also able to use Mustache templates to send as a HTML email. Simply pass the template
into the `template` function.

```java
public void index(Mail mail) {
    mail.from("example@email.com")
        .template("email.mustache", new HashMap<String, Object>())
        .subject("Subject Here")
        .send();
}
```