package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscription")
    public ResponseEntity<String> subscribe(Subscriber subscriber) {
        subscriptionService.create(subscriber);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subscription/{id}")
    public ResponseEntity<String> unsubscribe(@PathVariable(name = "id") Integer id) {
        subscriptionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
