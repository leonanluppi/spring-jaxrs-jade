package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import core.ModelAndView;

@Path("/")
public class HelloController {
	
	@GET
	@Path("/get")	
	public ModelAndView get() {
		return new ModelAndView("index.jade").set("name", "LEONAN");
	}
}
