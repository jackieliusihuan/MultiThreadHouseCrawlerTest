<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>主页</title>
</head>

<body>
    <h2>相似房源</h2>
    <table>
        <c:forEach items="${similarHouseList}" var="houseUrl" varStatus="status">
            <tr>
                <td><a href="${houseUrl}">相似房源：${houseUrl}</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>