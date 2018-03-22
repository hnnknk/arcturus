package xyz.hnnknk.arcturus.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.arcturus.dao.ArticleDAO;
import xyz.hnnknk.arcturus.model.Article;
import xyz.hnnknk.arcturus.model.RssQuery;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class RssParserImpl implements RssParser {

    @Autowired
    ArticleDAO articleDAO;

    @Transactional
    @Override
    public void parse(RssQuery query) throws IOException, FeedException {
        parseFeed(createParser(query.getUrl()), query);
    }

    public void parseFeed(SyndFeed feed, RssQuery query) throws IOException {

        for (int i = 0; i < 3; i++) {
            SyndEntry entry = feed.getEntries().get(i);
            
            Article article = new Article();
            article.setDate(entry.getPublishedDate().toString());
            article.setTitle(entry.getTitle());
            article.setBody(parseBody(entry.getLink(), query));
            articleDAO.save(article);
        }
    }

    private String parseBody(String link, RssQuery query) throws IOException {
        String result = "";
        Document document = Jsoup.connect(link).timeout(10000).validateTLSCertificates(false).get();
        Element element = document.getElementsByAttributeValue(query.getBodyTag(), query.getBodyName()).first();

        Elements childs;
        if(query.getBodyTextTag().isEmpty()) {
            childs = element.select("p");
        } else {
            childs = element.select(query.getBodyTextTag());
        }

        for (Element child : childs) {
            result = result + "\n" + child.text();
        }
        return result;
    }

    private SyndFeed createParser(String url) throws IllegalArgumentException, FeedException, IOException {
        return new SyndFeedInput().build(new XmlReader(new URL(url)));
    }
}
