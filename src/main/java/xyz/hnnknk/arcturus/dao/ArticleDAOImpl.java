package xyz.hnnknk.arcturus.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.arcturus.model.Article;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public final void save(final Article article) {
        sessionFactory.getCurrentSession().save(article);
    }

    @Override
    public final List<Article> listAll() {
        TypedQuery<Article> query = sessionFactory.getCurrentSession()
                .createQuery("from Article");
        return query.getResultList();
    }
}
