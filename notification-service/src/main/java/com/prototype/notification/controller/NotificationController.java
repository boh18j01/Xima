package com.prototype.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;

@RestController
public class NotificationController {




    @GetMapping("/notifications-stream")
    public Flux<String> getCart() {
        //TODO implement this functionality when having new notification to send to UI
        return Flux.interval(Duration.ofMillis(1000))
                .map(interval -> Collections.singletonList("new notification"))
                .flatMapIterable(notif -> notif);
    }
}
