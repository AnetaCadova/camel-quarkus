package org.apache.camel.quarkus.component.platform.http.custom.exception.mapping.it;

import org.apache.camel.component.vertx.VertxComponent;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("/platform-http")

public class Resource {

    @Provider
    public static class CustomNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
        @Override
        public Response toResponse(NotFoundException exception) {
            return Response.status(404)
                .entity("Custom Not Found exception")
                .build();
        }
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String quarkusVertxInstanceUsedInVertxComponent() {
       return "HI";
    }

}
