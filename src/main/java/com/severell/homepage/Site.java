package com.severell.homepage;

import java.util.ArrayList;
import java.util.List;

public class Site {

    private IndexPage indexPage;
    private List<Page> pages;
    private String srcDir;

    public Site(String srcDir) {
        this.srcDir = srcDir;
    }

    public String getSrcDir() {
        return srcDir;
    }

    public void setIndexPage(IndexPage indexPage) {
        this.indexPage = indexPage;
    }

    public IndexPage getIndexPage() {
        return this.indexPage;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public void addPage(Page page) {
        if(pages == null) {
            this.pages = new ArrayList<>();
        }

        this.pages.add(page);
    }
}
