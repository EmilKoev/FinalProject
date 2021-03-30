package technomarket.model.dto.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Order;
import technomarket.model.pojo.Product;
import technomarket.model.repository.OrderRepository;

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
    private List<ResponseProductDTO> products;


    public OrderResponseDTO(Order order){
        id = order.getId();
        price = 0;
        for (Product p : order.getProducts()) {
            price += p.getPrice();
        }
        products = new ArrayList<>();
        for (Product p : order.getProducts()) {
            products.add(new ResponseProductDTO(p));
        }
        user = new UserWithoutPassDTO(order.getUser());

    }
}
