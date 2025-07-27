package hara.restaurantservice.controller;

import hara.restaurantservice.constants.Category;
import hara.restaurantservice.dto.RestaurantDTO;
import hara.restaurantservice.dto.MenuItemDTO;
import hara.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/cuisine/{cuisineType}")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByCuisine(@PathVariable String cuisineType) {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsByCuisine(cuisineType);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/rating/{minRating}")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByRating(@PathVariable double minRating) {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsByRating(minRating);
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO createdRestaurant = restaurantService.createRestaurant(restaurantDTO);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO updatedRestaurant = restaurantService.updateRestaurant(id, restaurantDTO);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        List<MenuItemDTO> menuItems = restaurantService.getMenuItemsByRestaurantId(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/menu/category/{category}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByCategory(@PathVariable Category category) {
        List<MenuItemDTO> menuItems = restaurantService.getMenuItemsByCategory(category);
        return ResponseEntity.ok(menuItems);
    }
}