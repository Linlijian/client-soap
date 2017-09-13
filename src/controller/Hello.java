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
			   throw new RuntimeException("Failed : HTTP error code /: "
				+ clientResponse.getStatus());
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
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
				out.println(eElement.getElementsByTagName("lname").item(0).getTextContent());
	
			}
		}
	
		// output web
		response.setContentType("text/html;charset-UTF-8");
		out.print("<html>");
		out.print("<body>");
		out.println("hello "+name);
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
