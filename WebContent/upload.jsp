<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/upload" enctype="multipart/form-data">
	사진1 : <input type="file" name="org_file_path1" id="org_file_path1"><br>
	사진2 : <input type="file" name="org_file_path2" id="org_file_path2"><br>
	업로더 : <input type="text" name="up_name" id="up_name"><br>
	<button type="button" onclick="upload()">업로드</button>
	<progress max="0" value="0" id="prog"></progress>
	<div id="perDiv">0%</div>
</form>
<script >
function upload(){
var xhr = new XMLHttpRequest();
xhr.open('post','/upload');
xhr.onreadystatechange = function(){
	if(xhr.readyState==4){
		if(xhr.status==200){
			
		}
	}
}
var formData = new FormData();
var f1 = document.querySelector('#org_file_path1');
var file1 = f1.files[0];
var progObj = document.querySelector('#prog');
progObj.max = file1.size;
//var f2 = document.querySelector('#org_file_path2');
formData.append('org_file_path1',file1);
//formData.append('org_file_path2',f2.files[0]);
formData.append('up_name',document.querySelector('#up_name').value);

xhr.upload.onprogress = function(f){
	progObj.value = f.loaded;
	document.querySelector('#perDiv').innerHTML = MATH.floor((f.loaded/progObj.max)*100);
}
/* console.log(f1.files[0]);
console.log(f2.files[0]); */

 xhr.send(formData);
}
</script>
</body>
</html>