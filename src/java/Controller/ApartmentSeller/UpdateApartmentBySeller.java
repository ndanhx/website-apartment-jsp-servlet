/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.ApartmentSeller;

import DAO.BaiDangDAO;
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
@WebServlet(name = "UpdateApartmentBySeller", urlPatterns = {"/UpdateApartmentBySeller"})
public class UpdateApartmentBySeller extends HttpServlet {

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
        String id_canho = request.getParameter("id_canho");
        String tencanho = request.getParameter("tencanho");
        String sophong = request.getParameter("sophong");
        String dientich = request.getParameter("dientich");
        String giatien = request.getParameter("giatien");
        String mota = request.getParameter("mota");
        String ward = request.getParameter("ward");
        BaiDangDAO baidangdao = new BaiDangDAO();

        int trangthaithue = 0;
        String rdtrangthai = request.getParameter("trangthai");
        if (rdtrangthai.equals("0")) {
            trangthaithue = 0; //chưa được thuê

            CanHoDAO canhodao = new CanHoDAO();
            int check = canhodao.UpdateApartment(tencanho, sophong, dientich, giatien, mota, trangthaithue, ward, id_canho);

            if (check > 0) {

                CanHoDAO chDAO = new CanHoDAO();
                List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                DiaChiDAO diachiD = new DiaChiDAO();
                List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                String message = "Cập nhật thông tin căn hộ thành công!";
                request.setAttribute("success", message);
                request.setAttribute("listThanhPho", listTp);
                request.setAttribute("getall", getall);
                request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
            } else if (check <= 0) {

                CanHoDAO chDAO = new CanHoDAO();
                List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                DiaChiDAO diachiD = new DiaChiDAO();
                List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                String message = "Lỗi khi cập nhật thông tin căn hộ!";
                request.setAttribute("error", message);
                request.setAttribute("listThanhPho", listTp);
                request.setAttribute("getall", getall);
                request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
            }

        } else if (rdtrangthai.equals("1")) {
            trangthaithue = 1; // đã được thuê

            CanHoDAO canhodao = new CanHoDAO();
            int check = canhodao.UpdateApartment(tencanho, sophong, dientich, giatien, mota, trangthaithue, ward, id_canho);
            int checkiupdate = baidangdao.DeleteTrangThaiKhiDoiTrangThaiThue(id_canho);

            if (check > 0 && checkiupdate > 0) {

                CanHoDAO chDAO = new CanHoDAO();
                List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                DiaChiDAO diachiD = new DiaChiDAO();
                List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                String message = "Cập nhật thông tin căn hộ thành công!";
                request.setAttribute("success", message);
                request.setAttribute("listThanhPho", listTp);
                request.setAttribute("getall", getall);
                request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
            } else if (check <= 0 && checkiupdate <= 0) {

                CanHoDAO chDAO = new CanHoDAO();
                List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                DiaChiDAO diachiD = new DiaChiDAO();
                List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                String message = "Lỗi khi cập nhật thông tin căn hộ!";
                request.setAttribute("error", message);
                request.setAttribute("listThanhPho", listTp);
                request.setAttribute("getall", getall);
                request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
            }
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
