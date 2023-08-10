/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.ApartmentSeller;

import DAO.CanHoDAO;
import DAO.DiaChiDAO;
import Model.CanHo;
import Model.Tinh_ThanhPho;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Home
 */
@WebServlet(name = "AddApartBySeller", urlPatterns = {"/AddApartBySeller"})
public class AddApartBySeller extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        
        String username = request.getParameter("username");
        String tencanho = request.getParameter("tencanho");
        String sophong = request.getParameter("sophong");
        String dientich = request.getParameter("dientich");
        String giatien = request.getParameter("giatien");
        String mota = request.getParameter("mota");
        String ward = request.getParameter("ward");
        int trangthaithue = 0;
        String rdtrangthai = request.getParameter("trangthai");
        if (rdtrangthai.equals("0")) {
            trangthaithue = 0;
        } else if (rdtrangthai.equals("1")) {
            trangthaithue = 1;
        }

        CanHoDAO canhodao = new CanHoDAO();
        int check = canhodao.AddApartment(tencanho, sophong, dientich, giatien, mota, trangthaithue, ward, username);
        if (check > 0) {
            CanHoDAO chDAO = new CanHoDAO();
            List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

            DiaChiDAO diachiD = new DiaChiDAO();
            List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

            String message = "Thêm căn hộ thành công!";
            request.setAttribute("success", message);
            request.setAttribute("listThanhPho", listTp);
            request.setAttribute("getall", getall);
            request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
        } else if (check <= 0) {
            CanHoDAO chDAO = new CanHoDAO();
            List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

            DiaChiDAO diachiD = new DiaChiDAO();
            List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

            String message = "Thêm căn hộ thất bại! Vui lòng xem lại thông tin canw hộ trước khi thêm!";
            request.setAttribute("error", message);
            request.setAttribute("listThanhPho", listTp);
            request.setAttribute("getall", getall);
            request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
        }
        
        
        
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
