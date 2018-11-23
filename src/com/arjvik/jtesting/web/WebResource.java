package com.arjvik.jtesting.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.mvc.Template;

@Path("")
@Produces(MediaType.TEXT_HTML)
@RequestScoped
public class WebResource {
	
	@GET
	@Template(name="/index")
	public Response root() {
		return home();
	}
	
	@GET
	@Template(name="/index")
	@Path("home") public Response home() {
		return view();
	}

	//-----------------------------------------------------------------------------------------------------------
	
	@GET
	@Template(name="/404")
	@Path("{all:.*}") public Response notFound(@PathParam("all") String path) {
		return Response.status(Status.NOT_FOUND).entity(viewEntity()).build();
	}

	private Session session;
	private UriInfo uriInfo;
	
	@Inject
	public WebResource(@Context HttpServletRequest request, @Context UriInfo uriInfo) {
		this.session = new Session(request);
		this.uriInfo = uriInfo;
	}
	
	private Response view(Object... args) {
		return Response.ok(viewEntity(args)).build();
	}

	private WebpageEntity viewEntity(Object... args) {
		return new WebpageEntity(session, getUrlParams(), args);
	}
	
	private static Object[] cachedUrlParams;
	
	private Object[] getUrlParams(){
		if(cachedUrlParams == null)
			cachedUrlParams = new Object[]{

			};
		return cachedUrlParams;
	}

	private String buildUri(String method) {
		return uriInfo.getBaseUriBuilder().path(WebResource.class, method).build().toString();
	}
	
	private String buildUriSpecial(String path) {
		return uriInfo.getBaseUriBuilder().path(path).build().toString();
	}
	
}
