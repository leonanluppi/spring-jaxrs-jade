package core;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
public class ModelAndView {
    private final String name;
    private final Map<String, Object> attributes = new HashMap<String, Object>();
    
    public ModelAndView(final String name) {
        this.name = name;
    }
	
    public ModelAndView set(final String name, final Object value) {
        attributes.put(name, value);
        return this;
    }
	
    public Map<String, Object> attributes() {
        return attributes;
    }
 
    public String template() {
        return name;
    }
}