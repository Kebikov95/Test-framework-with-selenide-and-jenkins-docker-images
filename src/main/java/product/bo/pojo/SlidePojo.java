package product.bo.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SlidePojo {

    private List<String> items;
    private String title;
    private String type;
}
