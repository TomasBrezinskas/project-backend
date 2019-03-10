package com.backend.appbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserException("User not found in database"));
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws UserException {
        getUser(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws UserException {
        getUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public User signUpUser(@Valid User user) throws UserException {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new UserException("Email " + user.getEmail() + " already in the database.");
        }
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
