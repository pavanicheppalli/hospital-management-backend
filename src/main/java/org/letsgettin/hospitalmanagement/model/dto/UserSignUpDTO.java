package org.letsgettin.hospitalmanagement.model.dto;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.letsgettin.hospitalmanagement.model.entities.Role;

public class UserSignUpDTO {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
