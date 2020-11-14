package kevins.fun.blog.elastic.service.impl;

import kevins.fun.blog.elastic.entity.EsArticle;
import kevins.fun.blog.elastic.repository.EsArticleRepository;
import kevins.fun.blog.elastic.service.dao.EsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsArticleServiceImpl implements EsArticleService {
    @Autowired
    private EsArticleRepository articleRepository;

    @Override
    public EsArticle saveOrUpdate(EsArticle article) {
        return articleRepository.save(article);
    }

    @Override
    public List<EsArticle> getBlogs() {
        return articleRepository.findAll().getContent();
    }

}