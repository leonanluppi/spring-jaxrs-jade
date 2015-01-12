package core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.model.JadeModel;
import de.neuland.jade4j.template.JadeTemplate;

@Provider
@Produces({MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
public class ModelAndView implements MessageBodyWriter<ModelAndViewResource> {
	
	private Jade4J jade = null;
	
	public ModelAndView() {
		jade = new Jade4J();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(ModelAndViewResource modelAndView, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void writeTo(ModelAndViewResource modelAndView, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,OutputStream entityStream) throws IOException, WebApplicationException {
		final OutputStreamWriter writer = new OutputStreamWriter(entityStream);
		JadeTemplate jadeTemplate = new JadeTemplate();
		JadeModel jadeModel = new JadeModel(modelAndView.attributes());
		jade.getTemplate(modelAndView.template()).process(jadeModel, writer);
		writer.flush();
	}
}