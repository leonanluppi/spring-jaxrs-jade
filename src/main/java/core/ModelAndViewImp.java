package core;

import static de.neuland.jade4j.Jade4J.getTemplate;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Named;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import de.neuland.jade4j.model.JadeModel;

@Named
@Provider
@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
public class ModelAndViewImp implements MessageBodyWriter<ModelAndView> {

	private String dirJade = "";
	
	public ModelAndViewImp() {
		try {
			setDirJade(new File(".").getCanonicalPath() + "/src/main/resources/templates/");
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(ModelAndView modelAndView, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(ModelAndView modelAndView, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,OutputStream entityStream) throws IOException, WebApplicationException {
		final OutputStreamWriter writer = new OutputStreamWriter(entityStream);
	
		JadeModel jadeModel = new JadeModel(modelAndView.attributes());
		getTemplate(getDirJade() + modelAndView.template()).process(jadeModel, writer);
		
		writer.flush();
	}

	public String getDirJade() {
		return dirJade;
	}

	public void setDirJade(String dirJade) {
		this.dirJade = dirJade;
	}
}