package com.example.task_manager.security;

import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.Roles;
import com.example.task_manager.entity.Users;
import com.example.task_manager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepo.findById(username).orElseThrow(() -> new RuntimeException("user not fount"));

        Set<Roles> roles = users.getRoles();

//        Set<GrantedAuthority> roleSet = new HashSet<>();
//        roles.forEach(roles1 -> roleSet.add(new SimpleGrantedAuthority(roles1.getRole().name())));

        List<SimpleGrantedAuthority> list = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).toList();

        return new User(users.getUserName(),users.getPassword(),list);
    }
}
