package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Setter
@Getter
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotEmpty(message = "Name cannot be null ot empty!")
    private String name;

}
