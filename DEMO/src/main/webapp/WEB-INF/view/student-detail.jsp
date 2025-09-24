<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi tiết sinh viên</title>
</head>
<body>
<h2>Thông tin chi tiết</h2>
<p>Mã SV: <c:out value="${student.id}"/></p>
<p>Họ tên: <c:out value="${student.name}"/></p>
<p>Điểm: <c:out value="${student.garden}"/></p>

<p>
    <a href="${pageContext.request.contextPath}/students/${student.id}/edit">Sửa</a>
<form action="/students/${student.id}/delete" method="post" style="display:inline">
    <button type="submit" onclick="return confirm('Xóa sinh viên này?')">Xóa</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a>
</p>
</body>
</html>

