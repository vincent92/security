package nl.hu.v2iac1.rest.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.hu.v2iac1.Configuration;

@Path("/secret")
@Produces(MediaType.TEXT_PLAIN)
public class SecretRestService extends AbstractRestService {

	 @POST
	 @Path("/login")
	 @Produces(MediaType.TEXT_HTML)
	 public Response login(@FormParam("user") String user, @FormParam("pass") String password)  {

		 String output = "";
		 String userOrigin = configuration.getValue(Configuration.Key.USER).trim().replaceAll("\"", "");;
		 String passOrigin = configuration.getValue(Configuration.Key.PASSWORD).trim().replaceAll("\"", "");;

			if (user.trim().equals(userOrigin) && password.trim().equals(passOrigin)) {
				output = "This is the secret: " + configuration.getValue(Configuration.Key.SECRET);
			} else {
				output = "<html>"
						+ "<body>"
						+ "<h2>Login for secret access... username or password rong.. try again!</h2>"
						+ "<form method='post' action='login'>"
						+ "<p><input type='text' name='user' value=''></p>"
						+ "<p><input type='password' name='pass' value=''></p>"
						+ "<p class='submit'><input type='submit' name='commit' value='Login'></p>"
						+ "</form>"
						+ "</body>"
						+ "</html>";
			}
		 return Response.status(200).entity(output).build();
	 }
	 
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Response getSecret(@Context HttpServletRequest req) {

    	String output = "";
    	output = "<html>"
				+ "<body>"
				+ "<h2>Login for secret access...</h2>"
				+ "<form method='post' action='login'>"
				+ "<p><input type='text' name='user' value=''></p>"
				+ "<p><input type='password' name='pass' value=''></p>"
				+ "<p class='submit'><input type='submit' name='commit' value='Login'></p>"
				+ "</form>"
				+ "</body>"
				+ "</html>";
        return Response.status(200).entity(output).build();
    }

}