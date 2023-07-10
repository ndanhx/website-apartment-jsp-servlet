
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        int role = acc.getRole();
        if(role == 1 || role == 0){
            response.sendRedirect("Home");
        }
      }
    }
%>
<html>
    <head>
        <title>User Management</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <!--header-->
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <!--<link rel="stylesheet" href="css/reset.css">-->
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!--end header-->
        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        


<!--
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">-->

        <style>
            .form-control {
                margin-top: 10px;
            }

            .pagination-wrapper {
                text-align: center;
            }

            .pagination {
                display: inline-block;
                margin-bottom: 40px;
                height: 70px;
                margin-top: 70px;
                padding: 0 25px;
                border-radius: 35px;
                background-color: #eee;
            }
            @media only screen and (max-width: 1199px) {
                .pagination {
                    height: 50px;
                    margin-top: 50px;
                    padding: 0 10px;
                    border-radius: 25px;
                }
            }

            .page-numbers {
                display: block;
                padding: 0 25px;
                float: left;
                transition: 400ms ease;
                color: #595959;
                font-size: 20px;
                letter-spacing: 0.1em;
                line-height: 70px;
            }
            .page-numbers:hover, .page-numbers.current {
                background-color: #86c023;
                color: #fff;
            }
            .page-numbers.prev:hover, .page-numbers.next:hover {
                background-color: transparent;
                color: #86c023;
            }
            @media only screen and (max-width: 1199px) {
                .page-numbers {
                    padding: 0 15px;
                    font-size: 16px;
                    line-height: 50px;
                }
            }
            @media only screen and (min-width: 120px) and (max-width: 1024px) {
                .page-numbers {
                    padding: 0 14px;
                    display: none;
                }
                .page-numbers:nth-of-type(2) {
                    position: relative;
                    padding-right: 50px;
                }
                .page-numbers:nth-of-type(2)::after {
                    content: "...";
                    position: absolute;
                    font-size: 25px;
                    top: 0;
                    left: 45px;
                }
                .page-numbers:nth-child(-n+3), .page-numbers:nth-last-child(-n+3) {
                    display: block;
                }
                .page-numbers:nth-last-child(-n+4) {
                    padding-right: 14px;
                }
                .page-numbers:nth-last-child(-n+4)::after {
                    content: none;
                }
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
            .btn-hideform {
                padding-left: 40%;

            }
            .form-check-input:checked{
                background-color: blue;
                border-color: blue;

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
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <div class="container">

                <hr>
                <div>
                    <h3 class="text-center" style="font-size: 30px;
                        font-weight: bold;
                        padding-top: 60px;">Quản lý tài khoản người dùng</h3>
                </div>
                <div>
                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-octagon me-1"></i>
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
            </div> 
            <div>

                <% if (request.getAttribute("success") != null) { %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle me-1"></i>
                    <%= request.getAttribute("success") %> 
                </div>
                <% } %>
            </div>
            <hr>
            <div class="container text-left" style=" padding-left: 50px;">
                <button onclick="togglePopup()" class="btn btn-success">Thêm tài khoản</button>
            </div>
            <div class="popup" id="myPopup" >
                <div class="inner">
                    <h4 class="text-center">Thêm tài khoản mới</h4>
                    <form action="AddAccount" method="post">
                        <fieldset class="form-group">
                            <label>Tên đăng nhập</label> 
                            <input type="text"
                                   value=""   class="form-control"
                                   name="username" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mật khẩu</label> 
                            <input type="password"
                                   value=""   class="form-control"
                                   name="password" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Họ và tên</label> 
                            <input type="text"
                                   value=""   class="form-control"
                                   name="fullname" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Số điện thoại</label> 
                            <input type="text"
                                   value=""   class="form-control"
                                   name="sdt" required="required">
                        </fieldset>  

                        <fieldset class="form-group">
                            <label>Email</label> 
                            <input type="text"
                                   value=""   class="form-control"
                                   name="email" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Số dư</label> 
                            <input type="text"
                                   value=""   class="form-control"
                                   name="sodu" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <legend>Vai trò</legend>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="role" id="seller" value="1">
                                <label class="form-check-label" for="seller">
                                    Người cho thuê
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="role" id="normal" value="0">
                                <label class="form-check-label" for="normal">
                                    Người thuê
                                </label>
                            </div>
                        </fieldset>
                        <hr>
                        <fieldset class="form-group">
                            <input type="checkbox" id="vehicle1" name="admin" value="0" ${user.getRole() == 0 ? 'checked' : ''}>
                            <label class="form-check-label" for="vehicle1" >Phong làm nhân viên kiểm duyệt</label><br>
                        </fieldset>
                        <div class="btn-hideform">
                            <button type="submit" class="btn btn-success">Thêm </button>
                            <button type="btn" class="btn btn-success" onclick="closePopup()">Đóng</button>
                        </div>
                    </form>
                </div>


            </div>
            <script>
                
                
                // Lấy phần tử popup

                function togglePopup() {
                    var popup = document.getElementById("myPopup");
                    popup.style.display = "block";
                }
                function closePopup() {
                    var popup = document.getElementById("myPopup");
                    popup.style.display = "none";
                }
            </script>

            <br>
            <table class="table table-striped" style=" max-width: 1200px; margin: auto;">
                <thead>
                    <tr class="user-row">
                        <th>Tên đăng nhập</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Số dư</th>
                        <th>Chức vụ</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUser}">
                        <tr>
                            <td><c:out value="${user.getUserName()}" /></td>
                            <td><c:out value="${user.getName()}" /></td>
                            <td><c:out value="${user.getEmail()}" /></td>
                            <td><c:out value="${user.getSDT()}" /></td>
                            <td><fmt:formatNumber value="${user.getSoDu()}" pattern="#,##0 VND" /></td>

                            <c:if test="${user.getSeller()==1 && user.role==1}">
                                <td>Người cho thuê</td>
                            </c:if> 
                            <c:if test="${user.getRole()==0}">
                                <td style="color: green;">Quản trị viên</td>
                            </c:if> 
                            <c:if test="${user.getRole()==3}">
                                <td style="color: red;">Administrator</td>
                            </c:if>
                            <c:if test="${user.getRole()==1 && user.getSeller()==0}">
                                <td>Người dùng</td>
                            </c:if> 

                            <td>
                                <c:if test="${user.getRole()!=3}">
                                    <a class="btn btn-success" href="EditAccount?id=${user.getUserName()}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a class="btn btn-danger" href="DeleteAccount?id=${user.getUserName()}">Delete</a>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
            <div class="pagination-wrapper">
                <div class="pagination">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <c:if test="${i == currentPage}">
                            <a class="page-numbers current-page" href="accountmanager?index=${i}">${i}</a>
                        </c:if>
                        <c:if test="${i != currentPage}">
                            <a  class="page-numbers" href="accountmanager?index=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

        </div>                              
  <footer>
                <jsp:include page="Footer.jsp"></jsp:include>
            </footer>

    </body>
</html>