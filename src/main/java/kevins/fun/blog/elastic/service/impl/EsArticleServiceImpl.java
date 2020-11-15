package kevins.fun.blog.elastic.service.impl;

import kevins.fun.blog.elastic.entity.EsArticle;
import kevins.fun.blog.elastic.repository.EsArticleRepository;
import kevins.fun.blog.elastic.service.dao.EsArticleService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EsArticleServiceImpl implements EsArticleService {
    @Autowired
    private EsArticleRepository esArticleRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchRestTemplate;

    @Override
    public SearchPage<EsArticle> searchByTitleAndDescription(String term, PageRequest pageRequest) {
        final MultiMatchQueryBuilder qb = QueryBuilders.multiMatchQuery(term, "title", "description");

        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(qb)
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<span style=\"highlight color-yellow\">").postTags("</span>"),
                        new HighlightBuilder.Field("description").preTags("<span style=\"highlight color-red\">").postTags("</span>"))
//                .withPageable(pageRequest)
                .build();


        final SearchHits<EsArticle> searchHits = elasticsearchRestTemplate.search(searchQuery, EsArticle.class, IndexCoordinates.of("article"));

        SearchPage<EsArticle> esArticles = SearchHitSupport.searchPageFor(searchHits, searchQuery.getPageable());
//        esArticles.forEach(esArticleSearchHit -> System.out.println(esArticleSearchHit.toString()));

        return esArticles;
    }

    @Override
    public List<EsArticle> getBlogs() {
        return esArticleRepository.findAll().getContent();
    }

    @Override
    public Optional<EsArticle> findById(String id) {
        return esArticleRepository.findById(id);
    }

    @Override
    public EsArticle save(EsArticle blog) {
        return esArticleRepository.save(blog);
    }

    @Override
    public Optional<EsArticle> update(String id, EsArticle esArticle) {
        Optional<EsArticle> oldEsArticle = esArticleRepository.findById(id);
        if (oldEsArticle.isPresent()) {
            EsArticle _esArticle = oldEsArticle.get();
            _esArticle.setTitle(esArticle.getTitle());
            _esArticle.setDescription(esArticle.getDescription());
            _esArticle.setContent(esArticle.getContent());
            _esArticle.setTags(esArticle.getTags());
            _esArticle.setUpdatedTime(new Date());
            _esArticle.setImagePath(esArticle.getImagePath());
            _esArticle.setVisitCount(esArticle.getVisitCount());
            EsArticle newEsArticle = esArticleRepository.save(_esArticle);
            return Optional.of(newEsArticle);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String id) {
        esArticleRepository.deleteById(id);
    }

    @Override
    public Optional<EsArticle> findOne(String id) {
        return esArticleRepository.findById(id);
    }

}