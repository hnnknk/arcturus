package xyz.hnnknk.arcturus.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import xyz.hnnknk.arcturus.model.Article;
import xyz.hnnknk.arcturus.model.Query;
import xyz.hnnknk.arcturus.service.ParserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
public class ArticleController {

    @Autowired
    ParserService CityParserService;

    @RequestMapping(value = "/parsing/parse/", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuery(@Valid @RequestBody Query query) {
        System.out.println("Creating " + query.toString());

        try {
            CityParserService.parse(query);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/parsing/results/", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> listAll() {
        List<Article> articles = CityParserService.listAll();
        if(articles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}

