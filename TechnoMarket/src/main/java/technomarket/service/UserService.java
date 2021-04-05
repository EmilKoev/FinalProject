package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.userDTO.LoginRequestDTO;
import technomarket.model.dto.requestDTO.PasswordRequestDTO;
import technomarket.model.dto.requestDTO.userDTO.UserRegisterRequestDTO;
import technomarket.model.dto.requestDTO.userDTO.UserEditRequestDTO;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.model.repository.OrderRepository;
import technomarket.model.repository.UserRepository;
import technomarket.utill.ValidationUtil;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ValidationUtil validationUtil;

    @Transactional
    public User addUser(UserRegisterRequestDTO userDTO){
        validationUtil.checkUser(userDTO);
        if(userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new BadRequestException("Email already exists");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        Order order = new Order();
        order.setAddress(user.getAddress());
        user = userRepository.save(user);
        order.setUser(user);
        order = orderRepository.save(order);
        user.setOrder(order);
        return user;
    }

    public User login(LoginRequestDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null){
            throw new AuthenticationException("Wrong credentials");
        }else {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(loginDTO.getPassword(), user.getPassword())){
                throw  new AuthenticationException("Wrong credentials");
            }else {
                return user;
            }
        }
    }


    public User edit(UserEditRequestDTO requestDto, User user) {
        validationUtil.checkUser(requestDto);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(requestDto.getOldPassword(),user.getPassword())){
            throw new AuthenticationException("Wrong credentials");
        }else {
            if (requestDto.getNewPassword() != null){
                user.setPassword(encoder.encode(requestDto.getNewPassword()));
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
            return user;
        }
    }

    public void delete(PasswordRequestDTO passwordDTO, User user) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(passwordDTO.getPassword(),user.getPassword())) {
            userRepository.delete(user);
        }else {
            throw new BadRequestException("Wrong password!");
        }
    }

    public Order addProductToCart(User user, Product product) {
        Order order = user.getOrder();
        order.getProducts().add(product);
        order.setPrice(user.getOrder().getPrice() + product.getPrice());
        orderRepository.save(order);
        return order;
    }

    public Order removeProductFromCart(User user, Product product) {
        Order order = user.getOrder();
        if (!order.getProducts().contains(product)){
            throw new BadRequestException("No product like this in cart!");
        }
        order.getProducts().remove(product);
        order.setPrice(user.getOrder().getPrice() - product.getPrice());
        orderRepository.save(order);
        return order;
    }

    public Order getProductsFromCart(User user) {
        return user.getOrder();
    }

    @Transactional
    public void finishOrder(User user) {
        if (user.getOrder().getProducts().size() == 0){
            throw new BadRequestException("You can not finish empty order!");
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.getOrder().setFinishedAt(LocalDateTime.now());
        orderRepository.save(user.getOrder());
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setUser(user);
        order = orderRepository.save(order);
        user.setOrder(order);
        user = userRepository.save(user);
        order.setUser(user);
        orderRepository.save(order);
    }
}
