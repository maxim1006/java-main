package com.example.contexts;


import com.example.request.LocalizationRequestBean;
import com.example.request.SessionRequestBean;
import lombok.Data;

@Data
public class RequestContextContent {
    private final LocalizationRequestBean localization = new LocalizationRequestBean();
    private final SessionRequestBean session = new SessionRequestBean();
    private final String requestId;
    private final boolean internal;
    private final long requestStartTimeMillisecond;

    public RequestContextContent(String requestId, boolean internal) {
        this.requestId = requestId;
        this.internal = internal;
        this.requestStartTimeMillisecond = System.currentTimeMillis();
    }

    public RequestContextContent(String requestId) {
        this(requestId, false);
    }

    /**
     * This constructor is used only for technical reasons as uninitialized/empty context.
     */
    RequestContextContent() {
        this(null, true);
    }
}
