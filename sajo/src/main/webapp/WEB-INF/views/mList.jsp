<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우리영화관에 오신것을 환영합니다!</title>
<link href="${path}/resources/css/movie.css" rel="stylesheet" />
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


	<section id="Zone"></section>

	<footer> </footer>

</body>

<script>
	let section = document.getElementById("Zone");
	let movieList = JSON.parse('${jsonMovie}');
	let record = parseInt(movieList.length / 5);
	record = (movieList.length % 5 > 0) ? record + 1 : record;
	for (rIndex = 0; rIndex < record; rIndex++) {
		let div = document.createElement('Div');
		div.style.display = "inline-flex";
		div.setAttribute("name", "line");
		section.appendChild(div);
	}
	
	for (index = 0; index < movieList.length; index++) {
		let rDivIndex = parseInt(index / 5);
		let mvDiv = document.createElement('Div');
		mvDiv.style.width = "150px";
		mvDiv.style.height = "300px";
		mvDiv.style.margin = "0px 10px 20px 0px";
		mvDiv.style.backgroundImage = "url(/resources/img/"
				+ movieList[index].mvImage + ")"
		mvDiv.style.backgroundSize = "contain";
		mvDiv.style.cursor = "pointer";
		let mvCode = movieList[index].mvCode;
		mvDiv.addEventListener('click', function() {
			goData(mvCode);
		});
		let line = document.getElementsByName("line")[rDivIndex];
		line.appendChild(mvDiv);
	}

	function goData(mv) {

		var data = "${Access}:";
		alert(data);
		let now = new Date();
		let tester = "&mvDate=" + data;

		for (var index = 1; index <= 5; index++) {
			now.setDate(now.getDate() + 1);
			tester += now.getFullYear() + "-";
			tester += (now.getMonth() + 1) >= 10 ? (now.getMonth() + 1) : '0'
					+ (now.getMonth() + 1) + "-";
			tester += now.getDate() >= 10 ? now.getDate() : '0' + now.getDate();
			(index == 5) ? tester += "" : tester += ":";

		}
		location.href = "/goData?mvCode=" + mv + "&sCode=1" + tester;

	}
</script>

</html>