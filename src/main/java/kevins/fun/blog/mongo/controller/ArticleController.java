package kevins.fun.blog.mongo.controller;


import kevins.fun.blog.mongo.entity.Article;
import kevins.fun.blog.model.Cargo;
import kevins.fun.blog.mongo.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/mongo")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;


    @PostMapping("/blogs")
    public Cargo createBlog(@RequestBody Article article) {
        return articleServiceImpl.createBlog(article);
    }

    @GetMapping("/blogs")
    public ResponseEntity<Cargo> getBlogList() {
       return ResponseEntity.ok().body(articleServiceImpl.getBlogs());
//                .headers(responseHeaders)
    }

    @GetMapping("/blogs/{id}")
    public Cargo getBlogById(@PathVariable("id") String id) {
        return articleServiceImpl.getBlog(id);
    }

    @PutMapping(value = "/blogs/{id}")
    public Cargo updateBlog(@PathVariable("id") String id, @RequestBody Article article) {
        return articleServiceImpl.updateBlog(id, article);
    }

    @DeleteMapping(value = "/blogs/{id}")
    public Cargo deleteBlog(@PathVariable("id") String id) {
        return articleServiceImpl.deleteBlog(id);
    }

}
