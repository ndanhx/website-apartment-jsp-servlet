<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Model.*" %>
<%@ page import="DAO.*" %>
<%

    HttpSession mySession = request.getSession(false);
    if (mySession == null || mySession.getAttribute("acc") == null) {
        response.sendRedirect("Login.jsp");
    }
    else{
     Object accObj = mySession.getAttribute("acc");
        if (accObj != null && accObj instanceof Account) {
            Account acc = (Account) accObj;
            int isManager = acc.getRole();
            if(isManager != 0){
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
        <link rel="stylesheet" href="css/apart-list-seller.css">
        <!--header-->
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!--end header-->

        <style>
            .form-control {
                margin-top: 10px;
            }
            .list-group{
                max-width: 300px;
            }
            .popup {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                z-index: 1;
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
                padding-left: 40%;

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
                                    <%=count%> Đang đăng</td>
                                </c:if> 
                                <c:if test="${o.getTrangThaiDangTin() == 0}">
                                <td style="color: red;">Chưa đăng</td>
                            </c:if> 
                            <c:if test="${o.getTrangThaiDangTin() == 2}">
                                <td style="color: fuchsia;">
                                    <c:set var="s" value="${o.getIdCanHo()}" />
                                    <%
                                      BaiDangDAO baidangdao = new BaiDangDAO();
                                      int pending = baidangdao.getCountForListing_Pending((String)pageContext.getAttribute("s"));
                                    %>
                                    <%=pending%> Chờ duyệt 
                                </td>
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
                                        <!-- Nút Upload -->
                                        <button disabled="true" type="button" class="btn btn-primary" onclick="showUploadModal(${o.getIdCanHo()})">
                                            <i class="bi bi-cloud-arrow-up"></i> Upload
                                        </button>
                                    </c:if>
                                        <c:if test="${num < 6  }">
                                            <button type="button" class="btn btn-primary" onclick="showUploadModal(${o.getIdCanHo()})">
                                            <i class="bi bi-cloud-arrow-up"></i> Upload
                                        </button>
                                        </c:if>
                            </td>
                            <td>
                                <a class="btn btn-success" href="edit-apartment?id=${o.getIdCanHo()}"  >Edit</a> &nbsp;&nbsp;

                                <a class="btn btn-danger" href="DeleteApart?id_canho=${o.getIdCanHo()}"> Delete</a>&nbsp;&nbsp;
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
               


            </div>
        </div>
        <script>
            function EditApart(id) {
                var form = document.createElement("form");
                form.setAttribute("method", "POST");
                form.setAttribute("action", "EditApart");

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