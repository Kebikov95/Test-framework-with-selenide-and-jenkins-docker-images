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
public class Customer extends Entity {

    private long id;
    private String firstName;

    public Customer(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 41;
        final int secondPrime = 47;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(firstName)
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
        Customer other = (Customer) obj;
        return new EqualsBuilder()
                .append(firstName, other.firstName)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
