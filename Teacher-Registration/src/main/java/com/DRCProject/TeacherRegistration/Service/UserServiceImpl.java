package com.DRCProject.TeacherRegistration.Service;

import com.DRCProject.TeacherRegistration.model.Role;
import com.DRCProject.TeacherRegistration.model.User;
import com.DRCProject.TeacherRegistration.repository.UserRepository;
import com.DRCProject.TeacherRegistration.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),registrationDto.getName(),registrationDto.getUsername(),registrationDto.getGender(),registrationDto.getAge(),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid Username or Password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }

    //create a method to map roles to authority
    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles){
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
