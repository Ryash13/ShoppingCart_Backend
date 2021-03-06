package com.eshoppingzone.pojo;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int profileId;

    @NotNull(message = "Enter your full name")
    @Size(min = 3 , message = "Full Name must be greater than 3 letters")
    private String fullName;
    private String image;

    @Email(message = "Enter a valid email ID")
    private String emailId;

    @NotNull(message = "Mobile Number cannot be NULL")
    private Long mobileNumber;
    @Embedded
    @ElementCollection
    private List<Address> Addresses;
    private String about;

    @Past(message = "Invalid Date Of Birth, Entered a future date")
    private LocalDate dateOfBirth;
    private String gender;
    private String role;

    @NotNull(message = "Password must in between 6 - 32 Characters")
    @Size(min = 6 , max = 32 , message = "Password must in between 6 - 32 Characters")
    private String password;

    public UserProfile() {
    }

    public UserProfile(int profileId, String fullName, String image, String emailId, Long mobileNumber,
                       List<Address> addresses, String about, LocalDate dateOfBirth, String gender, String role, String password) {
        super();
        this.profileId = profileId;
        this.fullName = fullName;
        this.image = image;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        Addresses = addresses;
        this.about = about;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.role = role;
        this.password = password;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<Address> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<Address> addresses) {
        Addresses = addresses;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileId=" + profileId +
                ", fullName='" + fullName + '\'' +
                ", image='" + image + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", addressList=" + Addresses +
                ", about='" + about + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
