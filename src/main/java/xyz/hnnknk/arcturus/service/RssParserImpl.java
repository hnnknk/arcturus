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
import java.text.SimpleDateFormat;

@Service
public class RssParserImpl implements RssParser {

    private final int timeout = 10000;

    @Autowired
    private ArticleDAO articleDAO;

    @Transactional
    @Override
    public void parse(final RssQuery query) throws IOException, FeedException {
        parseFeed(createParser(query.getUrl()), query);
    }

    private void parseFeed(final SyndFeed feed, final RssQuery query) throws IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        for (int i = 0; i < 3; i++) {
            SyndEntry entry = feed.getEntries().get(i);

            Article article = new Article();
            article.setDate(dateFormat.format(entry.getPublishedDate()));
            article.setTitle(entry.getTitle());
            article.setBody(parseBody(entry.getLink(), query));
            articleDAO.save(article);
        }
    }

    @SuppressWarnings( "deprecation" )
    private String parseBody(final String link, final RssQuery query) throws IOException {
        StringBuilder result = new StringBuilder();
        Document document = Jsoup.connect(link).timeout(timeout).validateTLSCertificates(false).get();
        Element element = document.getElementsByAttributeValue(query.getBodyTag(), query.getBodyName()).first();

        Elements childs;
        if (query.getBodyTextTag().isEmpty()) {
            childs = element.select("p");
        } else {
            childs = element.select(query.getBodyTextTag());
        }

        for (Element child : childs) {
            if (result.length() == 0) {
                result.append("\t").append(child.text());
            } else {
                result.append("\n").append("\t").append(child.text());
            }
        }
        return result.toString();
    }

    private SyndFeed createParser(final String url) throws IllegalArgumentException, FeedException, IOException {
        return new SyndFeedInput().build(new XmlReader(new URL(url)));
    }
}
