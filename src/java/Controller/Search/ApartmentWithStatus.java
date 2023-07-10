/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Search;

import DAO.BaiDangDAO;
import Model.BaiDang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Home
 */
@WebServlet(name = "ApartmentWithStatus", urlPatterns = {"/ApartmentWithStatus"})
public class ApartmentWithStatus extends HttpServlet {

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

        BaiDangDAO baidangdao = new BaiDangDAO();

        String valueString = request.getParameter("status");
        PrintWriter out = response.getWriter();
        int status = Integer.parseInt(valueString);
        if (status == 3) {
            List<BaiDang> list_baidang = baidangdao.getAll_BaiDangManager();
            for (BaiDang o : list_baidang) {
                String a = "green;\">Đang đăng";
                if (o.getTrangThai() == 0) {
                    a = "red;\">Chờ duyệt";
                }

                out.println("<tr id=\"dataShow\">\n"
                        + "    <td>" + o.getIdBaidang() + "</td>\n"
                        + "    <td>" + o.getTenTieuDe() + "</td>\n"
                        + "    <td>" + o.getNgayDangBai() + "</td>\n"
                        + "    <td style=\"color:" + a + "</td>\n"
                        + "    <td>" + o.getIdCanHo() + "</td>\n"
                        + "    <td>\n"
                        + "        <a class=\"btn btn-success\" href=\"#\" onclick=\"myPopup_baidang("+o.getIdBaidang()+",'"+o.getTenTieuDe()+"')\">Edit</a> &nbsp;&nbsp;\n"
                        + "        <a class=\"btn btn-danger\" href=\"DeleteListing?idbaidang=" + o.getIdBaidang() + "&idcanho=" + o.getIdCanHo() + "\">Delete</a>&nbsp;&nbsp;\n");
                if (o.getTrangThai() == 0) {
                    out.println("        <a class=\"btn btn-success\" href=\"#\" onclick=\"togglePopup_dangtin(" + o.getIdBaidang() + "," + o.getIdCanHo() + ")\">Accept</a>\n");
                }
                out.println("    </td>\n"
                        + "</tr>");

            }
        }
        else{
            List<BaiDang> list_baidang = baidangdao.getBaiDangBy_StausPosted(status);
            for (BaiDang o : list_baidang) {
                String a = "green;\">Đang đăng";
                if (o.getTrangThai() == 0) {
                    a = "red;\">Chờ duyệt";
                }

                out.println("<tr id=\"dataShow\">\n"
                        + "    <td>" + o.getIdBaidang() + "</td>\n"
                        + "    <td>" + o.getTenTieuDe() + "</td>\n"
                        + "    <td>" + o.getNgayDangBai() + "</td>\n"
                        + "    <td style=\"color:" + a + "</td>\n"
                        + "    <td>" + o.getIdCanHo() + "</td>\n"
                        + "    <td>\n"
                        + "        <a class=\"btn btn-success\" href=\"#\" onclick=\"myPopup_baidang("+o.getIdBaidang()+",'"+o.getTenTieuDe()+"')\">Edit</a> &nbsp;&nbsp;\n"
                        + "        <a class=\"btn btn-danger\" href=\"DeleteListing?idbaidang=" + o.getIdBaidang() + "&idcanho=" + o.getIdCanHo() + "\">Delete</a>&nbsp;&nbsp;\n");
                if (o.getTrangThai() == 0) {
                    out.println("        <a class=\"btn btn-success\" href=\"#\" onclick=\"togglePopup_dangtin(" + o.getIdBaidang() + "," + o.getIdCanHo() + ")\">Accept</a>\n");
                }
                out.println("    </td>\n"
                        + "</tr>");

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
