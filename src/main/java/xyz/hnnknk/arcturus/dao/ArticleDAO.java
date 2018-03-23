package xyz.hnnknk.arcturus.dao;

import xyz.hnnknk.arcturus.model.Article;

import java.util.List;

public interface ArticleDAO {

    void save(Article article);

    List<Article> listAll();
}
