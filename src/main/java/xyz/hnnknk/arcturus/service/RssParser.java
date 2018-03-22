package xyz.hnnknk.arcturus.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import xyz.hnnknk.arcturus.model.RssQuery;

import java.io.IOException;

public interface RssParser {

    void parse(RssQuery query) throws IOException, FeedException;
}
