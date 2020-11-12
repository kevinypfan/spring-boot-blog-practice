package kevins.fun.blog.mongo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;



@Document(collection="articles")
@TypeAlias("article")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Article {

    @Id
    private String id;

    private String title;
    private String description;
    private String content;
    private String imagePath;
    private List<String> tags;
    private Integer visitCount = 0;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdTime = new Date();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedTime = new Date();




//    @Override
//    public String toString() {
//        return String.format(
//                "Customer[id=%s, firstName='%s', lastName='%s']",
//                id, firstName, lastName);
//    }

}
