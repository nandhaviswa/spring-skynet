package com.skynet.profilemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("/getProfileDetails")
    public String getProfileDetails(@RequestParam(name="ProfileId", required = false) String profileId){        
        return "Hello World!";
    }
    
}
