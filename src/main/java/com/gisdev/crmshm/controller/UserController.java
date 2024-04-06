package com.gisdev.crmshm.controller;

import com.gisdev.crmshm.dto.UserDto;
import com.gisdev.crmshm.entity.User;
import com.gisdev.crmshm.service.UserService;
import com.gisdev.crmshm.util.constant.RestConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(RestConstants.UserController.BASE_PATH)
public class UserController {

    private UserService userService;

    @PostMapping(RestConstants.UserController.SAVE_USER)
    public ResponseEntity<Long> createUser(@Validated @RequestBody User user) {
        User saveduser = userService.createUser(user);
        return new ResponseEntity<>(saveduser.getId(), HttpStatus.OK);
    }

    @PutMapping(RestConstants.UserController.UPDATE_USER)
    public ResponseEntity<Long> updateUser(@PathVariable Long userId,
                                           @Validated @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @DeleteMapping(RestConstants.UserController.DELETE_USER)
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!!!");
    }

    @GetMapping(RestConstants.USER_ID)
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
