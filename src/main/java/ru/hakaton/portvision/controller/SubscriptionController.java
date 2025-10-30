package ru.hakaton.portvision.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hakaton.portvision.dto.Subscriber;
import ru.hakaton.portvision.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscription")
    public Subscriber subscribe(@RequestBody Subscriber subscriber) {
        return subscriptionService.create(subscriber);
    }

    @GetMapping("/subscription/{id}")
    public ResponseEntity<String> unsubscribe(@PathVariable(name = "id") Integer id) {
        subscriptionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
