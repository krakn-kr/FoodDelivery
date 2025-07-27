package hara.restaurantservice.config;

import hara.restaurantservice.constants.Category;
import hara.restaurantservice.model.*;
import hara.restaurantservice.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantDataInitializer {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @PostConstruct
    public void initializeData() {
        if (restaurantRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Italian Restaurants
        Restaurant italianRestaurant1 = createRestaurant(
                "Bella Italia",
                "123 Main St, New York, NY 10001",
                "+1-212-555-1234",
                "Italian",
                "Authentic Italian cuisine with homemade pasta and fresh ingredients",
                "https://picsum.photos/seed/italian1/300/200.jpg",
                4.7,
                true
        );

        Restaurant italianRestaurant2 = createRestaurant(
                "Mama Mia",
                "456 Broadway, New York, NY 10002",
                "+1-212-555-5678",
                "Italian",
                "Family-style Italian restaurant with wood-fired pizzas",
                "https://picsum.photos/seed/italian2/300/200.jpg",
                4.5,
                true
        );

        // Mexican Restaurants
        Restaurant mexicanRestaurant1 = createRestaurant(
                "El Camino",
                "789 Park Ave, New York, NY 10003",
                "+1-212-555-9012",
                "Mexican",
                "Traditional Mexican dishes with a modern twist",
                "https://picsum.photos/seed/mexican1/300/200.jpg",
                4.3,
                true
        );

        Restaurant mexicanRestaurant2 = createRestaurant(
                "Taco Paradise",
                "321 West St, New York, NY 10004",
                "+1-212-555-3456",
                "Mexican",
                "Authentic street tacos and margaritas",
                "https://picsum.photos/seed/mexican2/300/200.jpg",
                4.8,
                true
        );

        // Indian Restaurants
        Restaurant indianRestaurant1 = createRestaurant(
                "Spice Route",
                "555 Oak St, New York, NY 10005",
                "+1-212-555-7890",
                "Indian",
                "Fine Indian dining with regional specialties",
                "https://picsum.photos/seed/indian1/300/200.jpg",
                4.6,
                true
        );

        Restaurant indianRestaurant2 = createRestaurant(
                "Bombay Bistro",
                "888 Pine St, New York, NY 10006",
                "+1-212-555-2345",
                "Indian",
                "Casual Indian restaurant with lunch buffet",
                "https://picsum.photos/seed/indian2/300/200.jpg",
                4.2,
                true
        );

        // Create menu items for Italian Restaurant 1
        createMenuItems(italianRestaurant1, Arrays.asList(
                new MenuItem("Margherita Pizza", "Classic cheese pizza", new BigDecimal("12.99"), Category.MAIN_COURSE),
                new MenuItem("Spaghetti Carbonara", "Creamy pasta with bacon", new BigDecimal("15.99"), Category.MAIN_COURSE),
                new MenuItem("Tiramisu", "Traditional Italian dessert", new BigDecimal("7.99"), Category.DESSERT),
                new MenuItem("Caprese Salad", "Fresh mozzarella and tomatoes", new BigDecimal("9.99"), Category.APPETIZER),
                new MenuItem("Red Wine", "Italian red wine", new BigDecimal("8.99"), Category.BEVERAGE)
        ));

        // Create menu items for Mexican Restaurant 1
        createMenuItems(mexicanRestaurant1, Arrays.asList(
                new MenuItem("Beef Tacos", "Three soft tacos with beef", new BigDecimal("11.99"), Category.MAIN_COURSE),
                new MenuItem("Guacamole", "Fresh avocado dip", new BigDecimal("6.99"), Category.APPETIZER),
                new MenuItem("Churros", "Cinnamon sugar pastries", new BigDecimal("5.99"), Category.DESSERT),
                new MenuItem("Margarita", "Classic margarita", new BigDecimal("8.99"), Category.BEVERAGE),
                new MenuItem("Chicken Quesadilla", "Grilled chicken with cheese", new BigDecimal("13.99"), Category.MAIN_COURSE)
        ));

        // Create menu items for Indian Restaurant 1
        createMenuItems(indianRestaurant1, Arrays.asList(
                new MenuItem("Butter Chicken", "Creamy chicken curry", new BigDecimal("16.99"), Category.MAIN_COURSE),
                new MenuItem("Samosas", "Crispy vegetable pastries", new BigDecimal("5.99"), Category.APPETIZER),
                new MenuItem("Gulab Jamun", "Sweet milk dumplings", new BigDecimal("6.99"), Category.DESSERT),
                new MenuItem("Mango Lassi", "Yogurt drink with mango", new BigDecimal("4.99"), Category.BEVERAGE),
                new MenuItem("Chicken Biryani", "Fragrant rice with chicken", new BigDecimal("14.99"), Category.MAIN_COURSE)
        ));
    }

    private Restaurant createRestaurant(String name, String address, String phone,
                                        String cuisineType, String description,
                                        String imageUrl, double rating, boolean isOpen) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        restaurant.setCuisineType(cuisineType);
        restaurant.setDescription(description);
        restaurant.setImageUrl(imageUrl);
        restaurant.setRating(rating);
        restaurant.setOpen(isOpen);
        return restaurantRepository.save(restaurant);
    }

    private void createMenuItems(Restaurant restaurant, List<MenuItem> menuItems) {
        menuItems.forEach(item -> item.setRestaurant(restaurant));
        menuItemRepository.saveAll(menuItems);
    }
}