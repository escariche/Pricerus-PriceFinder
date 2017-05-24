package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class BQ extends Retailer {


    public BQ(String url) throws IOException {
        super(url);
    }

    @Override
    public String getPrice() {

        String priceOut = null;
        Document docIn = this.getBody();

        Elements headersOne = docIn.select("h1");
        Elements spans = docIn.select("span");
        Elements labels = docIn.select("label.inline_element");
        String bqName = "notfound";

        for (Element h : headersOne) {
            for (Attribute a : h.attributes()) {
                if (a.getValue().contains("name")) {

                    bqName = h.text();
                }
            }
        }
        for (Element l : labels) {

            if (l.text().contains(bqName)) {
                spans = l.select("span.price");
            }
        }
        for (Element e : spans) {

            priceOut = e.text().substring(0, e.text().length() - 2).replace(",", ".").trim();
        }
        return priceOut;
    }

}
