/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Search;

import DAO.BaiDangDAO;
import DAO.CanHoDAO;
import DAO.HinhAnhDAO;
import Model.BaiDang;
import Model.CanHo;
import Model.HinhAnh;
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
@WebServlet(name = "LoadMoreCtr", urlPatterns = {"/Loading"})
public class LoadMoreCtr extends HttpServlet {

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
        
        String getsoluong = request.getParameter("exits");
        
        int int_soluong = Integer.parseInt(getsoluong);
        CanHoDAO chdao = new CanHoDAO();
        
        BaiDangDAO baidangdao  = new BaiDangDAO();
        List<BaiDang> gettop3 = baidangdao.getNext3CanHo(int_soluong);
        HinhAnhDAO hinhanhdao = new HinhAnhDAO();
        
        
        
        PrintWriter out = response.getWriter();
        int count = 0;
        for(BaiDang o : gettop3){
            
            count++;
            HinhAnh image  = hinhanhdao.get1ImageByIDCAnHo(o.getIdCanHo());
            CanHo ch = chdao.getAllCanHoByID(o.getIdCanHo());
            out.println("<div class=\"load3 apart__list-items\">\n" +
"                        <div class=\"apart__items-img\">\n" +
"                            <a href=\"DetailCtr?id_baidang="+o.getIdBaidang()+"\"><img src=\""+image.getDuongDan()+"\"\n" +
"                                    alt=\"Lỗi\"></a>\n" +
"                        </div>\n" +
"                        <div class=\"apart__items-caption\">\n" +
"                            <h3 class=\"apart__caption-title\">\n" +
"                                <a href=\"DetailCtr?id_baidang="+o.getIdBaidang()+"\">"+o.getTenTieuDe()+"</a>\n" +
"                            </h3>\n" +
"\n" +
"\n" +
"                            <div class=\"apart__caption-bottom\">\n" +
"                                <p class=\"price\">"+ch.getGiaTien()+" VNÐ</p>\n" +
"                                <div class=\"apart__bottom-info\">\n" +
"                                    \n" +
"                                    <div class=\"info\">\n" +
"                                        <i class=\"icon\"> <img srcset=\"./img/img-luxstate/icon-bed.png 2x\"\n" +
"                                                alt=\"icon-bed\"></i>\n" +
"                                        <p>"+ch.getSoPhong()+" Phòng ngủ</p>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </div>");
                
            }//end for
        if(count==0){
                 out.println("<h1 class=\"thongbao-het\">Hết</h1>");
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
