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



   <section class="section">

      ${MovieList }

   </section>

   <footer> </footer>

</body>

<script>

function goData(mvcode){
   
   alert("OK");
   
   var form = document.createElement("form");
   
   form.action="goData?mvCode="+mvcode;
   form.method="POST";
   
   document.body.appendChild(form);
   
   form.submit();
   
}

</script>

</html>