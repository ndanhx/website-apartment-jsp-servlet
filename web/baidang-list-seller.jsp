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
        if(seller == 0){
            response.sendRedirect("Home");
        }
      }
    }
%>
<html>
    <head>
        <title>Apartment Management </title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <!--header-->
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <!--<link rel="stylesheet" href="css/reset.css">-->
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!--end header-->
        <style>
            .list-group{
                max-width: 300px;
            }
            .popup {
                display: none;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }
            .form-control {
                margin-top: 10px;
            }

            .popup .inner {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                width: 80%; /* Thay đổi chiều rộng của form */
                max-width: 600px; /* Giới hạn chiều rộng tối đa của form */
                height: 90%; /* Thay đổi chiều cao của form */
                max-height: 650px; /* Giới hạn chiều cao tối đa của form */
                overflow-y: auto; /* Bật thanh cuộn để tránh lỗi tràn nội dung */
            }
            .pop {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .pop .inn {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                width: 80%; /* Thay đổi chiều rộng của form */
                max-width: 600px; /* Giới hạn chiều rộng tối đa của form */
                height: 90%; /* Thay đổi chiều cao của form */
                max-height: 250px; /* Giới hạn chiều cao tối đa của form */
                overflow-y: auto; /* Bật thanh cuộn để tránh lỗi tràn nội dung */
            }
            .btn-hideform {
                margin-top: 40px;
                text-align: -webkit-center;
            }

            .select-trangthai select {
                padding: 0.5rem 1rem;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                font-size: 1.2rem;
                line-height: 1.5;
                appearance: none;
                background-color: white;
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' fill='currentColor'%3E%3Cpath d='M10 12l-5-5 1.41-1.41L10 9.17l3.59-3.58L15 7z'%3E%3C/path%3E%3C/svg%3E");
                background-repeat: no-repeat;
                background-position: right 0.5rem center;
                background-size: 1rem 1rem;
                width: 180px;
            }
            .select-trangthai select:focus {
                outline: none;
                box-shadow: 0 0 0 0.25rem rgba(0, 123, 255, 0.25);
            }
            .lb-search{
                font-size: 20px;
                padding: 10px;
                padding-right: 20px;
                font-weight: bold;
                color: black;
            }




        </style>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>


        <c:if test="${empty getall }">
            <div class="container" style=" margin-bottom: 500px;">
            </c:if> 
            <c:if test="${not empty getall }">
                <div class="container">
                </c:if> 

                <hr>
                <div style="
                     padding-top: 70px;
                     ">

                    <h3 class="text-center" style="font-size: 30px; font-weight: bold;">Quản lý Bài đăng</h3>
                </div>

                <div >             
                    <% if (request.getAttribute("error") != null) { %>
                    <hr>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-octagon me-1"></i>
                        <%= request.getAttribute("error") %>

                    </div>
                    <% } %>
                </div> 
                <div>
                    <% if (request.getAttribute("success") != null) { %>
                    <hr><div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle me-1"></i>
                        <%= request.getAttribute("success") %> 

                    </div>
                    <% } %>
                </div>

                <br>
                <table class="table table-striped">
                    <thead>
                        <tr class="user-row">
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Ngày đăng</th>
                            <th>Trạng thái</th>
                            <th>ID căn hộ</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody id = "dataShow">
                        <c:forEach var="o" items="${getall}">
                            <tr >
                                <td><c:out value="${o.getIdBaidang()}" /></td>
                                <td><c:out value="${o.getTenTieuDe()}" /></td>
                                <td><c:out value="${o.getNgayDangBai()}" /></td>
                                <c:if test="${o.getTrangThai() == 1 }">
                                    <td style="color: green;">Đang đăng</td>
                                </c:if> 
                                <c:if test="${o.getTrangThai() == 0 }">
                                    <td style="color: red;">Chờ duyệt</td>
                                </c:if>


                                <td>
                                    <c:out value="${o.getIdCanHo()}"  />
                                </td>
                                <td>
                                    <a class="btn btn-danger" href="detele-listing-seller?idbaidang=${o.getIdBaidang()}&idcanho=${o.getIdCanHo()}"> Delete</a>&nbsp;&nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--popup-->
                <div class="pop" id="myPopup_dangtin" >
                    <div class="inn" >
                        <h2 class="text-center">Xác nhận</h2>
                        <form action="AcceptListing" method="post">
                            <hr>
                            <input type="hidden" id="idbaidang" name="idbaidang" value="">
                            <input type="hidden" id="idcanho" name="idcanho" value="">

                            <h4 class="text-center">Bạn có chắc muốn duyệt tin không?</h4> 



                            <div class="btn-hideform">
                                <button type="submit" class="btn btn-success">Đồng ý </button>
                                <button type="reset" class="btn btn-danger" onclick="closePopup_dangtin()">Không</button>
                            </div>
                        </form>
                    </div>
                    <script>
                        // Lấy phần tử popup

                        function togglePopup_dangtin(idbaidang, idcanho) {
                            var popup = document.getElementById("myPopup_dangtin");
                            popup.style.display = "block";

                            var inputId = document.getElementById("idbaidang");
                            inputId.value = idbaidang;
                            var inidcanho = document.getElementById("idcanho");
                            inidcanho.value = idcanho;

                        }
                        function closePopup_dangtin() {
                            var popup = document.getElementById("myPopup_dangtin");
                            popup.style.display = "none";
                        }
                    </script>


                </div>

            </div>
            <script>
                function EditListing(idbaidang) {
                    var form = document.createElement("form");
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "edit-listing");

                    var input = document.createElement("input");
                    input.setAttribute("type", "hidden");
                    input.setAttribute("name", "id");
                    input.setAttribute("value", idbaidang);

                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                }
            </script>
            <footer>
                <jsp:include page="Footer.jsp"></jsp:include>
            </footer>
    </body>
</html>