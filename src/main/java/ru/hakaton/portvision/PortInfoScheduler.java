package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PortInfoScheduler {


    @Value("${host-url}")
    private String hostUrl;

    private final SubscriberRepository subscriberRepository;
    private final PortRepository portRepository;
    private final EmailService emailService;

    // Комментарии для дебилов
//    @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void update() {
        List<Subscriber> subscribers = subscriberRepository.findByEventType("PORT_INFO");
        Map<Integer, List<Port>> portsWithId = portRepository.findByIdIn(subscribers.stream().map(Subscriber::getTargetId).toList()).stream()
                .collect(Collectors.groupingBy(Port::getId));

        Map<Subscriber, List<Port>> subscriberAndPorts = new HashMap<>();

    }
}
