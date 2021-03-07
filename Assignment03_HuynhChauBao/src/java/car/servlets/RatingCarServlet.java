/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.OrderDAO;
import car.dtos.HistoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "RatingCarServlet", urlPatterns = {"/RatingCarServlet"})
public class RatingCarServlet extends HttpServlet {

    private final String HISTORY_PAGE = "historyPage";

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
        String url = mapping.get(HISTORY_PAGE);
        String idCar = request.getParameter("txtIdCar");
        String email = request.getParameter("txtEmail");
        String idOrder = request.getParameter("txtIdOrder");
        String amount = request.getParameter("txtAmount");
        String Score = request.getParameter(idOrder);
        HttpSession session = request.getSession();
        try {
            OrderDAO dao = new OrderDAO();
            Date returnDate = dao.getReturnDate(idOrder, idCar, Integer.parseInt(amount));
            Date currentDate = new Date();
            if (returnDate.compareTo(currentDate) >= 0) {
                request.setAttribute("RATING_FAIL", idOrder);
            } else {
                dao.setRating(Integer.parseInt(Score), idCar, email);
                ArrayList<HistoryDTO> listHistory = (ArrayList<HistoryDTO>) session.getAttribute("HISTORY");
                for (HistoryDTO historyDTO : listHistory) {
                    if (historyDTO.getIdOrder().equals(idOrder)) {
                        historyDTO.setRating(Integer.parseInt(Score));
                    }
                }
            }
        } catch (NamingException ne) {
            log("RatingServlet_Naming:" + ne);
        } catch (SQLException sq) {
            log("RatingSerlvet_SQLException: " + sq);
        } catch (NumberFormatException nu) {
            log("RatingServlet_NumberFormat: " + nu);
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
