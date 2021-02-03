<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매하기</title>
<link href="${path}/resources/css/moviedetail.css" rel="stylesheet" />
</head>
<body>


	<header>
		<div class="title">
			<span class="title_detail">ICIA 인천시네마</span>
		</div>
	</header>

	<nav>
		<div class="menu">빠른예매</div>
		<div class="menu">상영관별예매</div>
		<div class="menu">영화추천</div>
		<div class="menu">오시는길</div>
	</nav>



	<section>

		<div class="main_div">

			<div class="movie">

				<div class="movie_top">
					<img src="../resources/img/${Image }">
				</div>

				<div class="movie_name">${Name }</div>

				<div class="movie_age">${Grade }</div>

				<div class="movie_comment">${Comment }</div>

			</div>

			<div class="select">
				
				<div class="date">
						
						<div class="button" onClick="goScreen('${today}')">${today }</div>
						<div class="button" onClick="goScreen('${tomorrow }')">${tomorrow }</div>
						<div class="button" onClick="goScreen('${three }')">${three }</div>
						<div class="button" onClick="goScreen('${four }')">${four }</div>
						<div class="button" onClick="goScreen('${five }')">${five }</div>
				
				</div>

			</div>

		</div>
	</section>

</body>

<script>

	function goScreen(tt){
		
		location.href = "/goScreen?mvDate="+tt+"&mvCode="+${mvCode}+"&sCode=2";
		
	}

</script>


</html>








