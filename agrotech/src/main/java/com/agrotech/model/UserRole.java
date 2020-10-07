package com.agrotech.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String getAuthority() { return name; }

    /**
     * Get role name without ROLE_ prefix
     * */
    public String getRoleNameWithoutPrefix(String roleName) {

        String prefix = "ROLE_";

        if(roleName != null && name.startsWith(prefix)) {
            roleName = roleName.substring(prefix.length());
            roleName = roleName.substring(0, 1).toUpperCase() + roleName.substring(1).toLowerCase();
            return roleName;
        }

        return null;
    }
}
