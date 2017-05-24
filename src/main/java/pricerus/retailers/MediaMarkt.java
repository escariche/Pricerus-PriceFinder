package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class MediaMarkt extends Retailer {

    public MediaMarkt(String url) throws IOException {
        super(url);
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
}
