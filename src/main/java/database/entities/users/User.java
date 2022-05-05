package database.entities.users;

import database.entities.Entity;
import helpers.JsonRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@AllArgsConstructor
@Builder
@Data
public class User extends Entity {

    private long id;
    private String userName;
    private String password;
    private String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 37;
        final int secondPrime = 83;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(userName)
                .append(password)
                .append(email)
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
        User other = (User) obj;
        return new EqualsBuilder()
                .append(userName, other.userName)
                .append(password, other.password)
                .append(email, other.email)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}
