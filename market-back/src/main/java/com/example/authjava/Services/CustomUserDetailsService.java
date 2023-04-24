package com.example.authjava.Services;

import com.example.authjava.Data.Models.CustomUserDetails;
import com.example.authjava.Data.Models.UserModel;
import com.example.authjava.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleService
            .findUserRoles(user.getId())
            .forEach(roleModel -> {
                authorities.add(new SimpleGrantedAuthority(roleModel.getName()));
            });

        var customUserDetails = new CustomUserDetails();

        customUserDetails.setUser(user);
        customUserDetails.setAuthorities(authorities);

        return customUserDetails;
    }
}
