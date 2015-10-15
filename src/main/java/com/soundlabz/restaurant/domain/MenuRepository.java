package com.soundlabz.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by selasiehanson on 15/10/2015.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    public Collection<Menu> findByRestaurantId(Long restaurantId);
}
