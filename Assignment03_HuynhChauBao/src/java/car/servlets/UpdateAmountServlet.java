/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.dtos.CartDTO;
import car.dtos.CartObjectDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
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
@WebServlet(name = "UpdateAmountServlet", urlPatterns = {"/UpdateAmountServlet"})
public class UpdateAmountServlet extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            ArrayList<CartDTO> listCart = (ArrayList<CartDTO>) session.getAttribute("CART_INFOR");
            CartObjectDetail obj = new CartObjectDetail();
            String[] idCart = request.getParameterValues("txtIdCart");
            String[] amount = request.getParameterValues("txtNumber");
            if (idCart.length == amount.length) {
                for (int i = 0; i < idCart.length; i++) {
                    try {
                        if (amount[i] == null) {
                            request.setAttribute("UPDATE_FAIL", "Please enter Number!");
                        } else {
                            int amt = Integer.parseInt(amount[i]);
                            if (amt < 1) {
                                for (CartDTO cartDTO : listCart) {
                                    if (cartDTO.getId().equals(idCart[i])) {
                                        ArrayList<CartDTO> listCar = obj.deleteItemtoCart(cartDTO, listCart);
                                        request.setAttribute("UPDATED", "Status: Updated");
                                        session.setAttribute("CART_INFOR", listCart);
                                    }
                                }
                            } else {
                                for (CartDTO cartDTO : listCart) {
                                    if (cartDTO.getId().equals(idCart[i])) {
                                        ArrayList<CartDTO> listCar = obj.updateItemtoCart(cartDTO, listCart, amt);
                                        request.setAttribute("UPDATED", "Status: Updated");
                                        session.setAttribute("CART_INFOR", listCart);
                                    }
                                }
                            }
                        }
                    } catch (NumberFormatException ne) {
                        log("Update_Servlet_Number: " + ne);
                        request.setAttribute("UPDATE_FAIL", "Please enter Number!");
                    }
                }
            }
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
