package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
