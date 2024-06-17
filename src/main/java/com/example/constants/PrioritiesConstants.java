package com.example.constants;

public final class PrioritiesConstants {

    public static final int MAIN = 0;
    public static final int REQUEST_CONTEXT_INIT = 30;
    public static final int COOKIE_ROUTE_FILTER = 300;
    public static final int REQUEST_CONTEXT_INIT_ROUTE_FILTER = COOKIE_ROUTE_FILTER - 10;

    private PrioritiesConstants() {

    }
}
