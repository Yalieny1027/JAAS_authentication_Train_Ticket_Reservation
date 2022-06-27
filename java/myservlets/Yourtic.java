package myservlets;

import java.io.IOException;
import jfiles.Common;
import jfiles.Intermediate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
public class Yourtic extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;
    public void service(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        Common.yourticdata=null;
        Intermediate intermediate= new Intermediate();
        intermediate.selected();
        intermediate.view_yourtic();
    }
}