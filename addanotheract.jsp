<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="jaas.AULoginModule"%>
<%@page import="jfiles.Common"%>
<%@page import="jaas.JaasLc"%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");

AULoginModule au = new AULoginModule();
	JaasLc lc = new JaasLc();
	lc.newlogin();
	if(au.login()){
		System.out.println("double login");
		response.sendRedirect("Useropt.jsp");
	}

else{
    System.out.println("failed double");
    response.sendRedirect("index.jsp");
}

 %>
