package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.userDTO.LoginDTO;
import technomarket.model.dto.requestDTO.PasswordDTO;
import technomarket.model.dto.requestDTO.userDTO.RegisterRequestUserDTO;
import technomarket.model.dto.requestDTO.userDTO.UserEditRequestDTO;
import technomarket.model.dto.responseDTO.OrderResponseDTO;
import technomarket.model.dto.responseDTO.UserWithoutPassDTO;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.model.repository.OrderRepository;
import technomarket.model.repository.UserRepository;
import technomarket.utill.ValidationUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ValidationUtil validationUtil;

    public UserWithoutPassDTO addUser(RegisterRequestUserDTO userDTO){
        validationUtil.checkUser(userDTO);
        if(userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new BadRequestException("Email already exists");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        Order order = new Order();
        order = orderRepository.save(order);
        user.setOrder(order);
        user = userRepository.save(user);
        order.setUser(user);
        order.setAddress(user.getAddress());
        orderRepository.save(order);

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

    public OrderResponseDTO addProductToCart(User user, Product product) {
        user.getOrder().getProducts().add(product);
        user.getOrder().setPrice(user.getOrder().getPrice() + product.getPrice());
        orderRepository.save(user.getOrder());
        return new OrderResponseDTO(orderRepository.getByUserId(user.getId()));
    }

    public OrderResponseDTO removeProductFromCart(User user, Product product) {
        if (!user.getOrder().getProducts().contains(product)){
            throw new BadRequestException("No product like this in cart!");
        }
        user.getOrder().getProducts().remove(product);
        user.getOrder().setPrice(user.getOrder().getPrice() - product.getPrice());
        orderRepository.save(user.getOrder());
        return new OrderResponseDTO(orderRepository.getByUserId(user.getId()));
    }

    public OrderResponseDTO getProductsFromCart(User user) {
        return new OrderResponseDTO(orderRepository.getByUserId(user.getId()));
    }
}
