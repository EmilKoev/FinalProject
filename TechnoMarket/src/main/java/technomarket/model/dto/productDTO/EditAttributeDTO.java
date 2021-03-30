package technomarket.model.dto.productDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class EditAttributeDTO {

    private String name;
    @JsonProperty("new_name")
    private String newName;
    @JsonProperty("new_value")
    private String newValue;

}
