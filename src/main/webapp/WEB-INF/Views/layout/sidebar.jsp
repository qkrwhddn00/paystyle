<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script src="https://kit.fontawesome.com/ecb3ecb40d.js" crossorigin="anonymous"></script>
<link href="/css/after_login.css" rel="stylesheet">
<div id="backLine"></div>
<aside id="sidenav" class="bg_white">
    <div id="side-off" onclick="sidenav()"></div>
    <a href="/"><h3 id="sidenav_title"></h3></a>
    <hr>
    <ul id="navUl">
        <li id="navDash" class="navItem ">
            <a href="/" class="navLink">Dashboard</a>
        </li>
        <li id="navHouse" class="navItem noactive">
            <a href="/deposit" class="navLink">수익</a>
        </li>
        <li  class="navItem noactive">
            <a href="/withdraw" class="navLink">지출</a>
        </li>
        <li id="navCal" class="navItem">
            <a href="/calendar" class="navLink">캘린더</a>
        </li>
        <li class="navItem noactive">
            <a href="/userUpdate" class="navLink">마이페이지</a>
        </li>
        <li class="navItem noactive">
            <a href="/inquire" class="navLink">질문게시판</a>
        </li>
        <li class="navItem noactive">
            <a href="/board/csboard" class="navLink">공지사항</a>
        </li>
    </ul>
    <script>
        function sideActive(){
            if($(location).attr('pathname')=="/calendar"){
                $("#navDash").addClass("noactive");
                $("#navCal").addClass("active");
            }else{
                $("#navDash").addClass("active");
                $("#navCal").addClass("noactive");
            }
        }
        sideActive();
    </script>

    <ul id="sideOpt">
        <hr>
<%--        <li>--%>
<%--            <label id="dark-mode-button">다크모드--%>
<%--                <input type="checkbox" name="darkmode" id="darkmode">--%>
<%--                <span id="onoff-switch"></span>--%>
<%--            </label>--%>
<%--        </li>--%>
        <li>
            <a href="/logout" id="logoutBtn">로그아웃</a>
        </li>
    </ul>
</aside>
<script>
    function sidenav() {
        $("#sidenav").toggleClass("sideon");
    }

    $("#darkmode").change(function () {
        $("body").toggleClass("dark-on");
        $("#backLine").toggleClass("dark-on");
        $(".bg_white").toggleClass("dark-on");
        $(".active").toggleClass("dark-on");
        $(".navLink").toggleClass("dark-on");
        $("#logoutBtn").toggleClass("dark-on");
        $("#dark-mode-button").toggleClass("dark-on");
    })
</script>
