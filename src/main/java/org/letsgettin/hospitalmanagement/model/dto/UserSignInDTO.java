package org.letsgettin.hospitalmanagement.model.dto;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import org.letsgettin.hospitalmanagement.model.entities.Role;

public class UserSignInDTO {

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
