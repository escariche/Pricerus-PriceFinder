package pricerusSE.pricerusSE.controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SEController {

    @RequestMapping("/getPrice/")
    public Product getPrice(@RequestParam long id,
                            @RequestParam String url) {
        String name = getName();
        String retailer = getRetailer(url);
        String price = getPrice(url);
        Product p = new Product(id, price, name, retailer, url);
        return p;
    }

    private String getName(){
        return "NAME";
    }

    private String getPrice(String url){
        //Insertion of HTML
        //BQ Aquaris X5 Plus: https://www.bq.com/en/aquaris-x5plus
        //BQ all smartphones: https://www.bq.com/en/smartphones

        //System.out.print("Paste (Ctrl/Cmd + V) the URL: ");

        // get their input as a String

        String priceOut = null;
        try {
            Document docIn = (Document) Jsoup.connect(url).get();
            //System.out.println(docIn);

            System.out.println("0");
            //Web BQ
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
                System.out.println(l);
                System.out.println(l.text());
                if(l.text().contains(bqName)){
                    spans = l.select("span.price");
                    //System.out.println("-----------LABEL found: " + l.text());
                }
            }

            for(Element e : spans){
                System.out.println("-----------PRICE found: " + e.text());
                priceOut = e.text().substring(0, e.text().length()-1);
            }

            //Web CARREFOUR
            Elements products = docIn.select("div.texto-producto");
            Elements form = docIn.select("form#addToCart");
            Elements divs = form.select("div");
            Elements span = divs.select("span");
            for (Element s : span){
                String sClassName = s.className();
                if(s.className().contains("new-price")){
                    System.out.println(s);
                    priceOut = s.text().substring(0, s.text().length()-1);
                }
            }
            //priceOut = span.last().text();

        } catch (Exception e) {
            System.out.println("There was an error: " + e.getMessage());
        }
        return priceOut;
    }


    public String getRetailer(String url) {
        int start = url.indexOf("www.") + 4;
        return url.substring(start, url.indexOf(".", start + 1));
    }
}
