<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">


        <style>
            .list-group{
                max-width: 300px;
            }
            .popup {
                display: none;
                position: fixed;
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
            .lb-formngay{
                padding: 10px;
                font-size: 19px;
                font-weight: 400;
            }

            .form-group1{
                display: flex;
            }
            .row1{
                display: flex;
            }

        </style>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <div class="container" style="  padding-top: 70px;">
                <div class="card">
                    <div class="card-body">
                        <h1  class="text-center" style="font-size: 30px; font-weight: bold; padding: 20px;">Thống kê</h1>

                        <!-- Vertical Pills Tabs -->
                        <div class="d-flex align-items-start">
                            <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical" style="    min-width: 200px; padding-top: 25px;  min-height: 200px;">
                                <button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">Thống kê bài đăng</button>
                                <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Thống kê người dùng</button>
                            </div>

                            <div class="tab-content" id="v-pills-tabContent">

                                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">

                                    <div >     
                                        <div>
                                            <div class="form-group1" style=" padding: 20px;">

                                                <div class="row1">
                                                    <label class="lb-formngay">Từ ngày: </label>
                                                    <div class="col-md-4 box" style=" max-width: 17%;">
                                                        <input type="date" id="start_date" class="form-control" placeholder="Date" style=" width: 150px;">
                                                    </div>
                                                </div> 
                                                <div class="row1">
                                                    <label class="lb-formngay">  Đến ngày:  </label>
                                                    <div class="col-md-4 box" style=" max-width: 17%;">
                                                        <input type="date" class="form-control" id="end_date" placeholder="Date" style="width: 150px;" disabled>
                                                    </div>
                                                </div>
                                                <div class="row1">
                                                    <div class="select-trangthai">
                                                        <label class="lb-formngay">Trạng thái:</label> 

                                                        <select id="trangthai" name="trangthai" aria-label=".form-select-lg" onchange="thongke()";>
                                                            <option value="3" >Tất cả </option>
                                                            <option value="1">Đang đăng</option>
                                                            <option value="0">Chờ duyệt</option>
                                                        </select>
                                                    </div>
                                                </div>



                                                <script>
                                                    var a, b;
                                                    var start_date_input = document.getElementById("start_date");
                                                    var end_date_input = document.getElementById("end_date");
                                                    start_date_input.addEventListener("change", function () {
                                                        end_date_input.disabled = false;
                                                    });
                                                    end_date_input.addEventListener("change", function () {
                                                        var start_date = new Date(start_date_input.value);
                                                        var end_date = new Date(this.value);

                                                        if (start_date <= end_date) {
                                                            a = start_date.toLocaleDateString('vi-VN');
                                                            b = end_date.toLocaleDateString('vi-VN');
                                                            console.log(a, b)


                                                        } else {
                                                            alert("Phải chọn ngày sau ngày bắt đầu");
                                                            this.value = "";
                                                        }
                                                    });


                                                    function thongke() {
                                                        if (a == null || b == null) {
                                                            alert("Vui lòng chọn ngày tháng năm để xem thống kê");
                                                        }
                                                        $.ajax({
                                                            type: "post",
                                                            url: "/DangTinThueDangHo/ThongKeAjax",
                                                            data: {
                                                                start_date: a,
                                                                end_date: b,
                                                                trangthai: $("#trangthai").val()
                                                            },
                                                            success: function (dataResponse) {
                                                                var dataShowInWeb = document.getElementById("dataShow");
                                                                dataShowInWeb.innerHTML = dataResponse;
                                                            }
                                                        });
                                                    }

                                                </script>


                                            </div>
                                            <script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
                                            <script>
                                                    webshims.setOptions('forms-ext', {types: 'date'});
                                                    webshims.polyfill('forms forms-ext');
                                            </script>
                                        </div>
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
                                                <th> </th>
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
                                                <c:if test="${o.getTrangThai() == 1 }">
                                                    <td>
                                                        <a href="DetailCtr?id_baidang=${o.getIdBaidang()}">Xem chi tiết</a>
                                                    </td>
                                                </c:if>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">

                                <table class="table table-striped" style=" min-width: 900px;">
                                    <thead>
                                        <tr class="user-row">
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Phone number</th>
                                            <th>Money</th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${listUser}">
                                            <tr>
                                                <td><c:out value="${user.getUserName()}" /></td>
                                                <td><c:out value="${user.getName()}" /></td>
                                                <td><c:out value="${user.getEmail()}" /></td>
                                                <td><c:out value="${user.getSDT()}" /></td>
                                                <td><c:out value="${user.getSoDu()}" /></td>
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

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                    <!-- End Vertical Pills Tabs -->

                </div>
            </div>

        </div>


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