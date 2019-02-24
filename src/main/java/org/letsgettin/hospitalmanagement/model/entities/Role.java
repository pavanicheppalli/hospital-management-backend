package org.letsgettin.hospitalmanagement.model.entities;




import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_DOCTOR, ROLE_HOSPITAL, ROLE_APPLICANT, ROLE_LABORATORY, ROLE_PHARMACY ;

    public String getAuthority() {
        return name();
    }

}