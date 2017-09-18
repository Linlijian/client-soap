package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

/**
 * Servlet implementation class Create
 */
@WebServlet("/create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String id = request.getParameter("id");
		
		// out put web
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/tradefootball-client/\"></head>");
		out.print("<body>");
		
		out.println("<form action=\"save\">");
		out.println(	"Number: <input type=\"text\" name=\"number\" value=\"\"><br>");
		out.println("Name: <input type=\"text\" name=\"name\" value=\"\"><br>");
		out.println("Last name : <input type=\"text\" name=\"lname\" value=\"\"><br>");
		out.println("Age : <input type=\"text\" name=\"age\" value=\"\"><br>");
		out.println("Weight: <input type=\"text\" name=\"weight\" value=\"\"><br>");
		out.println(" Hight: <input type=\"text\" name=\"height\" value=\"\"><br>");
		out.println("Date: <input type=\"text\" name=\"D_M_Y\" value=\"\"><br>");
		out.println("Team: <input type=\"text\" name=\"team\" value=\"\"><br>");
		out.println("Position: <input type=\"text\" name=\"position\" value=\"\"><br>");
		out.println("<input type=\"submit\" value=\"GO!\">");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private static URI getBaseURI() { 
		return UriBuilder.fromUri( "http://localhost:8080/tradefootball-server/find").build(); 
	}
}
