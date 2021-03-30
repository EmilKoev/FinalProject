package technomarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.dto.productDTO.ProductResponseDTO;
import technomarket.model.dto.userDTO.UserWithoutPassDTO;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
public class OrderResponseDTO {

    private int id;
    private UserWithoutPassDTO user;
    private double price;
    private List<ProductResponseDTO> products;


    public OrderResponseDTO(Order order){
        id = order.getId();
        user = new UserWithoutPassDTO(order.getUser());
        price = order.getPrice();
        products = new ArrayList<>();
        for (Product p : order.getProducts()) {
            products.add(new ProductResponseDTO(p));
        }

    }
}
