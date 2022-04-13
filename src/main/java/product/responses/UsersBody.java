package product.responses;

import helpers.JsonRepresentation;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import product.bo.pojo.DataPojo;
import product.bo.pojo.SupportPojo;

import java.util.List;

@Getter
public class UsersBody {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<DataPojo> data;
    private SupportPojo support;

    @Override
    public int hashCode() {
        final int firstPrime = 23;
        final int secondPrime = 17;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(page)
                .append(per_page)
                .append(total)
                .append(total_pages)
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
        UsersBody other = (UsersBody) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(page, other.page)
                .append(per_page, other.per_page)
                .append(total, other.total)
                .append(total_pages, other.total_pages)
                .append(data, other.data)
                .append(support, other.support)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
