package validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pingwit.basic_spring.controller.user.CreateUserInputDto;
import pl.pingwit.basic_spring.exception.ValidationException;
import pl.pingwit.basic_spring.repository.UserRepository;
import pl.pingwit.basic_spring.validators.UserValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
   @Mock
   private UserRepository userRepository;
   @InjectMocks
   private UserValidator userValidator;

   private CreateUserInputDto validInput;
   private CreateUserInputDto invalidNameInput;
   private CreateUserInputDto invalidSurnameInput;
   private CreateUserInputDto invalidEmailInput;
   private CreateUserInputDto invalidPhoneInput;

   @BeforeEach
   void setUp(){
      validInput = new CreateUserInputDto("Paweł", "Stankewicz", "pawełstankiewicz@gmail.com", "797777626");
      invalidNameInput = new CreateUserInputDto("Paweł123", "Stankewicz", "pawełstankiewicz@gmail.com", "797777626");
      invalidSurnameInput = new CreateUserInputDto("Paweł", "Stankewicz123", "pawełstankiewicz@gmail.com", "797777626");
      invalidEmailInput = new CreateUserInputDto("Paweł", "Stankewicz", "pawełstankiewicz.gmail.com", "797777626");
      invalidPhoneInput = new CreateUserInputDto("Paweł", "Stankewicz", "pawełstankiewicz@gmail.com", "a797777626");
   }
   @Test
   void validateUser_withValidInput_shouldNoThrowValidationException(){
      userValidator.validateUser(validInput);
   }
   @Test
   void validateUser_withInvalidNameInput_shouldThrowValidationException(){
      ValidationException exception = assertThrows(ValidationException.class, () -> userValidator.validateUser(invalidNameInput));
      List<String> errors = exception.getErrors();
      assert(errors.contains("User name is invalid"));
   }
   @Test
   void validateUser_withInvalidSurnameInput_shouldThrowValidationException(){
      ValidationException exception = assertThrows(ValidationException.class, () -> userValidator.validateUser(invalidSurnameInput));
      List<String> errors = exception.getErrors();
      assert(errors.contains("User surname is invalid"));
   }
   @Test
   void validateUser_withInvalidEmailInput_shouldThrowValidationException(){
      ValidationException exception = assertThrows(ValidationException.class, () -> userValidator.validateUser(invalidEmailInput));
      List<String> errors = exception.getErrors();
      assert(errors.contains("User email is invalid"));
   }
   @Test
   void validateUser_withInvalidPhoneInput_shouldThrowValidationException(){
      ValidationException exception = assertThrows(ValidationException.class, () -> userValidator.validateUser(invalidPhoneInput));
      List<String> errors = exception.getErrors();
      assert(errors.contains("User phone is invalid"));
   }

}