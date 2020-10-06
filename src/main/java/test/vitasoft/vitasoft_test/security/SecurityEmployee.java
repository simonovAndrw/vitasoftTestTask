package test.vitasoft.vitasoft_test.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import test.vitasoft.vitasoft_test.entity.User;

import java.util.Collection;
import java.util.List;

public class SecurityEmployee implements UserDetails {

    private String name;
    private String password;

    private List<SimpleGrantedAuthority> authorities;

    public SecurityEmployee() {}

    public SecurityEmployee(String name, String password, List<SimpleGrantedAuthority> authorities) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public static UserDetails fromUser(User user) {
//        return new org.springframework.security.core.userdetails.User(
//                user.getName(), user.getPassword(),
//                true, true, true, true,
//                user.get)
//    }
}
