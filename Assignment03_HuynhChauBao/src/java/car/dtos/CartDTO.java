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
public class CartDTO implements Serializable {
    private String cartId;
    private String id;
    private String name;
    private String image;
    private String category;
    private int amount;
    private float price;
    private boolean isActive;
    private Date rentalDate;
    private Date returnDate;
    private int maxCar;

    public CartDTO() {
    }

    public CartDTO(String cartId, String id, String name, String image, String category, int amount, float price, boolean isActive, Date rentalDate, Date returnDate, int maxCar) {
        this.cartId = cartId;
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.isActive = isActive;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.maxCar = maxCar;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getMaxCar() {
        return maxCar;
    }

    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }
    
}