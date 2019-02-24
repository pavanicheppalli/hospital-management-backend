package org.letsgettin.hospitalmanagement.controller;



import io.swagger.annotations.*;
import jdk.nashorn.internal.objects.NativeArray;
import org.letsgettin.hospitalmanagement.model.dto.UserSignUpDTO;
import org.letsgettin.hospitalmanagement.model.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.letsgettin.hospitalmanagement.model.dto.UserSignInDTO;
import org.letsgettin.hospitalmanagement.services.UserService;

import java.util.HashMap;


@RestController
@RequestMapping("/user")
@Api(value="User", description="Operations related User")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Let the user to signin",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Signed In"),
            @ApiResponse(code = 404, message = "The user not found")
    }
    )
    @RequestMapping(value = "/signin", method= RequestMethod.POST, produces = "application/json")
    public HashMap<String, String>  SignIn(
        @RequestBody UserSignInDTO userSignInDTO
    ){
        HashMap<String, String> resp = new HashMap<>();
        resp.put("token", this.userService.signin(userSignInDTO.getUsername(), userSignInDTO.getPassword()));
        return resp;
    }


    @ApiOperation(value = "Let the user to Sign up",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Signed Up"),
            @ApiResponse(code = 404, message = "Something went wrong")
    }
    )
    @RequestMapping(value = "/signup", method= RequestMethod.POST, produces = "application/json")
    public HashMap<String, String> SignUp(
            @RequestBody UserSignUpDTO userSignUpDTO
    ){
        HashMap<String, String> resp = new HashMap<>();
        User user = new User();
        user.setUsername(userSignUpDTO.getUsername());
        user.setPassword(userSignUpDTO.getPassword());
        resp.put("token", this.userService.signupUser(user));
        return resp;
    }

}
