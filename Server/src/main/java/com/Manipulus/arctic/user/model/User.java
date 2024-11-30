package com.Manipulus.arctic.user.model;

import com.Manipulus.arctic.role.model.Role;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "designation", nullable = false)
    private String designation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = {
            @JoinColumn(name = "user_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    })

    private Set<Role> roles;

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserEmail() {
       return email;
    }
   public String getUserPassword() {
      return password;
    }

    public User UserRequestMapper(String first_name, String last_name, String user_name, String address, String mobile_number, String email, String password, String status, String designation, Set<Role> roles){
        this.firstName=first_name;
        this.lastName=last_name;
        this.userName=user_name;
        this.address=address;
        this.mobileNumber=mobile_number;
        this.email=email;
        this.password=password;
        this.status=status;
        this.designation=designation;
        this.roles = roles;
        return this;
    }

    public UserResponse UserResponseMapper(String first_name, String last_name, String user_name, String email, String status, String designation){
        UserResponse response = new UserResponse();
        response.firstName=first_name;
        response.lastName=last_name;
        response.userName=user_name;
        response.address=address;
        response.email=email;
        response.password=password;
        response.status=status;
        response.designation=designation;
        return response;
    }

}
