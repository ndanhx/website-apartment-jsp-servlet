/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import DAO.BaiDangDAO;
import DAO.CanHoDAO;
import DAO.DiaChiDAO;
import DAO.HinhAnhDAO;
import Model.*;

/**
 *
 * @author Home
 */
@WebServlet(name = "DetailCtr", urlPatterns = {"/DetailCtr"})
public class DetailCtr extends HttpServlet {

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

        String id_baidang = request.getParameter("id_baidang");

        HinhAnhDAO hinhanhdao = new HinhAnhDAO();

        BaiDangDAO baidangdao = new BaiDangDAO();
        DiaChiDAO diachidao = new DiaChiDAO();
        AccountDAO acdao = new AccountDAO();

        List<HinhAnh> listImage = hinhanhdao
                .getAllImageByIDCanHo(baidangdao
                        .SelectIDCanHoByIDBaiDang(id_baidang));
        CanHoDAO canhodao = new CanHoDAO();
        CanHo canho = new CanHo();

        BaiDang getbaidang = baidangdao.getBaiDang_ByID(id_baidang);
        List<CanHo> listcanho = canhodao.getAllCanHo();

        request.setAttribute("listImage", listImage);
        request.setAttribute("baidang", getbaidang);
        request.setAttribute("listcanho", listcanho);
        request.setAttribute("canho", canho);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);

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
