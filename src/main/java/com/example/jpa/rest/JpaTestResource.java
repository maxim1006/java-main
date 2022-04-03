package com.example.jpa.rest;

import com.example.jpa.constants.UrlConstants;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "WebContent", description = "WebContent records")
@Path(UrlConstants.API + "/web-content")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JpaTestResource {

    @Data
    @Builder
    private static class TestEntity {
        private String key;
        private String title;
    }

    @GET
    @Path("/")
    public Response getItems(@QueryParam("structureId") Long structureId, @QueryParam("key") String key) {
        List<TestEntity> list = new ArrayList<TestEntity>();

        list.add(TestEntity.builder().key("key").title("title").build());
        list.add(TestEntity.builder().key("key1").title("title1").build());
        list.add(TestEntity.builder().key("key2").title("title2").build());

        return Response.ok(list).build();
    }

    @DELETE
    public Response clear() {
        return Response.ok().build();
    }
}

