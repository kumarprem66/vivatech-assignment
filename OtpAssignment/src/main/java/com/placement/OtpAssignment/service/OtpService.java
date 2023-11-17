package com.placement.OtpAssignment.service;

import com.placement.OtpAssignment.exception.OtpException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {


    public String generateOpt(){
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }

    public boolean validateOtp(String enteredOtp, String generatedOtp) throws OtpException {
        // Compared entered OTP with the generated OTP
        try{
            return enteredOtp.equals(generatedOtp);
        }catch (OtpException e){
            throw new OtpException("Opt did not matched");
        }

    }


}
