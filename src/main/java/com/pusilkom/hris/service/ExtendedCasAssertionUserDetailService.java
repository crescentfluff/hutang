package com.pusilkom.hris.service;

import com.pusilkom.hris.model.UserWeb;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtendedCasAssertionUserDetailService extends AbstractCasAssertionUserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String NON_EXISTENT_PASSWORD_VALUE = "NO_PASSWORD";
    private final String[] attributes;
    private final Collection<? extends GrantedAuthority> defaultGrantedAuthorities;
    private boolean toUppercase = true;

    AuthItemService authItemService;

    public ExtendedCasAssertionUserDetailService(AuthItemService authItemService, String[] attributes, Collection<? extends GrantedAuthority> defaultGrantedAuthorities) {
        this.authItemService = authItemService;
        this.attributes = attributes;
        this.defaultGrantedAuthorities = defaultGrantedAuthorities;
    }

    @Override
    protected UserDetails loadUserDetails(Assertion assertion) {
        logger.debug("==START loadUserDetails===");
        String username = assertion.getPrincipal().getName();
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Unable to retrieve username from CAS assertion");
        } else {
            List<String> roles = new ArrayList<>();
            List<GrantedAuthority> authorities = (List) Arrays.stream(this.attributes).map((a) -> {
                return assertion.getPrincipal().getAttributes().get(a);
            }).filter(Objects::nonNull).flatMap((v) -> {
                return v instanceof Collection ? ((Collection)v).stream() : Stream.of(v);
            }).map((v) -> {
                return this.toUppercase ? v.toString().toUpperCase() : v.toString();
            }).map((r) -> {
                return r.toString().replaceFirst("^ROLE_", "");
            }).map((r) -> {
                roles.add("ROLE_" + r.toString());
                return new SimpleGrantedAuthority("ROLE_" + r);
            }).collect(Collectors.toList());

            authorities.addAll(this.defaultGrantedAuthorities);

            //get authorities from database
            for (String role : roles) {
                logger.debug("==Get authority for roles: " + role);
                List<String> listAuthItem = authItemService.getAllChildNameByParentId(role);

                for (String authItem : listAuthItem) {
                    authorities.add(new SimpleGrantedAuthority(authItem));
                }
            }
            logger.debug("==END loadUserDetails===");
            logger.debug("authorities == "+authorities);
            return new UserWeb(username, NON_EXISTENT_PASSWORD_VALUE, authorities, assertion.getPrincipal().getAttributes());
        }
    }
}
