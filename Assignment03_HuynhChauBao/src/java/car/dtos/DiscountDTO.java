/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dtos;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class DiscountDTO {

    private String id;
    private String name;
    private int value;
    private Date expriedDate;
    private boolean isActive;

    public DiscountDTO() {
    }

    public DiscountDTO(String id, String name, int value, Date expriedDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.expriedDate = expriedDate;
        this.isActive = isActive;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getExpriedDate() {
        return expriedDate;
    }

    public void setExpriedDate(Date expriedDate) {
        this.expriedDate = expriedDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
