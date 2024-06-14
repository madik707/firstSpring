package com.example.springdemo.services.impls;

import com.example.springdemo.entities.Roles;
import com.example.springdemo.entities.Users;
import com.example.springdemo.repositories.RoleRepository;
import com.example.springdemo.repositories.UserRepository;
import com.example.springdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users myUser = userRepository.findByEmail(username);
        if (myUser != null){

            User securityUser = new User(myUser.getEmail(),myUser.getPassword(), myUser.getRoles());
            return securityUser;

        }

        throw new UsernameNotFoundException("Username not found");
    }

    @Override
    public Users getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user){
        Users checkUser = userRepository.findByEmail(user.getEmail());

        if (checkUser == null){
            Roles role = roleRepository.findByPermission("ROLE_USER");
            if (role != null){
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                user.setPassword(passwordEncoder().encode(user.getPassword()));
                return userRepository.save(user);
            }
        }

        return null;
    }
}
