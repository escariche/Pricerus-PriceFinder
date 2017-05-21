package pricerusSE.pricerusSE.controller;

public class Product {

    /*Attributes needed to define a product
        * id: for the DB
        * name: to show (useful for testing)
        * url: where to look for the price (until we get IA)
        * price: got from url
        * */
    private final long id;
    private final String price;
    private final String name;
    private final String retailer;
    private final String url;

    public Product(long id, String price, String name, String retailer, String url) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.retailer = retailer;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getName(){ return name;}

    public String getUrl(){return url;}

    public String getPrice(){ return price;}

    public String getRetailer(){return retailer;}

}
