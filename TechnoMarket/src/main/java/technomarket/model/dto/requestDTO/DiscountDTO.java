package technomarket.model.dto.requestDTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
public class DiscountDTO {

    private String title;
    @JsonProperty("discount_percent")
    private int discountPercent;
    @JsonProperty("start_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startAt;
    @JsonProperty("end_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endAt;

}
