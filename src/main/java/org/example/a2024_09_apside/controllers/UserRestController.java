package org.example.a2024_09_apside.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.example.a2024_09_apside.model.beans.UserBean;
import org.example.a2024_09_apside.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    //http://localhost:8080/users
    @GetMapping
    public ResponseEntity<List<UserBean>> getAllUsers() {
        return ResponseEntity.ok(UserService.load());
    }

    //http://localhost:8080/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserBean> getUserById(@PathVariable Long id) {

        var user = UserService.findById(id);
        if (user == null) {
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND)
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    //http://localhost:8080/users
    //{"login":"aaa", "password":"bbb"}
    @PostMapping
    public UserBean createUser(@RequestBody UserBean user, HttpServletResponse responses) {

        UserService.save(user);

        responses.setStatus(HttpStatus.CREATED.value());

        return user;
    }

    //http://localhost:8080/users/1
    //{"login":"aaa", "password":"bbb"}
    @PutMapping("/{id}")
    public ResponseEntity<UserBean> updateUser(@PathVariable Long id, @RequestBody UserBean userDetails) {

        var user = UserService.findById(id);
        if (user == null) {
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND)
            return ResponseEntity.notFound().build();
        } else {
            userDetails.setId(id);
            UserService.save(userDetails);
            return ResponseEntity.ok(user);
        }

    }

    //http://localhost:8080/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (UserService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
