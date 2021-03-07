/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.daos;

import car.dtos.HistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void closeConnection() throws NamingException, SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean createOrder(String id, String email, float totalPrice, String discountId, boolean status) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblOrder(id,email,totalPrice,discountId,status) values(?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                pst.setString(2, email);
                pst.setFloat(3, totalPrice);
                pst.setString(4, discountId);
                pst.setBoolean(5, status);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean createOrderDetail(String OrderId, Date rentaDate, Date returnDate, int carId, int quantity, float price, boolean status) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblOrderDetail(orderId,rentalDate,returnDate,carid,quantity,price,isActive) values(?,?,?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, OrderId);
                pst.setDate(2, rentaDate);
                pst.setDate(3, returnDate);
                pst.setInt(4, carId);
                pst.setInt(5, quantity);
                pst.setFloat(6, price);
                pst.setBoolean(7, status);
                int result = pst.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public void updateDiscount(String id) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update tblDiscount set status = 'false' where id = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                int result = pst.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    public ArrayList<HistoryDTO> listHistory(String email) throws NamingException, SQLException {
        ArrayList<HistoryDTO> listHis = new ArrayList<>();
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "	select o.id as idOrder,c.id, o.createDate ,o.discountId,d.value,c.price, c.name as nameOfCar,od.quantity, od.rentalDate, od.returnDate,o.status,c.img\n"
                        + "	from tblOrderDetail od\n"
                        + "	inner join tblOrder o \n"
                        + "	on o.id = od.orderId\n"
                        + "	inner join tblCar c\n"
                        + "	on od.carid = c.id\n"
                        + "	inner join tblDiscount d\n"
                        + "	on d.id = o.discountId\n"
                        + "	where o.email = ?\n"
                        + "	order by createDate";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                while (rs.next()) {
                    float Price = (float) rs.getFloat("price");
                    float totalPrice = Price * (rs.getInt("quantity"));
                    float discountPrice = (float) totalPrice - ((totalPrice * rs.getInt("value")) / 100);
                    HistoryDTO obj = new HistoryDTO(rs.getString("id"), rs.getString("img"), rs.getString("idOrder"), rs.getDate("createDate"), rs.getString("discountId"), rs.getString("nameOfCar"), rs.getInt("quantity"), rs.getFloat("price"), discountPrice, totalPrice, rs.getDate("rentalDate"), rs.getDate("returnDate"), rs.getBoolean("status"), 0);
                    listHis.add(obj);
                }
            }
        } finally {
            closeConnection();
        }
        return listHis;
    }

    public float getRating(String email, String idCar) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "	select rating\n"
                        + "	from tblRate\n"
                        + "	where email = ? and idCar = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, idCar);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getFloat("rating");
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public ArrayList<HistoryDTO> getSearch(String email, String name) throws NamingException, SQLException {
        ArrayList<HistoryDTO> listHis = new ArrayList<>();
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "	select o.id as idOrder,c.id, o.createDate ,o.discountId,d.value,c.price, c.name as nameOfCar,od.quantity, od.rentalDate, od.returnDate,o.status,c.img\n"
                        + "	from tblOrderDetail od\n"
                        + "	inner join tblOrder o \n"
                        + "	on o.id = od.orderId\n"
                        + "	inner join tblCar c\n"
                        + "	on od.carid = c.id\n"
                        + "	inner join tblDiscount d\n"
                        + "	on d.id = o.discountId\n"
                        + "where o.email = ? and c.name like ?\n"
                        + "order by createDate";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, "%" + name + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    float Price = (float) rs.getFloat("price");
                    float totalPrice = Price * (rs.getInt("quantity"));
                    float discountPrice = (float) totalPrice - ((totalPrice * rs.getInt("value")) / 100);
                    HistoryDTO obj = new HistoryDTO(rs.getString("id"), rs.getString("img"), rs.getString("idOrder"), rs.getDate("createDate"), rs.getString("discountId"), rs.getString("nameOfCar"), rs.getInt("quantity"), rs.getFloat("price"), discountPrice, totalPrice, rs.getDate("rentalDate"), rs.getDate("returnDate"), rs.getBoolean("status"), 0);
                    listHis.add(obj);
                }
            }
        } finally {
            closeConnection();
        }
        return listHis;
    }

    public ArrayList<HistoryDTO> getSearchDate(String email, Date date) throws NamingException, SQLException {
        ArrayList<HistoryDTO> listHis = new ArrayList<>();
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "	select DISTINCT  o.id as idOrder,c.id, o.createDate ,o.discountId,d.value,c.price, c.name as nameOfCar,od.quantity, od.rentalDate, od.returnDate,o.status,c.img, r.rating\n"
                        + "	from tblOrderDetail od\n"
                        + "	inner join tblOrder o \n"
                        + "	on o.id = od.orderId\n"
                        + "	inner join tblCar c\n"
                        + "	on od.carid = c.id\n"
                        + "	inner join tblDiscount d\n"
                        + "	on d.id = o.discountId\n"
                        + "	inner join tblRate r\n"
                        + "	on r.idCar = c.id\n"
                        + "	where o.email = ? and o.createDate = ?\n"
                        + "	order by createDate";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setDate(2, date);
                rs = pst.executeQuery();
                while (rs.next()) {
                    float Price = (float) rs.getFloat("price");
                    float totalPrice = Price * (rs.getInt("quantity"));
                    float discountPrice = (float) totalPrice - ((totalPrice * rs.getInt("value")) / 100);
                    HistoryDTO obj = new HistoryDTO(rs.getString("id"), rs.getString("img"), rs.getString("idOrder"), rs.getDate("createDate"), rs.getString("discountId"), rs.getString("nameOfCar"), rs.getInt("quantity"), rs.getFloat("price"), discountPrice, totalPrice, rs.getDate("rentalDate"), rs.getDate("returnDate"), rs.getBoolean("status"), 0);
                    listHis.add(obj);
                }
            }
        } finally {
            closeConnection();
        }
        return listHis;
    }

    public void statusOrder(String idOrder) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "update tblOrder\n"
                        + "set status = 'false'\n"
                        + "where id = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, idOrder);
                int result = pst.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    public void setRating(int score, String idCar, String email) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblRate(idCar,email, rating) values(?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, idCar);
                pst.setString(2, email);
                pst.setInt(3, score);
                int result = pst.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }

    public Date getRentalDate(String idOrder, String idCar, int amount) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select rentalDate\n"
                        + "from tblOrderDetail\n"
                        + "where orderId = ? and carid = ? and quantity = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, idOrder);
                pst.setString(2, idCar);
                pst.setInt(3, amount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getDate("rentalDate");
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public Date getReturnDate(String idOrder, String idCar, int amount) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select returnDate\n"
                        + "from tblOrderDetail\n"
                        + "where orderId = ? and carid = ? and quantity = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, idOrder);
                pst.setString(2, idCar);
                pst.setInt(3, amount);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getDate("returnDate");
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
}
