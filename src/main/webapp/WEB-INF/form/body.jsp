<%@page contentType="text/html;charset=UTF-8" %>
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

