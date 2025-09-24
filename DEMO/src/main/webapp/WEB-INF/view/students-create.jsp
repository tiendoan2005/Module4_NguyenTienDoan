<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sinh viên</title>
</head>
<body>
<h3>Thêm sinh viên</h3>
<form action="${pageContext.request.contextPath}/students/create" method="post">
    Mã sinh viên: <input type="text" name="id"><br>
    Tên sinh viên: <input type="text" name="name"><br>
    Điểm tổng kết: <input type="number" name="garden" step="any"><br>
    <button type="submit">Thêm</button>
    <a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a>
</form>
</body>
</html>
