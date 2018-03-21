package xyz.hnnknk.arcturus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.hnnknk.arcturus.service.CityParserService;

import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    CityParserService cityParserService;

    @RequestMapping(value = "/")
    public String index() {
        try {
            cityParserService.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
