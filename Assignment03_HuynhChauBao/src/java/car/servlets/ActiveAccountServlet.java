/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.UserDAO;
import car.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "ActiveAccountServlet", urlPatterns = {"/ActiveAccountServlet"})
public class ActiveAccountServlet extends HttpServlet {

    private final String ACTIVE_FAIL = "activePage";
    private final String ACTIVE_SUCCESS = "search";

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
        HashMap<String, String> mapping = (HashMap<String, String>) context.getAttribute("MAPPING");
        String url = mapping.get(ACTIVE_FAIL);
        String verifyCode = request.getParameter("txtVerifyCode");
        String userCode = null;
        String userEmail = null;
        try {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession(false);
            UserDTO currenUser = (UserDTO) session.getAttribute("USER_ADMIN");
            if (currenUser != null) {
                userCode = dao.getVerifyCode(currenUser.getEmail());
                userEmail = currenUser.getEmail();
            }

            if (verifyCode != null) {
                if (verifyCode.trim().equals(userCode)) {
                    boolean result = dao.changeStatus(userEmail);
                    if (result) {
                        System.out.println("ACtive thanh cong");
                        currenUser.setStatus("Active");
                        session.setAttribute("USER_ADMIN", currenUser);
                        url = mapping.get(ACTIVE_SUCCESS);
                    }
                } else {
                    request.setAttribute("ACTIVE_FAIL", "Incorrect verify code!");
                }
            } else {
                request.setAttribute("ACTIVE_FAIL", "Please enter verify code!");
            }
        } catch (NamingException ne) {
            log("ActiveAccountServlet_NamingException: " + ne);
        } catch (SQLException sq) {
            log("ActiveAccountServlet_SQLException: " + sq);
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
