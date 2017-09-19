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
		out.println("<head><base href=\"http://localhost:8080/tradefootball-client/\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script></head>");
		out.print("<body>");
		
		out.print("<div class=\"container\">");
		out.print("<div class=\"form-group\">");
		out.println("<form action=\"save\">");
		out.println("Name: <input type=\"text\" name=\"name\" class=\"form-control\" placeholder=\"Name\" value=\"\"><br>");
		out.println("Last name : <input type=\"text\" name=\"lname\" class=\"form-control\" placeholder=\"Last name\" value=\"\"><br>");
		out.println("Age : <input type=\"text\" name=\"age\" class=\"form-control\" placeholder=\"Age\" value=\"\"><br>");
		out.println("Weight: <input type=\"text\" name=\"weight\" class=\"form-control\" placeholder=\"Weight\" value=\"\"><br>");
		out.println(" Hight: <input type=\"text\" name=\"height\" class=\"form-control\" placeholder=\"Hight\" value=\"\"><br>");
		out.println("Date: <input type=\"text\" name=\"D_M_Y\" class=\"form-control\" placeholder=\"Date_Month_Year\" value=\"\"><br>");
		out.println("Team: <input type=\"text\" name=\"team\" class=\"form-control\" placeholder=\"Team\" value=\"\"><br>");
		out.println("Position: <input type=\"text\" name=\"position\" class=\"form-control\" placeholder=\"Position\" value=\"\"><br>");
		out.println("Number: <input type=\"text\" name=\"number\" class=\"form-control\" placeholder=\"Number\" value=\"\"><br>");
		out.println("Country: <input type=\"text\" name=\"country\" class=\"form-control\" placeholder=\"country\" value=\"\"><br>");
		out.println("_idteam: <input type=\"text\" name=\"_idteam\" class=\"form-control\" placeholder=\"_idteam\" value=\"\"><br>");
		out.println("<input class=\"btn btn-success\" type=\"submit\" value=\"Create\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
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
