package com.severell.homepage;

import java.io.File;

public class Page {

    public Page(File file) {
        this.file = file;
        this.name = file.getName().split("\\.md")[0].toLowerCase() + ".html";
    }

    protected String output;
    protected String template;
    protected String name;
    protected File file;

    public Page() {
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public String getOutput() {
        return output;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }


}
