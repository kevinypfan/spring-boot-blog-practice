
package kevins.fun.blog.elastic.repository;

import kevins.fun.blog.elastic.entity.EsArticle;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsArticleRepository extends ElasticsearchRepository<EsArticle, String> {
    Page<EsArticle> findAll();
}