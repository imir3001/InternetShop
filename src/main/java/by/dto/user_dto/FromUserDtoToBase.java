package by.dto.user_dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FromUserDtoToBase {
    String name;
    String birthday;
    String password;
    String status;
}
