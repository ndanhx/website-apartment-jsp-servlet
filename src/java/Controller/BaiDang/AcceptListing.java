/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.BaiDang;

import DAO.BaiDangDAO;
import DAO.CanHoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Home
 */
@WebServlet(name = "AcceptListing", urlPatterns = {"/AcceptListing"})
public class AcceptListing extends HttpServlet {

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
        String idbaidang = request.getParameter("idbaidang");
        String idcanho = request.getParameter("idcanho");
        CanHoDAO canhodao = new CanHoDAO();

        BaiDangDAO baidangdao = new BaiDangDAO();

        int check = canhodao.AcceptTrangThaiDang_Apartment(idcanho);

        if (check > 0) {
            int checkTrangThai = baidangdao.Accept_Listing(idbaidang);

            if (checkTrangThai > 0) {
                String message = "Duyệt bài đăng thành công!";
                request.setAttribute("success", message);
                request.getRequestDispatcher("GetAllBaiDang").forward(request, response);
            } else {
                String message = "Duyệt Bài đăng thất bại!";
                request.setAttribute("error", message);
                request.getRequestDispatcher("GetAllBaiDang").forward(request, response);
            }

        } else {
            String message = "Duyệt Bài đăng thất bại!";
            request.setAttribute("error", message);
            request.getRequestDispatcher("GetAllBaiDang").forward(request, response);
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
