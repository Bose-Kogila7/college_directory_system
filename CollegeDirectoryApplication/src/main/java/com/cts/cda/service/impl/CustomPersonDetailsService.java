//package com.example.cda.service.impl;
//
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.cda.entity.Person;
//import com.example.cda.repository.PersonRepository;
//
//@Service
//public class CustomPersonDetailsService implements UserDetailsService {
//
//    private PersonRepository personRepository;
//
//    public CustomPersonDetailsService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//          Person person = personRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
//                 .orElseThrow(() ->
//                         new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
//
//        Set<GrantedAuthority> authorities = person.getRoles()
//                .stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(person.getEmail(),
//                person.getPassword(),
//                authorities);
//    }
//}



