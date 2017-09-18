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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class Create
 */
@WebServlet("/save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String id = request.getParameter("id");
    	String number = request.getParameter("number");
    	String name = request.getParameter("name");
    	String lname = request.getParameter("lname");
    	String age = request.getParameter("age");
    	String weight = request.getParameter("weight");
    	String height = request.getParameter("height");
    	String D_M_Y = request.getParameter("D_M_Y");
    	String team = request.getParameter("team");
    	String position = request.getParameter("position");
    	
    	System.out.println(id+" "+name+" "+number+" "+lname);
    	
    	MultivaluedMap queryParams = new MultivaluedMapImpl();
    	queryParams.add("number", number);
		queryParams.add("name", name);
		queryParams.add("lname", lname);
    	queryParams.add("age", age);
		queryParams.add("weight", weight);
		queryParams.add("height", height);
    	queryParams.add("D_M_Y", D_M_Y);
		queryParams.add("team", team);
		queryParams.add("position", position);
		
		// request and response RESTful
		ClientConfig config = new DefaultClientConfig(); 
		Client client = Client.create(config); 
		WebResource service = client.resource(getBaseURI()); 
		
		WebResource path = service.path("find").path("createPLayer");
		if(id == null) {
			path = path.path("create");
		}else {
			queryParams.add("id", id);
			path = path.path("update");
		}
				
		ClientResponse clientResponsePost = path .accept(MediaType.TEXT_PLAIN).post(ClientResponse.class, queryParams);
		
		String result = "";
		if (clientResponsePost.getStatus() != 200) {
			result = "What do you want???";
		}else {
			result = "HELP ME!!";
		}

//		String outputFromPost = clientResponsePost.getEntity(String.class);
//		out.print(outputFromPost);
		
		// out put web
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/tradefootball-client/\"></head>");
		out.print("<body>");
		out.println("<center>");
		out.println("<h2>C R E A T E _ S U C C E S S ! ! !</h2>");
		out.println(result+"<br><br>");
		out.println("<a href=\"index.html\">INDEX</a>");
		out.println("</center>");
		
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
		return UriBuilder.fromUri( "http://localhost:8080/tradefootball-server/").build(); 
	}
}
