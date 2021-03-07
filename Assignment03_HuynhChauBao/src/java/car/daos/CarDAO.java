/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.daos;

import car.dtos.CarDTO;
import car.dtos.CartDTO;
import car.dtos.CategoryDTO;
import car.dtos.DiscountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class CarDAO {

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

    public int countAllCar() throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(id) as number from tblCar";
                pst = con.prepareCall(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt("number");
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public ArrayList<CategoryDTO> getCategory() throws NamingException, SQLException {
        ArrayList<CategoryDTO> listCategory = new ArrayList<>();
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id, name from tblCategory";
                pst = con.prepareCall(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    listCategory.add(new CategoryDTO(rs.getString("id"), rs.getString("name")));
                }
            }
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public ArrayList<CarDTO> getAllCar(int Page) throws NamingException, SQLException {
        ArrayList<CarDTO> listCar = new ArrayList<>();
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.id, ca.name as category, c.name, c.color, c.img, c.year, c.price, c.quanity, c.createdDate\n"
                        + "from tblCar c\n"
                        + "inner join tblCategory ca\n"
                        + "on ca.id = c.cateId \n"
                        + "where c.isActive = 1 and c.quanity > 0 \n"
                        + "order by name desc\n"
                        + "offset(?-1)*20rows\n"
                        + "FETCH NEXT 20 ROW ONLY";
                pst = con.prepareCall(sql);
                pst.setInt(1, Page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    listCar.add(new CarDTO(rs.getString("id"), rs.getString("category"), rs.getString("name"), rs.getString("color"), rs.getString("img"), rs.getString("year"), rs.getFloat("price"), rs.getInt("quanity"), rs.getDate("createdDate"), 0));
                }
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public float ratingCar(String id) throws NamingException, SQLException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT AVG(rating) as rating\n"
                        + "from tblRate\n"
                        + "where idCar = ?";
                pst = con.prepareCall(sql);
                pst.setInt(1, Integer.parseInt(id));
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

    public int getQuantity(String carId, Date rentalDate, Date returnDate) throws SQLException, NamingException {
        int result = 0;
        Connection connect = null;
        PreparedStatement pstd = null;
        ResultSet rst = null;
        try {
            connect = car.utils.DBHelper.makeConnection();
            if (connect != null) {
                String sql = "select sum(quantity)  as sum \n"
                        + "from tblOrderDetail\n"
                        + "where isActive=1 and carId= ? and \n"
                        + "( ( ? between rentalDate and returnDate) or \n"
                        + "( ? between  rentalDate and returnDate) or (rentalDate between ? and ? ) \n"
                        + "or (returnDate between ? and ? ))";
                pstd = connect.prepareCall(sql);
                pstd.setString(1, carId);
                pstd.setDate(2, rentalDate);
                pstd.setDate(3, returnDate);
                pstd.setDate(4, rentalDate);
                pstd.setDate(5, returnDate);
                pstd.setDate(6, rentalDate);
                pstd.setDate(7, returnDate);
                rst = pstd.executeQuery();
                if (rst.next()) {
                    result = rst.getInt(("sum"));
                }
            }
        } finally {
            if (rst != null) {
                rst.close();
            }
            if (pstd != null) {
                pstd.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return result;
    }

    public ArrayList<CarDTO> searchCarByName(String name, int page, int quantity, Date rentalDate, Date returnDate, ArrayList<CartDTO> listCarRenting) throws SQLException, NamingException {
        ArrayList<CarDTO> listCar = null;
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.id, ca.name as category, c.name, c.color, c.img, c.year, c.price, c.quanity, c.createdDate\n"
                        + "from tblCar c\n"
                        + "inner join tblCategory ca\n"
                        + "on ca.id = c.cateId \n"
                        + "where c.name like ?\n"
                        + "order by  createdDate\n"
                        + " offset ( ?-1) *20 rows fetch next 20 rows only";
                pst = con.prepareCall(sql);
                pst.setString(1, "%" + name + "%");
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("id");
                    int totalQuantity = rs.getInt("quanity");
                    int totalQuantityinCart = 0;
                    if (listCarRenting != null) {
                        //kiem tra trong cart co gi
                        for (CartDTO cartDTO : listCarRenting) {
                            if (cartDTO.getId().equals(carId)) {
                                totalQuantityinCart = totalQuantityinCart + cartDTO.getAmount();
                            }
                        }
                    }
                    int rentalQuantity = getQuantity(carId, rentalDate, returnDate);
                    if ((totalQuantity - rentalQuantity - totalQuantityinCart) >= quantity) {
                        int quantityLeft = totalQuantity - rentalQuantity - totalQuantityinCart;
                        if (listCar == null) {
                            listCar = new ArrayList<>();
                        }
                        listCar.add(new CarDTO(rs.getString("id"), rs.getString("category"), rs.getString("name"), rs.getString("color"), rs.getString("img"), rs.getString("year"), rs.getFloat("price"), quantityLeft, rs.getDate("createdDate"), 0));
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public int getCountBySearchName(String name, int quantity, Date rentalDate, Date returnDate, ArrayList<CartDTO> listCarRenting) throws SQLException, NamingException {
        int result = 0;
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select  id, quanity\n"
                        + "from tblCar\n"
                        + "where name like ?\n";
                pst = con.prepareCall(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("id");
                    int totalQuantity = rs.getInt("quanity");
                    int totalQuantityinCart = 0;
                    if (listCarRenting != null) {
                        //kiem tra trong cart co gi
                        for (CartDTO cartDTO : listCarRenting) {
                            if (cartDTO.getId().equals(carId)) {
                                totalQuantityinCart = totalQuantityinCart + cartDTO.getAmount();
                            }
                        }
                    }
                    int rentalQuantity = getQuantity(carId, rentalDate, returnDate);
                    //Tổng quantity - đã thuê - lượng có trong cart
                    if ((totalQuantity - rentalQuantity - totalQuantityinCart) >= quantity) {
                        result++;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<CarDTO> searchCarByCategory(int cateId, int page, int quantity, Date rentalDate, Date returnDate, ArrayList<CartDTO> listCarRenting) throws SQLException, NamingException {
        ArrayList<CarDTO> listCar = null;
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select c.id, ca.name as category, c.name, c.color, c.img, c.year, c.price, c.quanity, c.createdDate\n"
                        + "from tblCar c\n"
                        + "inner join tblCategory ca\n"
                        + "on ca.id = c.cateId \n"
                        + "where c.cateId =?\n"
                        + "order by  createdDate\n"
                        + " offset ( ?-1) *20 rows fetch next 20 rows only";
                pst = con.prepareCall(sql);
                pst.setInt(1, cateId);
                pst.setInt(2, page);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("id");
                    int totalQuantity = rs.getInt("quanity");
                    int rentalQuantity = getQuantity(carId, rentalDate, returnDate);
                    int totalQuantityinCart = 0;
                    if (listCarRenting != null) {
                        //kiem tra trong cart co gi
                        for (CartDTO cartDTO : listCarRenting) {
                            if (cartDTO.getId().equals(carId)) {
                                totalQuantityinCart = totalQuantityinCart + cartDTO.getAmount();
                            }
                        }
                    }
                    if ((totalQuantity - quantity - totalQuantityinCart) >= rentalQuantity) {
                        int quantityLeft = totalQuantity - rentalQuantity - totalQuantityinCart;
                        System.out.println("Car id: " + carId);
                        System.out.println("Tong xe co: " + totalQuantity);
                        System.out.println("Tong xe thue: " + rentalQuantity);
                        System.out.println("Tong xe con: " + quantityLeft);
                        System.out.println("Co trong cart: " + totalQuantityinCart);
                        if (listCar == null) {
                            listCar = new ArrayList<>();
                        }
                        listCar.add(new CarDTO(rs.getString("id"), rs.getString("category"), rs.getString("name"), rs.getString("color"), rs.getString("img"), rs.getString("year"), rs.getFloat("price"), quantityLeft, rs.getDate("createdDate"), 0));
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return listCar;
    }

    public int getCountBySearchCate(int cateId, int quantity, Date rentalDate, Date returnDate, ArrayList<CartDTO> listCarRenting) throws SQLException, NamingException {

        int result = 0;
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select  id,quanity\n"
                        + "from tblCar\n"
                        + "where cateId = ?\n";
                pst = con.prepareCall(sql);
                pst.setInt(1, cateId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carId = rs.getString("id");
                    int totalQuantity = rs.getInt("quanity");
                    int rentalQuantity = getQuantity(carId, rentalDate, returnDate);
                    if ((totalQuantity - rentalQuantity) >= quantity) {
                        result++;
                    }
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public int getCarAmount(String id) throws SQLException, NamingException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select quanity\n"
                        + "from tblCar\n"
                        + "where id = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt("quanity");
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int getCarRental(String id) throws SQLException, NamingException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select sum(quantity) as number\n"
                        + "from tblOrderDetail o\n"
                        + "where o.carid =?";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt("number");
                }
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public DiscountDTO getDiscount(String id) throws SQLException, NamingException {
        try {
            con = car.utils.DBHelper.makeConnection();
            if (con != null) {
                String sql = "select id, name, value,expiredDate,status\n"
                        + "from tblDiscount\n"
                        + "where status = 1 and id = ? ";
                pst = con.prepareCall(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    DiscountDTO object = new DiscountDTO(rs.getString("id"), rs.getString("name"), rs.getInt("value"), rs.getDate("expiredDate"), rs.getBoolean("status"));
                    return object;
                }
            }

        } finally {
            closeConnection();
        }
        return null;
    }
}
