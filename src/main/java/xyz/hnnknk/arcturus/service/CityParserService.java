package xyz.hnnknk.arcturus.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import xyz.hnnknk.arcturus.model.Article;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CityParserService implements ParserService {

    public void parse() throws IOException {
        ArrayList<Article> articles = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.city-n.ru/div/2/300").timeout(10000).validateTLSCertificates(false).get();

        Elements elements = doc.getElementsByAttributeValue("class", "news_title");

            for (int i = 0; i < 5; i++) {
                Article article = new Article();
                Element el = elements.get(i).child(0).child(0);
                article.setTitle(el.text());
                article.setUrl(el.attr("href"));
                articles.add(article);
                System.out.println(article);
            }
        System.out.println("------------------------");

        for (Article article : articles) {
            Document doc1 = Jsoup.connect("https://www.city-n.ru" + article.getUrl()).timeout(10000).validateTLSCertificates(false).get();
            Element el= doc1.selectFirst(".news_text");

            Elements childs = el.select("p");
            for (Element child : childs) {
                article.setBody(article.getBody() + "\n" + child.text());
            }
            System.out.println(article);

        }

        System.out.println(doc.title());
    }
}
