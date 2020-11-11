package kevins.fun.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cargo {
    private String returnCode = "000000";
    private String returnMessage = "SUCCESS";
    private Object info = null;
}
