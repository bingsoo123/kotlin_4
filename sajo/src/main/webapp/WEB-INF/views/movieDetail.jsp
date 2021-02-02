<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body{
background:url('../resources/img/cc.jpg');
background-size: 100%;

}
</style>
<meta charset="UTF-8">
<title>영화 디테일</title>
<link href="${path}/resources/css/movie.css" rel="stylesheet" />
</head>
<body>

<div class="center">
<span class="choice">예매 날짜 선택</span>
<span class="c" onClick="fuck()">${today }</span>
<span class="c">${tomorrow }</span>
<span class="c">${three }</span>
<span class="c">${four }</span>
<span class="c">${five }</span>
</div>
<br>
<div class="movie" style="margin : auto 0px; background-color:white;">

	<div class="movie_top">
		<img src="../resources/img/${Image }"style="margin-left: 40%;">
	</div>
	<div class="movie_name">${Name }</div>
	<div class="movie_age">${Grade }</div>
	
	<div class="comments">${Comments }</div>

</div>




</body>
<script>
 function fuck(){
	 
	 alert("OK");
	   
	  location.href="/gosc?"+"sCode=2";
 }
</script>
</html>
