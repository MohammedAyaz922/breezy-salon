package com.mask.controller;
import com.mask.Repository.UserRepository;
import com.mask.controller.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public User getUser() {
        User user = new User();
        user.setEmail("ayaankhan25116@gmail.com");
        user.setFullName("Ayaz Khan");
        user.setPhone("5198681231");
        user.setRole("Tester");
        return user;
    }

    @PutMapping("/api/user/{id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long id) throws Exception {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new Exception("user not found with id" + id);
        }
        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);

    }

    @DeleteMapping("/api/user/{id}")
    public String deleteUserById (@PathVariable Long id) throws Exception {
        Optional <User> otp=userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new Exception("user not exist with id"+id);
        }
        userRepository.deleteById(otp.get().getId());
        return "User deleted";
    }
}
