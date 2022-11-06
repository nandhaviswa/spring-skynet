package com.skynet.profilemanagement.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.skynet.profilemanagement.model.Profile;
import com.skynet.profilemanagement.model.ProfileDetail;
import com.skynet.profilemanagement.service.ProfileService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @MockBean
    private ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    private Profile profileMock;

    private List<Profile> profileListMock;

    private ProfileDetail profileDetailMock;

    private List<ProfileDetail> profileDetailListMock;

    @BeforeEach
    void setUp() {
        profileMock = new Profile();
        profileMock.setId(1);
        profileMock.setName("john");

        profileDetailMock = new ProfileDetail();
        profileDetailMock.setOtherDetails("john detail");
        
        profileDetailListMock = new ArrayList<>();
        profileDetailListMock.add(profileDetailMock);

        profileMock.setProfileDetails(profileDetailListMock);

        profileListMock = new ArrayList<>();
        profileListMock.add(profileMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should return all profiles if profile id is not present in the query string")
    void getProfileDetails() throws Exception {
        when(profileService.findAllById(null)).thenReturn(profileListMock);
        mockMvc.perform(get("/getProfileDetails"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
        verify(profileService, atMostOnce()).findAllById(null);
    }

    @Test
    @DisplayName("Should return specific if profile id is present in the query string")
    void getProfileDetails2() throws Exception {
        when(profileService.findAllById(anyString())).thenReturn(profileListMock);
        mockMvc.perform(get("/getProfileDetails?ProfileId=1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
        verify(profileService, atMostOnce()).findAllById(anyString());
    }
}