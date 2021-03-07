/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.CarDAO;
import car.dtos.CarDTO;
import car.dtos.CategoryDTO;
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
@WebServlet(name = "LoadAllServlet", urlPatterns = {"/LoadAllServlet"})
public class LoadAllServlet extends HttpServlet {

    private final String SEARCH_PAGE = "searchPage";
    private final int NUMBER_PAGE = 20;

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
            String pageNumber = request.getParameter("PageNumber");
            int page = 1;
            if (pageNumber != null) {
                page = Integer.parseInt(pageNumber);
            }
            CarDAO dao = new CarDAO();
            int totalPage;
            int countCar = dao.countAllCar();
            if (countCar % NUMBER_PAGE != 0) {
                totalPage = (countCar / NUMBER_PAGE) + 1;
            } else {
                totalPage = (countCar / NUMBER_PAGE);
            }
            ArrayList<CarDTO> listCar = dao.getAllCar(page);
            for (CarDTO carDTO : listCar) {
                float ratingScore = dao.ratingCar(carDTO.getId());
                carDTO.setRating(ratingScore);
            }
            ArrayList<CategoryDTO> listCatgory = dao.getCategory();
            HttpSession session = request.getSession();
            session.setAttribute("LIST_CATEGORY", listCatgory);
            session.setAttribute("TOTAL_PAGE", totalPage);
            session.setAttribute("PAGE_SELECT", page);
            session.setAttribute("LIST_CAR", listCar);
        } catch (NamingException ne) {
            log("LoadAllServlet_NamingException: " + ne);
        } catch (SQLException sq) {
            log("LoadALlServlet_SQLException: " + sq);
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
