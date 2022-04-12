package product.responses;

import helpers.JsonRepresentation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import product.bo.pojo.CoursePojo;

public class CourseBody {

    private CoursePojo course;

    @Override
    public int hashCode() {
        final int firstPrime = 29;
        final int secondPrime = 19;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(course)
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
        CourseBody other = (CourseBody) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(course, other.course)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
