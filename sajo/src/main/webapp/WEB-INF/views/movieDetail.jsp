<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date Select</title>
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
	
	<div class="button" onclick="goScreen('${today }')">${today }</div>
	<div class="button" onclick="goScreen('${tomorrow }')">${tomorrow }</div>
	<div class="button" onclick="goScreen('${three }')">${three }</div>
	<div class="button" onclick="goScreen('${four }')">${four }</div>
	<div class="button" onclick="goScreen('${five }')">${five }</div>
</body>
<script>
function goScreen(gs){
	let test = gs;
	location.href="/goScreen?mvDate="+gs+"&mvCode="+${mvCode} + "&sCode=2";
}
</script>
</html>