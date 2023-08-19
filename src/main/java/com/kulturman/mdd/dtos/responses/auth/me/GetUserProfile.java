package com.kulturman.mdd.dtos.responses.auth.me;

import com.kulturman.mdd.entities.User;

import java.util.List;

public class GetUserProfile {
    public final String username;
    public final String email;
    public final List<Subscription> subscriptions;
    public GetUserProfile(User user) {
        username = user.username();
        email = user.getEmail();
        subscriptions = user.getSubscriptions().stream().map(Subscription::new).toList();
    }
}
