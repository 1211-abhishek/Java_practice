package com.example.spring_security_blog_app.service;

import com.example.spring_security_blog_app.entity.BlogUsers;
import com.example.spring_security_blog_app.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BlogUsers user = usersRepository.findByUserName(username);
        if (user == null){
            throw new RuntimeException("User not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(roles -> authorities.add(new SimpleGrantedAuthority(roles.getRole().name())));

       return new User(user.getUserName(),user.getPassword(),authorities);

//        UserModel userModel = new UserModel();
//        userModel.getAuthorities();
//        userModel.setUser(user);
//        return userModel;
    }
}
