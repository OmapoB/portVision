package ru.hakaton.portvision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.hakaton.portvision.dto.Port;
import ru.hakaton.portvision.repo.PortRepository;
import ru.hakaton.portvision.dto.Subscriber;
import ru.hakaton.portvision.repo.SubscriberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PortInfoScheduler {


    @Value("${host-url}")
    private String hostUrl;

    private final SubscriberRepository subscriberRepository;
    private final PortRepository portRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 10 * * *")
    public void update() {
        List<Subscriber> subscribers = subscriberRepository.findByEventType("PORT_INFO");
        Map<Integer, List<Port>> portsWithId = portRepository.findByIdIn(subscribers.stream().map(Subscriber::getTargetId).toList()).stream()
                .collect(Collectors.groupingBy(Port::getId));

        Map<Subscriber, List<Port>> subscriberAndPorts = new HashMap<>();

    }
}
