package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Component
@NoArgsConstructor
public class CommentRequestDTO {

    @NotBlank(message = "Comment" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String comment;

}
