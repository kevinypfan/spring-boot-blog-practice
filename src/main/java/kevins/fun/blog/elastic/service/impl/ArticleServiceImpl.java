package kevins.fun.blog.elastic.service.impl;

import kevins.fun.blog.elastic.entity.Article;
import kevins.fun.blog.elastic.repository.ArticleRepository;
import kevins.fun.blog.elastic.service.dao.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article saveOrUpdate(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
