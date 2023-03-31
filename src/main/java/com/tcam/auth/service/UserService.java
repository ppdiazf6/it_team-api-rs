package com.tcam.auth.service;

import com.tcam.auth.model.User;
import com.tcam.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;


    public User getUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> findAllUser() throws UsernameNotFoundException {
        return userRepository.findAll();
    }
}
