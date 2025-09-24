<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sửa sinh viên</title>
</head>
<body>
<h2>Sửa sinh viên</h2>

<form action="${pageContext.request.contextPath}/students/${studentEdit.id}/edit" method="post">
    <p>
        Mã SV: <input type="text" name="id" value="${studentEdit.id}" readonly/>
    </p>
    <p>
        Họ tên: <input type="text" name="name" value="${studentEdit.name}"/>
        <c:if test="${not empty errors['name']}">
            <span style="color:red">${errors['name']}</span>
        </c:if>
    </p>
    <p>
        Điểm: <input type="number" step="0.1" name="garden" value="${studentEdit.garden}"/>
        <c:if test="${not empty errors['garden']}">
            <span style="color:red">${errors['garden']}</span>
        </c:if>
    </p>
    <button type="submit">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/students">Quay lại</a>
</form>
</body>
</html>
