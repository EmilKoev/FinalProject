package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.AttributeDTO;
import technomarket.model.dto.EditAttributeDTO;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.pojo.User;
import technomarket.service.AttributeService;

import javax.servlet.http.HttpSession;

@RestController
public class AttributeController extends Controller{

    @Autowired
    private AttributeService service;

    @PutMapping("/attributes/{productId}")
    public ProductAttribute addAttribute(@RequestBody AttributeDTO attributeDTO, @PathVariable int productId, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw new AuthenticationException("Only admins can do this!");
        }
        return service.addAttribute(attributeDTO, productId);
    }

    @GetMapping("/attributes/{name}/{productId}")
    public ProductAttribute getAttribute(@PathVariable String name, @PathVariable int productId){
        return service.getAttribute(name, productId);
    }

    @PostMapping("/attributes/{productId}")
    public ProductAttribute editAttribute(@PathVariable int productId
                                        , @RequestBody EditAttributeDTO editAttributeDTO
                                        , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw new AuthenticationException("Only admins can do this!");
        }
        return service.edit(editAttributeDTO, productId);
    }

    @DeleteMapping("/attributes/{name}/{productId}")
    public String deleteAttribute(@PathVariable String name, @PathVariable int productId, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw new AuthenticationException("Only admins can do this!");
        }
        service.delete(name, productId);
        return "Delete successful";
    }



}
