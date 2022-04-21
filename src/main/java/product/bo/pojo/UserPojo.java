package product.bo.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPojo {

    private int id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String avatar;
}
