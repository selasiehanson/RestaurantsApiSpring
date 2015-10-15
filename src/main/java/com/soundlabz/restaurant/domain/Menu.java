package com.soundlabz.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by selasiehanson on 15/10/2015.
 */

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private List<MenuItem> menuItems = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Menu(Restaurant restaurant, String description, List<MenuItem> menuItems) {
        this.description = description;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }

    Menu() {
    }
}
