package pl.pingwit.basic_spring.controller;

public record Product(Integer id, String name, String description, java.math.BigDecimal price) {
}