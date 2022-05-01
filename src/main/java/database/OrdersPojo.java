package database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrdersPojo {

    private String Id;
    private String ProductId;
    private String CustomerId;
    private String CreatedAt;
    private String ProductCount;
    private String Price;
}
