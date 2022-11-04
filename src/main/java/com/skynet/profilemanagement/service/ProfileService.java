package com.skynet.profilemanagement.service;

import java.sql.ResultSet;
import java.util.List;

import com.skynet.profilemanagement.model.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Profile> findAllById(){
        String sql = "SELECT * FROM profile;";
        List<Profile> profiles = jdbcTemplate.query(sql, this::mapRow);
        return profiles;
    }
    public Profile mapRow(ResultSet rs, int row){
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }        
        return null;
    }
}
