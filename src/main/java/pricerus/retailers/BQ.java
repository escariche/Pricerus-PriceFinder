package pricerus.retailers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;


public class BQ implements Retailer {

    private String url;
    private Document body;

    public BQ(String url) throws IOException {
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


        Elements headersOne = docIn.select("h1");
        Elements spans = docIn.select("span");
        Elements labels = docIn.select("label.inline_element");
        String bqName = "notfound";

        for (Element h: headersOne){
            for (Attribute a: h.attributes()){
                if(a.getValue().contains("name")){
                    //System.out.println(h.text());
                    //System.out.println(a.getKey().toString());
                    //System.out.println(a.getValue().toString());
                    bqName = h.text();
                }
            }
        }
        //System.out.println("BQ PRODUCT: " + bqName);

        for(Element l : labels){

            if(l.text().contains(bqName)){
                spans = l.select("span.price");
                //System.out.println("-----------LABEL found: " + l.text());
            }
        }

        for(Element e : spans){

            priceOut = e.text().substring(0, e.text().length()-2).replace(",",".").trim();
        }

        //System.out.println(priceOut);
        return priceOut;
    }

    @Override
    public String getHTML() {
        return this.getBody().toString();
    }
}
