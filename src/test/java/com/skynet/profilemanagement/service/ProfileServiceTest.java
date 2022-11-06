package com.skynet.profilemanagement.service;

import com.skynet.profilemanagement.model.Profile;
import com.skynet.profilemanagement.model.ProfileDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Mock
    private ProfileDetailService profileDetailService;

    @InjectMocks
    private ProfileService profileService;

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
        profileDetailMock.setProfileId(1);
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
    @DisplayName("Should return profile list for specific profile id")
    void findAllById() {
        when(jdbcTemplate.query(anyString(), anyMap(), any(RowMapper.class))).thenReturn(profileListMock);
        assertEquals(profileListMock, profileService.findAllById("1"));
        verify(jdbcTemplate, atMostOnce()).query(anyString(), anyMap(), any(RowMapper.class));
    }

    @Test
    @DisplayName("Should return all profile list if profile id is not present")
    void findAllById2() {
        when(jdbcTemplate.query(anyString(), anyMap(), any(RowMapper.class))).thenReturn(profileListMock);
        assertEquals(profileListMock, profileService.findAllById(null));
        verify(jdbcTemplate, atMostOnce()).query(anyString(), anyMap(), any(RowMapper.class));
    }

    @Test
    @DisplayName("Should read the result set and return profile with details")
    void mapRow() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        
        when(resultSetMock.getInt(anyString())).thenReturn(profileMock.getId());
        when(resultSetMock.getString(anyString())).thenReturn(profileMock.getName());
        when(profileDetailService.findAllByProfileId(anyInt())).thenReturn(profileDetailListMock);
        
        Profile profile = profileService.mapRow(resultSetMock, 1);
        assertEquals(profile.getId(), profileMock.getId());
        assertEquals(profile.getName(), profileMock.getName());
        assertEquals(profile.getProfileDetails(), profileDetailListMock);

        verify(resultSetMock, atMostOnce()).getInt(anyString());
        verify(resultSetMock, atMostOnce()).getString(anyString());
        verify(profileDetailService, atMostOnce()).findAllByProfileId(anyInt());
    }
}