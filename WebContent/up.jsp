<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/FileServlet" enctype="multipart/form-data">
	사진 : <input type="file" name="img"><br>
	사진 : <input type="file" name="img"><br>
	사진 : <input type="file" name="img"><br>
	올린 사람 : <input type="text" name="person"><br>
	<button>올리기</button>
</form>
</body>
</html>