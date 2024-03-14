package by.mapper.map_interfaces.user;

import by.database.entity.User;
import by.database.entity.UserStatus;
import by.dto.user_dto.FromUserDtoToBase;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T22:24:47+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToUserMapperImpl implements DtoToUserMapper {

    @Override
    public User mapFrom(FromUserDtoToBase userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userDto.getName() );
        if ( userDto.getBirthday() != null ) {
            user.birthday( LocalDate.parse( userDto.getBirthday() ) );
        }
        user.password( userDto.getPassword() );
        if ( userDto.getStatus() != null ) {
            user.status( Enum.valueOf( UserStatus.class, userDto.getStatus() ) );
        }

        return user.build();
    }
}
