package product.responses;

import helpers.JsonRepresentation;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
public class PersonBody {

    private String name;
    private String job;
    private int id;
    private String data;

    @Override
    public int hashCode() {
        final int firstPrime = 29;
        final int secondPrime = 79;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(name)
                .append(job)
                .append(id)
                .append(data)
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
        PersonBody other = (PersonBody) obj;
        return new EqualsBuilder()
                .append(name, other.name)
                .append(job, other.job)
                .append(id, other.id)
                .append(data, other.data)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
