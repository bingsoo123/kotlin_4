<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${welcome }  
	${hellow }
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="/LogInForm">로그인 폼 이동</a>
</body>
</html>
