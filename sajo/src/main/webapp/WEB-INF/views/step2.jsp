<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>

</head>
<body onLoad="init()">
	<P>  Now Time : ${Access} </P>
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
	let dateList = document.getElementById("selectionDate");
	dateList.style.color = "blueviolet";
	let dayStr = "${Access}";
	let day = dayStr.split("-");
	let now = new Date(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
		let month = ((now.getMonth()+1)<10)? "0" + (now.getDate()-2) : (now.getMonth()-2);
	for(i=0; i < 7; i++){
		now.setDate(now.getDate() + ((i==0)?0:1));
		let dateDiv = document.createElement('Div');
		let date = (now.getDate() < 10)? "0" + now.getDate() : now.getDate();
		dateDiv.textContent = now.getFullYear() + "-" + month + "-" + date;
		dateDiv.style.cursor = "pointer";
		dateDiv.style.margin = "5px";
		dateDiv.style.color = "green";
		dateDiv.style.background = "yellow";
		dateDiv.addEventListener('click', function(){divClick( movie.MvCode, this.textContent);});
		dateList.appendChild(dateDiv);
	}
	
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	alert(movie.MvImage);
	
	let mvImage = document.createElement('Div');
	mvImage.style.width = "150px";
	mvImage.style.height = "300px";
	mvImage.style.margin = "0px 10px 20px 0px";
	mvImage.style.backgroundImage = "url(/resources/img/" + movie.MvImage + ")";
	mvImage.style.backgroundSize = "contain";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie.MvName;
	mvImage.className = "movie";
	mvTitle.style.background = "red";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie.MvGrade;
	mvGrade.className = "movie";
	mvGrade.style.background = "orange";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie.MvStatus;
	mvStatus.className = "movie";
	mvStatus.style.background = "yellow";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie.MvComments;
	mvComments.className = "movie";
	mvComments.style.background = "green";
	movieInfo.appendChild(mvComments);
}

function divClick(MvCode, date){
	alert(MvCode + " : " + date);
	
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			let jsonData = decodeURIComponent(request.response);
			alert(jsonData);
			screeningData = JSON.parse(jsonData);
			alert(screeningData);
			screening();
			
			
		}
	};
	request.open("POST", "Step3", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	request.send("MvCode="+MvCode+"&MvDate="+date+"&sCode=3");
	}
	function screening(){
		
		let zTime = document.getElementById("selectionTime");
		
		for(index=0; index < screeningData.length; index++){
			let z = index;
			let zDiv = document.createElement("div");
			let zImg = document.createElement("img");
			zImg.src="/resources/img/"+ screeningData[index].MvGrade+".png";
			
			let zScreen = document.createElement("input");
			zScreen.type= "button";
			zScreen.value = screeningData[index].MvTime + " " + screeningData[index].MvScreen.replace("+", "") + "관";
			zScreen.style.cursor = "pointer";
			zScreen.addEventListener('click', function(){
			zScreenClick(z);
			});
			zDiv.appendChild(zImg);
			zDiv.appendChild(zScreen);
			
			
			zTime.appendChild(zDiv);
		}
	}
	function zScreenClick(index){
		let formData = "sCode=4&MvCode=" + screeningData[index].MvCode +
		"&MvThCode=1" +
		"&MvScreen="+ screeningData[index].MvScreen +
		"&MvName=" + screeningData[index].MvName +
		"&MvGrade=" + screeningData[index].MvGrade +
		"&MvTime=" + screeningData[index].MvTime;
	
		
		let form = document.createElement("form");
		form.action = "Step4?" + formData;
		form.method = "post";
		document.body.appendChild(form);
		form.submit();
	}
function gosc(jd){
	
	
	
	let movieInfo = document.getElementById("selectionTime");
	
	let movie = JSON.parse(jd);
	alert("testing=" + movie);
	alert(movie[0].MvScreen);
	alert(movie[0].MvName);
	
	for(var index=0; index<movie.length; index++){
		
		let mvSeat = document.createElement("Div");
		mvSeat.textContent = movie[index].MvScreen;
		movieInfo.appendChild(mvSeat);
		
		let mvTime = document.createElement("Div");
		mvTime.textContent = movie[index].MvTime;
		movieInfo.appendChild(mvTime);
		
		
		
		let mvName = document.createElement("Div");
		mvName.textContent = movie[index].MvName;
		movieInfo.appendChild(mvName);
		
		let mvGrade = document.createElement("Div");
		mvGrade.textContent = movie[index].MvGrade;
		movieInfo.appendChild(mvGrade);
	}
}
	
</script>
</html>
