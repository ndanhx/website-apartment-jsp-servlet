/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Image;

import DAO.HinhAnhDAO;
import Model.HinhAnh;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Home
 */
@WebServlet(name = "insertImage", urlPatterns = {"/insertImage"})
@MultipartConfig
public class insertImage extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String id_canho = request.getParameter("idcanho");
        String ten_anh = request.getParameter("ten_anh");
        HinhAnhDAO hinhanhdao = new HinhAnhDAO();
        HinhAnh image = new HinhAnh();
        int check = -1;
        List<Part> listFilePart = request.getParts().stream()
                .filter(part -> "file".equals(part.getName()))
                .collect(Collectors.toList());
        for (Part filePart : listFilePart) {
            String fileName = Paths.get(filePart.getSubmittedFileName())
                                    .getFileName()
                                    .toString();
            
            String savePath = "images/" + fileName;

            image.setIdCanHo(id_canho);
            image.setDuongDan(savePath);
            image.setTenHinhAnh(ten_anh);
            check = hinhanhdao.InsertImage(image);

        }
        if (check > 0) {
            String message = "Thành công!";
            String script = "<script>alert('" + message + "'); window.location='Home';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }else{
            String message = "Thất bại!";
            String script = "<script>alert('" + message + "'); window.location='Home';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
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
