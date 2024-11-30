package com.Manipulus.arctic.user.controller;

import com.Manipulus.arctic.auth.helpers.JWTHelper;
import com.Manipulus.arctic.role.model.Role;
import com.Manipulus.arctic.user.model.User;
import com.Manipulus.arctic.user.model.UserRequest;
import com.Manipulus.arctic.user.model.UserResponse;
import com.Manipulus.arctic.user.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.Manipulus.arctic.auth.constants.JWTUtil.AUTH_HEADER;
import static com.Manipulus.arctic.auth.constants.JWTUtil.SECRET;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private JWTHelper jwtHelper;

    public UserController(UserService userService, JWTHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

    @PostMapping({"/register"})
    @PreAuthorize("hasAuthority('Admin')")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping
    public UserResponse addUser(@RequestBody UserRequest user) {
        return userService.addUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public User getUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public UserResponse updateUser(@PathVariable("id") int id, @RequestBody UserRequest user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }


    @PostMapping({"/refresh-token"})
    public void generateNewAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jwtRefreshToken = jwtHelper.extractTokenFromHeaderIfExist(request.getHeader(AUTH_HEADER));
        if (jwtRefreshToken != null) {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
            String username = decodedJWT.getSubject();
            User user = userService.loadUserByUsername(username);
            Set<Role> roles = user.getRoles();
            List<String> rolesList = new ArrayList<>();
            for (Role role : roles) {
                rolesList.add(role.getRoleName());
            }
            String jwtAccessToken = jwtHelper.generateAccessToken(user.getUserName(), rolesList);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), jwtHelper.getTokenMap(jwtAccessToken, jwtRefreshToken));
        } else {
            throw new RuntimeException("Refresh token required");
        }
    }
}

    /*@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData){
        System.out.println(userData);
        User user =userRepository.findUserByEmail(userData.getUserEmail());
        if (user.getUserPassword().equals(userData.getUserPassword()))
            return ResponseEntity.ok(user);
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }*/

    /*@PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> request) {
        /*String email = request..("email");
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Generate reset password token and send to user's email address

        return new ResponseEntity<>(HttpStatus.OK);
        return null;
    }*/
