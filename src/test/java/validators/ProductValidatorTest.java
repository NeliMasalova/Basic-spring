package validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pingwit.basic_spring.controller.product.CreateProductInputDto;
import pl.pingwit.basic_spring.exception.ValidationException;
import pl.pingwit.basic_spring.repository.ProductRepository;
import pl.pingwit.basic_spring.validators.ProductValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductValidatorTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductValidator productValidator;

    private CreateProductInputDto validInput;
    private CreateProductInputDto invalidNameInput;
    private CreateProductInputDto invalidDescriptionInput;
    private CreateProductInputDto invalidPriceInput;

    @BeforeEach
    void setUp() {
        validInput = new CreateProductInputDto("ValidName", "ValidDescription", BigDecimal.valueOf(10));
        invalidNameInput = new CreateProductInputDto("123Invalid", "ValidDescription", BigDecimal.valueOf(10));
        invalidDescriptionInput = new CreateProductInputDto("ValidName", "", BigDecimal.valueOf(10));
        invalidPriceInput = new CreateProductInputDto("ValidName", "ValidDescription", BigDecimal.valueOf(-1));
    }

    @Test
    void validateProduct_withValidInput_shouldNotThrowException() {
        productValidator.validateProduct(validInput);
    }

    @Test
    void validateProduct_withInvalidName_shouldThrowValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> productValidator.validateProduct(invalidNameInput));
        List<String> errors = exception.getErrors();
        assert (errors.contains("Product name is invalid"));
    }

    @Test
    void validateProduct_withInvalidDescription_shouldThrowValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> productValidator.validateProduct(invalidDescriptionInput));
        List<String> errors = exception.getErrors();
        assert (errors.contains("Product description is invalid"));
    }

    @Test
    void validateProduct_withInvalidPrice_shouldThrowValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> productValidator.validateProduct(invalidPriceInput));
        List<String> errors = exception.getErrors();
        assert (errors.contains("Product price is invalid. "));
    }
}