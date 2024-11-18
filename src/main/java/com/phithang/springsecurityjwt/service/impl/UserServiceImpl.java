package com.phithang.springsecurityjwt.service.impl;

import com.phithang.springsecurityjwt.entity.Users;
import com.phithang.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user: " + username);
        }
        return new MyUserService(user);
    }
    public List<Users> allUsers(){
        return userRepository.findAll();
    }
}
