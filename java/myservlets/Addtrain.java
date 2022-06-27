package myservlets;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jfiles.Common;
import jfiles.Intermediate;
public class Addtrain extends HttpServlet {

private static final long serialVersionUID = 102831973239L;
public void service(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
    	Common.trainsource=req.getParameter("source");
    	Common.traindestination=req.getParameter("destination");
    	Common.traintrainno=req.getParameter("trainno");
    	Common.traindeparttime=req.getParameter("departtime");
    	Common.trainfirst=req.getParameter("fprice");
    	Common.trainsecond=req.getParameter("sprice");
    	Common.traineconomy=req.getParameter("eprice");
    	Common.trainavailabletic=req.getParameter("availabletics");
        Intermediate intermediate= new Intermediate();
        intermediate.selected();
        intermediate.addtrain();
        if(Common.addtrainsuccess=="1") {
        	HttpSession session = req.getSession(true);
        	resp.sendRedirect("Addtrain.jsp");
        	}
        else {
        	HttpSession session = req.getSession(false);
        	resp.sendRedirect("Adminopt.jsp");
        }
    }
    
}