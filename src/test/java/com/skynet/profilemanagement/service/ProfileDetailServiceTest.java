package com.skynet.profilemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@ExtendWith(MockitoExtension.class)
class ProfileDetailServiceTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProfileDetailService service;

    private ProfileDetail profileDetailMock;

    private List<ProfileDetail> profileDetailListMock;

    @BeforeEach
    void setUp() {
        profileDetailMock = new ProfileDetail();
        profileDetailMock.setOtherDetails("1");

        profileDetailListMock = new ArrayList<>();
        profileDetailListMock.add(profileDetailMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should return profile detail list based on profile id")
    void findAllByProfileId() {
        when(jdbcTemplate.query(anyString(), anyMap(), any(RowMapper.class))).thenReturn(profileDetailListMock);
        assertEquals(profileDetailListMock, service.findAllByProfileId(1));
        verify(jdbcTemplate, atMostOnce()).query(anyString(), anyMap(), any(RowMapper.class));
    }

    @Test
    @DisplayName("Should return profile detail based on result set")
    void mapRow() throws SQLException {
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        when(resultSetMock.getString(anyString())).thenReturn(profileDetailMock.getOtherDetails());
        ProfileDetail profileDetail = service.mapRow(resultSetMock, 1);
        assertEquals(profileDetail.getOtherDetails(), profileDetailMock.getOtherDetails());
        verify(resultSetMock, atMostOnce()).getInt(anyString());
        verify(resultSetMock, atMostOnce()).getString(anyString());
    }
}