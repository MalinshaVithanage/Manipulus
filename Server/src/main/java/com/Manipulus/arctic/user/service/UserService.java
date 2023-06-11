package com.Manipulus.arctic.user.service;

import com.Manipulus.arctic.role.model.Role;
import com.Manipulus.arctic.user.dao.RoleDao;
import com.Manipulus.arctic.user.exception.UserNotFoundException;
import com.Manipulus.arctic.user.model.User;
import com.Manipulus.arctic.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements IUserService{

    private static IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Autowired
    private RoleDao roleDao;

    public User addUser(User user){
        String encodedPassword = getEncodedPassword(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    public User updateUser(int id, User user){
        return userRepository.save(user);
    }
    public User findUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(" user by id"+ id + "was not found"));
    }

    @Transactional
    public void deleteUserById(int id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser != null) {
            userRepository.deleteById(id);
        }
    }

        public void initRolesAndUser () {
            boolean isAdminExisting = userRepository.existsById(1);
            boolean isUserExisting = userRepository.existsById(2);

            if (!isAdminExisting) {
                Role adminRole = new Role();
                Set<Role> adminRoles = new HashSet<>();
                adminRole.setRoleName("Admin");
                adminRole.setRoleDescription("Admin role");
                roleDao.save(adminRole);
                adminRoles.add(adminRole);

                User adminUser = new User();
                adminUser.setFirst_name("Admin");
                adminUser.setLast_name("Person");
                adminUser.setUserName("admin123");
                adminUser.setEmail("admin.user@test.lk");
                adminUser.setPassword(getEncodedPassword("admin@pass"));
                adminUser.setRoles(adminRoles);
                this.userRepository.save(adminUser);
            }

            if (!isUserExisting) {
                Role userRole = new Role();
                Set<Role> userRoles = new HashSet<>();
                userRole.setRoleName("User");
                userRole.setRoleDescription("User role");
                userRoles.add(userRole);
                roleDao.save(userRole);

                User user = new User();
                user.setFirst_name("Nethmini");
                user.setLast_name("Kavindya");
                user.setUserName("neth123");
                user.setEmail("nethmini.kavindya@test.lk");
                user.setPassword(getEncodedPassword("neth@pass"));
                user.setRoles(userRoles);
                userRepository.save(user);
            }
        }

            public User registerNewUser(User user){
                return new User();
            }

            public User findUserByUserName(String username) {
                return userRepository.findUserByUserName(username);
            }
            public User loadUserByEmail (String email){
                return userRepository.findUserByEmail(email);
            }
            public String getEncodedPassword (String password){
                return new BCryptPasswordEncoder().encode(password);
            }


        public User loadUserByUsername (String username) {
            return userRepository.findUserByUserName(username);
        }

    }