package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class PCComponentes implements Retailer {

    private String url;
    private Document body;

    public PCComponentes(String url) throws IOException {
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

        Elements product = docIn.select("#contenedor-principal > div.container.ficha-producto__ficha-basica.ficha-producto__ficha-id > div.row.m-t-2.m-b-3 > div:nth-child(2) > div > div.row > div > div.priceBlock > div.precioMain.h1");
        priceOut=product.text().substring(0, product.text().length()-1).replace(",",".");
        priceOut=priceOut.replace(" ","");

        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
