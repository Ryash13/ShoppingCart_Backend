package com.eshoppingzone.service;

import com.eshoppingzone.exceptions.ResourceNotFoundException;
import com.eshoppingzone.pojo.Role;
import com.eshoppingzone.pojo.UserProfile;
import com.eshoppingzone.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Override
    public UserProfile addNewCustomerProfile(UserProfile profile) {
        profile.setRole(Role.Customer);
        return repository.save(profile);
    }

    @Override
    public void addNewMerchantProfile(UserProfile profile) {
        profile.setRole(Role.Merchant);
        profile.setAddresses(null);
        repository.save(profile);

    }

    @Override
    public void addNewDeliveryProfile(UserProfile profile) {
        profile.setRole(Role.DeliveryAgent);
        profile.setAddresses(null);
        repository.save(profile);
    }

    @Override
    public List<UserProfile> getAllProfiles() {
        List<UserProfile> profiles = repository.findAll();
        if (profiles == null) {
            throw new ResourceNotFoundException("No Profiles Found");
        }
        return repository.findAll();
    }

    @Override
    public UserProfile getByProfileId(int profileId) {
        return repository.findById(profileId).orElseThrow(() ->
            new ResourceNotFoundException("User Not Found With ID:- " + profileId)
        );
    }

    @Override
    public void updateProfile(UserProfile userProfile) {
        UserProfile update = repository.findById(userProfile.getProfileId()).orElseThrow(() ->
                new ResourceNotFoundException("User Not Found With ID:- " + userProfile.getProfileId()));
        update.setFullName(userProfile.getFullName());
        update.setAbout(userProfile.getAbout());
        update.setGender(userProfile.getGender());
        update.setDateOfBirth(userProfile.getDateOfBirth());
        update.setEmailId(userProfile.getEmailId());
        update.setImage(userProfile.getImage());
        update.setMobileNumber(userProfile.getMobileNumber());
        update.setPassword(userProfile.getPassword());
        repository.save(update);
    }

    @Override
    public void deleteProfile(int profileId) {
        UserProfile profile = repository.findById(profileId).orElseThrow(() ->
                new ResourceNotFoundException("User Not Found With ID:- \" + profileId"));
        repository.delete(profile);
    }

    @Override
    public UserProfile findByMobileNo(Long phoneNo) {
        UserProfile profile = repository.findAllByMobileNumber(phoneNo);
        if (profile == null) {
            throw new ResourceNotFoundException("No Such User with Mobile Number:- " + phoneNo);
        }
        return repository.findAllByMobileNumber(phoneNo);
    }

    @Override
    public UserProfile getByUserName(String username) {
        UserProfile profile = repository.findByFullName(username);
        if (profile == null) {
            throw new ResourceNotFoundException("No Such User with UserName:- " + username);
        }
        return repository.findByFullName(username);
    }
}
