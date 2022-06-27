package myservlets;

import java.io.IOException;
import jfiles.Common;
import jfiles.Intermediate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class Booktic extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;
    public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        Intermediate intermediate= new Intermediate();
        Common.trainsource=req.getParameter("bsource");
        Common.traindestination=req.getParameter("bdestination");
        Common.traintrainno=req.getParameter("btrainno");
        Common.trainclass=req.getParameter("bclass");
        Common.trainnumtic=req.getParameter("bnumtic");
        intermediate.selected();
        intermediate.booktic();
        if(Common.bookticsuccess=="1") {
        	HttpSession session = req.getSession(true);
        	resp.sendRedirect("Bookticket.jsp");
        }
        else {
        	HttpSession session = req.getSession(false);
        	resp.sendRedirect("Useropt.jsp");
        }
    }
}