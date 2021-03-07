/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.CarDAO;
import car.daos.OrderDAO;
import car.dtos.CartDTO;
import car.dtos.CartObjectDetail;
import car.dtos.DiscountDTO;
import car.dtos.UserDTO;
import car.erros.CheckOutError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String SEARCH_PAGE = "searchPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        Map<String, String> mapping = (Map<String, String>) context.getAttribute("MAPPING");
        String url = mapping.get(SEARCH_PAGE);
        try {
            HttpSession session = request.getSession();
            CarDAO dao = new CarDAO();
            UserDTO currentUser = (UserDTO) session.getAttribute("USER_ADMIN");
            OrderDAO orderDAO = new OrderDAO();
            ArrayList<CartDTO> listCart = (ArrayList<CartDTO>) session.getAttribute("CART_INFOR");
            DiscountDTO discount = (DiscountDTO) session.getAttribute("DISCOUNT");
            for (CartDTO cartDTO : listCart) {
                int count = 0;
                for (CartDTO cartDTO1 : listCart) {
                    if (cartDTO.getId().equals(cartDTO1.getId())) {
                        count = count + cartDTO1.getAmount();
                    }
                }
                if ((dao.getCarAmount(cartDTO.getId()) - dao.getCarRental(cartDTO.getId())) < count) {
                    CheckOutError error = new CheckOutError();
                    error.setIdCar(cartDTO.getCartId());
                    error.setMsg(cartDTO.getName() + " is out of stock MAX[" + dao.getCarAmount(cartDTO.getId()) + "]");
                    request.setAttribute("CHECKOUT_FAIL", error);
                } else {
                    float totalPrice = 0;
                    for (CartDTO cartDTO1 : listCart) {
                        totalPrice = totalPrice + (cartDTO1.getAmount() * cartDTO1.getPrice());
                    }
                    boolean createOrderSuccess = true;
                    CartObjectDetail obj = new CartObjectDetail();
                    String idOrder = obj.generateID();
                    if (discount != null) {
                        float discountMoney = (float) totalPrice * discount.getValue() / 100;
                        createOrderSuccess = orderDAO.createOrder(idOrder, currentUser.getEmail(), discountMoney, discount.getId(), true);
                        orderDAO.updateDiscount(discount.getId());
                    } else {
                        createOrderSuccess = orderDAO.createOrder(idOrder, currentUser.getEmail(), totalPrice, "NOOFF", true);
                    }
                    if (createOrderSuccess) {
                        String discountId = "";
                        if (discount == null) {
                            discountId = "NOOFF";
                        }
                        if (orderDAO.createOrderDetail(idOrder, cartDTO.getRentalDate(), cartDTO.getReturnDate(), Integer.parseInt(cartDTO.getId()), count, totalPrice, true)) {
                            session.setAttribute("CART_INFOR", null);
                            session.setAttribute("DISCOUNT", null);
                            request.setAttribute("CHECKOUT_SUCCESS", "Status: Checkout Successful");
                        }
                    }

                }
            }
        } catch (NamingException ne) {
            log("CheckOutServlet_NamingException: " + ne);
        } catch (SQLException sq) {
            log("CheckOutServlet_SQLException: " + sq);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
