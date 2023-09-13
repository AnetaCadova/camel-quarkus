/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.platform.http.custom.exception.mapping.it;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class PlatformHttpRouteBuilder extends RouteBuilder {
    @Provider
    public static class CustomNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
        @Override
        public Response toResponse(NotFoundException exception) {
            return Response.status(404)
                .entity("Custom Not Found exception")
                .build();
        }
    }
    //    @SuppressWarnings("unchecked")
    @Override
    public void configure() {
//        restConfiguration().component("platform-http").bindingMode(RestBindingMode.off)
//                // and output using pretty print
//                .dataFormatProperty("prettyPrint", "true")
//                // setup context path and port number that api will use
//                .contextPath("my-context");

        //        rest()
        //                .get("/platform-http/rest-get")
        //                .to("direct:echoMethodPath")
        //                .post("/platform-http/rest-post")
        //                .consumes("text/plain").produces("text/plain")
        //                .to("direct:echoMethodPath");
        //
        //        from("direct:echoMethodPath")
        //                .setBody().simple("${header.CamelHttpMethod}: ${header.CamelHttpPath}");
        //
        //        from("direct:greet")
        //                .setBody().simple("Hello ${header.name}");
        //

        //
        //        from("platform-http:/registry/inspect")
        //                .process(e -> {
        //                    Registry registry = e.getContext().getRegistry();
        //
        //                    Object engine = registry.lookupByName(PlatformHttpConstants.PLATFORM_HTTP_ENGINE_NAME);
        //                    Object component = registry.lookupByName(PlatformHttpConstants.PLATFORM_HTTP_COMPONENT_NAME);
        //
        //                    String engineClassName = "";
        //                    String componentClassName = "";
        //
        //                    if (engine != null) {
        //                        engineClassName = engine.getClass().getName();
        //                    }
        //
        //                    if (component != null) {
        //                        componentClassName = component.getClass().getName();
        //                    }
        //
        //                    String json = String.format("{\"engine\": \"%s\", \"component\": \"%s\"}", engineClassName,
        //                            componentClassName);
        //                    Message message = e.getMessage();
        //                    message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        //                    message.setBody(json);
        //                });

        from("platform-http:/platform-http/helloworld")
                .setBody(simple("Hello world"));

    }

}
