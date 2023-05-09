package com.liftlife.liftlife.userModule.user;

import com.liftlife.liftlife.common.UserRole;
import com.liftlife.liftlife.util.database.FirestoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User extends FirestoreEntity implements UserDetails  {
    private String email;
    private String password;
    private boolean enabled;
    private UserRole userRole;
    private Date registerDate;

    public User(String email, String password, boolean enabled, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);    }

    @Override
    public String getPassword() {return this.password;}

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Date getRegisterDate() {
        return this.registerDate;
    }
    
}
