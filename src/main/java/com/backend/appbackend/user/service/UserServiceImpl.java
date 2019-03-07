package com.backend.appbackend.user.service;

import com.backend.appbackend.user.entity.User;
import com.backend.appbackend.user.exception.UserNotFoundException;
import com.backend.appbackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) throws UserNotFoundException {
        //#TODO sake augustinas galima oneliner padaryt, paupgradinom i twoliner
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found in database"));
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        getUser(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws UserNotFoundException {
        getUser(id);
        userRepository.deleteById(id);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User signUpUser(User user) throws UserNotFoundException {
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

}
