package database.enums;

public enum OrdersTableFields {

    ID("Id"),
    PRODUCT_ID("ProductId"),
    CUSTOMER_ID("CustomerId"),
    CREATED_AT("CreatedAt"),
    PRODUCT_COUNT("ProductCount"),
    PRICE("Price");

    private final String fieldName;

    OrdersTableFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
