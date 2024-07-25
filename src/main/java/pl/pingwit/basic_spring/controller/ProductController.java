package pl.pingwit.basic_spring.controller;

import org.springframework.web.bind.annotation.*;
import pl.pingwit.basic_spring.controller.product.CreateProductInputDto;
import pl.pingwit.basic_spring.controller.product.ProductDto;
import pl.pingwit.basic_spring.controller.product.UpdateProductInputDto;
import pl.pingwit.basic_spring.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable(name = "id") Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Integer createProduct(@RequestBody CreateProductInputDto input) {
        return productService.createProduct(input);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable(name = "id") Integer id, @RequestBody UpdateProductInputDto inputDto) {
        productService.updateProduct(id, inputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.deleteProductById(id);
    }
}