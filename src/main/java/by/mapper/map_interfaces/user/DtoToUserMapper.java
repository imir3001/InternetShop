package by.mapper.map_interfaces.user;

import by.database.entity.User;
import by.dto.user_dto.FromDtoToUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToUserMapper {
    DtoToUserMapper INSTANCE = Mappers.getMapper(DtoToUserMapper.class);

    User mapFrom(FromDtoToUser userDto);
}
