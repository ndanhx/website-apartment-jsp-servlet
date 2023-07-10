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
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
@WebServlet(name = "SignupCtr", urlPatterns = {"/SignupCtr"})
public class SignupCtr extends HttpServlet {

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
        //đăng kí
        int seller = 0;
        String userType = request.getParameter("userType");
        if(userType.equals("customer")){
          seller = 0;
        } else if(userType.equals("seller")){
           seller = 1;
        } 
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String name=request.getParameter("fullname");
        String repass=request.getParameter("confirm-password");      
        String sdt=request.getParameter("sdt");
        String email=request.getParameter("email");
        AccountDAO accountDAO= new AccountDAO();
        if (password.equals(repass)){
            Account checkAccountisValue = accountDAO.CheckAccountisValue(username);
            if (checkAccountisValue==null) {
                try {
                    accountDAO.Signup(username,accountDAO.MaHoaMD5(password),name,sdt,email,seller);
                    // Thiết lập thông báo
                    String message = "Đăng ký thành công!";
                    // Tạo đoạn mã JavaScript chuyển hướng và hiển thị thông báo
                    String script = "<script>alert('" + message + "'); window.location='Login.jsp';</script>";
                    // Gửi đoạn mã JavaScript về trình duyệt
                    response.setContentType("text/html");
                    response.getWriter().write(script);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(SignupCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                    String message = "Tên đăng nhập đã được sử dụng!";
                    // Tạo đoạn mã JavaScript chuyển hướng và hiển thị thông báo
                    String script = "<script>alert('" + message + "');  window.location='Login.jsp';</script>";
                    // Gửi đoạn mã JavaScript về trình duyệt
                    response.setContentType("text/html");
                    response.getWriter().write(script);
            }
        }else{
              String message = "Nhập lại mật khẩu sai!";
                // Tạo đoạn mã JavaScript chuyển hướng và hiển thị thông báo
                String script = "<script>alert('" + message + "');  window.location='Login.jsp';</script>";
                // Gửi đoạn mã JavaScript về trình duyệt
                response.setContentType("text/html");
                response.getWriter().write(script);
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
