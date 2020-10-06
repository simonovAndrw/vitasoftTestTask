package test.vitasoft.vitasoft_test.entity;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    OPERATOR_READ("operator:read");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
