/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dtos;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class OrderDTO {
    private String email;
    private float totalPrice;
    private Date createDate;
    private String discountId;
    private boolean status;

    public OrderDTO(String email, float totalPrice, Date createDate, String discountId, boolean status) {
        this.email = email;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.discountId = discountId;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
