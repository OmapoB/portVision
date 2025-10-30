package ru.hakaton.portvision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hakaton.portvision.dto.Event;
import ru.hakaton.portvision.dto.Subscriber;
import ru.hakaton.portvision.repo.SubscriberRepository;

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
            String body = event.getBody() + "<br>" +
                   "<a href=\"http://" + hostUrl + "/subscription/" + e.getId() + "\">Отписаться</a>";
            emailService.sendSimpleEmail(e.getMail(), "Новый комментарий/отчет по подписке", body);
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
