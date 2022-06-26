<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@page import="jfiles.Common"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
 %>
 <%
 String name =  request.getRemoteUser();
 System.out.println(name);
 %>
 <div style="margin-left:25%">
    <header>
		<h1 class="hd"> Admin Options <br/></h1>
	<div class="hd">
		<p1 class="menu"><a href="index.jsp">Home</a></p1>
	</div>
	<div class="hd">
		<p1 class="menu"><a href="Addtrain.jsp">Add New Train</a></p1>
	</div>
	<div class="hd">
		<p1 class="menu"><a href="Alltickets.jsp">Booked Ticktes</a></p1>
        <form action="bookedtick" method="get"></form>
	</div>
	<div class="hd">
		<p1 class="menu"><a href="Availabletrains.jsp">Available Trains</a></p1>
	</div>
	<div class="hd">
		<p1 class="menu"><a href="Userdetails.jsp">User Details </a></p1>
	</div>
	<div class="hd">
		<p1 class="menu"><a href="Adminsignup.jsp">Admin Sign Up</a></p1>
	</div>
    <div class="hd">
		<p1 class="menu"><a href="Logout.jsp">Log Out</a></p1>
	</div>
</header>
</div>
</body>
</html>
