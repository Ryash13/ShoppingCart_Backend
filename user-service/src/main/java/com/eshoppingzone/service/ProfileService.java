package com.eshoppingzone.service;

import com.eshoppingzone.pojo.UserProfile;

import java.util.List;

public interface ProfileService {

    UserProfile addNewCustomerProfile(UserProfile profile);
    List<UserProfile> getAllProfiles();
    UserProfile getByProfileId(int profileId);
    void updateProfile(UserProfile userProfile);
    void deleteProfile(int profileId);
    void addNewMerchantProfile(UserProfile profile);
    void addNewDeliveryProfile(UserProfile profile);
    UserProfile findByMobileNo(Long mobileNumber);
    UserProfile getByUserName(String username);

}
