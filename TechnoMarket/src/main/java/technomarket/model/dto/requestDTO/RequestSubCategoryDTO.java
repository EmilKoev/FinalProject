package technomarket.model.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
public class RequestSubCategoryDTO {

    @JsonProperty("category_id")
    private int categoryId;
    private String name;

}
