package hara.restaurantservice.repository;

import hara.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findByCuisineType(String cuisineType);
    List<Restaurant> findByRatingGreaterThanEqual(double rating);
}