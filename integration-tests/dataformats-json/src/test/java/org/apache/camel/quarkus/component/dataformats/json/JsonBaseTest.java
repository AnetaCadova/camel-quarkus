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
package org.apache.camel.quarkus.component.dataformats.json;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.json.bind.JsonbBuilder;
import org.apache.camel.quarkus.component.dataformats.json.model.AnotherObject;

import static org.hamcrest.Matchers.*;

public class JsonBaseTest {
    public void testMarshallAndUnmarshall(String jsonComponent) {
        RestAssured.given().contentType(ContentType.TEXT)
                .queryParam("json-component", jsonComponent)
                .body("[{\"dummy_string\": \"value1\"}, {\"dummy_string\": \"value2\"}]")
                .post("/dataformats-json/in");
        RestAssured.given()
                .queryParam("json-component", jsonComponent)
                .post("/dataformats-json/out")
                .then()
                .body("dummy_string", equalTo("value1"))
                .body("date", containsString("1970"));
        RestAssured.given()
                .queryParam("json-component", jsonComponent)
                .post("/dataformats-json/out")
                .then()
                .body("dummy_string", equalTo("value2"))
                .body("date", containsString("1970"));
    }

    public void testUnmarshallingDifferentPojos(String jsonComponent) {
        String bodyA = "{\"name\":\"name A\"}";
        String bodyB = "{\"value\":1.0}";

        RestAssured.given().contentType(ContentType.TEXT)
                .queryParam("json-component", jsonComponent)
                .body(bodyA)
                .post("/dataformats-json/in-a");
        RestAssured.given().contentType(ContentType.TEXT)
                .queryParam("json-component", jsonComponent)
                .body(bodyB)
                .post("/dataformats-json/in-b");
        RestAssured.given()
                .queryParam("json-component", jsonComponent)
                .post("/dataformats-json/out-a")
                .then()
                .body(equalTo(bodyA));
        RestAssured.given()
                .queryParam("json-component", jsonComponent)
                .post("/dataformats-json/out-b")
                .then()
                .body(equalTo(bodyB));
    }

    public void testUnmarshal(String directId) {
        AnotherObject object = new AnotherObject();
        object.setDummyString("95f669ce-d287-4519-b212-4450bc791867");

        RestAssured.given()
                .contentType(ContentType.TEXT)
                .body(JsonbBuilder.create().toJson(object))
                .post("/dataformats-json/unmarshal/{direct-id}", directId)
                .then()
                .body("dummyString", is(object.getDummyString()));
    }
}
