<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.*" %>
<%@ page import="DAO.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Duy Anh Apartment</title>
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">
        <link rel="stylesheet" href="css/styles_home.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <style>
            .thongbao-rong{
                font-size: 24px;
                padding-left: 450px;
                color: red;
                padding-top: 10px;
            }
            .thongbao-het{
                font-size: 30px;
                color: red;
                padding-left: 540px;
            }

            .addresss {
                background-color: #f2f2f2;
                padding: 20px 0 0 9%;
                padding-left: 180px;
            }

            .addresss form {
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .addresss select {
                margin-left: 10px;
            }

            .addresss label {
                margin-right: 10px;
            }

            .card-header {
                padding: 10px;
            }

            ul.list-group {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }

            ul.list-group select {
                flex: 1;
                font-size: 1.2rem;
                border-radius: 5px;
                border: none;
                padding: 10px;
                background-color: #fff;
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
            }

            ul.list-group select:focus {
                outline: none;
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.3);
            }

            ul.list-group select:not([value=""]) {
                flex: 1;
                font-size: 1.2rem;
                border-radius: 5px;
                border: none;
                padding: 10px;
                background-color: #fff;
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15);
                min-width: 240px;
            }


        </style>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <main style=" padding-top: 70px; ">
                <section class="hero">
                    <div class="container" >
                        <div class="hero__content">

                            <h1 class="hero__content-heading">Find Your New Modern Apartment</h1>
                            <form action="#" class="hero__search">
                                <input oninput="timcanho(this)" value="" name="txt" type="text" placeholder="Nhập thông tin cần tìm kiếm">
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
                                <script>
                                    //tim dia chi bằng nhập
                                    function timcanho(param) {
                                        var txtSearch = param.value;
                                        $.ajax({
                                            url: "/DangTinThueDangHo/SeacrhAjax",
                                            type: "get",
                                            data: {
                                                txt: txtSearch
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("apart_list");
                                                row.innerHTML = data;
                                            },
                                            error: function (xhr) {
                                            }
                                        });
                                    }
                                </script>
                            </form>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="addresss">
                        <form id="search-form">
                            <div class="card-header bg-primary text-white">
                                <span style="font-size:30px; width: -webkit-fill-available;">Tìm kiếm căn hộ: </span>
                            </div>
                            <br>
                            <ul class="list-group ">
                                <select id="city" name="city"  style="font-size: 1.2rem;" onchange="timdiachi(this)";>
                                    <option value="none">Chọn tỉnh thành</option>  
                                <c:forEach items="${listThanhPho}" var="thanhpho">
                                    <option  value="${thanhpho.getId_tinhTP()}">${thanhpho.getTen_TinhTP()} </option>
                                </c:forEach>
                            </select>
                            <select id="district" name="district" aria-label=".form-select-lg" style="font-size: 1.2rem;" onchange="timdiachi_quanhuyen(this)";>
                                <option value="none">Chọn quận huyện</option>  
                            </select>
                            <select  id="ward" name="ward"  aria-label=".form-select-lg" style="font-size: 1.2rem;"    onchange="timCanHobyPhuong()";>
                                <option value="" selected>Chọn phường xã</option>
                            </select>
                        </ul>   
                    </form>
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
                                            //                      if (!layDataHuyen.ERROR) {
                                            //                          console.log(layDataHuyen)
                                            //                      }
                                            districtContainer.innerHTML = layDataHuyen;
                                            //                      ajax 2
                                            $.ajax({
                                                type: "GET",
                                                url: "/DangTinThueDangHo/SearchAjax2?type=canho",
                                                data: {id_thanhpho: valueIDTinh.value},
                                                success: function (layCanHoTuIdTinh) {
                                                    //                                if (!layCanHoTuIdTinh.ERROR) {
                                                    //                                    console.log(layCanHoTuIdTinh)
                                                    //                                }
                                                    var canhoContainer = document.getElementById("apart_list");
                                                    canhoContainer.innerHTML = layCanHoTuIdTinh;
                                                }
                                            });
                                            //                        end ajax 2
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
                                            $.ajax({
                                                type: "GET",
                                                url: "/DangTinThueDangHo/SearchAjax3_CanHoByQuanHuyen?type=canho",
                                                data: {
                                                    id_quan: valueQuanHuyen.value,
                                                    id_thanhpho: $("#city").val()
                                                },
                                                success: function (layCanHoTuIDQuanHuyen) {
                                                    var canhoContainerbyQuan = document.getElementById("apart_list");
                                                    canhoContainerbyQuan.innerHTML = layCanHoTuIDQuanHuyen;
                                                }
                                            });

                                        }
                                    });
                                }

                                function timCanHobyPhuong() {
                                    $.ajax({
                                        url: "/DangTinThueDangHo/SearchAjax4",
                                        type: "POST",
                                        data: {
                                            id_phuong: $("#ward").val(),
                                        },
                                        success: function (layCanHoTuIDPhuongXa) {
                                            var phuongxaContainer = document.getElementById("apart_list");
                                            phuongxaContainer.innerHTML = layCanHoTuIDPhuongXa;
                                        },
                                        error: function (xhr) {
                                            //code
                                        }
                                    });
                                }
                    </script>
                </div>
            </section>
            <section class="apart">
                <div class="container h2">

                    <div id="apart_list" class="apart__list">
                        <c:forEach items="${getall}" var="o">
                            <c:forEach items="${getallch}" var="getch">
                                <c:if test="${o.getIdCanHo() == getch.getIdCanHo()}">
                                    <div  class="load3 apart__list-items">
                                        <c:set var="idcanho" value="${getch.getIdCanHo()}" />
                                        <div class="apart__items-img">
                                            <%
                                                HinhAnhDAO hinhanhdao = new HinhAnhDAO();
                                                HinhAnh image = hinhanhdao.get1ImageByIDCAnHo((String)pageContext.getAttribute("idcanho"));
                                                request.setAttribute("image", image);
                                            %>
                                            <a href="DetailCtr?id_baidang=${o.getIdBaidang()}">
                                                <img src="${image.getDuongDan()}" alt="Lỗi">
                                            </a>

                                        </div>
                                        <div class="apart__items-caption">
                                            <h3 class="apart__caption-title">
                                                <a href="DetailCtr?id_baidang=${o.getIdBaidang()}">${o.getTenTieuDe()}</a>
                                            </h3>


                                            <div class="apart__caption-bottom">
                                                <p class="price">${getch.getGiaTien()} VNÐ</p>
                                                <div class="apart__bottom-info">

                                                    <div class="info">
                                                        <i class="icon"> <img srcset="./img/img-luxstate/icon-bed.png 2x"
                                                                              alt="icon-bed"></i>
                                                        <p>${getch.getSoPhong()} Phòng ngủ</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> 
                                </c:if>
                            </c:forEach>
                        </c:forEach>

                    </div>
                    <div class="apart__btn">
                        <a onclick="loadMore()" style="cursor: pointer;">Xem thêm</a>
                    </div>
                    <script>
                        function loadMore() {
                            var soluong = document.getElementsByClassName("load3").length
                            $.ajax({
                                url: "/DangTinThueDangHo/Loading",
                                type: "get",
                                data: {
                                    exits: soluong
                                },
                                success: function (data) {
                                    var row = document.getElementById("apart_list");
                                    row.innerHTML += data;
                                },
                                error: function (xhr) {
                                    //code
                                }
                            });

                        }


                    </script>

                </div>
            </section>


            <footer>
             
                <jsp:include page="Footer.jsp"></jsp:include>
           
            </footer>
        </main>
    </body>


</html>