<%-- 
    Document   : user-profile
    Created on : Apr 20, 2023, 1:25:36 PM
    Author     : Home
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="Model.Account" %>
<%
    HttpSession mySession = request.getSession(false);
    if (mySession == null || mySession.getAttribute("acc") == null) {
        response.sendRedirect("Login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>User profile</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!--header-->
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!--end header-->




        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->

        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">

    </head>

    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <!-- ======= Header ======= -->
            <main id="main" class="main" style="  padding-top: 100px; margin-bottom: 200px;"> 
                <div class="pagetitle">
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="Home">Home</a></li>
                            <li class="breadcrumb-item active">Profile</li>
                        </ol>
                    </nav>
                    <div>
                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-octagon me-1"></i>
                        <%= request.getAttribute("error") %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <% } %>
                </div> 
                <div>
                    <% if (request.getAttribute("success") != null) { %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle me-1"></i>
                        <%= request.getAttribute("success") %> 
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <% } %>
                </div>

            </div> 
        </div><!-- End Page Title -->

        <section class="section profile">
            <div class="row">
                <div class="col-xl-4">

                    <div class="card">
                        <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

                            <img src="images/image-profile.jpg" alt="Profile" class="rounded-circle">



                            <h2>${account.getName()}

                                <c:if test="${account.getRole() == 0 || account.getRole() == 3 }">
                                    <img title="Đã xác minh " class="imgcheck" src="images/checklist.png" alt="lỗi"/>
                                </c:if>
                            </h2>
                            <br>
                            <h3 class="money-profile" style="background: beige; border: aliceblue;">Số dư hiện tại: <fmt:formatNumber value="${account.getSoDu()}" pattern="#,##0 VND" /></h3>

                            <c:if test="${account.getRole() == 0}">
                                <br> <h3>Kiểm duyệt viên</h3>
                            </c:if>
                            <c:if test="${account.getRole() == 3}">
                                <br> <h3>Administrator</h3>
                            </c:if>
                            <c:if test="${account.getRole() == 1 && account.getSeller()==1}">
                                <br><h3>Người cho thuê</h3>
                                <hr>

                            </c:if>
                            <c:if test="${account.getRole() == 1 && account.getSeller()== 0}">
                                <br><h3>Người thuê</h3>
                            </c:if>

                        </div>
                    </div>

                </div>

                <div class="col-xl-8">

                    <div class="card">
                        <div class="card-body pt-3">
                            <!-- Bordered Tabs -->
                            <ul class="nav nav-tabs nav-tabs-bordered">

                                <li class="nav-item">
                                    <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Tổng quan</button>
                                </li>

                                <li class="nav-item">
                                    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">Chỉnh sửa thông tin</button>
                                </li>



                                <li class="nav-item">
                                    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">Đổi mật khẩu</button>
                                </li>

                            </ul>
                            <div class="tab-content pt-2">

                                <div class="tab-pane fade show active profile-overview" id="profile-overview">

                                    <h5 class="card-title2" style="    padding: 20px 0 15px 0;  font-size: 24px;   font-weight: 500;    color: #012970; font-family: Poppins, sans-serif;     ">Thông tin chi tiết</h5>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label ">Tên đăng nhập</div>
                                        <div class="col-lg-9 col-md-8">${account.getUserName()}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label ">Họ và tên</div>
                                        <div class="col-lg-9 col-md-8">${account.getName()}</div>
                                    </div>


                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">Số điện thoại</div>
                                        <div class="col-lg-9 col-md-8">${account.getSDT()}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">Email</div>
                                        <div class="col-lg-9 col-md-8">${account.getEmail()}</div>
                                    </div>

                                </div>

                                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                                    <!-- Profile Edit Form -->
                                    <form action="edit-account-profile">

                                        <div class="row mb-3">
                                            <label for="Username" class="col-md-4 col-lg-3 col-form-label">Tên đăng nhập</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input readonly="true" name="username" type="text" class="form-control" id="username" value="${account.getUserName()}">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Họ và tên</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="fullName" type="text" class="form-control" id="fullName" value="${account.getName()}">
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Số điện thoại</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="phoneNumber" type="text" class="form-control" id="Phone" value="${account.getSDT()}"  pattern="0[0-9]{9}">
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="Email" type="email" class="form-control" id="Email" value="${account.getEmail()}">
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                        </div>
                                    </form><!-- End Profile Edit Form -->

                                </div>
                                <div class="tab-pane fade pt-3" id="profile-change-password">
                                    <!-- Change Password Form -->
                                    <script type="text/javascript">
                                        function matchpass() {
                                            var newpassword = document.formchangepass.newpassword.value;
                                            var reNewpassword = document.formchangepass.renewpassword.value;

                                            if (newpassword === reNewpassword) {
                                                return true;
                                            } else {
                                                alert("Nhập lại mật khẩu trùng nhau!");
                                                return false;
                                            }
                                        }
                                    </script>
                                    <form name="formchangepass" action="change-pass-user-account" method="post" onsubmit="return matchpass()">
                                        <input readonly="true" name="username" type="hidden" class="form-control" id="username" value="${account.getUserName()}">
                                        <div class="row mb-3">
                                            <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Mật khẩu hiện tại</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="password" type="password" class="form-control" id="currentPassword">
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">Mật khẩu mới</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="newpassword" type="password" class="form-control" id="newPassword">
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Nhập lại mật khẩu mới</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="renewpassword" type="password" class="form-control" id="renewPassword">
                                            </div>
                                        </div>

                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                                        </div>
                                    </form><!-- End Change Password Form -->

                                </div>

                            </div><!-- End Bordered Tabs -->

                        </div>
                    </div>

                </div>
            </div>
        </section>

    </main><!-- End #main -->
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/chart.js/chart.umd.js"></script>
    <script src="assets/vendor/echarts/echarts.min.js"></script>
    <script src="assets/vendor/quill/quill.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
 <footer>
                <jsp:include page="Footer.jsp"></jsp:include>
            </footer>
</body>

</html>
