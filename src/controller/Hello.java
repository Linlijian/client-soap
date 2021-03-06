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
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String name = request.getParameter("name");
    	
		// request and response RESTful
		ClientConfig config = new DefaultClientConfig(); 
		Client client = Client.create(config); 
		WebResource service = client.resource(getBaseURI()); 
		
		// request get

		ClientResponse clientResponse = service.path("player").path("findplayer").path(name) .accept(MediaType.TEXT_XML).get(ClientResponse.class);

		if (clientResponse.getStatus() != 200) {
			out.print("<html>");
			out.print("<body>");
			out.print("<div>");
			out.print("<h11>");
			out.print("Search not fornd!!!");
			out.print("/h1");
			out.print("</body>");
			out.print("</html>");
		}

		String outputFromXml =  clientResponse.getEntity(String.class);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    Document doc = null;
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        doc = builder.parse( new InputSource( new StringReader( outputFromXml ) ) );
	        doc.getDocumentElement().normalize();
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } 
	
		NodeList nList  = doc.getElementsByTagName("player");
		response.setContentType("text/html;charset-UTF-8");
		out.print("<html>");
		out.print("<body>");
		out.print("<center>");out.print("<h2>");out.print("Search Results");out.print("</h2>");out.print("</center>");
		out.print("<center>");
		out.print("<table border=1>");
		out.print("<tr>");
		out.print("<th>");out.print("name");out.print("</th>");
		out.print("<th>");out.print("lname");out.print("</th>");
		out.print("<th>");out.print("age");out.print("</th>");
		out.print("<th>");out.print("weight");out.print("</th>");
		out.print("<th>");out.print("height");out.print("</th>");
		out.print("<th>");out.print("D_M_Y");out.print("</th>");
		out.print("<th>");out.print("team");out.print("</th>");
		out.print("<th>");out.print("position");out.print("</th>");
		out.print("<th>");out.print("number");out.print("</th>");
		out.print("<th>");out.print("country");out.print("</th>");
		out.print("<th>");out.print("_idteam");out.print("</th>");
		out.print("</tr>");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			out.print("<tr>");
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				out.print("<h2>");
				out.print("<td>");out.println(eElement.getElementsByTagName("name").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("lname").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("number").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("weight").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("height").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("D_M_Y").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("team").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("position").item(0).getTextContent());out.print("</td>");
				out.print("</h2>");
			}
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("</center>");
		out.print("</body>");
		out.print("</html>");
		
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
