package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.userDTO.LoginRequestDTO;
import technomarket.model.dto.requestDTO.PasswordRequestDTO;
import technomarket.model.dto.requestDTO.userDTO.UserRegisterRequestDTO;
import technomarket.model.dto.requestDTO.userDTO.UserEditRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.dto.responseDTO.OrderResponseDTO;
import technomarket.model.dto.responseDTO.UserWithoutPassResponseDTO;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;
import technomarket.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController extends Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PutMapping("/user/register")
    public UserWithoutPassResponseDTO register(@Valid @RequestBody UserRegisterRequestDTO userDTO, HttpSession session){
        if(sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You have to log out first!");
        }
        User user = userService.addUser(userDTO);
        return new UserWithoutPassResponseDTO(user);
    }

    @GetMapping("/user")
    public UserWithoutPassResponseDTO userInfo(HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return new UserWithoutPassResponseDTO(user);
    }

    @PostMapping("/user/login")
    public UserWithoutPassResponseDTO login(@Valid @RequestBody LoginRequestDTO loginDTO, HttpSession session){
        if (sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You are already logged in!");
        }
        User user = userService.login(loginDTO);
        sessionManager.loginUser(session,user.getId());
        return new UserWithoutPassResponseDTO(user);
    }

    @PostMapping("/user/logout")
    public MessageResponseDTO logout(HttpSession session){
        if (!sessionManager.isSomeoneLoggedIn(session)){
            throw new BadRequestException("You are already logged out!");
        }
        sessionManager.logoutUser(session);
        return new MessageResponseDTO("You are logged out!");
    }

    @PutMapping("/user/edit")
    public UserWithoutPassResponseDTO edit(@Valid @RequestBody UserEditRequestDTO requestDto, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        user = userService.edit(requestDto,user);
        return new UserWithoutPassResponseDTO(user);
    }

    @DeleteMapping("/user")
    public MessageResponseDTO delete(@Valid @RequestBody PasswordRequestDTO password , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        sessionManager.logoutUser(session);
        userService.delete(password ,user);
        return new MessageResponseDTO("Delete successfully");
    }

    @PutMapping("/cart/{id}")
    public OrderResponseDTO addProductToCart(@PathVariable(name = "id") int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(id);
        Order order = userService.addProductToCart(user, product);
        return new OrderResponseDTO(order);
    }

    @DeleteMapping("/cart/{id}")
    public OrderResponseDTO removeProductFromCart(@PathVariable(name = "id") int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(id);
        Order order = userService.removeProductFromCart(user,product);
        return new OrderResponseDTO(order);
    }

    @PostMapping("/cart")
    public OrderResponseDTO getProductsFromCart(HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Order order = userService.getProductsFromCart(user);
        return new OrderResponseDTO(order);
    }

}
