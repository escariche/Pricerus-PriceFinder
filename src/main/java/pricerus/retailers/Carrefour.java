package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Abel on 21/05/2017.
 */
public class Carrefour implements Retailer {

    private String url;
    private Document body;

    public Carrefour(String url) throws IOException {
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
        Elements form = docIn.select("form#addToCart");
        Elements divs = form.select("div");//.select("div.col-xs-12 margin_top15 no-padding");
        Elements span = divs.select("span");
        //System.out.println("Precio Carrefour: " + span.first().text().replaceAll("€",""));
        priceOut = span.first().text().replaceAll("€","");



        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
