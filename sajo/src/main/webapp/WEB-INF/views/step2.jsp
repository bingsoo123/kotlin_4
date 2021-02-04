<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<!--  <link href="/resources/css/kotlin.css" rel="stylesheet"/> -->
</head>
<body onLoad="init()">
	<a href="http://192.168.123.104/LoginForm">로그인폼 이동</a>
	<br />
	<P>Now Time : ${Access}</P>
	<section id="movieZone" style="display: flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">selectionDate</div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>

function init(){
	   /* Convert Date */
	   let dateList = document.getElementById("selectionDate");
	   let dayStr = "${Access}";
	   let day = dayStr.split("-");
	   //let now = new Date();
	   //now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	   let now = new Date(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	   let month = ((now.getMonth()+1)<10)? "0"+(now.getDate()-3): (now.getMonth()-3);
	   
	   let movieInfo = document.getElementById("movieInfo");
	   /* Append movieInfo Div */
	   let movie = JSON.parse('${movieData}');
	   
	   for(i=0; i<7; i++){
	         
	       now.setDate(now.getDate()+((i==0) ? 0:1));
	       let date = (now.getDate()<10)? "0" + now.getDate() : now.getDate();
	       let dateDiv=document.createElement('Div');
	       dateDiv.textContent = now.getFullYear() + "-" + month + "-" + date;
	       dateDiv.style.cursor = "pointer";
	       dateDiv.addEventListener('click', function(){divClick(movie.mvCode, this.textContent);});
	       dateList.appendChild(dateDiv);
	   }
	   
	   
	   
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
	
	
	

function divClick(mvCode, mvDate){
	
	alert(mvCode + " : " + mvDate);	
	
   let request = new XMLHttpRequest();
   
   request.onreadystatechange = function(){
	   
	if (this.readyState == 4 && this.status == 200) {
				let jsonData = decodeURIComponent(request.response);
				//screeningData = JSON.parse(jsonData);
				//gosc();
				gosc(jsonData);
			}
		};

		request.open("POST", "goScreen", true);
		request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		 request.send("mvDate=" + mvDate + "&mvCode=" + mvCode+"&sCode=2");
	}

	function gosc(data) {
	alert(data);
    let movieInfo = document.getElementById("selectionTime");
    
    let movie = JSON.parse(data);
    //alert("test=" + movie);
    //alert(movie[0].mvScreen);

    for(let index=0; index<movie.length; index++){		
    	
    	let value = index;
    	
        let mvSeat = document.createElement('Div');              
        mvSeat.textContent = movie[index].mvScreen;      
        
        movieInfo.appendChild(mvSeat);

        let mvTime = document.createElement('Div');              
        mvTime.textContent = movie[index].mvTime;     
        mvTime.addEventListener('click',function(){ step4();});
        movieInfo.appendChild(mvTime);

        let mvTitle = document.createElement('Div');              
        mvTitle.textContent = movie[index].mvName;           
        movieInfo.appendChild(mvTitle);

        let mvGrade = document.createElement('Div');              
        mvGrade.textContent = movie[index].mvGrade;   
		movieInfo.appendChild(mvGrade);
		} 
    
	}		

	function step4(){

	}
	
</script>
</html>