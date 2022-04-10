package product.http.response;

import framework.helpers.JsonRepresentation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import product.pojo.SlideshowPojo;

public class JsonResponseFormatBody {

    private SlideshowPojo slideshow;

    @Override
    public int hashCode() {
        final int firstPrime = 23;
        final int secondPrime = 17;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(slideshow)
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
        JsonResponseFormatBody other = (JsonResponseFormatBody) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(slideshow, other.slideshow)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
