package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import technomarket.model.dto.requestDTO.DiscountDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.User;
import technomarket.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService{

    private static final String EMAIL_SUBJECT = "One of your favourite items on Emag has been discounted! Check it out!";
    private static final String URL = "78.90.70.28::7878/discounts/";

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserRepository userRepository;

    public void sendMessage(Discount discount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("technomarketteam@gmail.com");
        message.setTo(getRecipients());
        message.setSubject(EMAIL_SUBJECT);
        message.setText(createMailText(discount));
        emailSender.send(message);
        System.out.println("Message sent successfully");
    }

    private String[] getRecipients(){
        List<User> users = userRepository.findAll();
        List<String> emails = new ArrayList<>();
        users.removeIf(user -> !user.isSubscribed());
        users.forEach(user -> emails.add(user.getEmail()));
        return emails.toArray(new String[0]);
    }

    private String createMailText(Discount discount){
        StringBuilder text = new StringBuilder("There is a new discount \"")
                .append(discount.getTitle())
                .append("\" with ")
                .append(discount.getDiscountPercent())
                .append(" discount percent starting at: ")
                .append(discount.getStartAt())
                .append(" and end at: ")
                .append(discount.getEndAt())
                .append("!!!\n")
                .append("Don't waste time and get our awesome offers now!!!\n")
                .append("You can see more abouth this discount and all reduced products on: \n")
                .append(URL).append(discount.getId());

        return text.toString();
    }
}
