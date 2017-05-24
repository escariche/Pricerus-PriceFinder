package pricerusSE.pricerusSE.controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SEController {

    @RequestMapping("/getPrice/")
    public Product getPrice(@RequestParam long id,
                            @RequestParam String url) {
        String name = getName();
        String retailer = getRetailer(url);
        String price = getPrice(url);
        Product p = new Product(id, price, name, retailer, url);
        return p;
    }

    private String getName(){
        return "NAME";
    }

    private String getPrice(String url){
        //Insertion of HTML
        //BQ Aquaris X5 Plus: https://www.bq.com/en/aquaris-x5plus
        //BQ all smartphones: https://www.bq.com/en/smartphones

        //System.out.print("Paste (Ctrl/Cmd + V) the URL: ");

        // get their input as a String

        String priceOut = null;
        try {
            Document docIn = (Document) Jsoup.connect(url).get();
            //System.out.println(docIn);

            System.out.println("0");
            //Web BQ
            Elements priceBox = docIn.select("p.price_box");
            Elements spans = priceBox.select("span.price_qty");
            //System.out.println(spans.text());
            System.out.println("1");


            HashMap<String, String> hmap = new HashMap<String, String>();
            Elements grid = docIn.select("li.item_grid");
            for (Element gr : grid) {
                Elements name = gr.select("p.name");
                Elements price = gr.select("span.price_qty");
                hmap.put(name.text(), price.text());
                System.out.println(name.text() + " : " + hmap.get(name.text()));
                priceOut = hmap.get(name.text());
            }
            System.out.println("2");

            //Web CARREFOUR
            Elements products = docIn.select("div.texto-producto");
            Elements form = docIn.select("form#addToCart");
            Elements divs = form.select("div");
            Elements span = divs.select("span");
            for (Element s : span){
                if(s.className().contains("new-price")){
                    System.out.println(s);
                    priceOut = s.text().substring(0, s.text().length()-1);
                }
            }
            //priceOut = span.last().text();


            // Elements priceAlone = divs.select("span.col-xs-12 rojo01 new-price");
            // System.out.println(priceAlone.text());

            for (Element p : products) {
                Elements name = p.select("h2.titular-producto");
                Elements price = p.select("p.precio-nuevo");
                hmap.put(name.text(), price.text());
                System.out.println(name.text() + " : " + hmap.get(name.text()));
                priceOut = hmap.get(name.text());
            }

            System.out.println(hmap.size() + " RESULTS FOUND");
        } catch (Exception e) {
            System.out.println("There was an error: " + e.getMessage());
        }
        return priceOut;
    }


    public String getRetailer(String url) {
        int start = url.indexOf("www.") + 4;
        return url.substring(start, url.indexOf(".", start + 1));
    }
}
