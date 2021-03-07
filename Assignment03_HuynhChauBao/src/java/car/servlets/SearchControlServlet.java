/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.dtos.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
@WebServlet(name = "SearchControlServlet", urlPatterns = {"/SearchControlServlet"})
public class SearchControlServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search";
    private final String SEARCH_BY_NAME = "searchName";
    private final String SEARCH_BY_CATEGORY = "searchCategory";

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
            String choice = request.getParameter("radioSelec");
            String textRentalDate = request.getParameter("txtRentalDate");
            String textReturnDate = request.getParameter("txtReturnDate");
            String name = request.getParameter("txtCarName");
            String amountText = request.getParameter("txtAmount");
            HttpSession session = request.getSession();
            session.setAttribute("CARNAME", name);
            session.setAttribute("AMOUNT", amountText);
            session.setAttribute("RENTALDATE", textRentalDate);
            session.setAttribute("RETURNDATE", textReturnDate);
            String categoryID = request.getParameter("txtCategory");
            ArrayList<CategoryDTO> listCategory = (ArrayList<CategoryDTO>) session.getAttribute("LIST_CATEGORY");
            for (int i = 0; i < listCategory.size(); i++) {
                if (listCategory.get(i).getId().equals(categoryID)) {
                    Collections.swap(listCategory, 0, i);
                }
            }
            session.setAttribute("LIST_CATEGORY", listCategory);
            if (choice != null) {
                if (choice.trim().equals("nameAmount")) {
                    url = mapping.get(SEARCH_BY_NAME);
                    session.setAttribute("SEARCH_BY", 1);
                } else if (choice.trim().equals("categoryAmount")) {
                    url = mapping.get(SEARCH_BY_CATEGORY);
                    session.setAttribute("SEARCH_BY", 0);
                }
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
