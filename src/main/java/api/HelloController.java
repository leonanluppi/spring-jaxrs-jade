package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.ModelAndViewResource;

@Path("/")
public class HelloController {
	
	@GET
	@Path("/get")	
	@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
	public ModelAndViewResource get() {
		return new ModelAndViewResource("index.jade").set("name", "LEONAN");
	}
}
