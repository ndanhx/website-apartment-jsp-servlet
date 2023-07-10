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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
@WebServlet(name = "AccountCtr", urlPatterns = {"/AccountCtr"})
public class AccountCtr extends HttpServlet {

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
        String id = request.getParameter("id");
        if (id.equals("dangxuat") && id != null) {
            HttpSession session = request.getSession();
            session.invalidate(); // Xóa session
            // Chuyển hướng đến trang đăng nhập
            String contextPath = request.getContextPath();
            String homePath = contextPath + "/Home";
            response.sendRedirect(homePath);
            System.out.println("Redirecting to home page");
        }

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
        try {
            processRequest(request, response);
            response.setContentType("text/html;charset=UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AccountDAO accountDAO = new AccountDAO();

            Account checkLogin = accountDAO.Login(username, accountDAO.MaHoaMD5(password).trim());
            if (checkLogin == null) {
                String message = "Sai tên tài khoản hoặc mật khẩu!";
                String script = "<script>alert('" + message + "'); window.location='Login.jsp';</script>";
                response.setContentType("text/html");
                response.getWriter().println("<html><head><title>Thông báo</title></head><body>" + script + "</body></html>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("acc", checkLogin);
                session.setMaxInactiveInterval(10000);
                response.sendRedirect("Home");  
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountCtr.class.getName()).log(Level.SEVERE, null, ex);
        }  //end đăng nhập/xuất

    }//end class

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
