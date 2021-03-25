package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.LoginDTO;
import technomarket.model.dto.RegisterRequestUserDTO;
import technomarket.model.dto.RegisterResponseUserDTO;
import technomarket.model.dto.UserWithoutPassDTO;
import technomarket.model.pojo.User;
import technomarket.model.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public RegisterResponseUserDTO addUser(RegisterRequestUserDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new BadRequestException("Email already exists");
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = new User(userDTO);
        user = userRepository.save(user);
        RegisterResponseUserDTO responseUserDTO = new RegisterResponseUserDTO(user);
        return responseUserDTO;
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
}
