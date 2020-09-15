<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="" enctype="multipart/form-data">
	사진 : <input type="file" name="org_file_path1" id="org_file_path1" >
	사진 : <input type="file" name="org_file_path2" id="org_file_path2" >
	업로더 : <input type="text" name="up_name" id="up_name">
	<button type="button" onclick="doLoad()">올리기</button>
</form>
<script >
function doLoad(){
	var xhr = XMLHttpRequest();
	xhr.open('post','/file2');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				
			}
		}
	}
var formdata = new FormData();
var f1 = document.querySelector('#org_file_path1');
var f2 = document.querySelector('#org_file_path2');
fordata.append('org_file_path1',f1.files[0]);
fordata.append('org_file_path2',f2.files[0]);
fordata.append('up_name',documentSelector('#up_name').value);
xhr.send();

}
</script>
</body>
</html>