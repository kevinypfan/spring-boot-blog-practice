package kevins.fun.blog.elastic.service.dao;

import kevins.fun.blog.elastic.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    public Article saveOrUpdate(Article article);

//    public void delete(String id);

    public Page<Article> findAll(Pageable pageable);

}
