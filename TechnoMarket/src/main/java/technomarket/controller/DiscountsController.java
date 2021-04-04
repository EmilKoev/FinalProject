package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.DiscountRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.User;
import technomarket.service.DiscountService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DiscountsController extends Controller{

    @Autowired
    private DiscountService discountService;

    @PutMapping("/discounts")
    public Discount addDiscount(@Valid @RequestBody DiscountRequestDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return discountService.addDiscount(discountDTO);
    }

    @GetMapping("/discounts/{id}")
    public Discount getDiscount(@PathVariable int id){
        return discountService.getDiscount(id);
    }

    @PostMapping("/discounts/{id}")
    public Discount editDiscount(@PathVariable int id, @RequestBody DiscountRequestDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return discountService.edit(discountDTO, id);
    }

    @DeleteMapping("/discounts/{id}")
    public MessageResponseDTO deleteDiscount(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        discountService.deleteDiscount(id);
        return new MessageResponseDTO("Delete discount successful");
    }

    @GetMapping("/discounts")
    public List<Discount> getAllDiscounts(){
        return discountService.getAll();
    }

}
