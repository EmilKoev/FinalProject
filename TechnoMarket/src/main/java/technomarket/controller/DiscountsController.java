package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.DiscountRequestDTO;
import technomarket.model.dto.responseDTO.DiscountResponseDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.User;
import technomarket.service.DiscountService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DiscountsController extends Controller{

    @Autowired
    private DiscountService discountService;

    @PutMapping("/discounts")
    public DiscountResponseDTO addDiscount(@Valid @RequestBody DiscountRequestDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Discount discount = discountService.addDiscount(discountDTO);
        return new DiscountResponseDTO(discount);
    }

    @GetMapping("/discounts/{id}")
    public DiscountResponseDTO getDiscount(@PathVariable int id){
        Discount discount = discountService.getDiscount(id);
        return new DiscountResponseDTO(discount);
    }

    @PostMapping("/discounts/{id}")
    public DiscountResponseDTO editDiscount(@PathVariable int id, @RequestBody DiscountRequestDTO discountDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Discount discount = discountService.edit(discountDTO, id);
        return new DiscountResponseDTO(discount);
    }

    @DeleteMapping("/discounts/{id}")
    public MessageResponseDTO deleteDiscount(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        discountService.deleteDiscount(id);
        return new MessageResponseDTO("Delete discount successful");
    }

    @GetMapping("/discounts")
    public List<DiscountResponseDTO> getAllDiscounts(){
        List<Discount> discountList = discountService.getAll();
        List<DiscountResponseDTO> responseList = new ArrayList<>();
        for (Discount discount : discountList) {
            responseList.add(new DiscountResponseDTO(discount));
        }
        return responseList;
    }

}
