

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Model.Account" %>
<%

    HttpSession mySession = request.getSession(false);
    if (mySession == null || mySession.getAttribute("acc") == null) {
        response.sendRedirect("Login.jsp");
    }
    else {
     
      Object accObj = mySession.getAttribute("acc");
      if (accObj != null && accObj instanceof Account) {
        Account acc = (Account) accObj;
        int seller = acc.getSeller();
       
      }
    }
%>
<html>
    <head>
        <title>Apartment Management</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <style>
            .form-control {
                margin-top: 10px;
            }
            .list-group{
                max-width: 300px;
            }
        </style>
    </head>
    <body>

        <br>
        <div class="container col-md-5">
            <div class="card">

                <div class="card-body">
                    <h3 class="text-center">Chỉnh sửa Căn hộ</h3>
                    
                    
                    <form action="UpdateApartment" method="post">
                        
                        <fieldset class="form-group">
                            <label>ID căn hộ</label> 
                            <input type="hidden"
                           value="${canho.getUsername()}" readonly  class="form-control"
                           name="username" required="required">
                            <input type="text"
                                   value="${canho.getIdCanHo()}" readonly  class="form-control"
                                   name="id_canho" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Tên căn hộ</label> 
                            <input type="text"
                                   value="${canho.getTenCanHo()}"   class="form-control"
                                   name="tencanho" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Số phòng</label> 
                            <input type="text"
                                   value="${canho.getSoPhong()}"   class="form-control"
                                   name="sophong" required="required">
                        </fieldset>  

                        <fieldset class="form-group">
                            <label>Diện tích</label> 
                            <input type="text"
                                   value="${canho.getDienTich()}"   class="form-control"
                                   name="dientich" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Giá tiền</label> 
                            <input type="text"
                                   value="${canho.getGiaTien()}"   class="form-control"
                                   name="giatien" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mô tả</label> 
                            <input type="text"
                                   value="${canho.getMoTa()}"   class="form-control"
                                   name="mota" required="required">
                        </fieldset>      

                        <fieldset class="form-group">
                            <legend>Địa chỉ</legend> 
                            <ul class="list-group ">
                                <select id="city" name="city"  style="font-size: 1.2rem;" onchange="timdiachi(this)";>
                                    <option value="none">Chọn tỉnh thành</option>  
                                    <c:forEach items="${listThanhPho}" var="thanhpho">
                                        <option value="${thanhpho.getId_tinhTP()}">${thanhpho.getTen_TinhTP()}</option>
                                    </c:forEach>
                                </select>
                                <select id="district" name="district" aria-label=".form-select-lg" style="font-size: 1.2rem;" onchange="timdiachi_quanhuyen(this)";>
                                    <option value="none">Chọn quận huyện</option>  
                                </select>
                                <select  id="ward" name="ward" aria-label=".form-select-lg" style="font-size: 1.2rem;"   >
                                    <option value="" selected>Chọn phường xã</option>
                                </select>
                            </ul>  
                        </fieldset>  
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
                        <script>
                                                function timdiachi(valueIDTinh) {
                                                    $.ajax({
                                                        type: "GET",
                                                        url: "/DangTinThueDangHo/SearchAjax2?type=district",
                                                        data: {
                                                            id_thanhpho: valueIDTinh.value
                                                        },
                                                        success: function (layDataHuyen) {
                                                            var districtContainer = document.getElementById("district");
                                                            districtContainer.innerHTML = layDataHuyen;

                                                        } //end success
                                                    });
                                                }
                                                function timdiachi_quanhuyen(valueQuanHuyen) {
                                                    $.ajax({
                                                        type: "GET",
                                                        url: "/DangTinThueDangHo/SearchAjax3",
                                                        data: {
                                                            id_quan: valueQuanHuyen.value
                                                        },
                                                        success: function (layDataPhuong) {
                                                            var wardContainer = document.getElementById("ward");
                                                            wardContainer.innerHTML = layDataPhuong;
                                                        }
                                                    });
                                                }
                        </script>

                        <fieldset class="form-group">
                            <legend>Trạng thái thuê</legend>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="trangthai" id="seller" value="1" ${canho.getTrangThaiTHUE() == 1 ? 'checked' : ''}>
                                <label class="form-check-label" for="seller">
                                    Đã cho thuê
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="trangthai" id="normal" value="0" ${canho.getTrangThaiTHUE() == 0 ? 'checked' : ''}>
                                <label class="form-check-label" for="normal">
                                    Cho thuê
                                </label>
                            </div>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Cập nhật thông tin</button>

                        <script>

                        </script>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>