package pricerus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pricerus.model.Product;
import pricerus.retailers.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SEController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/getPrice")
    public void checkPrice(@RequestBody Product product) {

        int id = product.getId();
        String name = product.getName();
        List<String> urls = product.getUrls();
        String price = "0.0";

        for (String url : urls) {
            price = String.valueOf(Double.parseDouble(getPrice(url, getRetailer(url))));
            addPrice(id, price, url);
        }

    }

    public String getRetailer(String url) {
        int start = url.indexOf(".");
        //System.out.println(url.substring(start+1,url.indexOf(".",start+1)));
        return url.substring(start + 1, url.indexOf(".", start + 1));
    }

    public void addPrice(int id, String price, String url) {
        try {
//            System.out.println(price);
//            System.out.println(getRetailer(url));
//            System.out.println(id);
            String selectQuery = "  (SELECT productID from products WHERE productID=" + id + ") ";

            jdbcTemplate.execute("INSERT INTO Prices (productID, date, price, retailer) VALUES\n" +
                    "                ( " + selectQuery + ", current_timestamp," + price + ",\'" + getRetailer(url) + "\');");
            System.out.println("Succesfully added product with productID:" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getPrice(String url, String type) {
        String price = null;

        switch (type) {

            case "bq":
                BQ bq = null;
                try {
                    bq = new BQ(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price = bq.getPrice();
                break;


            case "carrefour":
                Carrefour carrefour = null;
                try {
                    carrefour = new Carrefour(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price = carrefour.getPrice();
                break;

            case "fnac":
                Fnac fnac = null;
                try {
                    fnac = new Fnac(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price = fnac.getPrice();
                break;

            case "pccomponentes":
                PCComponentes pccomponentes = null;
                try {
                    pccomponentes = new PCComponentes(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price = pccomponentes.getPrice();
                break;

            case "mediamarkt":
                MediaMarkt mediamarkt = null;
                try {
                    mediamarkt = new MediaMarkt(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                price = mediamarkt.getPrice();
                break;


        }

        return price;
    }
}
