package com.placement.OtpAssignment.security;

import com.placement.OtpAssignment.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserProfileDetails implements UserDetails {

    private UserProfile userProfile;

    public UserProfileDetails(UserProfile userProfile){
        this.userProfile = userProfile;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }

    @Override
    public String getPassword() {
        return userProfile.getPassword();
    }

    @Override
    public String getUsername() {
        return userProfile.getEmail();
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
}
