<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Students</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/myStyle.css"%>
</style>
<style>
    <%@include file="/WEB-INF/css/menuStyle.css"%>
</style>
<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Студенты
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Список всех студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Предметы
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown2">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Список всех студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Оценки
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown3">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
</div>
<script>
    <%@include file="/WEB-INF/js/index.js"%>
</script>
<table width="100%" cellpadding="1" cellspacing="1">
    <tr>
        <td>Id</td>
        <td>Имя</td>
        <td>Фамилия</td>
        <td>Дата рождения</td>
        <td>Год поступления</td>
    </tr>

    <c:forEach items="${students}" var="students">
    <tr>
        <td>${students.id}</td>
        <td>${students.name}</td>
        <td>${students.surname}</td>
        <td>${students.birthDate}</td>
        <td>${students.enterYear}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
