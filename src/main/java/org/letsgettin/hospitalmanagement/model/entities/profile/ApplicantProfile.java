package org.letsgettin.hospitalmanagement.model.entities.profile;

import org.letsgettin.hospitalmanagement.model.entities.User;

public class ApplicantProfile {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    User user;

    String gender;

    String email;

    String address;


}
