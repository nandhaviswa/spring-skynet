package com.skynet.profilemanagement.controller;

import java.util.List;

import com.skynet.profilemanagement.model.Profile;
import com.skynet.profilemanagement.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getProfileDetails")
    public List<Profile> getProfileDetails(@RequestParam(name="ProfileId", required = false) String profileId){
        List<Profile> profileList = profileService.findAllById(profileId);
        return profileList;
    }
    
}
