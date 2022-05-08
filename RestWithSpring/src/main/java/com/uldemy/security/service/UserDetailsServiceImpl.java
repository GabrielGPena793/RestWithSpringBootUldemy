package com.uldemy.security.service;

import com.uldemy.model.Permission;
import com.uldemy.model.User;
import com.uldemy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl {
    @Autowired
    UserRepository userRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User appUser = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        for (String role: appUser.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantList.add(grantedAuthority);
        }
        UserDetails user = null;
        user = (UserDetails) new User(username, appUser.getPassword(), grantList);
        return user;
    }
}

