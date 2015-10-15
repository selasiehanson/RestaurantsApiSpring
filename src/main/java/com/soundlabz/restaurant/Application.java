package com.soundlabz.restaurant;

import com.soundlabz.restaurant.domain.Restaurant;
import com.soundlabz.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public InitializingBean populateWithSample(RestaurantRepository restaurantRepository) {
        return () -> {
            restaurantRepository.save(new Restaurant("Cool chop", "a local chop bar", "121 banana street", "0243-123-456"));
            restaurantRepository.save(new Restaurant("Muni's Dome", "another local chop bar", "zonga abc 191 street", "0578-090-456"));

            restaurantRepository.findAll().forEach(System.out::println);
        };
    }
}
