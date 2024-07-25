package pl.pingwit.basic_spring.service;

import org.springframework.stereotype.Service;
import pl.pingwit.basic_spring.controller.product.CreateProductInputDto;
import pl.pingwit.basic_spring.controller.product.Product;
import pl.pingwit.basic_spring.controller.product.ProductDto;
import pl.pingwit.basic_spring.controller.product.UpdateProductInputDto;
import pl.pingwit.basic_spring.repository.ProductRepository;
import pl.pingwit.basic_spring.validators.ProductValidator;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    public List<ProductDto> findAll() {
        return productRepository.findAllProducts().stream()
                .map(product -> new ProductDto(product.id(), product.name(), product.price()))
                .toList();
    }

    public ProductDto findProductById(Integer id) {
        Optional<Product> productById = productRepository.findProductById(id);
        return productById.map(product -> new ProductDto(product.id(), product.name(), product.price()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public Integer createProduct(CreateProductInputDto input) {
        productValidator.validateProduct(input);
        Product product = new Product(null, input.getName(), input.getDescription(), input.getPrice());
        return productRepository.createProduct(product);
    }

    public void updateProduct(Integer id, UpdateProductInputDto inputDto) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));

        Product productUpdate = new Product(product.id(),
                inputDto.getName(),
                inputDto.getDescription(),
                inputDto.getPrice());
        productRepository.updateProduct(productUpdate);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }
}