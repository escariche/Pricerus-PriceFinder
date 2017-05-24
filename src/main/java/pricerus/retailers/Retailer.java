package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Retailer {

    private String url;
    private Document body;

    public Retailer(String url) throws IOException {
        this.url=url;
        Document body = (Document) Jsoup.connect(url).get();
        this.body=body;
    }

    public String getPrice(){
        return null;
    }

    public String getHTML() {
        return this.getBody().toString();
    }
    public String getUrl() {
        return url;
    }

    public Document getBody() {
        return body;
    }

}
