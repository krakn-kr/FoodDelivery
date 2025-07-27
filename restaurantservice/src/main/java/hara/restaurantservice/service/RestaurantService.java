package hara.restaurantservice.service;

import hara.restaurantservice.constants.Category;
import hara.restaurantservice.dto.RestaurantDTO;
import hara.restaurantservice.dto.MenuItemDTO;
import hara.restaurantservice.exception.ResourceNotFoundException;
import hara.restaurantservice.model.Restaurant;
import hara.restaurantservice.model.MenuItem;
import hara.restaurantservice.repository.RestaurantRepository;
import hara.restaurantservice.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return convertToDTO(restaurant);
    }

    public List<RestaurantDTO> getRestaurantsByCuisine(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RestaurantDTO> getRestaurantsByRating(double minRating) {
        return restaurantRepository.findByRatingGreaterThanEqual(minRating).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return convertToDTO(savedRestaurant);
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));

        existingRestaurant.setName(restaurantDTO.getName());
        existingRestaurant.setAddress(restaurantDTO.getAddress());
        existingRestaurant.setPhone(restaurantDTO.getPhone());
        existingRestaurant.setCuisineType(restaurantDTO.getCuisineType());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        existingRestaurant.setImageUrl(restaurantDTO.getImageUrl());
        existingRestaurant.setRating(restaurantDTO.getRating());
        existingRestaurant.setOpen(restaurantDTO.isOpen());

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return convertToDTO(updatedRestaurant);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
    }

    public List<MenuItemDTO> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId).stream()
                .map(this::convertMenuItemToDTO)
                .collect(Collectors.toList());
    }

    public List<MenuItemDTO> getMenuItemsByCategory(Category category) {
        return menuItemRepository.findByCategory(category).stream()
                .map(this::convertMenuItemToDTO)
                .collect(Collectors.toList());
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        RestaurantDTO dto = modelMapper.map(restaurant, RestaurantDTO.class);
        dto.setMenuItems(restaurant.getMenuItems().stream()
                .map(this::convertMenuItemToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private MenuItemDTO convertMenuItemToDTO(MenuItem menuItem) {
        return modelMapper.map(menuItem, MenuItemDTO.class);
    }
}