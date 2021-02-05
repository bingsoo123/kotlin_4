<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<link href="/resources/css/movie.css" rel="stylesheet"/> 
</head>
<body onLoad="init()">
	<P> Now Time : ${Access} </P>
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">selectionDate</div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>
let screeningData;
function init(){
	/* Convert Date */
	let dateList = document.getElementById("selectionDate")
	let dayStr = "${Access}";
	let day = dayStr.split("-");
	let now = new Date(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	for(i=0; i < 7; i++){
		now.setDate(now.getDate() + ((i==0)?0:1));
		let dateDiv = document.createElement('Div');
		let month = ((now.getMonth()+1)<10)? "0"+(now.getMonth()+1) : (now.getMonth());
		let date = (now.getDate() < 10)? "0" + now.getDate() : now.getDate();
		dateDiv.textContent = now.getFullYear() + "-" + month + "-" + date;
		dateDiv.style.cursor = "pointer";
		dateDiv.addEventListener('click', function(){divClick( movie.mvCode, this.textContent);});
		dateList.appendChild(dateDiv);
	}
	
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	
	let mvImage = document.createElement('Div');
	mvImage.style.width = "150px";
	mvImage.style.height = "300px";
	mvImage.style.margin = "0px 10px 20px 0px";
	mvImage.style.backgroundImage = "url(/resources/img/" + movie.mvImage + ")";
	mvImage.style.backgroundSize = "contain";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie.mvName;
	mvImage.className = "movie";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie.mvGrade;
	mvGrade.className = "movie";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie.mvStatus;
	mvStatus.className = "movie";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie.mvComments;
	mvComments.className = "movie";
	movieInfo.appendChild(mvComments);
}

function divClick(mvCode, date){
	//서버전송
	
	alert(mvCode + " : " + date);
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			let jsonData = decodeURIComponent(request.response);
			alert(jsonData)
			screeningData = JSON.parse(jsonData);
			alert(screeningData);
			screening();
		}
	};
	request.open("POST", "step4", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	request.send("sCode=2&mvCode="+mvCode+"&mvDate="+date);
}

function gosc(jd){
	
	
	
	let movieInfo = document.getElementById("selectionTime");
	
	let movie = JSON.parse(jd);
	alert("testing=" + movie);
	alert(movie[0].MvScreen);
	alert(movie[0].MvName);
	
	for(var index=0; index<movie.length; index++){
		
		let mvSeat = document.createElement("Div");
		mvSeat.textContent = movie[index].mvSeat;
		movieInfo.appendChild(mvSeat);
		
		let mvTime = document.createElement("Div");
		mvTime.textContent = movie[index].mvTime;
		movieInfo.appendChild(mvTime);
		
		
		
		let mvName = document.createElement("Div");
		mvName.textContent = movie[index].mvName;
		movieInfo.appendChild(mvName);
		
		let mvGrade = document.createElement("Div");
		mvGrade.textContent = movie[index].mvGrade;
		movieInfo.appendChild(mvGrade);
	}



function screening(){
	let sTime = document.getElementById("selectionTime"); 
	
	for(index=0; index < screeningData.length; index++){
		let i = index;
		let tDiv = document.createElement("Div");
		let tImg = document.createElement("img");
		tImg.src = "/resources/img/"+ screeningData[index].mvGrade+"1123.jpg";
		
		let tScreen = document.createElement("input");
		tScreen.type = "button";
		tScreen.value = screeningData[index].mvTime + " " + screeningData[index].mvScreen + "관";
		tScreen.style.cursor = "pointer";
		tScreen.addEventListener('click', function(){
			tScreenClick(i);
		});
		
		tDiv.appendChild(tImg);
		tDiv.appendChild(tScreen);
		
		sTime.appendChild(tDiv);
	}
}

function tScreenClick(index){
	let formData = "sCode=4&mvCode=" + screeningData[index].mvCode + 
	"&mvThCode=1&mvDateTime=" + screeningData[index].mvDate.replace(/-/g, "") + screeningData[index].mvTime.replace(":", "") 
	 + "&mvScreen=" + screeningData[index].mvScreen;

	let form = document.createElement("form");
	form.action = "step5?" + formData;
	form.method = "post";
	document.body.appendChild(form);
	form.submit();
}
}

</script>
</html>
