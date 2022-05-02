package api.response;

import helpers.JsonRepresentation;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.Objects;

public class HttpResponse<T> {
    private int statusCode;
    private Map<String, String> headers;
    private T body;

    public HttpResponse(int statusCode, Map<String, String> headers, T body) {
        Objects.requireNonNull(headers, "Headers cannot be null");
        Objects.requireNonNull(body, "Body cannot be null");
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }

    @Override
    public int hashCode() {
        final int firstPrime = 13;
        final int secondPrime = 53;
        return new HashCodeBuilder(firstPrime, secondPrime)
                .append(statusCode)
                .append(headers)
                .append(body)
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
        HttpResponse<T> other = (HttpResponse<T>) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(statusCode, other.statusCode)
                .append(headers, other.headers)
                .append(body, other.body)
                .isEquals();
    }

    @Override
    public String toString() {
        return JsonRepresentation.convertToJsonString(this);
    }
}