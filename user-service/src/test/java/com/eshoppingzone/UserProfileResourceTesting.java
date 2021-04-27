package com.eshoppingzone;

import com.eshoppingzone.pojo.Address;
import com.eshoppingzone.pojo.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserProfileResourceTesting {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userGetAPITest() throws Exception {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("/profiles" , String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void addUserPostAPITest() {
        Address address=new Address(404,
                "jp road",
                "airoli",
                "mumbai",
                "maharashtra",
                4000);

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);


        UserProfile newProfile = new UserProfile(	112,
                "test",
                "testImage.png",
                "test@test.com",
                1234L,
                addressList,
                "test",
                LocalDate.now(),
                "male",
                "customer","1234");

        ResponseEntity<String> responseEntity= restTemplate.postForEntity("/profiles/customers", newProfile,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void userByProfileIdGetAPITest() {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("/profiles/1" , String.class);
        assertEquals(responseEntity.getStatusCode() , HttpStatus.OK);
    }

    @Test
    public void UserByProfileIdGetAPITestWhenUserIdNotAvailable() throws Exception {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("/profiles/id/50" , String.class);
        assertEquals(responseEntity.getStatusCode() , HttpStatus.NOT_FOUND);
    }
}
