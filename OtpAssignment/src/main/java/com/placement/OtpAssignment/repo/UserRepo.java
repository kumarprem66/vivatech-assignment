package com.placement.OtpAssignment.repo;

import com.placement.OtpAssignment.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserProfile,Integer> {

    Optional<UserProfile> findByEmail(String email);


}
