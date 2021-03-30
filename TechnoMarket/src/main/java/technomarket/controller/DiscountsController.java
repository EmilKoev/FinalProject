package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.DiscountDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.User;
import technomarket.service.DiscountService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DiscountsController extends Controller{

    @Autowired
    private DiscountService discountService;

    @PutMapping("/discounts")
    public Discount addDiscount(@RequestBody DiscountDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return discountService.addDiscount(discountDTO);
    }

    @GetMapping("/discounts/{id}")
    public Discount getDiscount(@PathVariable int id){
        return discountService.getDiscount(id);
    }

    @PostMapping("/discounts/{id}")
    public Discount editDiscount(@PathVariable int id, @RequestBody DiscountDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return discountService.edit(discountDTO, id);
    }

    @DeleteMapping("/discounts/{id}")
    public String deleteDiscount(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        discountService.deleteDiscount(id);
        return "Delete successful";
    }

    @GetMapping("/discounts")
    public List<Discount> getAllDiscounts(){
        return discountService.getAll();
    }

}
