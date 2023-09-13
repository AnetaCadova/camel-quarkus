package org.apache.camel.quarkus.component.platform.http.custom.exception.mapping.it;

import org.apache.camel.CamelContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(404)
                .entity("Custom Not Found exception")
                .build();
    }
}
