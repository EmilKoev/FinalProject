package technomarket.utill;

import org.springframework.stereotype.Component;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.userDTO.UserRegisterRequestDTO;
import technomarket.model.dto.requestDTO.userDTO.UserEditRequestDTO;

@Component
public class ValidationUtil {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static final String PHONE_REGEX ="^((\\+)|(00)|(\\*)|())[0-9]{9,14}((\\#)|())$";
    private static final String NAME_REGEX = "^[a-zA-Z._-]{3,}$";
    private static final String PASSWORD_REQUIREMENTS = "Password must have one upper and lower letter, " +
            "one number and no spaces and it must be at least 8 symbols";
    private static final String INVALID_LAST_NAME = "Invalid last name";
    private static final String INVALID_FIRST_NAME = "Invalid first name";
    private static final String INVALID_PHONE_NUMBER = "Invalid phone number";
    private static final String PASSWORDS_DO_NOT_MATCH = "New password and confirm password do not match";

    public void checkUser(UserRegisterRequestDTO user) {
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkPassword(user.getPassword());
        checkConfirmPassword(user.getPassword(), user.getConfirmPassword());
        if (user.getPhone() != null) {
            checkPhone(user.getPhone());
        }
    }

    public void checkUser(UserEditRequestDTO user){
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkPassword(user.getNewPassword());
        checkConfirmPassword(user.getNewPassword(), user.getConfirmNewPassword());
            if (user.getPhone() != null) {
                checkPhone(user.getPhone());
            }
    }

    private void checkPhone(String phone) {
        if(!phone.matches(PHONE_REGEX)){
            throw new BadRequestException(INVALID_PHONE_NUMBER);
        }
    }

    private void checkConfirmPassword(String password, String confirmPassword) {
        if(!password.equals(confirmPassword)){
            throw new BadRequestException(PASSWORDS_DO_NOT_MATCH);
        }
    }

    private void checkPassword(String password) {
        if(!password.matches(PASSWORD_REGEX)){
            throw new BadRequestException(PASSWORD_REQUIREMENTS);
        }
    }

    private void checkLastName(String lastName) {
        if(!lastName.matches(NAME_REGEX)){
            throw new BadRequestException(INVALID_LAST_NAME);
        }
    }

    private void checkFirstName(String firstName) {
        if(!firstName.matches(NAME_REGEX)){
            throw new BadRequestException(INVALID_FIRST_NAME);
        }
    }
}