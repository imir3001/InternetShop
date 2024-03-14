package by.dto.user_dto;

import by.database.entity.UserStatus;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Long id;
    String name;
    LocalDate birthday;
    UserStatus status;
}
