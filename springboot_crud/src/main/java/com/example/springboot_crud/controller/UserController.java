package com.example.springboot_crud.controller;

import com.example.springboot_crud.entity.Users;
import com.example.springboot_crud.responces.SingleUserResponse;
import com.example.springboot_crud.services.UsersService;
import com.example.springboot_crud.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/springboot_crud/users")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/")
    @Operation(description = "Operation description", summary = "Operation summary", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SingleUserResponse.class)),
                            examples = @ExampleObject(
                                    name = "Example Response",
                                    value = """
                                            [
                                              {
                                                "userId": 101,
                                                "email": "john.doe@example.com",
                                                "fullName": "John Doe",
                                                "phone": "+91-9876543210",
                                                "avatarURL": "https://example.com/avatar/john.png"
                                              },
                                              {
                                                "userId": 102,
                                                "email": "jane.smith@example.com",
                                                "fullName": "Jane Smith",
                                                "phone": "+91-9123456789",
                                                "avatarURL": "https://example.com/avatar/jane.png"
                                              }
                                            ]
                                            """
                            )
                    )
            )
    }
    )
    public ResponseEntity<List<SingleUserResponse>> getAllUsers() {
        List<Users> allUsers = usersService.getAllUsers();
        log.info("getAllUsers method called");
        ResponseEntity<List<SingleUserResponse>> listResponseEntity = new ResponseEntity<>(allUsers.stream().map(users -> new SingleUserResponse(users.getUserId(), users.getEmail(), users.getFullName(), users.getPhone(), users.getAvatarURL())).toList(), HttpStatus.OK);
        log.info(listResponseEntity.toString());
        return listResponseEntity;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SingleUserResponse> getUsersByUserId(@PathVariable int userId) {
        log.info("getUsersByUserId method called");

        Users users = usersService.getUsersByUserId(userId);
        log.info("Response in postUser method: {}", users.toString());
        return new ResponseEntity<>(new SingleUserResponse(users.getUserId(), users.getEmail(), users.getFullName(), users.getPhone(), users.getAvatarURL()), HttpStatus.OK);
    }


//    @PostMapping("/")
//    public ResponseEntity<SingleUserResponse> postUser(@Valid @RequestBody Users users) {
//        log.info("postUser method called");
//        Users instertedUsers = usersService.postUsers(users);
//        return new ResponseEntity<>(new SingleUserResponse(instertedUsers.getUserId(), instertedUsers.getEmail(), instertedUsers.getFullName(), instertedUsers.getPhone(), instertedUsers.getAvatarURL()), HttpStatus.OK);
//    }

    @Autowired
    Users tempUsers;

    @PostMapping("/")
    public ResponseEntity<SingleUserResponse> postUserDTO(@Valid @RequestBody UserDTO userDTO) {
        log.info("postUserDTO method called");

        tempUsers.setEmail(userDTO.getEmail());
        tempUsers.setPhone(userDTO.getPhone());
        tempUsers.setFullName(userDTO.getFullName());
        tempUsers.setAvatarURL(userDTO.getAvatarURL());
        tempUsers.setPasswordHash(userDTO.getPasswordHash());
        Users instertedUsers = usersService.postUsers(tempUsers);
        log.info("Inserted user in postUser method: {}", instertedUsers.toString());
        return new ResponseEntity<>(new SingleUserResponse(instertedUsers.getUserId(), instertedUsers.getEmail(), instertedUsers.getFullName(), instertedUsers.getPhone(), instertedUsers.getAvatarURL()), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SingleUserResponse> putUser(@PathVariable int userId, @Valid @RequestBody Users users) {
        log.info("putUser method called");

        users.setUserId(userId);
        Users updatedUsers = usersService.putUser(users);
        log.info("Updated user in putUser method: {}", updatedUsers.toString());
        return new ResponseEntity<>(new SingleUserResponse(updatedUsers.getUserId(), updatedUsers.getEmail(), updatedUsers.getFullName(), updatedUsers.getPhone(), updatedUsers.getAvatarURL()), HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        log.info("deleteUser method called");
        ResponseEntity<String> stringResponseEntity;

        if (usersService.deleteUsers(userId)) {
            stringResponseEntity = new ResponseEntity<>("UserDTO with userId " + userId + " is successfully deleted", HttpStatus.OK);
            log.info("delete user responce: {}", stringResponseEntity);
            return stringResponseEntity;
        }

        stringResponseEntity = new ResponseEntity<>("UserDTO with userId " + userId + "failed to delete", HttpStatus.NOT_MODIFIED);
        log.info("delete user responce: {}", stringResponseEntity);
        return stringResponseEntity;
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUsersByField(@PathVariable int userId){
//
//        if (usersService.deleteUsersByField(userId)) return new ResponseEntity<>("UserDTO with userId " + userId + " is successfully deleted",HttpStatus.OK);
//        return new ResponseEntity<>("UserDTO with userId " + userId + "failed to delete",HttpStatus.NOT_MODIFIED);
//    }
}
