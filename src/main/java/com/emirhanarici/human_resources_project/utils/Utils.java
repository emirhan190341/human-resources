package com.emirhanarici.human_resources_project.utils;

import com.emirhanarici.human_resources_project.model.EmailConfirmationToken;
import com.emirhanarici.human_resources_project.model.JobSeeker;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@UtilityClass
public class Utils {

    public static String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }


}
