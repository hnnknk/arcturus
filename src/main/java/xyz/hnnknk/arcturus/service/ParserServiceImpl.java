package xyz.hnnknk.arcturus.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hnnknk.arcturus.dao.ArticleDAO;
import xyz.hnnknk.arcturus.model.Article;
import xyz.hnnknk.arcturus.model.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {

    private final int timeout = 10000;

    @Autowired
    private ArticleDAO articleDAO;

    @SuppressWarnings( "deprecation" )
    @Transactional
    @Override
    public void parse(final Query query) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();

        Document doc = Jsoup.connect(query.getUrl() + query.getUrlSuffix()).timeout(timeout).validateTLSCertificates(false).get();

        Elements mainElements = doc.getElementsByAttributeValue(query.getTitleTag(), query.getTitleName());
        Elements dateElements = doc.getElementsByAttributeValue(query.getDateTag(), query.getDateName());

        for (int i = 0; i < 3; i++) {
            Article article = new Article();
            if (query.getTitleTextTag().isEmpty()) {
                article.setTitle(mainElements.get(i).text());
            } else {
                article.setTitle(mainElements.get(i).select(query.getTitleTextTag()).first().text());
            }
            if (query.getDateTextTag().isEmpty()) {
                article.setDate(dateElements.get(i).text());
            } else {
                article.setDate(dateElements.get(i).select(query.getDateTextTag()).first().text());
            }
            article.setUrl(mainElements.get(i).select("a").attr("href"));
            articles.add(article);
        }

        parseBody(articles, query);

        for (Article e: articles) {
            articleDAO.save(e);
        }
    }

    @Transactional
    @Override
    public List<Article> listAll() {
        return articleDAO.listAll();
    }

    @SuppressWarnings( "deprecation" )
    private void parseBody(final ArrayList<Article> articles, final Query query) throws IOException {
        for (Article a : articles) {
            Document document;
            if (query.getIsFullLinkToBody().equals("true")) {
                document = Jsoup.connect(a.getUrl()).timeout(timeout).validateTLSCertificates(false).get();
            } else {
                document = Jsoup.connect(query.getUrl() + a.getUrl()).timeout(timeout).validateTLSCertificates(false).get();
            }
            Element element = document.getElementsByAttributeValue(query.getBodyTag(), query.getBodyName()).first();

            Elements childs;
            if (query.getBodyTextTag().isEmpty()) {
                childs = element.select("p");
            } else {
                childs = element.select(query.getBodyTextTag());
            }
            for (Element child : childs) {
                if (a.getBody() == null) {
                    a.setBody(child.text());
                } else {
                    a.setBody(a.getBody() + "\n" + child.text());
                }
            }
        }
    }
}
