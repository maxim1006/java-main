package com.example.contexts;

public class RequestContext {
    private static final ThreadLocal<RequestContextContent> VALUE = new ThreadLocal<>();

    private RequestContext() {}

    public static RequestContextContent get() {
        return VALUE.get();
    }

    public static void set(RequestContextContent newValue) {
        if (newValue == null) {
            remove();
        } else {
            VALUE.set(newValue);
        }
    }

    public static void remove() {
        VALUE.remove();
    }

    public static boolean isInternalContext() {
        RequestContextContent requestContextContent = get();
        return requestContextContent == null || requestContextContent.isInternal();
    }
}
