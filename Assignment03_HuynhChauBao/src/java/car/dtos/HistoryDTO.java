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
public class HistoryDTO implements Serializable {

    private String idCar;
    private String imageCar;
    private String idOrder;
    private Date createDate;
    private String discountId;
    private String carName;
    private int quantity;
    private float price;
    private float discountPrice;
    private float totalPrice;
    private Date rentalDate;
    private Date returnDate;
    private boolean status;
    private float rating;

    public HistoryDTO() {
    }

    public HistoryDTO(String idCar, String imageCar, String idOrder, Date createDate, String discountId, String carName, int quantity, float price, float discountPrice, float totalPrice, Date rentalDate, Date returnDate, boolean status, float rating) {
        this.idCar = idCar;
        this.imageCar = imageCar;
        this.idOrder = idOrder;
        this.createDate = createDate;
        this.discountId = discountId;
        this.carName = carName;
        this.quantity = quantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.totalPrice = totalPrice;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.rating = rating;
    }

    public String getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }

    public String getImageCar() {
        return imageCar;
    }

    public void setImageCar(String imageCar) {
        this.imageCar = imageCar;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
