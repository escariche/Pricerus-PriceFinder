package pricerus.model;


import java.util.List;

public class Product {
    private int id;
    private String name;
    private List<String> urls;

    public Product(int id, String name, List<String> urls) {
        this.id = id;
        this.name = name;
        this.urls = urls;
    }

    public Product(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
