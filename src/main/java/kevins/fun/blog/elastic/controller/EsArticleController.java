package kevins.fun.blog.elastic.controller;

import kevins.fun.blog.elastic.entity.EsArticle;
import kevins.fun.blog.elastic.service.impl.EsArticleServiceImpl;
import kevins.fun.blog.mongo.model.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/elastic/blogs")
public class EsArticleController {

    @Autowired
    private EsArticleServiceImpl esArticleServiceImpl;

    @GetMapping("/search")
    public ResponseEntity<Cargo> search(@RequestParam(required = false) String term) {
        Cargo cargo = new Cargo();
        if (term == null) {
            List<EsArticle> esArticles = esArticleServiceImpl.getBlogs();
            cargo.setInfo(esArticles);
        } else {
            Page<EsArticle> esArticles = esArticleServiceImpl.searchByTitleAndDescription(term, PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id")));
            cargo.setInfo(esArticles.getContent());
        }
        return ResponseEntity.ok().body(cargo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getBlog(@PathVariable String id) {
        Cargo cargo = new Cargo();
        Optional<EsArticle> esArticle = esArticleServiceImpl.findById(id);
        if (esArticle.isPresent()) {
            EsArticle _esArticle = esArticle.get();
            _esArticle.setVisitCount(_esArticle.getVisitCount() + 1);
            Optional<EsArticle> updatedArticle = esArticleServiceImpl.update(id, _esArticle);
            cargo.setInfo(updatedArticle.get());
        } else {
            cargo.setReturnCode("100404");
            cargo.setReturnMessage("Not Found");
        }
        return ResponseEntity.ok().body(cargo);
    }

    @GetMapping("")
    public ResponseEntity<Cargo> getBlogList() {
        Cargo cargo = new Cargo();
        List<EsArticle> esArticles = esArticleServiceImpl.getBlogs();
        cargo.setInfo(esArticles);
        return ResponseEntity.ok().body(cargo);
//                .headers(responseHeaders)
    }

    @PostMapping("")
    public ResponseEntity<Cargo> createBlog(@RequestBody EsArticle esArticle) {
        Cargo cargo = new Cargo();
        EsArticle esArticle1 = esArticleServiceImpl.save(esArticle);
        cargo.setInfo(esArticle1);
        return ResponseEntity.ok().body(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> updateBlog(@PathVariable String id, @RequestBody EsArticle esArticle) {
        Cargo cargo = new Cargo();
        Optional<EsArticle> esArticle1 = esArticleServiceImpl.update(id, esArticle);
        if (esArticle1.isPresent()) {
            cargo.setInfo(esArticle);
        } else {
            cargo.setReturnCode("100404");
            cargo.setReturnMessage("Not Found");
        }
        return ResponseEntity.ok().body(cargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cargo> deleteBlog(@PathVariable String id) {
        Cargo cargo = new Cargo();
        Optional<EsArticle> esArticle = esArticleServiceImpl.findById(id);
        if (esArticle.isPresent()) {
            esArticleServiceImpl.deleteById(id);
        } else {
            cargo.setReturnCode("100404");
            cargo.setReturnMessage("Not Found");
        }
        return ResponseEntity.ok().body(cargo);
    }

}