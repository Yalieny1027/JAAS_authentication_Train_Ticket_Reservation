<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="jaas.AULoginModule"%>
<%@page import="jfiles.Common"%>
<%@page import="jaas.JaasLc"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store,no-cache,must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title> Train Ticket Reservations</title>
<link rel="stylesheet" href="UserHome_Css.css">

</head>
<body>
    <%
    String url=request.getHeader("Referer");
     %>
    <h1 class="hd"> Login <br/></h1>
    
    <form  class="tab" action="j_security_check" method="GET"><br/><br/>
        Name :<input type="text" name="j_username" ><br/><br/>
        Password :<input type="password" name="j_password"><br/><br/>
        <input type="submit" name="check" value=" LOGIN ">
    </form>
    
 
</body>
</html>