package xyz.hnnknk.arcturus.dao;

import xyz.hnnknk.arcturus.model.Article;

import java.util.List;

public interface ArticleDAO {

    void save(Article article);
    void update(Article article);
    void delete(long id);
    Article findById(long id);

    List<Article> listAll();
}
