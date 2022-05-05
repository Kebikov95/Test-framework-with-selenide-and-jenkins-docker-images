package database.enums.project;

public enum CustomersTableFields {

    ID("Id"),
    FIRST_NAME("FirstName");

    private final String fieldName;

    CustomersTableFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
