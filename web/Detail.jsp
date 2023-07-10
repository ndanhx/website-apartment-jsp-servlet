<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ page import="DAO.AccountDAO" %>
<%@ page import="DAO.DiaChiDAO" %>
<%@ page import="Model.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/phh.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Thông tin chi tiết</title>
        <link rel="stylesheet" href="css/reset.css">
        <link href="css/style_detail.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

        <!--header-->
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <!--end header-->


        <style>

            .main {
                flex: 1;
                margin: 0px 30px;
                display: inline-block;
                min-width: 100%;
                order: 2;
            }
           

            
            .slider-prev, .slider-next {
                color: #fff;
                background-color: #333;
                border-radius: 50%;
                padding: 10px;
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                z-index: 1;
                cursor: pointer;
            }

            .slider-prev {
                left: -70px;
            }

            .slider-next {
                right: -70px;
            }
            .slider-prev, .slider-next {
                /* Các thuộc tính CSS khác */
                background-color: #333;
                color: #fff;
                /* Thêm hover */
                transition: all 0.2s ease-in-out;
            }

            .slider-prev:hover, .slider-next:hover {
                background-color: red;
                color: #fff;
            }


        </style>


    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <div style=" padding-top: 70px;">
            <c:forEach items="${listcanho}" var="canho">

                <c:if test="${canho.getIdCanHo() == baidang.getIdCanHo()}">

                    <div class="main">
                        <div class="slider">
                            <i class="fa fa-angle-left fa-2x slider-prev" aria-hidden="true"></i>
                            <i class="fa fa-angle-right fa-2x slider-next" aria-hidden="true"></i>
                            <ul class="slider-dots">
                                <li class="slider-dot-item active" data-index="0"></li>
                                <li class="slider-dot-item" data-index="1"></li>
                                <li class="slider-dot-item" data-index="2"></li>
                                <li class="slider-dot-item" data-index="3"></li>
                                <li class="slider-dot-item" data-index="4"></li>
                            </ul>
                            <div class="slider-wrapper">
                                <div class="slider-main">
                                    <c:forEach items="${listImage}" var="image">
                                    <div class="slider-item">
                                        <img src="${image.getDuongDan()}" alt="${image.getTenHinhAnh()}" />
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <script src="js/slider.js"></script>

                        <div class="contents" style="font-style: normal;" >
                            <div class="content-information">
                                <div class="in-heading">
                                    <p class="home-sleep"> Phòng ngủ: ${canho.getSoPhong()}</p>
                                    <p class="home-area">Diện tích: ${canho.getDienTich()} m2</p>
                                    <p class="home-price"> <fmt:formatNumber value="${canho.getGiaTien()}" pattern="#,##0 VND" />  </p>
                                </div>
                                <c:set var="idPhuongXa" value="${canho.getIdPhuong()}" />
                                <c:set var="username" value="${canho.getUsername()}" />
                                <%
                                    DiaChiDAO diaChiDAO = new DiaChiDAO();
                                    String diaChi = diaChiDAO.getDiaChi_DAO((String)pageContext.getAttribute("idPhuongXa"));
                                
                                    AccountDAO dao = new AccountDAO();
                                    Account ac = dao.CheckAccountIsNull((String)pageContext.getAttribute("username"));
                                    String phoneNumber = ac.getSDT();
                                    String fullName = ac.getName();
                                %>
                                <div class="content-about">
                                    <pre>
                                <h1 class="about-heading">${baidang.getTenTieuDe()}</h1>
                                <hr>
                                 <p class="home-adrees"> Địa chỉ: <a href="BaiDangByAddress?idphuong=${canho.getIdPhuong()}" style="text-decoration:none; color:black;" class="blink-text"> <%=diaChi%> </a> </p>
                                <hr>
                                <p class="home-name" >  <%=fullName%> </p>
                                <hr>
                                 <p class="home-contact" > Điện thoại:  <%=phoneNumber%></p>
                                 <hr>
                                <p  class="home-date">Ngày đăng: ${baidang.getNgayDangBai()}</p>
                                
                                    </pre>

                                </div>
                            </div>
                            <div class="overview">
                                <div class="over-heading">
                                    <h3 class="over-h3-heading">Mô tả chi tiết</h3>
                                </div>
                                <div class="overview-about">
                                    <p class="about-content" style="   overflow-wrap: break-word;">
                                        ${canho.getMoTa()}
                                    </p>
                                </div>
                            </div>


                        </div>
                    </c:if>

                </c:forEach>
            </div>
        </div>

    </body>





</html>
