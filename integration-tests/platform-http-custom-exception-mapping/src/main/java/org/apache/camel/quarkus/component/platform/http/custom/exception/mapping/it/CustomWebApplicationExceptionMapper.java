package org.apache.camel.quarkus.component.platform.http.custom.exception.mapping.it;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomWebApplicationExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(400)
                .entity("Custom exception")
                .build();
    }
}
