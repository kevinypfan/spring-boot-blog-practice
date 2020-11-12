package kevins.fun.blog.elastic.controller;

import kevins.fun.blog.elastic.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/elastic")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;


}
