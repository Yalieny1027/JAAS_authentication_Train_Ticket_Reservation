<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@page import="jfiles.JWT"%>
	<%@page import="jfiles.Common"%>
	<%@page import="jaas.AULoginModule"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type" content="text/html; charset=UTF-8;width=device-width, initial-scale=1">
<title>Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">
<style>
	
	.sidenav {
	  height: 100%;
	  width: 240px;
	  position: fixed;
	  z-index: 1;
	  top: 0;
	  left: 0;
	  background-color: rgb(124, 205, 225);
	  overflow-x: hidden;
	  padding-top: 20px;
	}
	
	.sidenav a {
	  padding: 6px 8px 6px 16px;
	  text-decoration: none;
	  font-size: 20px;
	  color: #212121;
	  display: block;
	}
	
	.sidenav a:hover {
	  color: #f1f1f1;
	}
	
	.main {
	  margin-left: 160px; /* Same as the width of the sidenav */
	  font-size: 28px; /* Increased text to enable scrolling */
	  padding: 0px 10px;
	}
	.space {
  width: 4px;
  height: auto;
  display: inline-block;
}
	
	@media screen and (max-height: 450px) {
	  .sidenav {padding-top: 15px;}
	  .sidenav a {font-size: 18px;}
	}
	</style>
	</head>
	
<body>
	
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");



 String name =  request.getRemoteUser();
 System.out.println(name);
 JWT j = new JWT();
 j.declare();
 	%>
 <h1 class="hd"> User Options <br/></h1>
 <div class="sidenav">
	<ul>
		<p1><a name="check" value="Check" href="Loginservlet.jsp">Add another account</a></p1>
		<p1><a href="signoutall.jsp">Sign out of all accounts</a></p1>
	</ul> 
	<a href="#about">About</a>
  </div>
	
	<div class="main">
		<header>

		<div class="hd">
			<p1 class="menu"><a href="index.jsp">Home</a></p1>
		</div>
		<div class="hd">
			<p1 class="menu"><a href="Bookticket.jsp">Book Your Ticket</a></p1>
		</div>
		<div class="hd">
			<p1 class="menu"><a href="Availabletrains.jsp">Available Trains</a></p1>
		</div>
		<div class="hd">
			<p1 class="menu"><a href="Yourtickets.jsp">Your Tickets</a></p1>
		</div>
		<div class="hd">
			<p1 class="menu"><a href="Logout.jsp">Log Out</a></p1>
		</div>
	</header>

</div>
</body>
</html>


