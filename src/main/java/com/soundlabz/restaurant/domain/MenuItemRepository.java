package com.soundlabz.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by selasiehanson on 15/10/2015.
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
