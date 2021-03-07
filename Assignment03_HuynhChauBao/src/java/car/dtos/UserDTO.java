/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class UserDTO implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String address;
    private Date createDate;
    private String role;
    private String status;
    public UserDTO() {
    }
    public UserDTO(String email, String name, String phone, String address, Date createDate, String role, String status) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.role = role;
        this.status = status;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
