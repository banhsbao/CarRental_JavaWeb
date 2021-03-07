/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.servlets;

import car.daos.CarDAO;
import car.dtos.CarDTO;
import car.dtos.CartDTO;
import car.erros.SearchError;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SearchByNameServlet", urlPatterns = {"/SearchByNameServlet"})
public class SearchByNameServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String textRentalDate = request.getParameter("txtRentalDate");
        String textReturnDate = request.getParameter("txtReturnDate");
        String name = request.getParameter("txtCarName");
        String amountText = request.getParameter("txtAmount");
        String pageNumber = request.getParameter("PageNumber");
        String url = mapping.get(SEARCH_PAGE);
        boolean isError = false;
        SearchError errors = new SearchError();
        Date rentalDate = null;
        Date returnDate = null;
        try {
            if (textRentalDate == null) {
                isError = true;
                errors.setRentalDateIsEmpty("Please enter RentalDate!");
            }
            if (textReturnDate == null) {
                isError = true;
                errors.setReturnDateISEmpty("Please enter ReturnDate!");
            }
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            if (textRentalDate != null && textReturnDate != null) {
                rentalDate = (Date) sd.parse(textRentalDate);
                returnDate = (Date) sd.parse(textReturnDate);
                Date currentDate = new Date();
                if (rentalDate.compareTo(returnDate) > 0) {
                    isError = true;
                    errors.setRentalDateLargethanReturnDate("Please enter RentalDate smaller than ReturnDate");
                }
                if (rentalDate.compareTo(currentDate) == -1) {
                    isError = true;
                    errors.setRentalDateSmallerthanCurrentDate("Please enter RentalDate Large than CurrentDate");
                }
                if (returnDate.compareTo(currentDate) == -1) {
                    isError = true;
                    errors.setReturnDateSmallerthanCurrentDate("Please enter ReturnDate Large than CurrentDate");
                }
                if (returnDate.compareTo(rentalDate) == -1) {
                    isError = true;
                    errors.setReturnDateSmallerthanRentalDate("Please enter ReturnDate large than RentalDate");
                }
            }
            if (name.isEmpty()) {
                isError = true;
                errors.setSearchName("Please enter something to search!");
            }
            int amount = 0;
            if (amountText == null) {
                isError = true;
                errors.setSearchAmount("Please enter amount to search!");
            } else {
                amount = Integer.parseInt(amountText);
            }
            if (amount < 0) {
                isError = true;
                errors.setAmountNumberLargerThanZero("Please enter Number more than 0!");
            }
            if (isError == true) {
                request.setAttribute("ERROR", errors);
                session.setAttribute("TOTAL_PAGE", null);
                session.setAttribute("PAGE_SELECT", null);
                session.setAttribute("LIST_CAR", null);
            } else {
                CarDAO dao = new CarDAO();
                int page = 1;
                if (pageNumber != null) {
                    page = Integer.parseInt(pageNumber);
                }
                ArrayList<CartDTO> listCart = (ArrayList<CartDTO>) session.getAttribute("CART_INFOR");
                int totalPage;
                int countCar = dao.getCountBySearchName(name, page, new java.sql.Date(rentalDate.getTime()), new java.sql.Date(returnDate.getTime()), listCart);
                if (countCar % NUMBER_PAGE != 0) {
                    totalPage = (countCar / NUMBER_PAGE) + 1;
                } else {
                    totalPage = (countCar / NUMBER_PAGE);
                }
                ArrayList<CarDTO> listCar = dao.searchCarByName(name, page, amount, new java.sql.Date(rentalDate.getTime()), new java.sql.Date(returnDate.getTime()), listCart);
                if (listCar != null) {
                    for (CarDTO carDTO : listCar) {
                        float ratingScore = dao.ratingCar(carDTO.getId());
                        carDTO.setRating(ratingScore);
                    }
                }
                session.setAttribute("TOTAL_PAGE", totalPage);
                session.setAttribute("PAGE_SELECT", page);
                session.setAttribute("LIST_CAR", listCar);
            }
        } catch (NamingException ne) {
            log("SearchByName_NamingException: " + ne);
        } catch (SQLException sq) {
            log("SearchByName_SQLException: " + sq);
        } catch (NumberFormatException ne) {
            log("SearchByName_NumberFormatException: " + ne);
            errors.setAmountNumberType("Please enter Number Type!");
            session.setAttribute("TOTAL_PAGE", null);
            session.setAttribute("PAGE_SELECT", null);
            session.setAttribute("LIST_CAR", null);
            request.setAttribute("ERROR", errors);
        } catch (ParseException ex) {
            Logger.getLogger(SearchByNameServlet.class.getName()).log(Level.SEVERE, null, ex);
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
