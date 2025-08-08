package com.example.io_and_nio.service;

import com.example.io_and_nio.entity.ProfileEntity;
import com.example.io_and_nio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileEntity getProfile(int id) {

        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public ProfileEntity postProfile(ProfileEntity profileEntity) {

        return profileRepository.save(profileEntity);
    }

}
