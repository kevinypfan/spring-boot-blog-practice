package kevins.fun.blog.elastic.service.dao;

import kevins.fun.blog.elastic.entity.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.util.List;
import java.util.Optional;

public interface EsArticleService {


//    public void delete(String id);

//    public Page<EsArticle> findAll(Pageable pageable);

    SearchPage<EsArticle> searchByTitleAndDescription(String term , PageRequest pageRequest);

    List<EsArticle> getBlogs();

    Optional<EsArticle> findById(String id);

    EsArticle save(EsArticle esArticle);

    Optional<EsArticle> update(String id, EsArticle esArticle);

    void deleteById(String id);

    Optional<EsArticle> findOne(String id);

}