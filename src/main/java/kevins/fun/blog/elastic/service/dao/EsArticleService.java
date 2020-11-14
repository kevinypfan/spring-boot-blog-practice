package kevins.fun.blog.elastic.service.dao;

import kevins.fun.blog.elastic.entity.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EsArticleService {

    public EsArticle saveOrUpdate(EsArticle article);

//    public void delete(String id);

//    public Page<EsArticle> findAll(Pageable pageable);
    public List<EsArticle> getBlogs();

}