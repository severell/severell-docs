package com.severell.homepage;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.ibm.icu.impl.number.Parse;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SiteBuilder {

    private Site site;
    private MustacheFactory mf;
    private Parser parser;
    private MutableDataSet options = new MutableDataSet();


    public SiteBuilder setSite(Site site) {
        options.set(Parser.EXTENSIONS, Arrays.asList(AnchorLinkExtension.create(), TocExtension.create()));
        mf = new DefaultMustacheFactory();
        this.site = site;
        this.parser = Parser.builder(options).build();
        return this;
    }

    public SiteBuilder build() {
        IndexPage indexPage = new IndexPage("templates/index.mustache");
        site.setIndexPage(indexPage);

        //Gather other pages here
        File dir = new File(site.getSrcDir());
        processDir(dir);

        return this;
    }

    private void processDir(File dir) {
        for(File file : dir.listFiles()) {
            if(file.isDirectory()) {
                processDir(file);
            } else {
                if(!file.isHidden()) {
                    Page page = new Page(file);
                    page.setOutput(file.getPath().replaceFirst(site.getSrcDir(), "").replaceFirst("/" + file.getName(), ""));
                    page.setTemplate("templates/doc.mustache");
                    site.addPage(page);
                }
            }
        }

    }

    public void write() throws IOException {
        buildIndex();

        Node navDoc = parser.parseReader(new BufferedReader(new FileReader(Main.class.getResource("/nav.md").getFile())));
        //Now we need to build the other pages
        for(Page page : site.getPages()) {
            File directory = new File(Paths.get("./build") + page.getOutput());
            if (! directory.exists()){
                directory.mkdirs();
            }

            String outFileName = String.format("%s%s/%s",Paths.get("./build"), page.getOutput(), page.getName());
            try(FileWriter writer = new FileWriter(outFileName)) {

                Node document = parser.parseReader(new BufferedReader(new FileReader(page.getFile())));
                HtmlRenderer renderer = HtmlRenderer.builder(options).build();
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("htmlContent", renderer.render(document));
                data.put("navContent", renderer.render(navDoc));

                Mustache m = mf.compile(page.getTemplate());
                m.execute(writer, data).flush();
            }
        }

    }

    private void buildIndex() throws IOException {
        try(FileWriter writer = new FileWriter(Paths.get("./build") + "/index.html")) {
            Mustache m = mf.compile(site.getIndexPage().getTemplate());
            m.execute(writer, new Object[0]).flush();
        }
    }


}
