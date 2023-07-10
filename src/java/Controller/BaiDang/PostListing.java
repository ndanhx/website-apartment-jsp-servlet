/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.BaiDang;

import DAO.AccountDAO;
import DAO.BaiDangDAO;
import DAO.CanHoDAO;
import DAO.DiaChiDAO;
import DAO.HinhAnhDAO;
import Model.Account;
import Model.CanHo;
import Model.HinhAnh;
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
@WebServlet(name = "PostListing", urlPatterns = {"/PostListing"})
public class PostListing extends HttpServlet {

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
        String idcanho = request.getParameter("idcanho");
        String tieude = request.getParameter("tieude_dangtin");
        String username = request.getParameter("username");
        CanHoDAO canhodao = new CanHoDAO();
        BaiDangDAO baidangdao = new BaiDangDAO();
        HinhAnhDAO hinhanhdao = new HinhAnhDAO();
        AccountDAO accountdao = new AccountDAO();
        Account ac = accountdao.getAccountByID(username);
        HinhAnh image = hinhanhdao.get1ImageByIDCAnHo(idcanho);
        if (image.getIdHinhAnh() != null) {
            if (ac.getSoDu() >= 10000) {
                int runOk = accountdao.UpdateSoDu_AffterPosting(username);
                if (runOk > 0) {

                    int check = canhodao.UpdateTrangThaiDang_Apartment(idcanho);
                    if (check > 0) {
                        int checkTrangThai = baidangdao.AddListing(tieude, idcanho);
                        if (checkTrangThai > 0) {
                            CanHoDAO chDAO = new CanHoDAO();
                            List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                            DiaChiDAO diachiD = new DiaChiDAO();
                            List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                            String message = "Bài đăng sẽ được chờ admin phê duyệt!";
                            request.setAttribute("success", message);
                            request.setAttribute("listThanhPho", listTp);
                            request.setAttribute("getall", getall);
                            request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
                        } else {
                            String message = "Đăng tin thất bại!";
                            String script = "<script>alert('" + message + "'); window.location='Home';</script>";
                            response.setContentType("text/html");
                            response.getWriter().write(script);
                        }

                    } else {
                        accountdao.UpdateSoDu_AffterPosting_IsFail(username);
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
                } else {
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

            }//END IF CHECK SỐ DƯ
            else {
                CanHoDAO chDAO = new CanHoDAO();
                List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

                DiaChiDAO diachiD = new DiaChiDAO();
                List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

                String message = "Thất bại. Vui lòng NẠP TIỀN để thực hiện đăng tin!";
                request.setAttribute("error", message);
                request.setAttribute("listThanhPho", listTp);
                request.setAttribute("getall", getall);
                request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
            }
        } else {
            CanHoDAO chDAO = new CanHoDAO();
            List<CanHo> getall = chDAO.getCanHo_ByUsername(username);

            DiaChiDAO diachiD = new DiaChiDAO();
            List<Tinh_ThanhPho> listTp = diachiD.getAllTinhTP_DAO();

            String message = "Thất bại. Vui lòng UPLOAD HÌNH ẢNH trước khi đăng tin!";
            request.setAttribute("error", message);
            request.setAttribute("listThanhPho", listTp);
            request.setAttribute("getall", getall);
            request.getRequestDispatcher("apart-list-seller.jsp").forward(request, response);
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
