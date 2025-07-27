package hara.restaurantservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    @Size(min = 2, max = 100, message = "Restaurant name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    private String address;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Cuisine type is required")
    private String cuisineType;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @DecimalMin(value = "0", message = "Rating must be at least 0")
    @DecimalMax(value = "5", message = "Rating must be at most 5")
    private double rating;

    private boolean isOpen;

    private List<MenuItemDTO> menuItems;
}