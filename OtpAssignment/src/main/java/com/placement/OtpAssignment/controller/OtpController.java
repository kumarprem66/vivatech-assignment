package com.placement.OtpAssignment.controller;

import com.placement.OtpAssignment.UserProfile;
import com.placement.OtpAssignment.service.OtpService;
import com.placement.OtpAssignment.service.UserService;
import com.placement.OtpAssignment.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpService service;

    @Autowired
    private UserService userService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateOTP(){


        return new ResponseEntity<>(service.generateOpt(),HttpStatus.CREATED);

    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestParam String enteredOtp, @RequestParam String generatedOtp) {
        if (service.validateOtp(enteredOtp, generatedOtp)) {
            return ResponseEntity.ok("OTP is valid!");
        } else {
            return new ResponseEntity<>("Invalid OTP!",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUserProfile(@RequestBody UserProfile userProfile){

        userService.createUserProfile(userProfile);
        ApiResponse apiResponse = new ApiResponse("user registration successful");
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);

    }

    @PostMapping("/signIn")
    public ResponseEntity<ApiResponse> signInUser(Authentication authentication){

        System.out.println(authentication);
        if (authentication == null) {
            // Log or handle the null authentication case
            ApiResponse apiResponse = new ApiResponse("Authentication is null");
            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        }


        UserProfile userProfile= userService.getCustomerByEmail(authentication.getName());
        ApiResponse apiResponse = new ApiResponse(" Logged In Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);

    }
}
