package com.soundlabz.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by selasiehanson on 14/10/2015.
 */

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    List<Menu> menus = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, String description, String address, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("id=%d, name='%s', address='%s', phoneNumber='%s'",
                getId(), getName(), getAddress(), getPhoneNumber());
    }
}
