package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeRequestDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
@IdClass(AttributeId.class)
@Table(name = "attributes")
public class ProductAttribute implements Serializable {

    @Id
    @NotEmpty(message = "Name cannot be null ot empty!")
    private String name;
    @NotEmpty(message = "Value cannot be null ot empty!")
    private String value;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product productId;

    public ProductAttribute(AttributeRequestDTO attributeDTO, Product product){
        this.name = attributeDTO.getName();
        this.value = attributeDTO.getValue();
        this.productId = product;
    }
}
