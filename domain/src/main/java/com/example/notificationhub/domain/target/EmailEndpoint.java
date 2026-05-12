package com.example.notificationhub.domain.target;

import java.util.regex.Pattern;

public record EmailEndpoint(String value) implements DeliveryEndpoint {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public EmailEndpoint {
        if (value == null || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("invalid email endpoint");
        }
    }

    @Override
    public String channelType() { return "EMAIL"; }
}
