package com.foodservice.businesslogic;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    private String image;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
