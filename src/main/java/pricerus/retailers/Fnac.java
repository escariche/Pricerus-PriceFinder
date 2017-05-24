package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class Fnac implements Retailer {

    private String url;
    private Document body;

    public Fnac(String url) throws IOException {
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

        Elements product = docIn.select("#fnac > div.ProductPriceBox > div.ProductPriceBox-item.js-ProductBuyBoxStd > strong");
        priceOut=product.text().substring(0, product.text().length()-1).replace(",",".").trim();
        System.out.println("Precio asdasd: " + priceOut);


//        Elements form = docIn.select("form#addToCart");
//        Elements divs = form.select("div");//.select("div.col-xs-12 margin_top15 no-padding");
//        Elements span = divs.select("span");
        System.out.println("Precio FNAC: " + priceOut);
//        priceOut = span.first().text().replaceAll("€","");



        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
