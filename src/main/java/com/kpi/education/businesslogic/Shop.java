package com.kpi.education.businesslogic;

import com.kpi.education.businesslogic.user.ManagerUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "shop")
public class Shop {

    @Id
    private int id;

    private String name;

    @Embedded
    private Location location;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_user_id")
    private ManagerUser manager;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Table> tables = new ArrayList<Table>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<Photo>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<Dish>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManagerUser getManager() {
        return manager;
    }

    public void setManager(ManagerUser manager) {
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
