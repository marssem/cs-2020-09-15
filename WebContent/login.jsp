<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="common/menu.jsp"></jsp:include>

	<form class="px-4 py-3">
		<div class="form-group">
			<label for="exampleDropdownFormEmail1">아이디</label> <input
				type="email" class="form-control" id="id"
				placeholder="email@example.com">
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">비밀번호</label> <input
				type="password" class="form-control" id="pwd" placeholder="Password">
		</div>
		<div class="form-check">
			<input type="checkbox" class="form-check-input" id="dropdownCheck">
			<label class="form-check-label" for="dropdownCheck"> 아이디 기억하기
			</label>
		</div>
		<button type="submit" onclick="ab()"class="btn btn-primary">로그인</button>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
	<script>
	function ab(){
		var uiId = $("#id").val();
		var uiPwd = $("#pwd").val();
		var cmd = "login";
		var param = {
			uiId : uiId,
			uiPwd : uiPwd,
			cmd : cmd
		}

		$.ajax({
			url : "/ajax/user",
			method : "POST",
			data : JSON.stringify(param),
			contentType : "application/json",
			success : function(res) {
				if (res.result) {
					alert("로그인 되었습니다.");
					location.href = "/index.jsp";
				} else {
					alert("아이디와 비밀번호를 확인해 주세요");
				}
			}
		})
	}
	</script>
</body>
</html>