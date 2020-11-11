package kevins.fun.blog.repository;


import java.util.Optional;

import kevins.fun.blog.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    public Optional<Article> findByTitle(String title);
}

