package com.soundlabz.restaurant.controllers;

import com.soundlabz.restaurant.domain.Menu;
import com.soundlabz.restaurant.domain.MenuRepository;
import com.soundlabz.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by selasiehanson on 15/10/2015.
 */
@RestController
@RequestMapping("restaurants/{restaurantId}/menus")
public class MenuController {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;


    @Autowired
    public MenuController(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Menu> getMenus(@PathVariable Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Menu getMenu(@PathVariable Long id) {
        return menuRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        this.validateRestaurant(restaurantId);
        return restaurantRepository
                .findById(restaurantId)
                .map(restaurant -> {
                    Menu m = this.menuRepository.save(new Menu(restaurant, menu.getDescription(), null));
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(m.getId()).toUri()
                    );
                    return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
                }).get();
    }

    private void validateRestaurant(Long restaurantId) {
        restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
}


class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super(String.format("Could not find restaurant with id = %d", id));
    }
}