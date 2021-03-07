/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.dtos.CarDTO;
import car.dtos.CartDTO;
import car.dtos.CartObjectDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.text.ParseException;
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
@WebServlet(name = "AddItemServlet", urlPatterns = {"/AddItemServlet"})
public class AddItemServlet extends HttpServlet {

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
        ServletContext context = request.getServletContext();
        Map<String, String> mapping = (Map<String, String>) context.getAttribute("MAPPING");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("CarId");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String url = mapping.get(SEARCH_PAGE);
        try {
            HttpSession session = request.getSession();
            ArrayList<CartDTO> listCart = (ArrayList<CartDTO>) session.getAttribute("CART_INFOR");
            if (listCart == null) {
                listCart = new ArrayList<>();
            }
            String rentalDate = (String) session.getAttribute("RENTALDATE");
            String returnDate = (String) session.getAttribute("RETURNDATE");
            if (rentalDate == null || returnDate == null) {
                session.setAttribute("RENTAL_FAIL", "Please enter Date you want to rental and return first!");
            } else {
                session.setAttribute("RENTAL_FAIL", null);
                ArrayList<CarDTO> listCar = (ArrayList<CarDTO>) session.getAttribute("LIST_CAR");
                for (CarDTO carDTO : listCar) {
                    if (carDTO.getId().equals(id)) {
                        CartObjectDetail cartDAO = new CartObjectDetail();
                        CartDTO cartObject = new CartDTO(cartDAO.generateID(), carDTO.getId(), carDTO.getName(), carDTO.getImage(), carDTO.getCategory(), 1, carDTO.getPrice(), true, Date.valueOf(rentalDate), Date.valueOf(returnDate),carDTO.getQuantity());
                        listCart = cartDAO.addCart(cartObject, rentalDate, returnDate, listCart);
                        session.setAttribute("CART_INFOR", listCart);
                    }

                }
            }
        } catch (ParseException pe) {
            log("AddItemServler_ParseException: " + pe);
        } finally {
//            response.sendRedirect(SEARCH_PAGE);
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
