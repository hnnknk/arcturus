package xyz.hnnknk.arcturus.service;

import xyz.hnnknk.arcturus.model.Article;
import xyz.hnnknk.arcturus.model.Query;

import java.io.IOException;
import java.util.List;

public interface ParserService {

    void parse(Query query) throws IOException;
    List<Article> listAll();
}
