package com.example.notificationhub.domain.port;

import java.util.Map;

public interface TemplateRenderer {
    String render(String templateContent, Map<String, Object> payload);
}
