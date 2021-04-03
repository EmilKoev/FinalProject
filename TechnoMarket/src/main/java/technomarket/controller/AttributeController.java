package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditAttributeDTO;
import technomarket.model.dto.responseDTO.MessageDTO;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.pojo.User;
import technomarket.service.AttributeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AttributeController extends Controller{

    @Autowired
    private AttributeService service;

    @PutMapping("/attributes/{productId}")
    public ProductAttribute addAttribute(@RequestBody AttributeDTO attributeDTO, @PathVariable int productId, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return service.addAttribute(attributeDTO, productId);
    }

    @GetMapping("/attributes/{name}/{productId}")
    public ProductAttribute getAttribute(@PathVariable String name, @PathVariable int productId){
        if (name == null){
            throw new BadRequestException("name cannot be null");
        }
        return service.getAttribute(name, productId);
    }

    @PostMapping("/attributes/{productId}")
    public ProductAttribute editAttribute(@PathVariable int productId
                                        ,@Valid @RequestBody EditAttributeDTO editAttributeDTO
                                        , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return service.edit(editAttributeDTO, productId);
    }

    @DeleteMapping("/attributes/{name}/{productId}")
    public MessageDTO deleteAttribute(@PathVariable String name, @PathVariable int productId, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        if (name == null){
            throw new BadRequestException("name cannot be null");
        }
        service.delete(name, productId);
        return new MessageDTO("Delete attribute successful");
    }



}
