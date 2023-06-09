package com.example.todolist.registration;


import com.example.todolist.security.PasswordEncoder;
import com.example.todolist.user.User;
import com.example.todolist.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService  {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(RegistrationDetails registrationDetails) {
        if (userRepository.findByUsername(registrationDetails.getUsername()).isPresent()) {
            // Username already exists
            return false;
        }

        // Create a new user based on registration details
        User user = new User();
        user.setUsername(registrationDetails.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDetails.getPassword()));
        user.setEmail(registrationDetails.getEmail());

        // Save the user to the repository
        userRepository.save(user);

        return true;
    }
}
