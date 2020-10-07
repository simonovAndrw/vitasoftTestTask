package test.vitasoft.vitasoft_test.entity;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    USERS_MODIFY_DRAFT("users:modify_draft"),
    USER_LIST("users:list"),

    OPERATOR_READ("operators:read"),
    OPERATOR_ACCEPT("operators:accept"),
    OPERATOR_DECLINE("operators:decline"),

    ADMIN_LIST_ALL_USERS("admins:list_all_users"),
    ADMIN_SET_PRIVILEGES("admin:set_privileges");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
