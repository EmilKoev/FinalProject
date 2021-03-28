package technomarket.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.*;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.model.repository.OrderRepository;
import technomarket.model.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public UserWithoutPassDTO addUser(RegisterRequestUserDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new BadRequestException("Email already exists");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new BadRequestException("Passwords don't match");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);
        Order order = new Order(user);
        user.setOrder(order);
        user = userRepository.save(user);
        return new UserWithoutPassDTO(user);
    }

    public UserWithoutPassDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null){
            throw new AuthenticationException("Wrong credentials");
        }else {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(loginDTO.getPassword(), user.getPassword())){
                throw  new AuthenticationException("Wrong credentials");
            }else {
                return new UserWithoutPassDTO(user);
            }
        }

    }

    public UserWithoutPassDTO edit(UserEditRequestDTO requestDto, User user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(requestDto.getOldPassword(),user.getPassword())){
            throw new AuthenticationException("Wrong credentials");
        }else {
            if (requestDto.getNewPassword() != null){
                user.setPassword(encoder.encode(requestDto.getNewPassword()));
            }
            if (requestDto.getFirstName() == null || requestDto.getLastName() == null || requestDto.getEmail() == null){
                throw new BadRequestException("first name, last name and email is are required");
            }
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            if(!requestDto.getEmail().equals(user.getEmail())){
                if (userRepository.findByEmail(requestDto.getEmail()) != null) {
                    throw new BadRequestException("Email already exists");
                }
            }
            user.setEmail(requestDto.getEmail());
            user.setAddress(requestDto.getAddress());
            user.setPhone(requestDto.getPhone());
            user.setSubscribed(requestDto.isSubscribed());
            userRepository.save(user);
            return new  UserWithoutPassDTO(user);
        }
    }

    public void delete(PasswordDTO passwordDTO, User user) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(passwordDTO.getPassword(),user.getPassword())) {
            userRepository.delete(user);
        }else {
            throw new BadRequestException("Wrong password!");
        }
    }

    public List<Product> addProductToCart(User user, Product product) {
        user.getOrder().getProducts().add(product);
        orderRepository.save(user.getOrder());
        return orderRepository.getByUserId(user.getId()).getProducts();
    }
}
