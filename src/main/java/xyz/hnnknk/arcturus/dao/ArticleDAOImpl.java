package xyz.hnnknk.arcturus.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.hnnknk.arcturus.model.Article;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Article article) {
        sessionFactory.getCurrentSession().save(article);
    }

    @Override
    public void update(Article article) {
        sessionFactory.getCurrentSession().update(article);
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public Article findById(long id) {
        return sessionFactory.getCurrentSession().get(Article.class, id);
    }

    @Override
    public List<Article> listAll() {
        TypedQuery<Article> query = sessionFactory.getCurrentSession().createQuery("from Article");
        return query.getResultList();
    }
}
