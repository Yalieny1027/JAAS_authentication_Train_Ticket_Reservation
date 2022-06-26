<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jfiles.Common"%>
<%@page import="jfiles.Intermediate"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> All Booked Tickets</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
 %>
 
 <h1 class="hd"> Booked Tickets <br/></h1>
  
    <%          
    Intermediate inter=new Intermediate();
    inter.selected();
    inter.view_alltic();
        int size = (Common.allticdata == null) ? 0 : Common.allticdata.size();
        if(size==0){
		  response.sendRedirect("Nodata.jsp");
	  }
    else{
    %>
	<table border ="1" width="500" align="center">
    <TR>
          <th><b>Name</b></th>
          <th><b>Age</b></th>
          <th><b>Gender</b></th>
          <th><b>Phone</b></th>
          <th><b>Source</b></th>
          <th><b>Destination</b></th>
          <th><b>Train number</b></th>
          <th><b>Depart Time</b></th>
          <th><b>Total Price</b></th>
     </TR>
     <%
          int y=0;
          for(int i=0;i<Common.allticdata.size()/9;i++){
        		  out.println("<tr><td>" + Common.allticdata.get(y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  out.println("<td>" + Common.allticdata.get(++y) + "</td>");
        		  y++;
        	  out.println("</tr>");
          }
          Common.allticdata.clear();
    }
        %>
        </table>
    
</body>
</html>
