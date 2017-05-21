package pricerus.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pricerus.model.Product;
import pricerus.retailers.BQ;
import pricerus.retailers.Carrefour;

import java.io.IOException;

@RestController
public class SEController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method= RequestMethod.POST, value="/getPrice")
    public void  checkPrice(@RequestBody Product product) {

        int id=product.getId();
        String name=product.getName();
        String url=product.getUrl();


        String price = getPrice(url, getRetailer(url));

        jdbcTemplate.batchUpdate("INSERT INTO Prices (price, retailer, date, productID) VALUES\n" +
                "                ( '?', '?', current_timestamp,  (SELECT productID from products WHERE productID='?') )", price,getRetailer(url),Integer.toString(id));

    }

    public String getRetailer(String url){
        int start = url.indexOf("www.")+4;
        return url.substring(start,url.indexOf(".",start+1));
    }

    public void addPrice(int productID,String price, String retailer){

    }

    private String getPrice(String url, String type){
        String price=null;

        switch (type){
            case "carrefour":
                Carrefour carrefour= null;
                try {
                    carrefour = new Carrefour(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price=carrefour.getPrice();

            case "bq":
                BQ bq= null;
                try {
                    bq = new BQ(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price=bq.getPrice();
        }
    return price;
    }
}
