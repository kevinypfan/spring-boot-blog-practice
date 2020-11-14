package kevins.fun.blog.elastic.controller;

import kevins.fun.blog.elastic.entity.EsArticle;
import kevins.fun.blog.elastic.service.impl.EsArticleServiceImpl;
import kevins.fun.blog.mongo.model.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/elastic")
public class EsArticleController {

    @Autowired
    private EsArticleServiceImpl esArticleServiceImpl;

    @GetMapping("/blogs")
    public ResponseEntity<Cargo> getBlogList() {
        Cargo cargo = new Cargo();
        List<EsArticle> esArticles = esArticleServiceImpl.getBlogs();
        cargo.setInfo(esArticles);
        return ResponseEntity.ok().body(cargo);
//                .headers(responseHeaders)
    }

    @PostMapping("/blogs")
    public Cargo createBlog(@RequestBody EsArticle esArticle) {
        Cargo cargo = new Cargo();
        EsArticle esArticle1 = esArticleServiceImpl.saveOrUpdate(esArticle);
        cargo.setInfo(esArticle1);
        return cargo;
    }

}