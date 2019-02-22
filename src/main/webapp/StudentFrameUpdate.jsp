<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Обновление студента</title>
</head>

<body>
<form action="<c:url value="main?"/>" method="POST">
    <table>
        <tr>
            <td>Id:</td><td><input type="text" name="id" value="${student.id}"/></td>
        </tr>
        <tr>
            <td>Имя:</td><td><input type="text" name="name" value="${student.name}"/></td>
        </tr>
        <tr>
            <td>Фамилия:</td><td><input type="text" name="surname" value="${student.surname}"/></td>
        </tr>
        <tr>
            <td>Дата рождения:</td><td><input type="text" name="birthDate" value="${student.birthDate}"/></td>
        </tr>
        <tr>
            <td>Год поступления:</td><td><input type="text" name="enterYear" value="${student.enterYear}"/></td>
        </tr>
    </table>

    <table>
        <tr>
            <td><input type="submit" value="OK" name="Update"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
</form>
</body>
</html>