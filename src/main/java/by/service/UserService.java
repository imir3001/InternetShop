package by.service;


import by.database.repository.UserRepository;
import by.dto.user_dto.FromDtoToUser;
import by.dto.user_dto.UserDto;
import by.mapper.classes.users.DtoToUser;
import lombok.RequiredArgsConstructor;
import by.mapper.classes.users.UserToDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final DtoToUser dtoToUser;
    private final UserToDto userToDto;
    private final UserRepository userRepository;

    public Optional<UserDto> login(String password) {
        Optional<UserDto> result = userRepository.findByPassword(password)
                .map(userToDto::mapFrom);
        if (result.isEmpty()) {
            log.warn("The password is not exist!");
        } else {
            log.info("The User with name {} was login", result.get().getName());
        }
        return result;
    }

    public Long save(FromDtoToUser fromDtoToUser) {
        var user = dtoToUser.mapFrom(fromDtoToUser);
        var result = userRepository.save(user);
        log.info("Attempt to save fromUserDtoToBase object in method save()");
        return result.getId();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
        log.info("Attempt to delete User object by your id, in method delete()");

    }

    public void deleteByPassword(String password){
       var user = userRepository.findByPassword(password).get();
       userRepository.deleteById(user.getId());
        log.info("Attempt to delete User object by your password, in method delete()");

    }

    public Optional<UserDto> findById(Long id){
        log.info("Attempt to extract UserDto object in method findById()");
        return userRepository.findById(id).map(userToDto::mapFrom);
    }
}
