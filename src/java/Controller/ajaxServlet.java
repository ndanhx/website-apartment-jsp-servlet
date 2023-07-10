/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Config;
import com.google.gson.*;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Home
 */
@WebServlet(name = "ajaxServlet", urlPatterns = {"/vnpayajax"})
public class ajaxServlet extends HttpServlet {

    Config Config = new Config();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");

        String orderType = "250006";

        String vnp_TxnRef = Config.getRandomNumber(8);

        String vnp_IpAddr = Config.getIpAddress(request);

        String vnp_TmnCode = Config.vnp_TmnCode;

        int amount = Integer.parseInt(request.getParameter("amount")) * 100;

        Map vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
//        String bankCode = request.getParameter("bankCode");
        String bankCode = "";
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);

        String vnp_ExpireDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        String sdt = request.getParameter("SDT");

        String email = request.getParameter("Email");

        vnp_Params.put("vnp_Bill_Mobile", sdt);
        vnp_Params.put("vnp_Bill_Email", email);
        String fullName = request.getParameter("txt_inv_custome");

        if (fullName != null && !fullName.isEmpty()) {
            int idx = fullName.indexOf(' ');
            String firstName = fullName.substring(0, idx);
            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
            vnp_Params.put("vnp_Bill_FirstName", firstName);
            vnp_Params.put("vnp_Bill_LastName", lastName);

        }

        vnp_Params.put("vnp_Bill_Address", "BinhThanh");
        vnp_Params.put("vnp_Bill_City", "TPHCM");
        vnp_Params.put("vnp_Bill_Country", "VN");
        
        if (request.getParameter("txt_bill_state") != null && !request.getParameter("txt_bill_state").isEmpty()) {
            vnp_Params.put("vnp_Bill_State", request.getParameter("txt_bill_state"));
        }
        
        vnp_Params.put("vnp_Inv_Phone", request.getParameter("txt_inv_mobile"));
        vnp_Params.put("vnp_Inv_Email", "hotro@vnpay.vn");
        vnp_Params.put("vnp_Inv_Customer", request.getParameter("txt_inv_customer"));
        vnp_Params.put("vnp_Inv_Address", "BinhThanh, TPHCM");
        vnp_Params.put("vnp_Inv_Company", "Công ty VNPAY");
        vnp_Params.put("vnp_Inv_Taxcode", "20180924080900");
        vnp_Params.put("vnp_Inv_Type", "I");

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();

        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());

        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        com.google.gson.JsonObject job = new com.google.gson.JsonObject();

        job.addProperty("code", "00");

        job.addProperty("message", "success");

        job.addProperty("data", paymentUrl);

        Gson gson = new Gson();

        //test
        response.getWriter().write(gson.toJson(job));

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
