package com.placement.OtpAssignment.service;

import com.placement.OtpAssignment.UserProfile;
import com.placement.OtpAssignment.exception.UserException;
import com.placement.OtpAssignment.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfileById(Integer userId) {
        return userProfileRepository.findById(userId).orElseThrow(() -> new UserException("User Not found with id:" +userId));
    }

    public UserProfile getCustomerByEmail(String email){

        return userProfileRepository.findByEmail(email).orElseThrow(() -> new UserException("Customer Not found with Email: "+email));
    }

    public UserProfile createUserProfile(UserProfile userProfile) throws UserException{
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByEmail(userProfile.getEmail());
        if(optionalUserProfile.isPresent()){
            throw new UserException("User already exist with this email");
        }else{

            if(userProfile.getRole() == null){
                userProfile.setRole("ROLE_ADMIN");
            }
            userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
            return userProfileRepository.save(userProfile);

        }

    }

    public List<UserProfile> getAllCustomerDetails()throws UserException {

        List<UserProfile> customers= userProfileRepository.findAll();

        if(customers.isEmpty())
            throw new UserException("No Customer find");

        return customers;

    }

    public void updateUserProfile(Integer userId, UserProfile updatedProfile) {
        UserProfile existingProfile = userProfileRepository.findById(userId).orElseThrow(() -> new UserException("User Not found with id:" +userId));
        if (existingProfile != null) {
            // Update the existing profile with the new data
            existingProfile.setUsername(updatedProfile.getUsername());
            // Update other fields as needed
            userProfileRepository.save(existingProfile);
        }else{
            throw new UserException("User with this id does not exist");
        }
    }

    public Integer deleteUser(Integer userId) throws UserException {

        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if(userProfile.isPresent()){
            userProfileRepository.delete(userProfile.get());
            return userId;
        }else{
            throw new UserException("User with this id does not exist");
        }


    }


}
