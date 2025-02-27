//package com.example.cda.entity;
//
//import jakarta.persistence.Entity;
//
//import java.util.Set;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "person", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"username"}),
//        @UniqueConstraint(columnNames = {"email"})
//})
//public class Person {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//    private String username;
//    private String email;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;
//}



