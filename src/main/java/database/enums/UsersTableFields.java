package database.enums;

public enum UsersTableFields {

    ID("Id"),
    USER_NAME("UserName"),
    PASSWORD("Password"),
    EMAIL("Email");

    private final String fieldName;

    UsersTableFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
