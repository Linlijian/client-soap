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
 * Servlet implementation class Create
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String id = request.getParameter("id");
    	
    	// request and response RESTful
    	ClientConfig config = new DefaultClientConfig(); 
    	Client client = Client.create(config); 
    	WebResource service = client.resource(getBaseURI()); 
    			
    	// request xml
    	ClientResponse clientXmlResponse = service.path("find").path("createPLayer").path("getUpdate").path(id) .accept(MediaType.TEXT_XML).get(ClientResponse.class);
    	String outputFromXml =  clientXmlResponse.getEntity(String.class);
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
		
		// out put web
    	out.print("<html>");
    	out.println("<head><base href=\"http://localhost:8080/tradefootball-client/\"></head>");
		out.println("<body>");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				out.println("<form action=\"save\">");
				out.println("<input type=\"hidden\" name=\"id\" value=\""+id+"\"><br>");
				out.println("Number : <input type=\"text\" name=\"number\" value=\""+eElement.getElementsByTagName("number").item(0).getTextContent()+"\"><br>");
				out.println("Name : <input type=\"text\" name=\"name\" value=\""+eElement.getElementsByTagName("name").item(0).getTextContent()+"\"><br>");
				out.println("Last name : <input type=\"text\" name=\"lname\" value=\""+eElement.getElementsByTagName("lname").item(0).getTextContent()+"\"><br>");
				out.println("Age : <input type=\"text\" name=\"age\" value=\""+eElement.getElementsByTagName("age").item(0).getTextContent()+"\"><br>");
				out.println("Weight : <input type=\"text\" name=\"weight\" value=\""+eElement.getElementsByTagName("weight").item(0).getTextContent()+"\"><br>");
				out.println("Hight : <input type=\"text\" name=\"height\" value=\""+eElement.getElementsByTagName("height").item(0).getTextContent()+"\"><br>");
				out.println("Date : <input type=\"text\" name=\"D_M_Y\" value=\""+eElement.getElementsByTagName("D_M_Y").item(0).getTextContent()+"\"><br>");
				out.println("Team : <input type=\"text\" name=\"team\" value=\""+eElement.getElementsByTagName("team").item(0).getTextContent()+"\"><br>");
				out.println("Position : <input type=\"text\" name=\"position\" value=\""+eElement.getElementsByTagName("position").item(0).getTextContent()+"\"><br>");
				out.println("<input type=\"submit\" value=\"GO GO!!\">");
				out.println("</form>");
				
			}
		}
		
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
