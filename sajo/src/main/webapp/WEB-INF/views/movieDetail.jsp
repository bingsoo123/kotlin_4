<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보</title>
<link href="${path}/resources/css/movie.css" rel="stylesheet" />
</head>
<body>
	<div class="movie">
		<div class="movie_top">
			<img src="../resources/img/${Image }">
		</div>
		<div class="movie_name">${Name }</div>
		<div class="movie_age">${Grade }</div>
		<div>${Comments }</div>
	</div>
	
	<div>${one }</div>
	<div>${two }</div>
	<div>${three }</div>
	<div>${four }</div>
	<div>${five }</div>
</body>
</html>
