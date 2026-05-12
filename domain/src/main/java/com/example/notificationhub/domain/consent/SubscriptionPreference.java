package com.example.notificationhub.domain.consent;

import java.util.Map;
import java.util.Set;

public final class SubscriptionPreference {
    private final String userId;
    private final Map<Topic, Set<String>> optInChannelsByTopic;

    public SubscriptionPreference(String userId, Map<Topic, Set<String>> optInChannelsByTopic) {
        this.userId = userId;
        this.optInChannelsByTopic = Map.copyOf(optInChannelsByTopic);
    }

    public boolean canSend(Topic topic, String channelType) {
        return optInChannelsByTopic.getOrDefault(topic, Set.of()).contains(channelType);
    }

    public String userId() {
        return userId;
    }
}
