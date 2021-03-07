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
@WebServlet(name = "SearchByDateServler", urlPatterns = {"/SearchByDateServler"})
public class SearchByDateServler extends HttpServlet {

    private final String HISTORYPAGE = "historyPage";

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
        String url = mapping.get(HISTORYPAGE);
        java.util.Date createDate = null;
        try {
            HttpSession session = request.getSession();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            OrderDAO dao = new OrderDAO();
            String email = request.getParameter("txtEmail");
            String date = request.getParameter("orderDate");
            if (!date.isEmpty()) {
                createDate = (java.util.Date) sd.parse(date);
                if (email != null) {
                    ArrayList<HistoryDTO> lisHis = dao.getSearchDate(email, new java.sql.Date(createDate.getTime()));
                    for (HistoryDTO lisHi : lisHis) {
                        lisHi.setRating(dao.getRating(email, lisHi.getIdCar()));
                    }
                    session.setAttribute("HISTORY", lisHis);
                }
            } else {
                request.setAttribute("SEARCH_DATE", "Input CreateDate to Find");
                session.setAttribute("HISTORY", null);
            }
        } catch (NamingException ne) {
            log("Seach_By_name: " + ne);
        } catch (SQLException sq) {
            log("Search_By_name_SQLException: " + sq);
        } catch (ParseException pe) {
            log("Search_By_name_ParseException: " + pe);
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
