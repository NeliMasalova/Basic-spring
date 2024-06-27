package pl.pingwit.basic_spring.service;

import org.springframework.stereotype.Service;
import pl.pingwit.basic_spring.controller.Product;
import pl.pingwit.basic_spring.controller.ProductDto;
import pl.pingwit.basic_spring.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

}
