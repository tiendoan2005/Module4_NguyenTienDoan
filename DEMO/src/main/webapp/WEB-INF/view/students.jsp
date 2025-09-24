<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sinh viên</title>
</head>
<body>
<c:if test="${not empty message}">
    <div style="color: green">${message}</div>
</c:if>

<h3>Danh sách sinh viên</h3>
<form action="${pageContext.request.contextPath}/students" method="get">
    <div>
        <label>Tìm kiếm: </label>
        <input type="text" name="q" placeholder="Nhập tên hoặc mã SV" value="${q}">
        <button type="submit">Tìm</button>
    </div>
    <div>
        <label>Sắp xếp theo: </label>
        <select name="sort">
            <option value="id">Mã Sinh Viên</option>
            <option value="name">Tên</option>
            <option value="garden">Điểm</option>
        </select>
        <select name="dir">
            <option value="asc">Tăng dần</option>
            <option value="desc">Giảm dần</option>
        </select>
        <button type="submit">Sắp xếp</button>
    </div>

    <div>
        <h4>Phân trang</h4><br>
        Trang: <input type="number" name="page" min="1" value="${page}">
        Size mỗi trang: <input type="number" name="size" value="${size}">
        <button type="submit">Xem</button>
    </div>
</form>
<table border="1" cellspacing="0" cellpadding="5">
    <tr>
        <th>Mã sinh viên</th>
        <th>Họ tên</th>
        <th>Điểm tổng kết</th>
        <th>Xếp hạng</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.garden}</td>
            <td>${student.rank}</td>
            <td>
                <a href="${pageContext.request.contextPath}/students/${student.id}">Chi tiết</a>
                <a href="${pageContext.request.contextPath}/students/${student.id}/edit">Sửa</a>
                <form action="${pageContext.request.contextPath}/students/${student.id}/delete" method="post" style="display: inline">
                    <button type="submit" onclick="return confirm('Xóa sinh viên này?')">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="students/create">Thêm sinh viên</a>
</body>
</html>
