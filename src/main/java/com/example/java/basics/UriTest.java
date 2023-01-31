package com.example.java.basics;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;

public class UriTest {
    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("");

        UriBuilder.fromUri(uri).
                replacePath(uri.getPath())
                .build()
                .toString();
    }
}

