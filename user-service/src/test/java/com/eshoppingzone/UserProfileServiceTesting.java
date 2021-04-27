package com.eshoppingzone;

import com.eshoppingzone.pojo.Address;
import com.eshoppingzone.pojo.UserProfile;
import com.eshoppingzone.repository.ProfileRepository;
import com.eshoppingzone.service.ProfileService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileServiceTesting {

    @Autowired
    private ProfileService profileService;

    @MockBean
    private ProfileRepository repository;

    @BeforeEach
    public void setUp() {
        Address address = new Address(
                101,
                "New Street",
                "New Colony",
                "Motihari",
                "Bihar",
                845401
        );

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        UserProfile userProfile = new UserProfile(
                5000,
                "testing name",
                "testing image.png",
                "testing@testing.com",
                1111L,
                addressList,
                "about testing",
                LocalDate.now(),
                "Gender Test",
                "Customer",
                "123@"
        );

        List<UserProfile> profileList = new ArrayList<>();
        profileList.add(userProfile);

        Mockito.when(repository.findAll())
                .thenReturn(profileList);
    }

    @Test
    public void testForData() {
        List<UserProfile> profileList = profileService.getAllProfiles();
        assertThat(profileList.isEmpty() == false);
    }

    @Test
    public void numberOfUsersTest() {
        List<UserProfile> profileList = profileService.getAllProfiles();
        assertThat(profileList.size() == 1);
    }

    @Test
    public void getProfileDetailsTest() {
        List<UserProfile> profileList = profileService.getAllProfiles();
        assertThat(profileList.get(0).getFullName().equals("testing name"));
        assertThat(profileList.get(0).getEmailId().equals("testing@testing.com"));
    }
}
