<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 <h1 class="hd"> Book Ticket <br/></h1>
  
	<header>
	<div class="home">
		<p1 class="menu"><a href="Availabletrains.jsp">Available Trains</a></p1>
	</div>
	</header>
	<form  class="tab" action="booktic" method="put"><br/><br/>
        Source :<input type="text" name="bsource"><br/><br/>
        Destination :<input type="text" name="bdestination"><br/><br/>
        Train Number :<input type="text" name="btrainno"><br/><br/>
        Class(F/S/E) :<input type="text" name="bclass"><br/><br/>
        Number of tickets :<input type="text" name="bnumtic"><br/><br/>
        <input type="submit" value=" BOOK TICKET ">
	</form><br/>


</body>
</html>
