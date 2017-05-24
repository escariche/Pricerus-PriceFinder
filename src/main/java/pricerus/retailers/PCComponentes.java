package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class PCComponentes extends Retailer {

    public PCComponentes(String url) throws IOException {
        super(url);
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
}
