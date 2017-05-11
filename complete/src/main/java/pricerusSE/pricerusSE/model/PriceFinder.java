package pricerusSE.pricerusSE.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
/**
 * Created by javiescariz on 10/5/17.
 */
public class PriceFinder {

    public String findPrice(String url) {
        //Insertion of HTML
        //BQ Aquaris X5 Plus: https://www.bq.com/en/aquaris-x5plus
        //BQ all smartphones: https://www.bq.com/en/smartphones

        //System.out.print("Paste (Ctrl/Cmd + V) the URL: ");

        // get their input as a String

        String priceOut = null;
        try {
            Document docIn = (Document) Jsoup.connect(url).get();
            System.out.println(docIn);

            //Web BQ
            Elements priceBox = docIn.select("p.price_box");
            Elements spans = priceBox.select("span.price_qty");
            System.out.println(spans.text());

            HashMap<String, String> hmap = new HashMap<String, String>();
            Elements grid = docIn.select("li.item_grid");
            for (Element gr : grid) {
                Elements name = gr.select("p.name");
                Elements price = gr.select("span.price_qty");
                hmap.put(name.text(), price.text());
                System.out.println(name.text() + " : " + hmap.get(name.text()));
            }
            //Web CARREFOUR
            Elements products = docIn.select("div.texto-producto");
            Elements form = docIn.select("form#addToCart");
            Elements divs = form.select("div");//.select("div.col-xs-12 margin_top15 no-padding");
            Elements span = divs.select("span");
            System.out.println("Precio Carrefour: " + span.first().text());

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
            System.out.println("There was an error: " + e);
        }
        return priceOut;
    }

}