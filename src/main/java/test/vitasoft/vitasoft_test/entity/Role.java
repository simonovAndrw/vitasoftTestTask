package test.vitasoft.vitasoft_test.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_MODIFY_DRAFT, Permission.USER_LIST)),
    OPERATOR(Set.of(Permission.OPERATOR_READ, Permission.OPERATOR_ACCEPT, Permission.OPERATOR_DECLINE)),
    ADMIN(Set.of(Permission.ADMIN_LIST_ALL_USERS, Permission.ADMIN_SET_PRIVILEGES));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    public static Role getRoleByName(String name) {
        if (name.equalsIgnoreCase("OPERATOR"))
            return Role.OPERATOR;
        else if (name.equalsIgnoreCase("ADMIN"))
            return Role.ADMIN;
        else
            return Role.USER;
    }
}
