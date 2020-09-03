package com.severell.homepage;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Site site = new Site(Main.class.getResource("/content").getFile());

        SiteBuilder builder = new SiteBuilder();
        builder.setSite(site).build().write();

        if(!"build".equals(args[0])) {

            ResourceHandler h = Handlers.resource(new FileResourceManager(Paths.get("./build").toFile()));

            Undertow server = Undertow.builder()
                    .addHttpListener(5000, "localhost")
                    .setHandler(h)
                    .build();
            server.start();
        }
    }

    public static void buildIndexPage(MustacheFactory mf) throws IOException {

    }
}
