package kevins.fun.blog.mongo.service.dao;

import kevins.fun.blog.mongo.entity.Article;
import kevins.fun.blog.mongo.model.Cargo;
import org.springframework.stereotype.Service;




@Service
public interface ArticleService {

    Cargo getBlogs();

    Cargo getBlog(String id);

    Cargo createBlog(Article article);

    Cargo updateBlog(String id, Article article);

    Cargo deleteBlog(String id);

}
