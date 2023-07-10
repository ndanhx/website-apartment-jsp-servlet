/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Search;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SearchAjax2", urlPatterns = {"/SearchAjax2"})
public class SearchAjax2 extends HttpServlet {

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

        int count = 0;
        String type = request.getParameter("type");

        String id_thanhpho = request.getParameter("id_thanhpho");

        if (type.equals("district")) {

            DiaChiDAO diachiDao = new DiaChiDAO();

            List<Quan_Huyen> listqh = diachiDao.getQuanHuyenByIdTinh_TP(id_thanhpho);

            PrintWriter out = response.getWriter();

            for (Quan_Huyen o : listqh) {
                out.println("<select class=\"form-select form-select-lg mb-3\" id=\"district\" name=\"district\" aria-label=\".form-select-lg\" style=\"font-size: 1.2rem;\">\n"
                        + "                                        <option id=\"quan1\" value=\"" + o.getId_QuanHuyen() + "\">" + o.getTen_QuanHuyen() + "</option>\n"
                        + "                                    </select>");

            }
        } else if (type.equals("canho")) {
            PrintWriter out = response.getWriter();

            BaiDangDAO baidangdao = new BaiDangDAO();
            HinhAnhDAO hinhanhdao = new HinhAnhDAO();

            CanHoDAO canhodao = new CanHoDAO();

            List<BaiDang> listch = baidangdao.getAll_BaiDang();

            for (BaiDang o : listch) {

                HinhAnh image = hinhanhdao.get1ImageByIDCAnHo(o.getIdCanHo());
                CanHo ch = canhodao.getCanHo_ByIDTinhTP(o.getIdCanHo(), id_thanhpho);
                if (ch != null) {
                    count++;

                    out.println("<div class=\"apart__list-items\">\n"
                            + "                        <div class=\"apart__items-img\">\n"
                            + "                            <a href=\"DetailCtr?id_baidang=" + o.getIdBaidang() + "\"><img src=\"" + image.getDuongDan() + "\"\n"
                            + "                                    alt=\"Lỗi\"></a>\n"
                            + "                        </div>\n"
                            + "                        <div class=\"apart__items-caption\">\n"
                            + "                            <h3 class=\"apart__caption-title\">\n"
                            + "                                <a href=\"DetailCtr?id_baidang=" + o.getIdBaidang() + "\">" + o.getTenTieuDe() + "</a>\n"
                            + "                            </h3>\n"
                            + "\n"
                            + "\n"
                            + "                            <div class=\"apart__caption-bottom\">\n"
                            + "                                <p class=\"price\">" + ch.getGiaTien() + " VNÐ</p>\n"
                            + "                                <div class=\"apart__bottom-info\">\n"
                            + "                                    \n"
                            + "                                    <div class=\"info\">\n"
                            + "                                        <i class=\"icon\"> <img srcset=\"./img/img-luxstate/icon-bed.png 2x\"\n"
                            + "                                                alt=\"icon-bed\"></i>\n"
                            + "                                        <p>" + ch.getSoPhong() + " Phòng ngủ</p>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>");

                }
            }
            if (count == 0) {
                out.println("<h1 class=\"thongbao-rong\">Không tìm thấy thông tin</h1>");
            }
        }//end if
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
