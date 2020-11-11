package kevins.fun.blog.service.dao;

import kevins.fun.blog.entity.Article;
import kevins.fun.blog.model.Cargo;
import org.springframework.stereotype.Service;




@Service
public interface ArticleService {

    Cargo getBlogs();

    Cargo getBlog(String id);

    Cargo createBlog(Article article);

    Cargo updateBlog(String id, Article article);

    Cargo deleteBlog(String id);

}
