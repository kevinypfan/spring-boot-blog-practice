package kevins.fun.blog.mongo.repository;

import kevins.fun.blog.mongo.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

//    public Optional<Article> findByTitle(String title);
}

