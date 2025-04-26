package orion.userservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orion.userservice.dto.UserDto;
import orion.userservice.entity.User;
import orion.userservice.repository.UserRepository;
import orion.userservice.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public abstract class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository repository){
        this.userRepository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, UserDto userDto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstname(userDto.getFirstname());
                    existingUser.setLastname(userDto.getLastname());
                    existingUser.setRole(userDto.getRole());
                    existingUser.setBirthday(userDto.getBirthday());
                    existingUser.setPhone(userDto.getPhone());
                    existingUser.setAddress(userDto.getAddress());

                    User updatedUser = userRepository.save(existingUser);

                    return convertToDto(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }


    private UserDto convertToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setRole(user.getRole());
        dto.setBirthday(user.getBirthday());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        return dto;
    }

    private User convertToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setRole(userDto.getRole());
        user.setBirthday(userDto.getBirthday());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());

        return user;
    }
}
