package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.*;
import technomarket.model.pojo.User;
import technomarket.service.UserService;
import javax.servlet.http.HttpSession;

@RestController
public class UserController extends Controller {

    @Autowired
    private UserService userService;

    @PutMapping("/user/register")
    public UserWithoutPassDTO register(@RequestBody RegisterRequestUserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PostMapping("/user/login")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginDTO, HttpSession session){
        UserWithoutPassDTO user = userService.login(loginDTO);
        sessionManager.loginUser(session,user.getId());
        return user;
    }

    @PostMapping("/user/logout")
    public void logout(HttpSession session){
        sessionManager.logoutUser(session);
    }

    @PostMapping("/user/edit")
    public UserWithoutPassDTO edit(@RequestBody UserEditRequestDTO requestDto, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return userService.edit(requestDto,user);
    }

    @DeleteMapping("/user")
    public void delete(@RequestBody PasswordDTO password , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        userService.delete(password ,user);
    }
}
