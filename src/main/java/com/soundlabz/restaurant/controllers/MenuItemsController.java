package com.soundlabz.restaurant.controllers;

import com.soundlabz.restaurant.domain.Menu;
import com.soundlabz.restaurant.domain.MenuItem;
import com.soundlabz.restaurant.domain.MenuItemRepository;
import com.soundlabz.restaurant.domain.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by selasiehanson on 15/10/2015.
 */
@RestController
@RequestMapping("/restaurants/{restaurantId}/menus/{menuId}/menu-items")
public class MenuItemsController {


    private MenuItemRepository menuItemRepository;
    private MenuRepository menuRepository;

    @Autowired
    MenuItemsController(MenuItemRepository menuItemRepository, MenuRepository menuRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuRepository = menuRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Collection<MenuItem> createItems(@PathVariable Long menuId, @RequestBody List<MenuItem> menuItems) {
        return saveItems(menuId, menuItems);

       /* return menuItems.stream().map((item) -> item.getId())
                .collect(Collectors.toList()); */
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Collection<MenuItem> updateItems(@PathVariable Long menuId, @RequestBody List<MenuItem> menuItems) {
        return saveItems(menuId, menuItems);
    }

    private Collection<MenuItem> saveItems(Long menuId, List<MenuItem> menuItems) {
        Menu menu = menuRepository.findOne(menuId);
        menuItems.stream().forEach((x) -> x.setMenu(menu));
        menuItemRepository.save(menuItems);
        return menuItems;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemRepository.findOne(id);
        menuItemRepository.delete(menuItem);
    }
}
