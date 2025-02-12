package com.example.Assignment1_CPAN228.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long id;

    @NotNull(message = "Creation date is required")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 1000, message = "Price must be greater than 1000")
    private double price;

    @NotNull(message = "Release Date is required")
    private LocalDateTime releaseDate;

    @NotNull(message = "Brand is required")
    private Fashion_Brands brand;

    public Item(Long id, String name, String description, double price, LocalDateTime releaseDate,
            Fashion_Brands brand) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
        this.brand = brand;
    }

}
