<%-- 
    Document   : Header
    Created on : Apr 3, 2023, 7:25:53 PM
    Author     : Home
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Duy Anh Apartment</title>
        <link rel="icon" type="image/x-icon" href="img/icon-cfd-circle.ico">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/fonts.css">

        <style>
            .logo{
                min-width: 120px;
            }

            .btnred {
                background-color: white;
                border: 1px solid red;
                padding: 8px 16px;
                font-size: 14px;
                font-weight: 400;
                margin-right: 20px;
                cursor: pointer;
                border-radius: 5px;
                box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
                color: red;
                height: 35px;
                text-decoration: none;
                font-family: mr;
                font-size: 13px;
                border-radius: 7px;
                padding: 0 24px;

                display: flex;
                justify-content: center;
                align-items: center;
            }


            .user-menu {
                position: relative;
                min-width: 150px;
            }

            .user-menu .dropbtn {
                background-color: white;
                color: #000;
                border: none;
                cursor: pointer;
                font-weight: bold;
                font-size: 15px;
                padding: 8px;
            }

            .user-menu .dropbtn:hover, .user-menu .dropdown-menu:hover {
                background-color: #e6e6e6;
            }

            .user-menu .dropdown-menu {
                position: absolute;
                top: 100%;
                left: 0;
                display: none;
                list-style: none;
                background-color: #e6e6e6;
                padding-top: 20px;
                margin: 0;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .user-menu:hover .dropdown-menu {
                display: block;
            }

            .user-menu .dropdown-menu li {
                padding: 10px;
            }

            .user-menu .dropdown-menu li:hover {
                background-color: #f2f2f2;
                cursor: pointer;
            }

            .user-menu .dropdown-menu li a {
                color: #333;
                text-decoration: none;
                font-size: 17px;
                font-weight: normal;
            }

            .user-menu .dropdown-menu li a:hover {
                color: #555;
            }
            .menu li a {
                font-size: 20px;
            }

            .btnmain {
                font-size: 18px;
            }

        </style>
    </head>
    <body>

    </body>
    <header style="    height: 70px;
            position: fixed;
            top: 0;
            right: 0;
            left: 0;
            z-index: 1;
            background-color: #fff;
            ">
        <div class="container">
            <div class="logo">
                <a style="
                   display: flex;
                   align-items: center;" href="Home"><img style=" height: 70px;" srcset="./img/logo-duyanh.png 2.5x" alt="logo-luxstate " >
                </a>
            </div>
            <ul class="menu">
                <li><a href="Home">Trang chủ</a></li>
                   <c:if test="${sessionScope.acc.seller == 1 && sessionScope.acc.role == 1}">
                    <li><a style=" cursor: pointer;" onclick="submitForm()">Đăng tin</a></li>

                </c:if>
                <c:if test="${sessionScope.acc.role == 3}">
                    <li><a href="ThongKe" >Thống kê</a></li>

                </c:if>

                <c:if test="${sessionScope.acc == null}">
                    <li><a href="Login.jsp">Đăng tin</a></li>
                    </c:if>
                <li><a href="#">FAQ</a>
                </li><li><a href="#footer">Liên hệ</a></li>
            </ul>



            <div class="right">
                <c:if test="${ sessionScope.acc.seller == 1 && sessionScope.acc.role == 1}">
                    <button class="btnred" onclick = "myFormNapTien()">Nạp tiền</button>
                    <form id="myFormNapTien" action="nap-tien" method="post">
                        <input type="hidden" name="username" value="${sessionScope.acc.getUserName()}">
                    </form>
                    <script>
                        function myFormNapTien() {
                            document.getElementById("myFormNapTien").submit();
                        }
                    </script>
                </c:if>
                <c:if test="${sessionScope.acc==null}">
                    <a class="btnmain btnyellow" href="Login.jsp">Đăng nhập</a>
                </c:if>



                <c:if test="${sessionScope.acc!=null}">

                    <div class="user-menu" style=" Z-INDEX: 1;">
                        <button class="dropbtn">
                            Hello, ${sessionScope.acc.getName()} 
                            <i class="fas fa-chevron-down"></i></button>
                        <ul class="dropdown-menu">
                            <li>
                                <a  onclick="submitFormProfile()" >Quản lý tài khoản</a>
                                <!--form quản lý tài khoản cá nhân-->
                                <form id="myFormProfile" action="user-profile" method="post">
                                    <input type="hidden" name="username" value="${sessionScope.acc.getUserName()}">
                                </form>

                                <script>
                                    function submitFormProfile() {
                                        console.log("helo")
                                        document.getElementById("myFormProfile").submit();
                                    }
                                </script>
                            </li>

                            <c:if test="${sessionScope.acc.seller == 1 && sessionScope.acc.role == 1}">
                                <li><a  onclick="submitForm()">Quản lý Căn hộ </a></li>
                                <!--form quản lý căn hộ của seller-->
                                <form id="myForm" action="managerApartmentOfSeller" method="post">
                                    <input type="hidden" name="username" value="${sessionScope.acc.getUserName()}">
                                </form>

                                <script>
                                    function submitForm() {
                                        document.getElementById("myForm").submit();
                                    }
                                </script>

                                <li><a  onclick="submitForm1()">Quản lý bài đăng </a></li>
                                <!--form quản lý căn hộ của seller-->
                                <form id="myForm1" action="get-all-listing-for-seller" method="post">
                                    <input type="hidden" name="username" value="${sessionScope.acc.getUserName()}">
                                </form>

                                <script>
                                    function submitForm1() {
                                        document.getElementById("myForm1").submit();
                                    }
                                </script>



                            </c:if> 
                            <c:if test="${sessionScope.acc.role == 0}">
                                <li><a href="GetAllBaiDang">Quản lý Bài đăng </a></li>
                                <li><a href="GetAllList">Quản lý Căn hộ </a></li>
                                </c:if>
                                <c:if test="${sessionScope.acc.role == 3}">
                                <li><a href="accountmanager?index=1">Quản lý người dùng</a></li>

                            </c:if>
                            <li><a href="AccountCtr?id=dangxuat"> Đăng xuất </a></li>

                        </ul>
                    </div>

                </c:if> 

            </div>
        </div>
    </header>
</html>
