package com.skynet.profilemanagement.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skynet.profilemanagement.model.Profile;
import com.skynet.profilemanagement.model.ProfileDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ProfileDetailService profileDetailService;

    public List<Profile> findAllById(String profileId){
        String sql;
        Map<String, Object> params = new HashMap<>();
        if(profileId != null && !profileId.isBlank()) {
            sql = "SELECT * FROM profile WHERE id = :id;";
            params.put("id", profileId);
        } else{
            sql = "SELECT * FROM profile;";
        }
        List<Profile> profileList = jdbcTemplate.query(sql, params, this::mapRow);
        return profileList;
    }
    public Profile mapRow(ResultSet rs, int row){
        try {
            Profile profile = new Profile();
            profile.setId(1);
            profile.setName("nandha");

            List<ProfileDetail> profileDetailList = profileDetailService.findAllByProfileId(profile.getId());
            profile.setProfileDetails(profileDetailList);

            return profile;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }        
        return null;
    }
}
