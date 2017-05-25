package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class Carrefour extends Retailer {

    public Carrefour(String url) throws IOException {
        super(url);
    }

    @Override
    public String getPrice() {

        String priceOut=null;
        Document docIn=this.getBody();

        Elements products = docIn.select("div.texto-producto");
        Elements form = docIn.select("form#addToCart");
        Elements divs = form.select("div");
        Elements span = divs.select("span");
        for (Element s : span){
            if(s.className().contains("new-price")){
                priceOut = s.text().substring(0, s.text().length()-1);
            }
        }

        return priceOut;
    }
}

