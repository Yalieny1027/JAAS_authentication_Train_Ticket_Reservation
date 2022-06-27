package myservlets;
import java.io.IOException;

import jfiles.AEScode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jfiles.Common;
import jfiles.Intermediate;
import jfiles.Metadata;
import jfiles.Metadata.Selected;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import jaas.LogCallbackHandler;

public class Loginservlet extends HttpServlet {
    String name,password;
	private static final long serialVersionUID = 102831973239L;
    
    public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        name=req.getParameter("username");
    	password=req.getParameter("password");
        newlogin();
        System.out.println(name);
        System.out.println(password);
    	
    	try {
            Common.cipherString = AEScode.encrypt(password, Common.encKey);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    	
        if(Common.ussuccess=="1") {
            if(Common.rl.equals("admin")) {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", Common.username);
                resp.sendRedirect("Adminopt.jsp");
	    		System.out.println("adlog");
	    	}
	    	else {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", Common.username);
                resp.sendRedirect("Useropt.jsp");
	    		System.out.println("uslog");
	    	}
        }
        else {
        	HttpSession session = req.getSession(false);
        	resp.sendRedirect("Loginerror.jsp");
        }
    }
    public void newlogin(){
        LoginContext lc = null;
      try {
          lc = new LoginContext("Web_Train", 
                      new LogCallbackHandler(name,password));
      } catch (LoginException le) {
          System.err.println("Cannot create LoginContext. "
              + le.getMessage());
          System.exit(-1);
      } catch (SecurityException se) {
          System.err.println("Cannot create LoginContext. "
              + se.getMessage());
          System.exit(-1);
      } 

      try {
    
          lc.login();
    
      } catch (LoginException le) {
    
          System.err.println("Authentication failed: ");
          System.err.println("  " + le.getMessage());
          System.exit(-1);
    
      }
    
      System.out.println("Authentication succeeded!");
    }
}