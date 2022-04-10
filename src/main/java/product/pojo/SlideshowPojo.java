package product.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SlideshowPojo {

    private String author;
    private String date;
    private List<SlidePojo> slides;
    private String title;
}
