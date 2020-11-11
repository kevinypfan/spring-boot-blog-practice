package kevins.fun.blog.service;

import kevins.fun.blog.entity.Article;
import kevins.fun.blog.model.Cargo;
import kevins.fun.blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public Cargo getBlogs() {
        Cargo cargo = new Cargo();
        List<Article> articles = articleRepository.findAll();
        cargo.setInfo(articles);
        return cargo;
    }

    public Cargo getBlog(String id) {
        Cargo cargo = new Cargo();
        Optional<Article> _blog = articleRepository.findById(id);
        if (_blog.isPresent()) {
            Article oldArticle = _blog.get();
            oldArticle.setVisitCount(oldArticle.getVisitCount() + 1);
            Article updatedArticle = articleRepository.save(oldArticle);
            cargo.setInfo(updatedArticle);
        } else {
            cargo.setReturnCode("100404");
            cargo.setReturnMessage("Not found the blog");
        }
        return cargo;
    }

    public Cargo createBlog(Article article) {
        Cargo cargo = new Cargo();
        Article _article = articleRepository.insert(article);
        cargo.setInfo(_article);
        return cargo;
    }

    public Cargo updateBlog(String id, Article article) {
        Optional<Article> oldBlog = articleRepository.findById(id);
        Cargo cargo = new Cargo();
        if (oldBlog.isPresent()) {
            Article _article = oldBlog.get();
            _article.setTitle(article.getTitle());
            _article.setDescription(article.getDescription());
            _article.setContent(article.getContent());
            _article.setTags(article.getTags());
            _article.setUpdatedTime(new Date());
            _article.setImagePath(article.getImagePath());
            Article newArticle = articleRepository.save(_article);
            cargo.setInfo(newArticle);
        } else {
            cargo.setReturnCode("100404");
            cargo.setReturnMessage("Not found the blog");
        }
        return cargo;
    }

    public Cargo deleteBlog(String id) {
        Cargo cargo = new Cargo();
        try {
            articleRepository.deleteById(id);
            return cargo;
        } catch (Exception e) {
            cargo.setReturnCode("100500");
            cargo.setReturnMessage("INTERNAL_SERVER_ERROR");
            return cargo;
        }

    }

}
