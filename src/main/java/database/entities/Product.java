package database.entities;

import helpers.JsonRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@AllArgsConstructor
@Builder
@Data
public class Product extends Entity {

    private long id;
    private String productName;
    private String manufacturer;
    private int productCount;
    private int price;

    public Product(String productName, String manufacturer, int productCount, int price) {
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.productCount = productCount;
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 13;
        final int secondPrime = 89;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(productName)
                .append(manufacturer)
                .append(productCount)
                .append(price)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return new EqualsBuilder()
                .append(productName, other.productName)
                .append(manufacturer, other.manufacturer)
                .append(productCount, other.productCount)
                .append(price, other.price)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
