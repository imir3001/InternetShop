package by.dto.user_dto;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

@Value
@Builder
public class FromDtoToUser {
    String name;
    String birthday;
    String password;
    String status;
}
