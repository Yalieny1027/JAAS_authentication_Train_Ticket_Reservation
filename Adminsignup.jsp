<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@page import="jfiles.Common"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
 %>
 <h1 class="hd"> Admin Sign Up <br/></h1>
    
    <form  class="tab" action="adminsignup" method="put"><br/>
    Name :<input type="text" name="sadminname"><br/><br/>
    Password : <input type="password" name="sadminpassword"><br/><br/>
		<input type="submit" value=" SIGN UP ">
	</form><br/>
	

</body>
</html>
