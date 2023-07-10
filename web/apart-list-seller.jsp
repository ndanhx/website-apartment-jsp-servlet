<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Model.*" %>
<%@ page import="DAO.BaiDangDAO" %>
<%@ page import="DAO.*" %>
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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="css/apart-list-seller.css">


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

        <style>
            .form-control {
                margin-top: 10px;
            }
        </style>


<!--        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">

        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">-->

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
                    <h3 class="text-center" style="font-size: 30px; font-weight: bold;">Quản lý Căn hộ</h3>

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
            <div class="container text-left">
                <button onclick="togglePopup()" class="btn btn-success">Thêm Căn hộ</button>
            </div>
            <div class="popup" id="myPopup" >
                <div class="inner">
                    <h4 class="text-center">Thêm Căn hộ mới</h4>
                    <form action="AddApartBySeller" method="post">
                        <input type="hidden" name="username" value="${sessionScope.acc.getUserName()}">
                        <fieldset class="form-group">
                            <label>Tên căn hộ</label> 
                            <input type="text" class="form-control"
                                   name="tencanho" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Số phòng</label> 
                            <input type="text" class="form-control"
                                   name="sophong" required="required">
                        </fieldset>  

                        <fieldset class="form-group">
                            <label>Diện tích</label> 
                            <input type="text" class="form-control"
                                   name="dientich" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Giá tiền</label> 
                            <input type="text" class="form-control"
                                   name="giatien" required="required">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mô tả</label> 
                            <input type="text" class="form-control"
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
                                <input class="form-check-input" type="radio" name="trangthai" id="seller" value="1" ${user.getSeller() == 1 ? 'checked' : ''}>
                                <label class="form-check-label" for="seller">
                                    Đã cho thuê
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="trangthai" id="normal" value="0" ${user.getSeller() == 0 ? 'checked' : ''}>
                                <label class="form-check-label" for="normal">
                                    Cho thuê
                                </label>
                            </div>
                        </fieldset>
                        <div class="btn-hideform">
                            <button type="submit" class="btn btn-success">Thêm </button>
                            <button type="reset" class="btn btn-danger" onclick="closePopup()">Đóng</button>
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
            <table class="table table-striped">
                <thead>
                    <tr class="user-row">
                        <th>ID</th>
                        <th>Tên căn hộ</th>
                        <th>Giá tiền</th>
                        <th>Trạng thái thuê</th>
                        <th>Trạng thái đăng</th>
                        <th>Chủ sở hữu</th>
                        <th>Hình ảnh</th>
                        <th>Hành động</th>
                    </tr>
                </thead>



                <tbody>
                    <c:forEach var="o" items="${getall}">


                        <tr>
                            <td><c:out value="${o.getIdCanHo()}" /></td>
                            <td><c:out value="${o.getTenCanHo()}" /></td>
                            <td><c:out value="${o.getGiaTien()}" /></td>
                            <c:if test="${o.getTrangThaiTHUE() == 1 }">
                                <td style="color: red;">Đã cho thuê</td>
                            </c:if>
                            <c:if test="${o.getTrangThaiTHUE() == 0 }">
                                <td style="color: green;">Đang cho thuê</td>
                            </c:if> 
                            <c:if test="${o.getTrangThaiDangTin() == 1}">
                                <td style="color: green;">
                                    <c:set var="idcanho" value="${o.getIdCanHo()}" />
                                    <%
                                        BaiDangDAO baidangdao = new BaiDangDAO();
                                        int count = baidangdao.getCountForListing((String)pageContext.getAttribute("idcanho"));
                
                                    %>
                                    <%=count%> Đang đăng
                                </td>
                            </c:if> 
                            <c:if test="${o.getTrangThaiDangTin() == 0}">
                                <td style="color: red;">Chưa đăng</td>
                            </c:if> 
                            <c:if test="${o.getTrangThaiDangTin() == 2}">
                                <td style="color: fuchsia;">
                                    <c:set var="idcanho" value="${o.getIdCanHo()}" />
                                    <%
                                        BaiDangDAO baidangdao = new BaiDangDAO();
                                        int pending = baidangdao.getCountForListing_Pending((String)pageContext.getAttribute("idcanho"));
                                    %>
                                    <%=pending%> Chờ duyệt</td>
                                </c:if> 
                            <td><c:out value="${o.getUsername()}" /></td>

                            <td>
                                <c:set var="idcanho" value="${o.getIdCanHo()}" />
                                    <%
                                        HinhAnhDAO hinhanhdao = new HinhAnhDAO();
                                        int count = hinhanhdao.CountNumImage((String)pageContext.getAttribute("idcanho"));
                                        
                                        request.setAttribute("num", count);
                                    %>
                                     <c:if test="${num >= 6  }">
                                         <button disabled="true" type="button" class="btn btn-primary" onclick="showUploadModal(${o.getIdCanHo()})">
                                             <i class="bi bi-cloud-arrow-up"></i> Upload
                                        </button>
                                   
                                     </c:if> 
                                     <c:if test="${num < 6  }">
                                         <button  type="button" class="btn btn-primary" onclick="showUploadModal(${o.getIdCanHo()})">
                                             <i class="bi bi-cloud-arrow-up"></i> Upload
                                        </button>
                                   
                                     </c:if> 
                            </td>
                            <td>

                                <a class="btn btn-success" href="#"  onclick="EditApart(${o.getIdCanHo()})">Edit</a>  

                                <a class="btn btn-danger" href="DeleteApartBySeller?id_canho=${o.getIdCanHo()}&username=${o.getUsername()}"> Delete</a>
                                <c:if test="${o.getTrangThaiTHUE() == 0 }">
                                    <a  class="btn btn-success" href="#"  onclick="togglePopup_dangtin(${o.getIdCanHo()}, '${o.getUsername()}')"> Post</a>
                                </c:if> 
                   

                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <!-- Form modal Upload -->
            <div class="modal" id="uploadModal" style="padding-top: 100px;">
                <div class="modal-dialog">
                    <div class="modal-content" style="height: 450px;">
                        <div class="modal-header">
                            <h5 class="modal-title">Upload Image</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form action="insertImage" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="id_canho">ID Căn hộ</label>
                                    <input type="text" readonly="true"  class="form-control" id="idcanhobyUPLOAD" name="idcanho" required>
                                </div>
                                <div class="form-group">
                                    <label for="ten_anh">Tên ảnh</label>
                                    <input type="text" class="form-control" id="ten_anh" name="ten_anh" required>
                                </div>
                                <div class="form-group">

                                    <label for="duong_dan">Chọn hình ảnh</label>
                                    <!--<input type="file" class="form-control-file" name="file" required>-->

                                    <input type="file" class="form-control-file" id="file" name="file" accept="images/*" multiple onchange="validateFiles(event);" required>
                                </div>
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-cloud-arrow-up"></i> Upload
                                </button>
                                <button type="button" class="btn btn-secondary" onclick="clearForm()">
                                    <i class="bi bi-x"></i> Clear
                                </button>
                            </form>
                            <div class="row mt-3" id="preview-container">
                                <!-- Image previews will be added dynamically here -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                function showUploadModal(idcanho) {
                    const modal = document.getElementById("uploadModal");
                    modal.style.display = "block";
                    document.body.classList.add("modal-open");
                    document.body.insertAdjacentHTML('beforeend', '<div class="modal-backdrop"></div>');
                    //set gia trị id căn hộ cho form 
                    var idcanhobyUPLOAD = document.getElementById("idcanhobyUPLOAD");
                    idcanhobyUPLOAD.value = idcanho;
                }

                document.querySelector("#uploadModal .close").addEventListener("click", function () {
                    const modal = document.getElementById("uploadModal");
                    modal.style.display = "none";
                    document.body.classList.remove("modal-open");
                    const modalBackdrop = document.querySelector('.modal-backdrop');
                    if (modalBackdrop) {
                        modalBackdrop.parentNode.removeChild(modalBackdrop);
                    }
                });
                function clearForm() {
                    document.getElementById("ten_anh").value = "";
                    document.getElementById("duong_dan").value = "";
                    document.getElementById("preview-container").innerHTML = "";
                }

                function validateFiles(event) {
                    const files = event.target.files;
                    if (files.length > 6) {
                        alert("Bạn chỉ có thể chọn tối đa 6 file");
                        event.target.value = "";
                    }
                }
            </script>

            <!--DIV ĐĂNG TIN <DANG TIn>-->
            <div class="pop" id="myPopup_dangtin" >
                <div class="inn" >
                    <h4 class="text-center">Đăng tin</h4>
                    <form action="PostListing" method="post">
                        <input type="hidden" id="idcanho" name="idcanho" value="">
                        <input type="hidden" id="username" name="username" value="">
                        <fieldset class="form-group">
                            <label>Tiêu đề</label> 
                            <input type="text" class="form-control"
                                   name="tieude_dangtin" required="required"value="">
                        </fieldset>

                        <div class="btn-hideform">
                            <button type="submit" class="btn btn-success">Thêm </button>
                            <button type="reset" class="btn btn-danger" onclick="closePopup_dangtin()">Đóng</button>
                        </div>
                    </form>
                </div>
                <script>
                    // Lấy phần tử popup

                    function togglePopup_dangtin(idCanHo, username) {
                        var popup = document.getElementById("myPopup_dangtin");
                        popup.style.display = "block";

                        var inputId = document.getElementById("idcanho");
                        inputId.value = idCanHo;
                        var inusername = document.getElementById("username");
                        inusername.value = username;
                    }
                    function closePopup_dangtin() {
                        var popup = document.getElementById("myPopup_dangtin");
                        popup.style.display = "none";
                    }
                </script>


            </div>
        </div>
        <script>
            function EditApart(id) {
                var form = document.createElement("form");
                form.setAttribute("method", "POST");
                form.setAttribute("action", "edit-apart-seller");

                var input = document.createElement("input");
                input.setAttribute("type", "hidden");
                input.setAttribute("name", "id");
                input.setAttribute("value", id);

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