package com.soundlabz.restaurant.controllers;

import com.soundlabz.restaurant.domain.Restaurant;
import com.soundlabz.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by selasiehanson on 14/10/2015.
 */
@RestController
@RequestMapping("restaurants")
public class RestaurantsController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Restaurant> getRestaurants() {
        return this.restaurantRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurant(@PathVariable("id") Long id) {
        return this.restaurantRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRestaurant(@PathVariable Long id){
        Restaurant r =  restaurantRepository.findOne(id);
        restaurantRepository.delete(r);
    }

}
