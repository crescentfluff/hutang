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

    public UserWeb(String username, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attribute) {
        super(username, password, authorities);
        this.attributes = attribute;
            role = new ArrayList<>();
            String[] roles=authorities.toString().substring(1,authorities.toString().length()-2).split(",");
        for (String r : roles
             ) {
            if (!r.equalsIgnoreCase("role_user"))
                role.add(r);
        }

    }

    public UserWeb(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
