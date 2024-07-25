package pl.pingwit.basic_spring.validators;

import org.springframework.stereotype.Component;
import pl.pingwit.basic_spring.controller.user.CreateUserInputDto;
import pl.pingwit.basic_spring.exception.ValidationException;
import pl.pingwit.basic_spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\d+");

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(CreateUserInputDto input) {
        List<String> errors = new ArrayList<>();

        if (input.getName().isBlank()) {
            errors.add("User name is blank");
        }

        if (input.getSurname().isBlank()) {
            errors.add("User surname is blank");
        }

        if (!input.getEmail().matches(EMAIL_PATTERN.pattern())) {
            errors.add("Email is invalid: " + input.getEmail());
        }

        if (!input.getPhone().matches(PHONE_NUMBER_PATTERN.pattern())) {
            errors.add("Phone must contain only number: " + input.getPhone());
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("User data is invalid", errors);
        }
    }
}

//    private void validateUniqueEmail(UserDto userDto) {
//        List<String> allByEmail = userRepository.findAllEmails();
//        if (!allByEmail.isEmpty()) {
//            errors.add(String.format("email %s is already used in the system. please choose a different one!", userDto.getEmail()));
//        }
//    }
//}