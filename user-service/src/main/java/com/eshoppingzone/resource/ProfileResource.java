package com.eshoppingzone.resource;

import com.eshoppingzone.pojo.UserProfile;
import com.eshoppingzone.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileResource {

    @Autowired
    private ProfileService service;

    // Add new Customer Profile
    @PostMapping("/customers")
    public UserProfile addNewCustomerProfile(@Valid @RequestBody UserProfile profile) {
        UserProfile profile1 = service.addNewCustomerProfile(profile);
        return profile1;
    }

    // Add new Merchant Profile
    @PostMapping("/merchants")
    public void addNewMerchantProfile(@Valid @RequestBody UserProfile profile) {
        service.addNewMerchantProfile(profile);
    }

    // Add new Delivery Agent Profile
    @PostMapping("/deliveryagents")
    public void addNewDeliveryProfile(@Valid @RequestBody UserProfile profile) {
        service.addNewDeliveryProfile(profile);
    }

    // Get All Profiles
    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    // Get a profile By ID
    @GetMapping("/{profileId}")
    public UserProfile getByProfileId(@PathVariable int profileId) {
        return service.getByProfileId(profileId);
    }

    // Get a profile by Mobile Number
    @GetMapping("/mobile/{mobileNumber}")
    public UserProfile getByPhoneNumber(@PathVariable Long mobileNumber) {
        return service.findByMobileNo(mobileNumber);
    }

    // Updating a profile
    @PutMapping
    public void updateProfile(@RequestBody UserProfile userProfile) {
        service.updateProfile(userProfile);
    }

    // Deleting a profile By ID
    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable int profileId) {
        service.deleteProfile(profileId);
    }

    // Get a profile By UserName
    @GetMapping("/getbyusernames/{username}")
    public UserProfile getByUserName(@PathVariable String username) {
        return service.getByUserName(username);
    }
}
