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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_FAIL = "loginPage";
    private final String LOGIN_SUCCESS = "search";
    private final String ACTIVE_PAGE = "activePage";

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
        Map<String, String> Mapping = (Map<String, String>) context.getAttribute("MAPPING");
        String url = Mapping.get(LOGIN_FAIL);
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            HttpSession session = request.getSession();
            if (email != null && password != null) {
                UserDAO dao = new UserDAO();
                UserDTO currentUser = dao.loginUser(email, password);
                session.setAttribute("USER_ADMIN", currentUser);
                if (currentUser != null) {
                    if (!currentUser.getStatus().equals("Active")) {
                        url = Mapping.get(ACTIVE_PAGE);
                    } else {
                        url = Mapping.get(LOGIN_SUCCESS);
                    }
                } else {
                    request.setAttribute("LOGIN_FAIL", "Email/Password is incorrect!");
                    url = Mapping.get(LOGIN_FAIL);
                }
            }
            System.out.println(url);
        } catch (NamingException ne) {
            log("LoginServlet_NamingException: " + ne);
        } catch (SQLException sq) {
            log("LoginServlet_SQLException: " + sq);
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
