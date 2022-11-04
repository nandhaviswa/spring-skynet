package com.skynet.profilemanagement.service;

import java.sql.ResultSet;
import java.util.List;

import com.skynet.profilemanagement.model.ProfileDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileDetailService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProfileDetail> findAllByProfileId(int profileId){
        String sql = "SELECT * FROM profile_detail pd WHERE pd.profileId = 1;";
        List<ProfileDetail> profileDetails = jdbcTemplate.query(sql, this::mapRow);
        return profileDetails;
    }

    public ProfileDetail mapRow(ResultSet resultSet, int num){
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;        
    }
    
}
