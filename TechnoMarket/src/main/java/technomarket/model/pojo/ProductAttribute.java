package technomarket.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.AttributeDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "attributes")
public class ProductAttribute {

    @Id
    private String name;
    private String value;

    private int productId;

    public ProductAttribute(AttributeDTO attributeDTO, int productId){
        this.name = attributeDTO.getName();
        this.value = attributeDTO.getValue();
        this.productId = productId;
    }

}
