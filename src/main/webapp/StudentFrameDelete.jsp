<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<form action="<c:url value="main?"/>" method="POST">
    <table>
        <tr>
            <td>Id:</td><td><input type="text" name="id" value="${student.id}"/></td>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" value="OK" name="Delete"/></td>
            <td><input type="submit" value="Cancel" name="Cancel" /></td>
        </tr>
    </table>
</form>
</body>
</html>