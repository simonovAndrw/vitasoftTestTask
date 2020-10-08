package test.vitasoft.vitasoft_test.util;

import test.vitasoft.vitasoft_test.entity.Role;

import java.util.Collection;

public class Utility {

    public static boolean isSingleRole(Collection<Role> roles, Role neededRole) {
        return roles.size() == 1 && roles.contains(neededRole);
    }
}
