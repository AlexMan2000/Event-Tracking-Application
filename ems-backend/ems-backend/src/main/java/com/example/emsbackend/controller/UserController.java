package com.example.emsbackend.controller;

import com.example.emsbackend.dto.UserEntityDTO;
import com.example.emsbackend.service.paging.UserEntitySearchCriteria;
import com.example.emsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntityDTO>> getUsersByFilters(UserEntitySearchCriteria userEntitySearchCriteria) {
        return ResponseEntity.ok(userService.getUserDTOByFilter(userEntitySearchCriteria));
    }

    @GetMapping("/search/{email}")
    public ResponseEntity<UserEntityDTO> getUserEntityByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserDTOByEmail(email));
    }


    @PostMapping("/register")
    public ResponseEntity<UserEntityDTO> createNewUser(@RequestBody UserEntityDTO userEntityDTO) {

        userEntityDTO.setRegisteredAt(new Date());
        userEntityDTO.setRoleId(3L); // Initialize to 3, normal user
        try {
            UserEntityDTO createdUser = userService.createOrUpdateUser(userEntityDTO);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    /**
     * Manually add user to the system, restricted to admin(role 1)
     * @param userEntityDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<UserEntityDTO> createNewUserAdmin(@RequestBody UserEntityDTO userEntityDTO) {

        userEntityDTO.setRegisteredAt(new Date());
        try {
            UserEntityDTO createdUser = userService.createOrUpdateUser(userEntityDTO);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntityDTO> updateUserById(@RequestBody UserEntityDTO userEntityDTO, @PathVariable Long id) {
        try {
            UserEntityDTO updatedUser = userService.updateUserById(userEntityDTO, id);
            return updatedUser!= null ? ResponseEntity.ok(updatedUser): ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<UserEntityDTO> updateUserByEmail(@RequestBody UserEntityDTO userEntityDTO, @PathVariable String email) {
        try {
            UserEntityDTO updatedUser = userService.updateUserByEmail(userEntityDTO, email);
            return updatedUser!= null ? ResponseEntity.ok(updatedUser): ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Successfully deleted specified user");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
