package com.example.RestAPI_Universities.enity;

import java.util.List;

public class University {

    private String name;
    private List<String> web_pages;
    private List<String> domains;

    public University() {
    }

    public University(String name, List<String> web_pages, List<String> domains) {
        this.name = name;
        this.web_pages = web_pages;
        this.domains = domains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
