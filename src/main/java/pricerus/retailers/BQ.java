package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class BQ implements Retailer {

    private String url;
    private Document body;

    public BQ(String url) throws IOException {
        this.url=url;
        Document body = (Document) Jsoup.connect(url).get();
        this.body=body;
    }

    public String getUrl() {
        return url;
    }

    public Document getBody() {
        return body;
    }

    @Override
    public String getPrice() {

        String priceOut=null;
        Document docIn=this.getBody();
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


        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
