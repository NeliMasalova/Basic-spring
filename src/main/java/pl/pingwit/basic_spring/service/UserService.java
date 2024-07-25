package pl.pingwit.basic_spring.service;

import org.springframework.stereotype.Service;
import pl.pingwit.basic_spring.controller.user.CreateUserInputDto;
import pl.pingwit.basic_spring.controller.user.UpdateUserInputDto;
import pl.pingwit.basic_spring.controller.user.User;
import pl.pingwit.basic_spring.controller.user.UserDto;
import pl.pingwit.basic_spring.repository.UserRepository;
import pl.pingwit.basic_spring.validators.UserValidator;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<UserDto> findAll() {
        return userRepository.findAllUsers().stream()
                .map(user -> new UserDto(user.id(), user.name() + " " + user.surname(), user.email()))
                .toList();
    }
    public List<UserDto> findAllByEmail() {
        return userRepository.findAllUsers().stream()
                .map(user -> new UserDto(user.id(), user.name() + " " + user.surname(), user.email()))
                .toList();
    }

    public UserDto findUserById(Integer id) {
        Optional<User> userById = userRepository.findUserById(id);
        return userById.map(user -> new UserDto(user.id(), user.name() + " " + user.surname(), user.email()))
                .orElseThrow(() -> new IllegalArgumentException("User not found, sorry."));
    }

    public Integer createUser(CreateUserInputDto input) {
        userValidator.validateUser(input);
        User user = new User(null, input.getName(), input.getSurname(), input.getEmail(), input.getPhone());
        return userRepository.createUser(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUserById(id);
    }

    public void updateUser(Integer id, UpdateUserInputDto inputDto) {
        User existingUser = userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User userToUpdate = new User(existingUser.id(),
                existingUser.name(),
                inputDto.getSurname(),
                inputDto.getEmail(),
                inputDto.getPhone());

        userRepository.updateUser(userToUpdate);
    }
}