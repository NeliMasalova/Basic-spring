package pl.pingwit.basic_spring.validators;

import org.springframework.stereotype.Component;
import pl.pingwit.basic_spring.controller.product.CreateProductInputDto;
import pl.pingwit.basic_spring.exception.ValidationException;
import pl.pingwit.basic_spring.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ProductValidator {
    private static final Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");
    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateProduct(CreateProductInputDto input) {
        List<String> errors = new ArrayList<>();
        if (input.getName().isBlank() || !input.getName().matches(ONLY_LETTERS_PATTERN.pattern())){
            errors.add("Product name is invalid");
        }
        if (input.getDescription().isBlank() || !input.getDescription().matches(ONLY_LETTERS_PATTERN.pattern())){
            errors.add("Product description is invalid");
        }
        if (input.getPrice().compareTo(BigDecimal.ZERO) <= 0){
            errors.add("Product price is invalid. ");
        }
        if (!errors.isEmpty()){
            throw new ValidationException("Product data is invalid", errors);
        }
    }
}