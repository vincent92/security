package nl.hu.v2iac1.rest.resource;

import java.util.Map;

import nl.hu.v2iac1.Configuration;
import nl.hu.v2iac1.facebook.FBConnection;
import nl.hu.v2iac1.facebook.FBGraph;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/verysecret")
@Produces(MediaType.TEXT_PLAIN)
public class VerySecretRestService extends AbstractRestService {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Response getSecret(@Context HttpServletRequest req) {

    	FBConnection fbConnection = new FBConnection();
    	String output = null;

		if (req.getParameter("code") == null || req.getParameter("code").equals("")) {
			
			output = "<html>";
	    	output += "<body style='text-align: center; margin: 0 auto;'>";
	    	output += "<div>";
	    	output += "<a href='"+fbConnection.getFBAuthUrl()+"'> Klik hier om in te loggen op facebook </a> </div>";
	    	output += "</body>";
	    	output += "</html>";
	    	
		} else {

			String accessToken = fbConnection.getAccessToken(req.getParameter("code"));
			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();
			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			
			output = "<h1>Facebook Login using Java</h1>";
			output += "Welcome " + fbProfileData.get("first_name");
			output += "<br/>Your Email: " + fbProfileData.get("email");
			output += "<br/>You are " + fbProfileData.get("gender");
			output += "<br/><br/>** YOU HAVE NOW SECRET ACCESS ** LEVEL = 'Very Secret'";
			output += "<br/>" + configuration.getValue(Configuration.Key.VERYSECRET);
			
		}
		
        return Response.status(200).entity(output).build();

    }

}