package myservlets;

import java.io.IOException;

import jfiles.AEScode;
import jfiles.Common;
import jfiles.Intermediate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class Usersignup extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;
    public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

    	Common.username=req.getParameter("uname");
    	Common.userage=req.getParameter("uage");
    	Common.usergender=req.getParameter("ugender");
    	Common.userphone=req.getParameter("uphone");
    	Common.userpassword=req.getParameter("upassword");
        
        Common c= new Common();
    	try {
            Common.cipherString = AEScode.encrypt(Common.userpassword, Common.encKey);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Intermediate intermediate= new Intermediate();
        intermediate.selected();
        intermediate.usersignup();
        if(Common.ussignsuccess=="1") {
        	HttpSession session = req.getSession(true);
        	resp.sendRedirect("Useropt.jsp");
        }
        else {
        	HttpSession session = req.getSession(false);
        	resp.sendRedirect("index.jsp");
        }
    }
    
}