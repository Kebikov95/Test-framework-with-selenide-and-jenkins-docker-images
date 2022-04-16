package product.responses;

import helpers.JsonRepresentation;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import product.bo.pojo.DataPojo;
import product.bo.pojo.SupportPojo;

@Getter
public class UserBody {

    private DataPojo data;
    private SupportPojo support;

    @Override
    public int hashCode() {
        final int firstPrime = 11;
        final int secondPrime = 13;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(data)
                .append(support)
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
        UserBody other = (UserBody) obj;
        return new EqualsBuilder()
                .append(data, other.data)
                .append(support, other.support)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
