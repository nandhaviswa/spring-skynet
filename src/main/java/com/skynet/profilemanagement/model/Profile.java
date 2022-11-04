package com.skynet.profilemanagement.model;

import java.util.List;

import lombok.Data;

@Data
public class Profile {
    private int id;
    private String name;
    private List<ProfileDetail> profileDetails;
}
