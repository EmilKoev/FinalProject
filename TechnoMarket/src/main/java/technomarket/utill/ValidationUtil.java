package technomarket.utill;

import org.springframework.stereotype.Component;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditProductDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.ProductDTO;
import technomarket.model.dto.requestDTO.userDTO.RegisterRequestUserDTO;
import technomarket.model.dto.requestDTO.userDTO.UserEditRequestDTO;

@Component
public class ValidationUtil {
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b" +
            "\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9]" +
            "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)" +
            "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f" +
            "\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private static final String PHONE_REGEX ="^((\\+)|(00)|(\\*)|())[0-9]{9,14}((\\#)|())$";
    private static final String NAME_REGEX = "^[a-zA-Z._-]{3,}$";
    public static final Integer ADDRESS_LENGTH = 6;
    public static final String INVALID_ADDRESS = "Address must be at least " + ADDRESS_LENGTH + " symbols";
    private static final String PASSWORD_REQUIREMENTS = "Password must have one upper and lower letter, " +
            "one number and no spaces and it must be at least 8 symbols";
    private static final String INVALID_LAST_NAME = "Invalid last name";
    private static final String INVALID_FIRST_NAME = "Invalid first name";
    private static final String INVALID_EMAIL = "Invalid email";
    private static final String INVALID_PHONE_NUMBER = "Invalid phone number";
    private static final String PASSWORDS_DO_NOT_MATCH = "New password and confirm password do not match";

    public void checkUser(RegisterRequestUserDTO user) {
        checkEmail(user.getEmail());
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkPassword(user.getPassword());
        checkConfirmPassword(user.getPassword(), user.getConfirmPassword());
        if (user.getPhone() != null) {
            checkPhone(user.getPhone());
        }
        if (user.getAddress() != null) {
            checkAddress(user.getAddress());
        }
    }

    public void checkUser(UserEditRequestDTO user){
        checkEmail(user.getEmail());
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkPassword(user.getNewPassword());
        checkConfirmPassword(user.getNewPassword(), user.getConfirmNewPassword());
            if (user.getPhone() != null) {
                checkPhone(user.getPhone());
            }
            if (user.getAddress() != null) {
                checkAddress(user.getAddress());
            }
    }

    private void checkAddress(String address) {
        if(address.length() < ADDRESS_LENGTH){
            throw new BadRequestException(INVALID_ADDRESS);
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

    private void checkEmail(String email){
        if(!email.matches(EMAIL_REGEX)){
            throw new BadRequestException(INVALID_EMAIL);
        }
    }
}