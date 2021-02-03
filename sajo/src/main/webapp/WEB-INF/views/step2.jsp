<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<link href="${path}/resources/css/moviedetail.css" rel="stylesheet" />
</head>
<body onLoad="init()">
	<P>Now Time : ${Access}</P>
	<section id="movieZone" style="display: flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">

			<div class="date">
				<div class="button" onClick="goScreen('${today}')">${today }</div>
				<div class="button" onClick="goScreen('${tomorrow }')">${tomorrow }</div>
				<div class="button" onClick="goScreen('${three }')">${three }</div>
				<div class="button" onClick="goScreen('${four }')">${four }</div>
				<div class="button" onClick="goScreen('${five }')">${five }</div>
			</div>

		</div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body> 
<script>
	function init() {
		/* Convert Date */
		let dayStr = "${Access}";
		let day = dayStr.split("-");
		let now = new Date();
		now.setFullYear(parseInt(day[0]), parseInt(day[1]) - 1,
				parseInt(day[2]));

		let movieInfo = document.getElementById("movieInfo");
		/* Append movieInfo Div */
		let movie = JSON.parse('${movieData}');

		let mvImage = document.createElement('Div');
		mvImage.style.width = "150px";
		mvImage.style.height = "300px";
		mvImage.style.margin = "0px 10px 20px 0px";
		mvImage.className = "movie_top"
		mvImage.style.backgroundImage = "url(/resources/img/" + movie.mvImage
				+ ")";
		mvImage.style.backgroundSize = "contain";
		movieInfo.appendChild(mvImage);

		let mvTitle = document.createElement('Div');
		mvTitle.textContent = movie.mvName;
		mvImage.className = "movie_name";
		movieInfo.appendChild(mvTitle);

		let mvGrade = document.createElement('Div');
		mvGrade.textContent = movie.mvGrade;
		mvGrade.className = "movie_age";
		movieInfo.appendChild(mvGrade);

		let mvStatus = document.createElement('Div');
		mvStatus.textContent = movie.mvStatus;
		mvStatus.className = "movie";
		movieInfo.appendChild(mvStatus);

		let mvComments = document.createElement('Div');
		mvComments.textContent = movie.mvComments;
		mvComments.className = "movie_comment";
		movieInfo.appendChild(mvComments);
	}
	
	function goScreen(tt){
		
		let time = ${movieData}.mvCode;
		let request = new XMLHttpRequest();
		
		request.onreadystatechange = function(){
			
			if(this.readyState == 4 && this.status == 20){
				
				console.log("서버에 갓다옴");
			}
			
		};
		
		request.open("POST","goScreen?mvDate="+tt+"&mvCode="+time+"&sCode=2",true);
		request.send();
		//location.href = "/goScreen?mvDate="+tt+"&mvCode="+${mvCode}+"&sCode=2";
		
	}
	
	
</script>
</html>
