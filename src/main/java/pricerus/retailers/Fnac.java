package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class Fnac extends Retailer {

    public Fnac(String url) throws IOException {
        super(url);
    }

    @Override
    public String getPrice() {

        String priceOut=null;
        Document docIn=this.getBody();

        Elements product = docIn.select("#fnac > div.ProductPriceBox > div.ProductPriceBox-item.js-ProductBuyBoxStd > strong");
        priceOut=product.text().substring(0, product.text().length()-1).replace(",",".").trim();

        return priceOut;
    }
}
