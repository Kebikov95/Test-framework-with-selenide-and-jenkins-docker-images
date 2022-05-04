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
public class Order extends Entity {

    private long id;
    private long productId;
    private long orderId;
    private String createdAt;
    private int productCount;
    private int price;

    public Order(long productId, long orderId, String createdAt, int productCount, int price) {
        this.productId = productId;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.productCount = productCount;
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 11;
        final int secondPrime = 73;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(productId)
                .append(orderId)
                .append(createdAt)
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
        Order other = (Order) obj;
        return new EqualsBuilder()
                .append(productId, other.productId)
                .append(orderId, other.orderId)
                .append(createdAt, other.createdAt)
                .append(productCount, other.productCount)
                .append(price, other.price)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
