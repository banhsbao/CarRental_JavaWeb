/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.UserDAO;
import car.erros.RegisterErrors;
import car.mail.SendMailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REG_SUCCESS = "index";
    private final String REG_FALT = "registerPage";

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
        String url = Mapping.get(REG_FALT);
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String name = request.getParameter("txtName");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String confirm = request.getParameter("txtConfirm");
        boolean errosFound = false;
        RegisterErrors error = new RegisterErrors();
        try {
            if (!car.utils.Valids.checkEmail(email)) {
                errosFound = true;
                error.setEmailFormatError("Incorrect email format! [Ex: xxxx@gmail.com]");
            }
            if (password.length() < 6 || password.length() > 30) {
                errosFound = true;
                error.setPasswordLengthError("Password's characters [6 to 30]");
            }
            if (name.trim().length() < 1 || name.length() > 30) {
                errosFound = true;
                error.setNameLengthError("User's Name characters [1 to 30]");
            }
            if (!phone.trim().matches("^\\d{10}$")) {
                errosFound = true;
                error.setPhoneFormatError("User's Phone format! [Ex: 0356773***]");
            }
            if (address.trim().length() < 1 || address.length() > 80) {
                errosFound = true;
                error.setAddressLengthError("User's Address characters [1 to 80]");
            }
            if (!password.trim().equals(confirm.trim())) {
                errosFound = true;
                error.setConfirmNoMatchError("Confirm Password is incorrect");
            }
            if (errosFound) {
                request.setAttribute("REGISTER_FAIL", error);
                url = Mapping.get(REG_FALT);
            } else {
                UserDAO dao = new UserDAO();
                String verifyCode = car.utils.Valids.getID("USER");
                SendMailDAO mailDAO = new SendMailDAO();
                String msg = "Hello " + name + "," + "\n"
                        + "Please enter the password below on the login screen to be verified!" + "\n";
                mailDAO.sendMail("bhanimewallpaper2000@gmail.com", "Zoro06061990", email, "[CAR RENTAL] VERIFY MAIL", msg + "Yourcode: " + verifyCode);
                System.out.println("email: " + email + "password: " + password + "phone: " + phone + "name: " + name + "address: " + address + "Verify: " + verifyCode);
                boolean result = dao.register(email, password, phone, name, address, verifyCode, "New");
                if (result) {
                    url = Mapping.get(REG_SUCCESS);
                }
            }
        } catch (NamingException ne) {
            log("RegisterServlet_NamingException: " + ne);
        } catch (SQLException sq) {
            String msg = sq.getMessage();
            if (msg.contains("duplicate")) {
                error.setEmailAlreadyExist("Account already exists");
                request.setAttribute("REGISTER_FAIL", error);
            }
            log("RegisterServlet_SQLException: " + sq);
        } catch (AddressException ae) {
            log("RegisterServlet_AddresException: " + ae);
        } catch (MessagingException me) {
            log("RegisterServlet_MessagingException: " + me);
        } catch (Exception e) {
            log("RegisterServlet_Exception: " + e);
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
