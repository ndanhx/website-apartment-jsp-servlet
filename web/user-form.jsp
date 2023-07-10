

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Model.Account" %>
<%

    HttpSession mySession = request.getSession(false);
    if (mySession == null || mySession.getAttribute("acc") == null) {
        response.sendRedirect("Login.jsp");
    }
%>


<html>
    <head>
        <title>User Management </title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <style>
            /* Tùy chỉnh màu cho phần tử radio và label */
            .form-check-input:checked{
                background-color: blue;
                border-color: blue;

            }
            .form-control {
                margin-top: 10px;
            }

            .form-check-label{
                padding-left: 20px;
                margin-bottom: 15px;
                font-size: 17px;
            }

            /* Tùy chỉnh kích thước của phần tử radio */
            .form-check-input {
                width: 1.25em;
                border-color: blue;
                height: 1.25em;
            }
            input[type=checkbox]{
                transform: scale(1.4);


            }





        </style>
    </head>
    <body>

        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center" >Chỉnh sửa tài khoản người dùng</h3>
                    <form action="UpdateAccountCtr" method="post">
                        <fieldset class="form-group">
                            <label>Tên đăng nhập</label> 
                            <input type="text"
                                   value="${user.getUserName()}" readonly  class="form-control"
                                   name="username" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Họ và tên</label> 
                            <input type="text"
                                   value="${user.name}"   class="form-control"
                                   name="fullname" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Số điện thoại</label> 
                            <input type="text"
                                   value="${user.getSDT()}"   class="form-control"
                                   name="sdt" required="required" pattern="0[0-9]{9}">
                        </fieldset>  

                        <fieldset class="form-group">
                            <label>Email</label> 
                            <input type="text"
                                   value="${user.getEmail()}"   class="form-control"
                                   name="email" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Số dư</label>
                            <input type="text"    value="${user.getSoDu()}"  class="form-control" name="sodu" required="required" onkeyup="formatNumber(this)" onkeypress="return isNumber(event)" maxlength="11">
                        </fieldset>
                        <script>
                            function formatNumber(obj) {
                                var num = parseFloat(obj.value.replace(/,/g, ''));
                                if (!isNaN(num)) { // Nếu giá trị là một số hợp lệ, định dạng lại số và cập nhật giá trị
                                    obj.value = num.toLocaleString('en-US');
                                }
                            }
                            function isNumber(evt) {
                                var charCode = (evt.which) ? evt.which : evt.keyCode;
                                var currentValue = evt.target.value.replace(/,/g, '');
                                var newValue = currentValue + String.fromCharCode(charCode);
                                var num = parseFloat(newValue);
                                if (num > 999999999) {
                                    evt.preventDefault(); // Ngăn không cho nhập giá trị lớn hơn 999 tỷ
                                    return false;
                                }
                                return true;
                            }
                        </script>

                        <fieldset class="form-group">
                            <legend>Chức vụ</legend>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="role" id="seller" value="1" ${user.getSeller() == 1 ? 'checked' : ''}>
                                <label class="form-check-label" for="seller">
                                    Người cho thuê
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="role" id="normal" value="0" ${user.getSeller() == 0 ? 'checked' : ''}>
                                <label class="form-check-label" for="normal">
                                    Người thuê
                                </label>
                            </div>
                        </fieldset >
                        <hr>
                        <fieldset class="form-group">
                            <input type="checkbox" id="vehicle1" name="admin" value="0" ${user.getRole() == 0 ? 'checked' : ''}>
                            <label class="form-check-label" for="vehicle1" >Làm kiểm duyệt viên</label><br>
                        </fieldset>
                        <button type="submit" class="btn btn-success">Cập nhật thông tin</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>