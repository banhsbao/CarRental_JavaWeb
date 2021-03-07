/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.daos;

import car.dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class UserDAO {

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

    public UserDTO loginUser(String email, String password) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select u.name, u.phone, u.address, u.createDate, r.name as role, u.status\n"
                        + "from tblUsers u\n"
                        + "inner join tblRoles r\n"
                        + "on r.id = u.roleID\n"
                        + "where u.email = ? and u.password = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    UserDTO currentUser = new UserDTO(email, rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getDate("createDate"), rs.getString("role"), rs.getString("status"));
                    return currentUser;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public String getVerifyCode(String email) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select verifyCode from tblUsers where email = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getString("verifyCode");
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean changeStatus(String email) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblUsers SET status = 'Active' where email = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
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

    public boolean register(String email, String password, String phone, String name, String address, String verifyCode, String status) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUsers(email,password,name,phone,verifyCode,address,roleID,status) VALUES(?,?,?,?,?,?,?,?)";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, name);
                pst.setString(4, phone);
                pst.setString(5, verifyCode);
                pst.setString(6, address);
                pst.setInt(7, 1001);
                pst.setString(8, status);
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

}
