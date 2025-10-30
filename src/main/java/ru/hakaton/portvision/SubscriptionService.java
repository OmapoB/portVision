package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    @Value("${host-url}")
    private String hostUrl;

    private final SubscriberRepository subscriberRepository;
    private final EmailService emailService;

    public void send(Event event) {
        List<Subscriber> subscribers = subscriberRepository.findByEventTypeAndTargetId(event.getEventType(), event.getTargetId());
        for (Subscriber e : subscribers) {
            emailService.sendSimpleEmail(e.getMail(), "Новый комментарий по подписке", event.getBody().concat("\n").concat(hostUrl + "/subscription/" + e.getId()));
        }
    }

    public Subscriber create(Subscriber subscriber) {
        subscriber = subscriberRepository.save(subscriber);
        return subscriber;
    }

    public void delete(Integer id) {
        subscriberRepository.deleteById(id);
    }
}
