package com.skynet.profilemanagement.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skynet.profilemanagement.model.ProfileDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileDetailService {
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<ProfileDetail> findAllByProfileId(int profileId){
        String sql = "SELECT * FROM profile_detail pd WHERE pd.profileId = :profileId;";
        Map<String, Object> params = new HashMap<>();
        params.put("profileId", profileId);
        List<ProfileDetail> profileDetails = jdbcTemplate.query(sql, params, this::mapRow);
        return profileDetails;
    }

    public ProfileDetail mapRow(ResultSet resultSet, int num){
        try {
            ProfileDetail profileDetail = new ProfileDetail();
            profileDetail.setProfileId(1);
            profileDetail.setOtherDetails("nandha detail 1");
            return profileDetail;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;        
    }
    
}
