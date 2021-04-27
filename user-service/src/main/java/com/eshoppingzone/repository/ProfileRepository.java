package com.eshoppingzone.repository;

import com.eshoppingzone.pojo.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Integer>{

    UserProfile findAllByMobileNumber(Long mobileNumber);
    UserProfile findByFullName(String username);

}
