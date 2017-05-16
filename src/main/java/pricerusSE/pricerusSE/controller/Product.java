package pricerusSE.pricerusSE.controller;

public class Product {

    /*Attributes needed to define a product
        * id: for the DB
        * name: to show (useful for testing)
        * url: where to look for the price (until we get IA)
        * price: got from url
        * */
    private final long id;
    private final String name;
    private final String url;
    private final String price;

    public Product(long id, String price, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName(){ return name;}

    public String getUrl(){return url;}

    public String getPrice(){ return price;}
}
