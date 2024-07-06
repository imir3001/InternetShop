package by.dto.product_dto;

import by.database.entity.Category;
import by.database.entity.Supplier;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;

@Value
@Builder
@Validated
public class FromProductDtoToBase {
    Supplier supplier;
    String name;

    @PositiveOrZero
    Long count;

    @PositiveOrZero
    Long priceForOne;
    Category category;
}
