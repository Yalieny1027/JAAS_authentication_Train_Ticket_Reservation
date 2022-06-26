<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="jfiles.JWT"%>
<%@page import="jfiles.Common"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
<h2>Hi User : ${pageContext.request.remoteUser}</h2>
<h2>Hi User : ${pageContext.request.userPrincipal.name}</h2> 
<% 
Common com = new Common();
Common.Cookies cooki = com.new Cookies(null,null,null,null);
         
cooki.cookies.forEach(cook -> {
    if(cook.getcname() == Common.username){
        System.out.println("Name : " + cook.getcname() + ", Age : " + cook.getcage());
        cooki.cookies.remove(com.new Cookies(cook.getcname(),cook.getcage(),cook.getcphone(),cook.getcgender()));
    }
});
%>
</body>

</html>
