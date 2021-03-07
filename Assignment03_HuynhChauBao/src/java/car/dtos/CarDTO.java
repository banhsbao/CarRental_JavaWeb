/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class CarDTO implements Serializable {

    private String id;
    private String category;
    private String name;
    private String color;
    private String image;
    private String year;
    private float price;
    private int quantity;
    private Date createdDate;
    private float rating;

    public CarDTO() {
    }

    public CarDTO(String id, String category, String name, String color, String image, String year, float price, int quantity, Date createdDate, float rating) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.color = color;
        this.image = image;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}