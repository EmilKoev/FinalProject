package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.*;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;
import technomarket.service.UserService;
import javax.servlet.http.HttpSession;

@RestController
public class UserController extends Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PutMapping("/user/register")
    public UserWithoutPassDTO register(@RequestBody RegisterRequestUserDTO userDTO,HttpSession session){
        if(sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You have to log out first!");
        }
        return userService.addUser(userDTO);
    }

    @GetMapping("/user")
    public UserWithoutPassDTO userInfo(HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return new UserWithoutPassDTO(user);
    }

    @PostMapping("/user/login")
    public UserWithoutPassDTO login(@RequestBody LoginDTO loginDTO, HttpSession session){
        if (sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You are already logged in!");
        }
        UserWithoutPassDTO user = userService.login(loginDTO);
        sessionManager.loginUser(session,user.getId());
        return user;
    }

    @PostMapping("/user/logout")
    public void logout(HttpSession session){
        if (!sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You are already logged out!");
        }
        sessionManager.logoutUser(session);
    }

    @PutMapping("/user/edit")
    public UserWithoutPassDTO edit(@RequestBody UserEditRequestDTO requestDto, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return userService.edit(requestDto,user);
    }

    @DeleteMapping("/user")
    public void delete(@RequestBody PasswordDTO password , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        userService.delete(password ,user);
    }

    @PutMapping("/cart/{id}")
    public OrderResponseDTO addProductToCart(@PathVariable(name = "id") int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(id);
        return userService.addProductToCart(user, product);
    }

    @DeleteMapping("/cart/{id}")
    public OrderResponseDTO removeProductFromCart(@PathVariable(name = "id") int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(id);
        return userService.removeProductFromCart(user,product);
    }

    @PostMapping("/cart")
    public OrderResponseDTO getProductsFromCart(HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return userService.getProductsFromCart(user);
    }

}
