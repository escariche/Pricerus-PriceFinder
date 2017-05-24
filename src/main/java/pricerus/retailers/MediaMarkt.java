package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class MediaMarkt implements Retailer {

    private String url;
    private Document body;

    public MediaMarkt(String url) throws IOException {
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

        Elements product = docIn.select("#priceBlock > div");
        priceOut=product.text().substring(0, product.text().length()).replace(",",".");
        priceOut=priceOut.replace(" ","");

        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
