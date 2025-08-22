package com.example.springboot_crud.services;

import com.example.springboot_crud.entity.Users;
import com.example.springboot_crud.exceptions.UserNotFoundException;
import com.example.springboot_crud.exceptions.UserNotUpdatedException;
import com.example.springboot_crud.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> getAllUsers() {

        List<Users> allUsers = usersRepository.findAll();
        if (allUsers.isEmpty()) throw new UserNotFoundException("Users not found");
        return allUsers;
    }

    public Users getUsersByUserId(int userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("UserDTO with UserId " + userId + " not found."));
    }

    public Users postUsers(Users users) {

        users.setCreatedAt(LocalDateTime.now());
        users.setUpdatedAt(LocalDateTime.now());
        return usersRepository.save(users);
    }

    public Users putUser(Users users) {
        Users existingUser = usersRepository.findById(users.getUserId()).orElseThrow(() -> new UserNotFoundException("Can't found user with userId " + users.getUserId() + " to update"));
        boolean isUpdated = false;

        if (users.getFullName() != null && !users.getFullName().isEmpty()) {
            existingUser.setFullName(users.getFullName());
            isUpdated = true;
        }
        if (users.getPhone() != null && !users.getPhone().isEmpty()) {
            existingUser.setPhone(users.getPhone());
            isUpdated = true;
        }
        if (users.getAvatarURL() != null && !users.getAvatarURL().isEmpty()) {
            existingUser.setAvatarURL(users.getAvatarURL());
            isUpdated = true;
        }
        if (users.getEmail() != null && !users.getEmail().isEmpty()) {
            existingUser.setEmail(users.getEmail());
            isUpdated = true;
        }
        if (isUpdated) {
            existingUser.setUpdatedAt(LocalDateTime.now());
            return usersRepository.save(existingUser);
        }
        throw new UserNotUpdatedException("The user is not updated");
    }

    public boolean deleteUsers(int userId) {

        if (usersRepository.findById(userId).isPresent()) {
            usersRepository.deleteById(userId);
            return true;
        }
        throw new UserNotFoundException("The user with userId " + userId + " is not present");
    }


    public boolean deleteUsersByField(int userId) {

        Users user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user with userId " + userId + " is not present"));
        user.setIsDeleted(1);
        user.setDeletedAt(LocalDateTime.now());
        usersRepository.save(user);
        return true;
    }
}
