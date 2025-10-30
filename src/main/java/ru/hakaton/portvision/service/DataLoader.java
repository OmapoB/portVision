package ru.hakaton.portvision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hakaton.portvision.dto.Port;
import ru.hakaton.portvision.repo.PortRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoader {

    private final RestTemplate restTemplate = new RestTemplate();

    private final PortRepository portRepository;
    private final DataExtractor dataExtractor;

    @Scheduled(cron = "0 0 0 * * *")
    public void load(){
        List<Port> all = portRepository.findAll();
        all.forEach(port -> {searchInOpenData(port);});
        portRepository.saveAll(all);
    }

    private Port searchInOpenData(Port port) {
        ResponseEntity<String> newsResponse = restTemplate.getForEntity("https://www.ecoports.com/news", String.class);
        if (newsResponse.getStatusCode().is2xxSuccessful()) {
            if (newsResponse.getBody().contains(LocalDate.now().toString())) {
                return dataExtractor.extractPort(newsResponse.getBody().substring(newsResponse.getBody().indexOf(port.getName().charAt(0))), port);
            }
        }
        ResponseEntity<String> publicationsResponse = restTemplate.getForEntity("https://www.ecoports.com/publications", String.class);
        if (newsResponse.getStatusCode().is2xxSuccessful()) {
            if (newsResponse.getBody().contains(LocalDate.now().toString())) {
                return dataExtractor.extractPort(publicationsResponse.getBody().substring(publicationsResponse.getBody().indexOf(port.getName().charAt(0))), port);
            }
        }
        ResponseEntity<String> unepResponse = restTemplate.getForEntity("https://tehranconvention.org/en/documents", String.class);
        if (newsResponse.getStatusCode().is2xxSuccessful()) {
            if (newsResponse.getBody().contains(LocalDate.now().toString())) {
                return dataExtractor.extractPort(unepResponse.getBody().substring(unepResponse.getBody().indexOf(port.getName().charAt(0))), port);
            }
        }
        return port;
    }
}
