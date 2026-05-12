package com.example.notificationhub.domain.port;

import com.example.notificationhub.domain.notification.Notification;

public interface NotificationDispatcher {
    DispatchResult dispatch(Notification notification);

    record DispatchResult(boolean success, String reason) {
        public static DispatchResult ok() { return new DispatchResult(true, null); }
        public static DispatchResult fail(String reason) { return new DispatchResult(false, reason); }
    }
}
