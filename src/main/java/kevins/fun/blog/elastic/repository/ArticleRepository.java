package kevins.fun.blog.elastic.repository;

import kevins.fun.blog.elastic.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTagUsingDeclaredQuery(String tag, Pageable pageable);
}
