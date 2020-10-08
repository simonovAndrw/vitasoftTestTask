package test.vitasoft.vitasoft_test.entity;

public enum Role {
    USER,
    OPERATOR,
    ADMIN;

    Role() {}

    public static Role getRoleByName(String name) {
        if (name.equalsIgnoreCase("OPERATOR"))
            return Role.OPERATOR;
        else if (name.equalsIgnoreCase("ADMIN"))
            return Role.ADMIN;
        else
            return Role.USER;
    }
}
