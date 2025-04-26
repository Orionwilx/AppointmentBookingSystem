package orion.userservice.service;

import orion.userservice.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    List<UserDto> findAllUsers();
    Optional<UserDto> findUserById(UUID id);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UUID id, UserDto userDto);
    void deleteUser(UUID id);



}
