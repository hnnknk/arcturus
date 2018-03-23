package xyz.hnnknk.arcturus.controller;

import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import xyz.hnnknk.arcturus.model.Article;
import xyz.hnnknk.arcturus.model.Query;
import xyz.hnnknk.arcturus.model.RssQuery;
import xyz.hnnknk.arcturus.service.ParserService;
import xyz.hnnknk.arcturus.service.RssParser;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
public class ArticleController {

    @Autowired
    private ParserService parserServiceImpl;

    @Autowired
    private RssParser rssParserImpl;

    @RequestMapping(value = "/parsing/parse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuery(@Valid @RequestBody final Query query) {
        System.out.println("Creating " + query.toString());

        try {
            parserServiceImpl.parse(query);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/parsing/rssparse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRssQuery(@Valid @RequestBody final RssQuery query) {
        System.out.println("Creating " + query.toString());

        try {
            rssParserImpl.parse(query);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | FeedException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/parsing/results/", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> listAll() {
        List<Article> articles = parserServiceImpl.listAll();
        if (articles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}

