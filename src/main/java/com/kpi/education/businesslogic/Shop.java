package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "SHOPS")
public class Shop {

    @Id
    private int id;
    private String name;
    private String address;
    private String description;

    @ManyToOne
    private User manager;

    @OneToMany(mappedBy = "shop")
    private List<Table> tables = new ArrayList<Table>();

    @OneToMany
    private List<Photo> photos = new ArrayList<Photo>();

    @OneToMany(mappedBy = "shop")
    private List<Dish> dishes = new ArrayList<Dish>();

    @OneToMany(mappedBy = "shop")
    private List<Rating> ratings = new ArrayList<Rating>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
