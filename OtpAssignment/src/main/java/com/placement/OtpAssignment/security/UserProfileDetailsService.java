package com.placement.OtpAssignment.security;

import com.placement.OtpAssignment.UserProfile;
import com.placement.OtpAssignment.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserProfile> optionalUserProfile = userRepo.findByEmail(username);

        if (optionalUserProfile.isPresent()){
            UserProfile userProfile = optionalUserProfile.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(userProfile.getRole());
            authorities.add(sga);
            return new User(userProfile.getEmail(),userProfile.getPassword(),authorities);

//            return new UserProfileDetails(userProfile);
        }else{
            throw new BadCredentialsException("User details not found with this username "+username);
        }


    }
}
