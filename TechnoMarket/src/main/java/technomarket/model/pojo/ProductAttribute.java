package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeDTO;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
@IdClass(AttributeId.class)
@Table(name = "attributes")
public class ProductAttribute implements Serializable {

    @Id
    private String name;
    private String value;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product productId;

    public ProductAttribute(AttributeDTO attributeDTO, Product product){
        this.name = attributeDTO.getName();
        this.value = attributeDTO.getValue();
        this.productId = product;
    }


}
