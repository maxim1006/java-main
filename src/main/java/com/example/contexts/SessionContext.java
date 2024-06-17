package com.example.contexts;

import lombok.Data;

import java.util.Map;

@Data
public class SessionContext {

    private String sessionId;
    private String customerId;
    private Map<String, String> context;
    private SystemInfo systemInfo;

    @Data
    public static class SystemInfo {
        private Long createTimestamp;
        private Long activeTillTimestamp;
    }

}
