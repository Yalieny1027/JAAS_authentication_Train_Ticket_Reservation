<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
    <h1 class="hd"> Add New Train Details <br/></h1>			
		
<form  class="tab" action="addtrain" method="put"><br/>
    Source :<input type="text" name="source"><br/><br/>
    Destination : <input type="text" name="destination"><br/><br/>
    Train Number: <input type="text" name="trainno"><br/><br/>
    Depart time: <input type="text" name="departtime"><br/><br/>
    First class price: <input type="text" name="fprice"><br/><br/>
    Second class price: <input type="text" name="sprice"><br/><br/>
    Economic price: <input type="text" name="eprice"><br/><br/>
    Available Tickets: <input type="text" name="availabletics"><br/><br/>
		<input type="submit" value=" ADD TRAIN ">
	</form><br/>
	<%

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
 %>
   
</body>
</html>
