package com.pusilkom.hris.model;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Data
public class UserWeb extends User {
    private Map<String, Object> attributes;
    private List<String> role;
    private EmployeeModel employeeModel;

    public UserWeb(String username, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attribute) {
        super(username, password, authorities);
        this.attributes = attribute;
        role = new ArrayList<>();
        String[] roles = authorities.toString().split(",");
        for (String r : roles
                ) {
            if (!r.contains("USER")) {
                if (r.contains("[")) {
                    role.add(r.substring(1));
                } else {
                    role.add(r);
                }
            }
        }
    }
    public UserWeb(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
