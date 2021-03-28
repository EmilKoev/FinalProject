package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.AttributeDTO;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.pojo.User;
import technomarket.service.AttributeService;

import javax.servlet.http.HttpSession;

@RestController
public class AttributeController extends Controller{

    @Autowired
    private AttributeService service;

    @PutMapping("/attribute/{id}")
    public ProductAttribute addAttribute(@RequestBody AttributeDTO attributeDTO, @PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw new AuthenticationException("Only admins can do this!");
        }
        return service.addAttribute(attributeDTO, id);
    }

    @GetMapping("/attribute/{name}")
    public ProductAttribute getAttribute(@PathVariable String name){
        return service.getAttribute(name);
    }

    @DeleteMapping("/attribute/{name}/{id}")
    public String deleteAttribute(@PathVariable String name, @PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw new AuthenticationException("Only admins can do this!");
        }
        service.delete(name, id);
        return "Delete successful";
    }



}
