package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import technomarket.model.dto.LoginDTO;
import technomarket.model.dto.RegisterRequestUserDTO;
import technomarket.model.dto.UserWithoutPassDTO;
import technomarket.service.UserService;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/register")
    public UserWithoutPassDTO register(@RequestBody RegisterRequestUserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PostMapping("/user/login")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginDTO, HttpSession session){
        UserWithoutPassDTO user = userService.login(loginDTO);
        session.setAttribute("LoggedUser", user.getId());
        return user;

    }


}
