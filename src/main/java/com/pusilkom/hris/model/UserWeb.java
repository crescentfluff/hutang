package com.pusilkom.hris.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class UserWeb extends User {
    private Map<String, Object> attributes;
    private List<String> role;
    private EmployeeModel employeeModel;

    public UserWeb(String username, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attribute) {
        super(username, password, authorities);
        this.attributes = attribute;
            role = new ArrayList<>();
        String[] roles= authorities.toString().split(",");
        for (String r : roles
             ) {
            if (!r.contains("USER")) {
                if (r.contains("["))
                    role.add(r.substring(1));
                else role.add(r);
            }
        }

    }

    public UserWeb(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
