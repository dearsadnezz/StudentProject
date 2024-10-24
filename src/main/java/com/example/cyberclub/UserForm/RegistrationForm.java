package com.example.cyberclub.UserForm;

import com.example.cyberclub.Data.Users;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String email;
    public Users toUser(PasswordEncoder passwordEncoder) {
        return new Users(username, passwordEncoder.encode(password), email);
    }
}
