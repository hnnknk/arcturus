package xyz.hnnknk.arcturus.service;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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

    @Autowired
    ArticleDAO articleDAO;

    @Transactional
    @Override
    public void parse(Query query) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();

        Document doc = Jsoup.connect(query.getUrl() + query.getUrlSuffix()).timeout(10000).validateTLSCertificates(false).get();

        Elements mainElements = doc.getElementsByAttributeValue(query.getTitleTag(), query.getTitleName());
        Elements dateElements = doc.getElementsByAttributeValue(query.getDateTag(), query.getDateName());

        for (int i = 0; i < 3; i++) {
            Article article = new Article();
            if(query.getTitleTextTag().isEmpty()) {
                article.setTitle(mainElements.get(i).text());
            } else {
                article.setTitle(mainElements.get(i).select(query.getTitleTextTag()).first().text());
            }
            if(query.getDateTextTag().isEmpty()) {
                article.setDate(dateElements.get(i).text());
            } else {
                article.setDate(dateElements.get(i).select(query.getDateTextTag()).first().text());
            }
            article.setUrl(mainElements.get(i).select("a").attr( "href"));
            articles.add(article);
        }

        parseBody(articles, query);

        for (Article e: articles) {
            articleDAO.save(e);
            System.out.println("------------------------");
            System.out.println(e.getTitle());
            System.out.println(e.getDate());
            System.out.println("****************");
            System.out.println(e.getBody());
        }
    }

    @Transactional
    @Override
    public List<Article> listAll() {
        return articleDAO.listAll();
    }

    private void parseBody(ArrayList<Article> articles, Query query) throws IOException {
        for (Article a : articles) {
            Document document;
            if(query.getIsFullLinkToBody().equals("true")) {
                document = Jsoup.connect(a.getUrl()).timeout(10000).validateTLSCertificates(false).get();
            } else {
                document = Jsoup.connect(query.getUrl() + a.getUrl()).timeout(10000).validateTLSCertificates(false).get();
            }
            Element element = document.getElementsByAttributeValue(query.getBodyTag(), query.getBodyName()).first();

            Elements childs;
            if(query.getBodyTextTag().isEmpty()) {
                childs = element.select("p");
            } else {
                childs = element.select(query.getBodyTextTag());
            }
            for (Element child : childs) {
                if(a.getBody() == null) {
                    a.setBody(child.text());
                } else {
                    a.setBody(a.getBody() + "\n" + child.text());
                }
            }
        }
    }
}
