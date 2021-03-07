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
import java.text.ParseException;
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
@WebServlet(name = "DeleteHistoryServlet", urlPatterns = {"/DeleteHistoryServlet"})
public class DeleteHistoryServlet extends HttpServlet {

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
        String idHistory = request.getParameter("txtIdOrder");
        String idCar = request.getParameter("idCar");
        String amount = request.getParameter("txtAmount");
        ServletContext context = request.getServletContext();
        Map<String, String> mapping = (Map<String, String>) context.getAttribute("MAPPING");
        String url = mapping.get(HISTORY_PAGE);
        try {
            HttpSession session = request.getSession();
            OrderDAO dao = new OrderDAO();
            ArrayList<HistoryDTO> listHistory = (ArrayList<HistoryDTO>) session.getAttribute("HISTORY");
            Date renDate = dao.getRentalDate(idHistory,idCar, Integer.parseInt(amount));
            Date currentDate = new Date();
            if (renDate.compareTo(currentDate) < 0) {
                session.setAttribute("DELETE_FAIL", idHistory);
            } else {
                for (HistoryDTO historyDTO : listHistory) {
                    if (idHistory.equals(historyDTO.getIdOrder())) {
                        historyDTO.setStatus(false);
                        dao.statusOrder(idHistory);
                    }
                }
            }
        } catch (NamingException ne) {
            log("DeleteHistory_NamingException:" + ne);
        } catch (SQLException sq) {
            log("DeleteHistory_SQLException: " + sq);
        } catch (NumberFormatException nf) {
            log("DeleteHistory_NumberFormat: " + nf);
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
