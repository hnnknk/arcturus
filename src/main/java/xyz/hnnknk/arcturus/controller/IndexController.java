package xyz.hnnknk.arcturus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.hnnknk.arcturus.service.ParserService;

import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    ParserService cityParserService;

    @RequestMapping(value = "/")
    public String index() {
        try {
            cityParserService.parse();
        } catch (IOException e) {
        }
        return "index";
    }
}
