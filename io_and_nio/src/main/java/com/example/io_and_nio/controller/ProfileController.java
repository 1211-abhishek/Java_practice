package com.example.io_and_nio.controller;

import com.example.io_and_nio.entity.ProfileEntity;
import com.example.io_and_nio.service.ProfileService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

//    @GetMapping("/{id}")
//    public ProfileEntity getProfile(@PathVariable int id){
//
//        return profileService.getProfile(id);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable int id) {
        ProfileEntity profile = profileService.getProfile(id);

        if (profile == null || profile.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(profile.getImage());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> getProfile(@PathVariable int id) {
//        ProfileEntity profile = profileService.getProfile(id);
//
//        if (profile == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("name", profile.getName());
//        response.put("image", Base64.getEncoder().encodeToString(profile.getImage()));
//
//        return ResponseEntity.ok(response);
//    }



    @PostMapping("/")
    public ProfileEntity postProfile(@RequestParam String name, @RequestParam MultipartFile image){

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(name);
        try {
            profileEntity.setImage(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return profileService.postProfile(profileEntity);
    }
}
