package database.enums.project;

public enum ProductsTableFields {

    ID("Id"),
    PRODUCT_NAME("ProductName"),
    MANUFACTURER("Manufacturer"),
    PRODUCT_COUNT("ProductCount"),
    PRICE("Price");

    private final String fieldName;

    ProductsTableFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
