/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Account;

import DAO.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
@WebServlet(name = "AddAccount", urlPatterns = {"/AddAccount"})
public class AddAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int seller = 0;
        String role = request.getParameter("role");
        if (role.equals("0")) {
            seller = 0;
        } else if (role.equals("1")) {
            seller = 1;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("fullname");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String soduString = request.getParameter("sodu");
        int int_sodu = Integer.parseInt(soduString);

        String adminString = request.getParameter("admin");
        int admin = 1;
        if (adminString != null) {
            admin = Integer.parseInt(adminString);
        }

        AccountDAO accountDAO = new AccountDAO();
        try {
            accountDAO.AddAccountByAdmin(username, AccountDAO.MaHoaMD5(password), name, sdt, email, int_sodu, seller, admin);
            AccountDAO accountdao = new AccountDAO();
            int count = accountdao.GetTotalAccount();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            List<Account> listac = accountdao.getAllAccountChoose10Account(1);
            String message = "Thêm tài khoản thành công";
            request.setAttribute("success", message);
            request.setAttribute("listUser", listac);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("user-list.jsp").forward(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignupCtr.class.getName()).log(Level.SEVERE, null, ex);
            AccountDAO accountdao = new AccountDAO();
            int count = accountdao.GetTotalAccount();
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            List<Account> listac = accountdao.getAllAccountChoose10Account(1);
            String message = "Thêm tài khoản không thành công";
            request.setAttribute("error", message);
            request.setAttribute("listUser", listac);
            request.setAttribute("endPage", endPage);
            request.getRequestDispatcher("user-list.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
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
