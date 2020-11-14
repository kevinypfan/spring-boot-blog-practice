package kevins.fun.blog.elastic.service.impl;

import kevins.fun.blog.elastic.entity.EsArticle;
import kevins.fun.blog.elastic.repository.EsArticleRepository;
import kevins.fun.blog.elastic.service.dao.EsArticleService;
import kevins.fun.blog.mongo.entity.Article;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EsArticleServiceImpl implements EsArticleService {
    @Autowired
    private EsArticleRepository esArticleRepository;

    @Override
    public Page<EsArticle> searchByTitleAndDescription(String term, PageRequest pageRequest) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 新增基本分詞查詢
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(term, "title", "description"));
        // 搜尋，獲取結果
        Page<EsArticle> esArticles = this.esArticleRepository.search(queryBuilder.build());

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