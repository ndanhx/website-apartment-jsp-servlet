/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Profile;

import DAO.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
@WebServlet(name = "ChangePassWord", urlPatterns = {"/change-pass-user-account"})
public class ChangePassWord extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");
        AccountDAO accountdao = new AccountDAO();

        Account account = new Account();

        int check = 0;

        try {
            account = accountdao.CheckAccountIsNull(username);
            if (accountdao.MaHoaMD5(password).equals(account.getPassWord())) {
                check = accountdao.ChangePasswordByUserProfile(username, accountdao.MaHoaMD5(newpassword));
                if (check > 0) {
                    account = accountdao.CheckAccountIsNull(username);
                    String message = "Đổi mật khẩu thành công!";
                    request.setAttribute("success", message);
                    request.setAttribute("account", account);
                    request.getRequestDispatcher("user-profile.jsp").forward(request, response);
                }

            } else {

                account = accountdao.CheckAccountIsNull(username);
                String message = "Mật khẩu hiện tại không đúng!";
                request.setAttribute("error", message);
                request.setAttribute("account", account);
                request.getRequestDispatcher("user-profile.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ChangePassWord.class.getName()).log(Level.SEVERE, null, ex);
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
