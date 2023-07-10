<%-- 
    Document   : nap-tien
    Created on : May 5, 2023, 4:22:46 PM
    Author     : Home
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="Model.Account" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Nạp tiền</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <link href="assets/css/style.css" rel="stylesheet">
        
        
        
        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet">


        <style>
            body {
                position: relative;
                width: 100%;
                color: var(--gray-800);
                font-size: 14px;
                font-weight: 400;
            }

            .title {
                font-size: 22px;
                margin : 10px;
                font-weight: 700;
                color: var(--gray-900);
            }

            .container {
                max-width: 1200px;
            }

            .font-weight-bold {
                font-weight: 700 !important;
            }

        </style>


    </head>

    <body>

        <!-- ======= Header ======= -->

        <jsp:include page="Header.jsp"></jsp:include>


            <main id="main" class="main" style="   padding-top: 80px; margin-bottom: 70px;  ">
                <div class="container">

                    <div class="alert-custom alert alert-danger" style="margin: 10px;     margin-bottom: 30px;">
                        <ul>
                            <li style="margin: 10px;">Nghiêm cấm sử dụng với mục đích Lừa đảo, cờ bạc, phát hiện khóa tài khoản vĩnh viễn, sẵn sàng hợp tác với
                                bên thứ 3 nào yêu cầu cung cấp thông tin</li>
                            <li style="margin: 10px;">Quý Khách tạo hóa đơn Nạp tiền&nbsp;Thực hiện thanh toán theo hình thức đã chọn</li>
                            <li style="margin: 10px;">Nạp tiền TỰ ĐỘNG 100% duyệt 24/7</li>
                        </ul>
                    </div>

                    <div class="section-gap">
                        <div class="row">

                            <div class="col-md-6">
                                <div class="description mb-3">
                                    <div class="title">
                                        Tạo yêu cầu nạp tiền
                                    </div>
                                </div>
                                <div class="form-m1">
                                    <form action="vnpayajax" id="frmCreateOrder" method="POST" style="margin: 10px;">
                                        <div class="row row10">
                                            <div class="col-12">
                                                <label for="" class="col-form-label">
                                                    Số dư hiện tại: <b class="font-weight-bold text-success"><fmt:formatNumber value="${account.getSoDu()}" pattern="#,##0 VND" /></b>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row row10">
                                        <div class="col-5 col-sm-4">
                                            <label for="" class="col-form-label font-weight-bold">
                                                Nhập số tiền:
                                            </label>
                                        </div>
                                        <div class="col-7 col-sm-8">

                                            <input class="form-control" data-val="true" data-val-number="The field Amount must be a number." 
                                                   data-val-required="The Amount field is required."
                                                   id="amount" max="100000000" min="10000" name="amount" type="number" value="10000"
                                                   placeholder="Nhập số tiền bạn muốn nạp" style="margin-bottom: 14px;"/>

                                            <input type="hidden" class="txt_inv_custome" name="txt_inv_custome" id="txt_inv_custome" value="${account.getName()}">
                                            <input type="hidden" class="vnp_OrderInfo" name="vnp_OrderInfo" id="vnp_OrderInfo" value="${account.getUserName()},  Chuyen khoan">
                                            <input type="hidden" name="Email" value="${account.getEmail()}">
                                            <input type="hidden" name="SDT" value="${account.getSDT()}">


                                            <span class="text-danger text-small" style=" margin-left: 7px;">Tối thiểu 10,000 VND , Tối đa 10,000,000 VND</span>
                                        </div>
                                    </div>
                                    <div class="row row10" style=" display: flex; align-items: center; ">
                                        <div class="col-5 col-sm-4">
                                            <label for="" class="col-form-label font-weight-bold">
                                                Cổng thanh toán:
                                            </label>
                                        </div>

                                        <div class="col-7 col-sm-8">

                                            <div class="row" >
                                                <div class="col-sm-6 col-md-6 col-lg-3 mt-3 mt-lg-0 mb-3" style="
                                                     display: flex;
                                                     ">
                                                    <div type="button" onclick="openModalAmount(8)" class="blur-shadow p-4 shadow-showcase text-center">
                                                        <input hidden type="radio" id="bankCode" name="bankCode" value="VNBANK" checked>
                                                        <img src="img/vnpt.png" width="150px" height="75px">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row row10">
                                        <div class="col-sm-8 offset-sm-4 text-center text-center" style=" margin-top: 20px; ">
                                            <button type="submit" class="btn btn-primary btn-lg w-100">
                                                <i class="fas fa-dollar-sign"></i>
                                                Nạp tiền
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="col-md-6 mt-4 mt-lg-0">
                            <div class="description mb-1">
                                <div class="small-title" style=" margin-bottom: 20px; font-weight: bold; font-size: 18px; margin-top: 14px;">
                                    Hạn mức và phí:
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped table-module">
                                    <tbody>
                                        <tr>
                                            <td>Tổng hạn mức ngày</td>
                                            <th>100,000,000 VND</th>
                                        </tr>
                                        <tr>
                                            <td>Số tiền tối thiểu</td>
                                            <th>10,000 VND</th>
                                        </tr>
                                        <tr>
                                            <td>Số tiền tối đa</td>
                                            <th>10,000,000 VND</th>
                                        </tr>
                                         <tr>
                                            <td>Phí giao dịch</td>
                                            <th>0%</th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </main><!-- End #main -->

    <!-- ======= Footer ======= -->

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

    <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
    <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
    <script type="text/javascript">
                                                        $("#frmCreateOrder").submit(function () {
                                                            var postData = $("#frmCreateOrder").serialize();
                                                            var submitUrl = $("#frmCreateOrder").attr("action");
                                                            $.ajax({
                                                                type: "POST",
                                                                url: submitUrl,
                                                                data: postData,
                                                                dataType: 'JSON',
                                                                success: function (x) {
                                                                    if (x.code === '00') {
                                                                        if (window.vnpay) {
                                                                            vnpay.open({width: 768, height: 600, url: x.data});
                                                                        } else {
                                                                            location.href = x.data;
                                                                        }
                                                                        return false;
                                                                    } else {
                                                                        alert(x.Message);
                                                                    }
                                                                }
                                                            });
                                                            return false;
                                                        });
    </script>     
     <footer>
                <jsp:include page="Footer.jsp"></jsp:include>
            </footer>

</body>

</html>