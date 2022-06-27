package myservlets;

import java.io.IOException;
import jfiles.Common;
import jfiles.Intermediate;

import jfiles.AEScode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Adminsignup extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;
    public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

    	Common.adminname=req.getParameter("sadminname");
    	Common.adminpassword=req.getParameter("sadminpassword");
        Common c= new Common();
    	try {
            Common.cipherString = AEScode.encrypt(Common.adminpassword, Common.encKey);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Intermediate intermediate= new Intermediate();
        intermediate.selected();
        intermediate.adminsignup();
        if(Common.adsignsuccess=="1") {
        	HttpSession session = req.getSession(true);
        	resp.sendRedirect("Adminopt.jsp");
        }
        else {
        	HttpSession session = req.getSession(false);
        	resp.sendRedirect("index.jsp");
        }
    }
    
}