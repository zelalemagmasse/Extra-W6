package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Friend> getMyFriends() {
        return myFriends;
    }

    public void setMyFriends(Set<Friend> myFriends) {
        this.myFriends = myFriends;
    }

    public Set<Role> getRoleOfUsers() {
        return roleOfUsers;
    }

    public void setRoleOfUsers(Set<Role> roleOfUsers) {
        this.roleOfUsers = roleOfUsers;
    }

    private String password;
    private String username;
    @OneToMany(mappedBy = "myFriend")
    private Set<Friend> myFriends;

    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Role> roleOfUsers;


    public User() {

        this.roleOfUsers = new HashSet<>();
    }
    public void  addRole(Role rr){
        this.roleOfUsers.add(rr);
    }


}
