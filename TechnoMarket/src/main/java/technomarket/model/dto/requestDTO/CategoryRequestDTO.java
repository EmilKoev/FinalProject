package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Setter
@Getter
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String name;

}
