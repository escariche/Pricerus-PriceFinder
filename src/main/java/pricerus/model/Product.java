package pricerus.model;


import java.util.List;

public class Product {
    private int id;
    private String name;
    private double maxPrice;
    private double minPrice;
    private List<String> urls;
    private String email;


    public Product(int id, String name, double maxPrice, double minPrice, List<String> urls, String email) {
        this.id = id;
        this.name = name;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.urls = urls;
        this.email = email;
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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
