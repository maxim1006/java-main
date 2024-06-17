package com.example.request;

import com.example.contexts.SessionContext;
import lombok.Data;

import java.util.Map;

@Data
public class SessionRequestBean {
    private String sessionId;
    private SessionContext.SystemInfo systemInfo;
    private Map<String, String> sessionRequestParameters;

    public void reset() {
        setSessionRequestParameters(null);
        setSystemInfo(null);
        setSessionId(null);
    }
}
