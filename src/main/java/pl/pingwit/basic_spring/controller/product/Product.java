package pl.pingwit.basic_spring.controller.product;

public record Product(Integer id, String name, String description, java.math.BigDecimal price) {
}