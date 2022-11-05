package com.skynet.profilemanagement.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.skynet.profilemanagement.model.Profile;
import com.skynet.profilemanagement.model.ProfileDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfileDetailService profileDetailService;

    public List<Profile> findAllById(){
        String sql = "SELECT * FROM profile;";
        List<Profile> profileList = jdbcTemplate.query(sql, this::mapRow);
        return profileList;
    }
    public Profile mapRow(ResultSet rs, int row){
        try {
            Profile profile = new Profile();
            profile.setId(1);
            profile.setName("nandha");

            List<ProfileDetail> profileDetailList = new ArrayList<>();

            ProfileDetail profileDetail1 = new ProfileDetail();
            profileDetail1.setProfileId(1);
            profileDetail1.setOtherDetails("nandha detail 1");
            profileDetailList.add(profileDetail1);

            ProfileDetail profileDetail2 = new ProfileDetail();
            profileDetail2.setProfileId(1);
            profileDetail2.setOtherDetails("nandha detail 2");
            profileDetailList.add(profileDetail2);

            profile.setProfileDetails(profileDetailList);

            return profile;
        } catch (Exception e) {
            // TODO: handle exception
        }        
        return null;
    }
}
