package kevins.fun.blog.elastic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(indexName = "article", shards = 3) // default: shards = 1, replicas = 1
public class Article {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Text)
    private String imagePath;
    @Field(type = FieldType.Text)
    private String[] tags;
    @Field(type = FieldType.Integer)
    private Integer visitCount = 0;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdTime = new Date();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedTime = new Date();

}
