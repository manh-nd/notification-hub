package com.example.notificationhub.application.port.in;

import com.example.notificationhub.application.command.SendNotificationCommand;

public interface SendNotificationUseCase {
    void send(SendNotificationCommand command);
}
