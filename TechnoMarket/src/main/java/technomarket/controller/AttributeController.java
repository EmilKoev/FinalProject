package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.BadRequestException;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeRequestDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditAttributeRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
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
    public ProductAttribute addAttribute(@RequestBody AttributeRequestDTO attributeDTO, @PathVariable int productId, HttpSession session){
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
                                        ,@Valid @RequestBody EditAttributeRequestDTO editAttributeDTO
                                        , HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return service.edit(editAttributeDTO, productId);
    }

    @DeleteMapping("/attributes/{name}/{productId}")
    public MessageResponseDTO deleteAttribute(@PathVariable String name, @PathVariable int productId, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        if (name == null){
            throw new BadRequestException("name cannot be null");
        }
        service.delete(name, productId);
        return new MessageResponseDTO("Delete attribute successful");
    }



}
