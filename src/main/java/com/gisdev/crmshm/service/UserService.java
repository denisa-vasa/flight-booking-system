package com.gisdev.crmshm.service;

import com.gisdev.crmshm.dto.UserDto;
import com.gisdev.crmshm.entity.User;
import com.gisdev.crmshm.exception.BadRequestException;
import com.gisdev.crmshm.exception.UserDataEmpty;
import com.gisdev.crmshm.exception.UserNotFoundException;
import com.gisdev.crmshm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User createUser(User user) {
        validateNotEmptyUser(user);
        validateUserData(user);
        return userRepository.save(user);
    }

    public void validateNotEmptyUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getRole() == null || user.getRole().isEmpty()) {
            throw new UserDataEmpty("Username and email must not be empty");
        }
    }

    public void validateUserData(User user) {
        List<User> existingUser = userRepository.findByUsernameAndPasswordAndRole(
                user.getUsername(), user.getPassword(), user.getRole());

        if (!existingUser.isEmpty()) {
            throw new BadRequestException("User already exists.");
        }
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found!"));
    }

    public void updateUser(Long userId, UserDto userDto) {
        User user = findUserById(userId);

        updateUserData(user, userDto);
        userRepository.save(user);
    }

    private void updateUserData(User userFromDb, UserDto userDto) {
        userFromDb.setUsername(userDto.getUsername());
        userFromDb.setPassword(userDto.getPassword());
    }

    public void deleteUser(Long userId) {
        findUserById(userId);

        userRepository.deleteById(userId);
    }
}
