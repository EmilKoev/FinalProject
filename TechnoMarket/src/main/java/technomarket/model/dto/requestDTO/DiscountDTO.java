package technomarket.model.dto.requestDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
public class DiscountDTO {

    @NotEmpty(message = "Title cannot be null or empty")
    private String title;
    @JsonProperty("discount_percent")
    @Min(value = 0, message = "Discount Percent must be a positive number")
    private int discountPercent;
    @JsonProperty("start_at")
    //TODO validation
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startAt;
    @JsonProperty("end_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endAt;

}
